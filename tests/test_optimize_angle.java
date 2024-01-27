import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.util.hal.HALValue;

import java.util.stream.DoubleStream;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

@RunWith(JUnitQuickcheck.class)
public class TestSwerveModuleState {

    @Property
    public void optimizeTrivial(double speed, double angle) {
        SwerveModuleState moduleState = new SwerveModuleState(speed, Rotation2d.fromDegrees(angle));
        SwerveModuleState optimizedState = SwerveModuleState.optimize(moduleState, Rotation2d.fromDegrees(angle));

        assertEquals(optimizedState.speed, speed, 0.001);
        assertEquals(optimizedState.angle.getDegrees(), angle, 0.001);
    }

    @Property
    public void optimize(double currentRadians, double desiredRadians, double speed) {
        Rotation2d currentAngle = Rotation2d.fromRadians(currentRadians);
        Rotation2d desiredAngle = Rotation2d.fromRadians(desiredRadians);
        double difference = MathHelp.shortestAngleDifference(currentRadians, desiredRadians);
        SwerveModuleState moduleState = new SwerveModuleState(speed, desiredAngle);
        SwerveModuleState optimizedState = SwerveModuleState.optimize(moduleState, currentAngle);

        if (Math.abs(difference) < Math.PI / 2.0) {
            assertEquals(optimizedState.speed, speed, 0.001);
            assertEquals(optimizedState.angle.getRadians(), desiredRadians, 0.001);
        } else {
            assertEquals(optimizedState.speed, -speed, 0.001);
            double angleDiff = optimizedState.angle.getRadians() - MathHelp.wrapAngle(desiredRadians, -Math.PI);
            assertEquals(Math.abs(angleDiff), Math.PI, 0.001);
        }
    }

    @Property
    public void improvedOptimization(double currentRadians, double desiredRadians, double speed) {
        Rotation2d currentAngle = Rotation2d.fromRadians(currentRadians);
        Rotation2d desiredAngle = Rotation2d.fromRadians(desiredRadians);
        double difference = MathHelp.shortestAngleDifference(currentRadians, desiredRadians);
        SwerveModuleState moduleState = new SwerveModuleState(speed, desiredAngle);
        SwerveModuleState optimizedState = MathHelp.optimizeStateImproved(moduleState, currentAngle);

        if (Math.abs(difference) <= Math.PI / 2.0) {
            assertTrue(optimizedState.speed >= 0 == speed >= 0); // check for same sign
            assertEquals(optimizedState.angle.getRadians(), desiredRadians, 0.001);
        } else {
            if (optimizedState.speed > 0) {
                assertTrue(optimizedState.speed >= 0 == -speed >= 0); // Check for opposite signs
            }

            double angleDiff = optimizedState.angle.getRadians() - MathHelp.wrapAngle(desiredRadians, -Math.PI);
            assertEquals(Math.abs(angleDiff), Math.PI, 0.001);
        }
    }

    // Use this test case to reproduce hypothesis testing failures
    @Test
    public void falsifyingImprovedCase() {
        improvedOptimization(6.210083064008053, 6.210083064008053, 2);
    }
}
