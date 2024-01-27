import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveTelemetry {

    private SwerveDrive swerveDrive;

    public SwerveTelemetry(SwerveDrive swerveDrive) {
        this.swerveDrive = swerveDrive;
    }

    public void reportToDashboard() {
        SmartDashboard.putNumber("Robot X", swerveDrive.getPose().getTranslation().getX());
        SmartDashboard.putNumber("Robot Y", swerveDrive.getPose().getTranslation().getY());
        SmartDashboard.putNumber("Robot Heading", swerveDrive.getPose().getRotation().getDegrees());

        // Report telemetry for each swerve module
        for (ISwerveModule module : swerveDrive.getModules().values()) {
            SmartDashboard.putNumber("Module " + module.getId().ordinal() + " X", module.getLocation().getX());
            SmartDashboard.putNumber("Module " + module.getId().ordinal() + " Y", module.getLocation().getY());
            SmartDashboard.putNumber("Module " + module.getId().ordinal() + " Velocity", module.getVelocity());
            SmartDashboard.putNumber("Module " + module.getId().ordinal() + " Angle", module.getAngle());
            SmartDashboard.putNumber("Module " + module.getId().ordinal() + " Drive Motor Output", module.getDriveMotor().getAppliedOutput());
            SmartDashboard.putNumber("Module " + module.getId().ordinal() + " Angle Motor Output", module.getAngleMotor().getAppliedOutput());
        }

        // Add additional telemetry as needed
    }
}
