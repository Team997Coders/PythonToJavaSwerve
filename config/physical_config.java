import com.example.config.swerve.swervemoduledoubleProperty;
import com.example.config.swerve.swervemoduleIntProperty;
import com.example.config.swerve.OptionalswervemoduledoubleProperty;
import com.example.config.swerve.OptionalswervemoduleIntProperty;

public class PhysicalConfig {
    private double wheelDiameterCm;
    private double wheelGripCoefficientOfFriction;
    private OptionalswervemoduleIntProperty currentLimit;
    private OptionalswervemoduledoubleProperty rampRate;
    private swervemoduledoubleProperty encoderPulsesPerRevolution;
    private swervemoduledoubleProperty gearRatio;
    private double maxDriveSpeed;
    private double maxRotationSpeed;

    public PhysicalConfig(double wheelDiameterCm,
                          double wheelGripCoefficientOfFriction,
                          swervemoduledoubleProperty encoderPulsesPerRevolution,
                          swervemoduledoubleProperty gearRatio,
                          OptionalswervemoduledoubleProperty rampRate,
                          OptionalswervemoduleIntProperty currentLimit,
                          double maxDriveSpeed,
                          double maxRotationSpeed) {
        this.wheelDiameterCm = wheelDiameterCm;
        this.wheelGripCoefficientOfFriction = wheelGripCoefficientOfFriction;
        this.currentLimit = (currentLimit != null) ? currentLimit : new OptionalswervemoduleIntProperty();
        this.rampRate = (rampRate != null) ? rampRate : new OptionalswervemoduledoubleProperty();
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
