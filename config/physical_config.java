import com.example.config.swerve.SwerveModuledoubleProperty;
import com.example.config.swerve.SwerveModuleIntProperty;
import com.example.config.swerve.OptionalSwerveModuledoubleProperty;
import com.example.config.swerve.OptionalSwerveModuleIntProperty;

public class PhysicalConfig {
    private double wheelDiameterCm;
    private double wheelGripCoefficientOfFriction;
    private OptionalSwerveModuleIntProperty currentLimit;
    private OptionalSwerveModuledoubleProperty rampRate;
    private SwerveModuledoubleProperty encoderPulsesPerRevolution;
    private SwerveModuledoubleProperty gearRatio;
    private double maxDriveSpeed;
    private double maxRotationSpeed;

    public PhysicalConfig(double wheelDiameterCm,
                          double wheelGripCoefficientOfFriction,
                          SwerveModuledoubleProperty encoderPulsesPerRevolution,
                          SwerveModuledoubleProperty gearRatio,
                          OptionalSwerveModuledoubleProperty rampRate,
                          OptionalSwerveModuleIntProperty currentLimit,
                          double maxDriveSpeed,
                          double maxRotationSpeed) {
        this.wheelDiameterCm = wheelDiameterCm;
        this.wheelGripCoefficientOfFriction = wheelGripCoefficientOfFriction;
        this.currentLimit = (currentLimit != null) ? currentLimit : new OptionalSwerveModuleIntProperty();
        this.rampRate = (rampRate != null) ? rampRate : new OptionalSwerveModuledoubleProperty();
        this.encoderPulsesPerRevolution = encoderPulsesPerRevolution;
        this.gearRatio = gearRatio;
        this.maxDriveSpeed = maxDriveSpeed;
        this.maxRotationSpeed = maxRotationSpeed;
    }

    // Add getters and setters as needed
    public double getWheelDiameterCm() {
        return wheelDiameterCm;
    }

    public void setWheelDiameterCm(double wheelDiameterCm) {
        this.wheelDiameterCm = wheelDiameterCm;
    }

    // Repeat the above pattern for other fields
}
