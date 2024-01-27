import edu.wpi.first.wpilibj.kinematics.SwerveDrive4Odometry;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.swervemoduleState;
import java.util.List;
import java.util.Map;

public interface Iswervemodule {

    ModulePosition getId();

    Translation2d getLocation();

    double getVelocity();

    void setVelocity(double metersPerSec);

    double getAngle();

    void setAngle(double angle);

    kinematics.swervemodulePosition getPosition();

    Rotation2d getRotation2d();

    swervemoduleState getDesiredState();

    void setDesiredState(swervemoduleState value);

    swervemoduleState getMeasuredState();

    boolean initialize();

    void stop();
}

public interface ISwerveDrive {

    int numModules();

    void drive(double vx, double vy, double rotation, List<ModulePosition> runModules);

    void lockWheels();

    Map<ModulePosition, Iswervemodule> getModules();

    List<Iswervemodule> getOrderedModules();

    boolean initialize();

    void stop();

    SwerveDrive4Odometry getOdemetry();

    void periodic();
}

public enum ModulePosition {
    FRONT_LEFT, FRONT_RIGHT, BACK_LEFT, BACK_RIGHT
}
