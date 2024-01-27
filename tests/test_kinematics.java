import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.kinematics.*;

public class test_kinematics {

    Translation2d frontLeft = new Translation2d(10, 10);
    Translation2d frontRight = new Translation2d(10, -10);
    Translation2d backLeft = new Translation2d(-10, 10);
    Translation2d backRight = new Translation2d(-10, -10);

    @Test
    public void testModuleOrderOne() {
        SwerveDrive4Kinematics kinematics = new SwerveDrive4Kinematics(frontLeft, frontRight, backLeft, backRight);
        
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(1.0, 0.0, 0.2);
        swervemoduleState[] moduleStates = kinematics.toswervemoduleStates(chassisSpeeds);
        swervemoduleState fl = moduleStates[0];
        swervemoduleState fr = moduleStates[1];
        swervemoduleState bl = moduleStates[2];
        swervemoduleState br = moduleStates[3];

        // Add your assertions here
        // Example:
        assertTrue(fl.speed < fr.speed);
        assertTrue(bl.speed < br.speed);
        assertTrue(Math.abs(fl.angle.getDegrees()) > Math.abs(fr.angle.getDegrees()));
        assertTrue(Math.abs(bl.angle.getDegrees()) > Math.abs(br.angle.getDegrees()));
        assertEquals(fl.angle.getDegrees(), -bl.angle.getDegrees(), 0.001);
        assertEquals(fr.angle.getDegrees(), -br.angle.getDegrees(), 0.001);
    }

    @Test
    public void testModuleOrderTwo() {
        SwerveDrive4Kinematics kinematics = new SwerveDrive4Kinematics(frontRight, frontLeft, backRight, backLeft);
        
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(1.0, 0.0, 0.2);
        swervemoduleState[] moduleStates = kinematics.toswervemoduleStates(chassisSpeeds);
        swervemoduleState fr = moduleStates[0];
        swervemoduleState fl = moduleStates[1];
        swervemoduleState br = moduleStates[2];
        swervemoduleState bl = moduleStates[3];

        // Add your assertions here
        // Example:
        assertTrue(fl.speed < fr.speed);
        assertTrue(bl.speed < br.speed);
        assertTrue(Math.abs(fl.angle.getDegrees()) > Math.abs(fr.angle.getDegrees()));
        assertTrue(Math.abs(bl.angle.getDegrees()) > Math.abs(br.angle.getDegrees()));
        assertEquals(fl.angle.getDegrees(), -bl.angle.getDegrees(), 0.001);
        assertEquals(fr.angle.getDegrees(), -br.angle.getDegrees(), 0.001);
    }
}
