import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.kinematics.*;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.estimator.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class SwerveDrive implements ISwerveDrive {

    private final Map<ModulePosition, ISwerveModule> _modules;
    private boolean initialized = false;
    private final SwerveDrive4Kinematics _kinematics;
    private final Logger logger;
    private final List<ISwerveModule> _ordered_modules;
    private final AHRS _navx;
    private final SwerveDrive4PoseEstimator _odometry;
    private final PhysicalConfig _physical_config;
    private final ReentrantLock _odometry_lock;

    public SwerveDrive(AHRS navx, Map<ModulePosition, SwerveModuleConfig> swerve_config, PhysicalConfig physical_config, Logger logger) {
        this.logger = logger.getChild("swerve");
        this._navx = navx;
        this._modules = new HashMap<>();
        this._physical_config = physical_config;
        for (Map.Entry<ModulePosition, SwerveModuleConfig> entry : swerve_config.entrySet()) {
            ModulePosition position = entry.getKey();
            SwerveModuleConfig module_config = entry.getValue();
            this._modules.put(position, new SwerveModule(position, module_config, physical_config, this.logger));
        }

        this._ordered_modules = new ArrayList<>(this._modules.values());
        this.logger.info("Module Order: " + this._ordered_modules);

        List<Translation2d> locations = new ArrayList<>();
        for (ISwerveModule m : this._ordered_modules) {
            locations.add(m.getLocation());
        }
        this._kinematics = new SwerveDrive4Kinematics(locations.toArray(new Translation2d[0]));

        List<SwerveModulePosition> module_positions = new ArrayList<>();
        for (ISwerveModule m : this._ordered_modules) {
            module_positions.add(m.getPosition());
        }
        this._odometry = new SwerveDrive4PoseEstimator(this._kinematics,
                Rotation2d.fromDegrees(this._navx.getAngle()),
                module_positions.toArray(new SwerveModulePosition[0]),
                new Pose2d(0, 0, Rotation2d.fromDegrees(0)));

        this.initialize();
    }

    public boolean initialize() {
        List<Boolean> results = new ArrayList<>();
        for (ISwerveModule module : this._modules.values()) {
            results.add(module.initialize());
        }

        if (results.stream().allMatch(b -> b)) {
            this.initialized = true;
            return true;
        }

        return false;
    }

    public void periodic() {
        this.updateOdometry();
    }

    public void updateOdometry() {
        this._odometry_lock.lock();
        try {
            SwerveModulePosition[] module_positions = new SwerveModulePosition[this._ordered_modules.size()];
            for (int i = 0; i < this._ordered_modules.size(); i++) {
                module_positions[i] = this._ordered_modules.get(i).getPosition();
            }
            this._odometry.update(Rotation2d.fromDegrees(this._navx.getAngle()), module_positions);
        } finally {
            this._odometry_lock.unlock();
        }
    }

    public void drive(double vx, double vy, double rotation, Set<ModulePosition> runModules) {
        ChassisSpeeds chassis_speed = ChassisSpeeds.fromFieldRelativeSpeeds(vx, vy, rotation, Rotation2d.fromDegrees(this._navx.getAngle()));
        SwerveModuleState[] module_states = this._kinematics.toSwerveModuleStates(chassis_speed);

        module_states = this._kinematics.desaturateWheelSpeeds(module_states, this._physical_config.getMaxDriveSpeed());

        for (int i = 0; i < this.numModules(); i++) {
            ISwerveModule module = this._ordered_modules.get(i);

            if (runModules != null && !runModules.contains(module.getId())) {
                continue;
            }

            SwerveModuleState state = module_states[i];
            module.setDesiredState(state);
        }
    }

    public void stop() {
        for (ISwerveModule module : this._modules.values()) {
            module.stop();
        }
    }

    public void lockWheels() {
        double quarterPi = Math.PI / 4.0;
        this._modules.get(ModulePosition.FRONT_LEFT).setDesiredState(new SwerveModuleState(0, Rotation2d.fromRadians(quarterPi)));
        this._modules.get(ModulePosition.FRONT_RIGHT).setDesiredState(new SwerveModuleState(0, Rotation2d.fromRadians(-quarterPi)));
        this._modules.get(ModulePosition.BACK_LEFT).setDesiredState(new SwerveModuleState(0, Rotation2d.from
