import java.util.EnumMap;

public enum ModulePosition {
    FRONT_LEFT(0),
    FRONT_RIGHT(1),
    BACK_RIGHT(2),
    BACK_LEFT(3);

    private final int value;

    ModulePosition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name().replace("_", " ");
    }
}

public class OptionalswervemoduledoubleProperty {
    private final double drive;
    private final double angle;

    public OptionalswervemoduledoubleProperty(double drive, double angle) {
        this.drive = drive;
        this.angle = angle;
    }

    public double getDrive() {
        return drive;
    }

    public double getAngle() {
        return angle;
    }
}

public class swervemoduledoubleProperty {
    private final double drive;
    private final double angle;

    public swervemoduledoubleProperty(double drive, double angle) {
        this.drive = drive;
        this.angle = angle;
    }

    public double getDrive() {
        return drive;
    }

    public double getAngle() {
        return angle;
    }
}

public class OptionalswervemoduleIntProperty {
    private final int drive;
    private final int angle;

    public OptionalswervemoduleIntProperty(int drive, int angle) {
        this.drive = drive;
        this.angle = angle;
    }

    public int getDrive() {
        return drive;
    }

    public int getAngle() {
        return angle;
    }
}

public class swervemoduleIntProperty {
    private final int drive;
    private final int angle;

    public swervemoduleIntProperty(int drive, int angle) {
        this.drive = drive;
        this.angle = angle;
    }

    public int getDrive() {
        return drive;
    }

    public int getAngle() {
        return angle;
    }
}

public class MotorConfig {
    private final int id;
    private final boolean inverted;

    public MotorConfig(int id, boolean inverted) {
        this.id = id;
        this.inverted = inverted;
    }

    public int getId() {
        return id;
    }

    public boolean isInverted() {
        return inverted;
    }
}

public class EncoderConfig {
    private final int id;
    private final double offset;
    private final double conversionFactor;

    public EncoderConfig(int id, double offset, double conversionFactor) {
        this.id = id;
        this.offset = offset;
        this.conversionFactor = conversionFactor;

        if (offset != 0 && offset <= 0) {
            while (offset <= 0) {
                offset += Math.PI * 2.0;
            }
        }
    }

    public int getId() {
        return id;
    }

    public double getOffset() {
        return offset;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}

public class swervemoduleConfig {
    private final MotorConfig driveMotor;
    private final MotorConfig angleMotor;
    private final EncoderConfig encoder;
    private final double[] location;
    private final PIDConfig drivePid;
    private final PIDConfig anglePid;

    public swervemoduleConfig(MotorConfig driveMotor, MotorConfig angleMotor, EncoderConfig encoder,
                              double[] location, PIDConfig drivePid, PIDConfig anglePid) {
        this.driveMotor = driveMotor;
        this.angleMotor = angleMotor;
        this.encoder = encoder;
        this.location = location;
        this.drivePid = drivePid;
        this.anglePid = anglePid;
    }

    public MotorConfig getDriveMotor() {
        return driveMotor;
    }

    public MotorConfig getAngleMotor() {
        return angleMotor;
    }

    public EncoderConfig getEncoder() {
        return encoder;
    }

    public double[] getLocation() {
        return location;
    }

    public PIDConfig getDrivePid() {
        return drivePid;
    }

    public PIDConfig getAnglePid() {
        return anglePid;
    }
}
