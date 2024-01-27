import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SparkMax;
import edu.wpi.first.wpilibj.encoder.Encoder;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.swervemoduleState;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;

import java.util.logging.Logger;

public class swervemodule implements Iswervemodule {

    private final ModulePosition _id;
    private final CANSparkMax driveMotor;
    private final CANSparkMax angleMotor;
    private final CANEncoder driveEncoder;
    private final CANEncoder angleEncoder;
    private final CANPIDController drivePidController;
    private final CANPIDController anglePidController;
    private final CANEncoder angleAbsoluteEncoder;
    private final double ANGLE_CONVERSION_FACTOR = (2.0 * Math.PI) / physicalConfig.gearRatio.angle;

    private double relToAbsoluteAngleAdjustment;
    private double relToCorrectedAngleAdjustment;
    private swervemoduleConfig config;
    private kinematics.swervemoduleState desiredState;
    private Logger logger;

    @Override
    public ModulePosition getId() {
        return _id;
    }

    @Override
    public void stop() {
        angleMotor.set(0);
        driveMotor.set(0);
        desiredState = new kinematics.swervemoduleState(0, getRotation2d());
    }

    @Override
    public geom.Translation2d getLocation() {
        return new geom.Translation2d(config.location[0], config.location[1]);
    }

    @Override
    public double getVelocity() {
        return driveEncoder.getVelocity();
    }

    @Override
    public double getRawPosition() {
        return driveEncoder.getPosition();
    }

    @Override
    public double getAngle() {
        return angleEncoder.getPosition();
    }

    @Override
    public void setAngle(double angle) {
        desiredState = new kinematics.swervemoduleState(desiredState.speed, new Rotation2d(angle));
        double pidAngle = Units.degreesToRadians(angle) + relToCorrectedAngleAdjustment;
        double absPosition = angleAbsoluteEncoder.getPosition();
        double adjustment = MathHelp.shortestAngleDifference(absPosition, pidAngle);
        anglePidController.setReference(absPosition + adjustment, CANSparkMaxLowLevel.MotorType.kPosition);
    }

    // ... (other methods remain unchanged)

    public swervemodule(ModulePosition id, swervemoduleConfig moduleConfig, PhysicalConfig physicalConfig, Logger logger) {
        _id = id;
        this.logger = logger.getChild(id.toString());
        desiredState = new kinematics.swervemoduleState(0, new geom.Rotation2d(0));
        relToAbsoluteAngleAdjustment = 0;
        relToCorrectedAngleAdjustment = 0;
        config = moduleConfig;
        driveMotor = new CANSparkMax(moduleConfig.driveMotor.id, CANSparkMaxLowLevel.MotorType.kBrushless);
        angleMotor = new CANSparkMax(moduleConfig.angleMotor.id, CANSparkMaxLowLevel.MotorType.kBrushless);

        driveMotor.restoreFactoryDefaults();
        angleMotor.restoreFactoryDefaults();
        driveMotor.setIdleMode(IdleMode.kCoast);
        angleMotor.setIdleMode(IdleMode.kBrake);

        driveEncoder = driveMotor.getEncoder();
        driveEncoder.setPositionConversionFactor(1.0 / physicalConfig.gearRatio.drive);
        driveEncoder.setVelocityConversionFactor((1.0 / physicalConfig.gearRatio.drive * (physicalConfig.wheelDiameterCm / 100 * Math.PI)) / 60.0);

        drivePidController = driveMotor.getPIDController();
        drivePidController.setP(moduleConfig.drivePid.p);
        drivePidController.setI(moduleConfig.drivePid.i);
        drivePidController.setD(moduleConfig.drivePid.d);

        // ... (other configurations)

        angleAbsoluteEncoder = angleMotor.getAbsoluteEncoder(CANSparkMaxLowLevel.AbsoluteSensorType.kPWM);
        angleAbsoluteEncoder.setPositionConversionFactor(ANGLE_CONVERSION_FACTOR);

        // ... (other configurations)

        anglePidController = angleMotor.getPIDController();
        anglePidController.setP(moduleConfig.anglePid.p);
        anglePidController.setI(moduleConfig.anglePid.i);
        anglePidController.setD(moduleConfig.anglePid.d);

        // ... (other configurations)

        driveMotor.burnFlash();
        angleMotor.burnFlash();

        initialize();
    }

    @Override
    public void initialize() {
        if (config.encoder.offset != null) {
            relToAbsoluteAngleAdjustment = config.encoder.offset;
            angleMotorEncoder.setPosition(angleAbsoluteEncoder.getPosition());
            relToCorrectedAngleAdjustment = relToAbsoluteAngleAdjustment + config.encoder.offset;
        } else {
            relToAbsoluteAngleAdjustment = 0;
            relToCorrectedAngleAdjustment = 0;
        }
    }

    @Override
    public void periodic() {
        reportToDashboard();
    }

    @Override
    public void reportToDashboard() {
        SmartDashboard.putNumber("Drive " + _id.ordinal() + " Position", getRawPosition());
        SmartDashboard.putNumber("Drive " + _id.ordinal() + " Velocity", getVelocity());
        SmartDashboard.putNumber("Drive " + _id.ordinal() + " Tgt Vel", desiredState.speed);
        SmartDashboard.putNumber("Angle " + _id.ordinal() + " Position", Math.toDegrees(getAngle()));
        SmartDashboard.putNumber("Angle " + _id.ordinal() + " Absolute", Math.toDegrees(angleAbsoluteEncoder.getPosition()));
        SmartDashboard.putNumber("Angle " + _id.ordinal() + " Velocity", angleEncoder.getVelocity());
        SmartDashboard.putNumber("Angle PID " + _id.ordinal() + " Reference", Math.toDegrees(anglePidLastReference));
    }
}
