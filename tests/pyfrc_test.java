import edu.wpi.first.wpilibj.simulation.SimHooks;
import edu.wpi.first.wpilibj.simulation.SimDeviceSim;

import frc.robot.RobotContainer;

public class pyfrc_test extends TestHarness {
    
    @Override
    public void initialize() {
        RobotContainer robotContainer = new RobotContainer();
        setRobot(robotContainer.getRobot());

        SimDeviceSim.getInstance("SimDevice").getDouble("TestParam").set(42.0);
        
        // Set up any additional initialization if needed
    }

    @Override
    public void executeTest() {
        // Implement your test logic here
        // Example:
        double motorSpeed = getSimMotor("example_motor").get();
        assertEqual(motorSpeed, 0.5, 0.001);  // Check if the motor speed is approximately 0.5
    }

    public static void main(String... args) {
        SimHooks.setHooks(new SimHooksExtension());

        RobotTest test = new RobotTest();
        test.runTest();
    }
}
