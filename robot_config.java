import java.util.EnumMap;
import java.util.Map;

public class RobotConfig {

    public static final PIDConfig DEFAULT_ANGLE_PID = new PIDConfig(0.4, 0.0, 0.0, new OptionalRange(0, Math.PI * 2));
    public static final PIDConfig DEFAULT_DRIVE_PID = new PIDConfig(0.2, 0.0, 0.0, null);

    public static final DriverControlsConfig TELEOP_CONTROLS = new DriverControlsConfig(0.05, 0.05, 0.05);

    public static final Map<ModulePosition, SwerveModuleConfig> SWERVE_MODULES = new EnumMap<>(ModulePosition.class);

    public static final PhysicalConfig PHYSICAL_PROPERTIES = new PhysicalConfig(
            12, // wheel diameter cm
            1, // wheel grip coefficient of friction
            new OptionalSwerveModuleIntProperty(40, 20), // current limit
            new OptionalSwerveModuledoubleProperty(0.25, 0.25), // ramp rate
            new SwerveModuledoubleProperty(1, 1), // encoder pulses per revolution
            new SwerveModuledoubleProperty(150.0 / 7, 6.75), // gear ratio
            2.5, // max drive speed
            Math.PI / 8 // max rotation speed
    );

    static {
        SWERVE_MODULES.put(ModulePosition.FRONT_LEFT, new SwerveModuleConfig(
                new MotorConfig(8, false), // drive motor
                new MotorConfig(1, true), // angle motor
                null, // encoder
                new double[]{12, 12}, // location
                DEFAULT_ANGLE_PID,
                DEFAULT_DRIVE_PID
        ));

        SWERVE_MODULES.put(ModulePosition.FRONT_RIGHT, new SwerveModuleConfig(
                new MotorConfig(6, false), // drive motor
                new MotorConfig(7, true), // angle motor
                null, // encoder
                new double[]{12, -12}, // location
                DEFAULT_ANGLE_PID,
                DEFAULT_DRIVE_PID
        ));

        SWERVE_MODULES.put(ModulePosition.BACK_RIGHT, new SwerveModuleConfig(
                new MotorConfig(4, false), // drive motor
                new MotorConfig(5, true), // angle motor
                null, // encoder
                new double[]{-12, -12}, // location
                DEFAULT_ANGLE_PID,
                DEFAULT_DRIVE_PID
        ));

        SWERVE_MODULES.put(ModulePosition.BACK_LEFT, new SwerveModuleConfig(
                new MotorConfig(2, false), // drive motor
                new MotorConfig(3, true), // angle motor
                null, // encoder
                new double[]{-12, 12}, // location
                DEFAULT_ANGLE_PID,
                DEFAULT_DRIVE_PID
        ));
    }
}
