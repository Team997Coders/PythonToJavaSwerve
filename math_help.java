import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import org.ejml.simple.SimpleMatrix;

public class MathHelpers {

    public static double wrapAngle(double angle, double min) {
        double clamped = angle % (Math.PI * 2.0);
        if (min != 0) {
            double max = min + Math.PI * 2.0;
            while (clamped > max) {
                clamped -= Math.PI * 2.0;
            }
            while (clamped < min) {
                clamped += Math.PI * 2.0;
            }
        }
        return clamped;
    }

    public static double shortestAngleDifference(double angle1, double angle2) {
        double diff = (angle2 - angle1 + Math.PI) % (Math.PI * 2) - Math.PI;
        return diff;
    }

    public static SwerveModuleState optimizeStateImproved(SwerveModuleState desiredState, Rotation2d currentAngle) {
        Rotation2d desiredAngle = desiredState.angle;
        SimpleMatrix desiredVector = new SimpleMatrix(new double[][]{{desiredAngle.cos(), desiredAngle.sin()}});
        SimpleMatrix currentVector = new SimpleMatrix(new double[][]{{currentAngle.cos(), currentAngle.sin()}});
        double result = desiredVector.dot(currentVector);
        
        if (Math.abs(result) <= 1.00001) {
            desiredState = new SwerveModuleState(desiredState.speed * result, desiredAngle);
        }

        if (Math.abs(result) < 1e-6) {
            desiredState = new SwerveModuleState(0, desiredAngle);
        } else if (result < 0) {
            desiredAngle = desiredAngle.plus(new Rotation2d(Math.PI));
            desiredState = new SwerveModuleState(desiredState.speed * result, desiredAngle);
        }

        return desiredState;
    }

    public static SwerveModuleState optimizeImproved(double desiredAngleRadians, double desiredSpeed, double currentAngleRadians) {
        SwerveModuleState desiredState = new SwerveModuleState(desiredSpeed, new Rotation2d(desiredAngleRadians));
        Rotation2d currentRotation = new Rotation2d(currentAngleRadians);
        return optimizeStateImproved(desiredState, currentRotation);
    }
}
