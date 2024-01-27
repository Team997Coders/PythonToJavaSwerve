import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import org.apache.logging.log4j.Logger;

public class MyRobot extends TimedRobot {

    private ISwerveDrive swerveDrive;
    private SwerveTelemetry swerveTelemetry;
    private TestDriver testDriver;
    private Gyro navx;
    private XboxController controller;
    private NetworkTable photonvision;

    public void robotInit() {
        super.robotInit();
        navx = NavX.createSPI();
        controller = new XboxController(0);
        swerveDrive = new SwerveDrive(navx, RobotConfig.SWERVE_MODULES, RobotConfig.PHYSICAL_PROPERTIES, LoggerSupplier.get());
        swerveTelemetry = new SwerveTelemetry(swerveDrive, RobotConfig.PHYSICAL_PROPERTIES);
;
        swerveDrive.initialize();

        try {
            photonvision = NetworkTableInstance.getDefault().getTable("photonvision/Camera_Module_v2");
            if (photonvision != null) {
                LoggerSupplier.get().info("Photonvision connected!");
            } else {
                LoggerSupplier.get().error("Could not connect to PhotonVision.");
            }
        } catch (Exception e) {
            LoggerSupplier.get().error("Could not connect to PhotonVision.\n" + e);
            photonvision = null;
        }

        testDriver = new TestDriver(swerveDrive, LoggerSupplier.get());
    }

    public void robotPeriodic() {
        super.robotPeriodic();
        swerveTelemetry.reportToDashboard();
        swerveDrive.periodic();
        updatePosition();
    }

    public void updatePosition() {
        if (photonvision == null) {
            return;
        }

        boolean hasQRCode = photonvision.getEntry("hasTarget").getBoolean(false);

        if (hasQRCode) {
            LoggerSupplier.get().info("PhotonVision has_qr_code: " + hasQRCode);
            // TODO: Update pose position using angle to AprilTag and distance
            // swerveDrive.odometry.resetPosition(new SwerveModulePosition(0, 0, 0), Rotation2d.fromDegrees(0));
        }
    }

    public void teleopPeriodic() {
        super.teleopPeriodic();

        double vx = controller.getRawAxis(1);
        double vy = controller.getRawAxis(0);
        double theta = controller.getRawAxis(4);

        double xDeadband = RobotConfig.TELEOP_CONTROLS.getXDeadband();
        double yDeadband = RobotConfig.TELEOP_CONTROLS.getYDeadband();
        double thetaDeadband = RobotConfig.TELEOP_CONTROLS.getThetaDeadband();

        if (Math.abs(theta) < thetaDeadband) {
            theta = 0;
        }

        if (Math.abs(vx) < xDeadband && Math.abs(vy) < yDeadband && Math.abs(theta) < thetaDeadband) {
            swerveDrive.lockWheels();
        } else {
            vx *= RobotConfig.PHYSICAL_PROPERTIES.getMaxDriveSpeed();
            vy *= RobotConfig.PHYSICAL_PROPERTIES.getMaxDriveSpeed();
            double scaledSpeed = Math.sqrt(vx * vx + vy * vy);

            if (scaledSpeed > RobotConfig.PHYSICAL_PROPERTIES.getMaxDriveSpeed()) {
                double scaleFactor = RobotConfig.PHYSICAL_PROPERTIES.getMaxDriveSpeed() / scaledSpeed;
                vx *= scaleFactor;
                vy *= scaleFactor;
            }

            swerveDrive.drive(-vx, -vy, theta * RobotConfig.PHYSICAL_PROPERTIES.getMaxRotationSpeed());
        }
    }

    public void autonomousInit() {
        super.autonomousInit();
    }

    public void autonomousPeriodic() {
        super.autonomousPeriodic();
    }

    public void testInit() {
        super.testInit();
        testDriver.testInit();
    }

    public void testPeriodic() {
        super.testPeriodic();
        testDriver.testPeriodic();
    }
}
