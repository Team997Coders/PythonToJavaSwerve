import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class swerve_telemetry {

    private ISwerveDrive swerveDrive;
    private PhysicalConfig physicalConfig;

    public swerve_telemetry(ISwerveDrive swerveDrive, PhysicalConfig physicalConfig) {
        this.swerveDrive = swerveDrive;
        this.physicalConfig = physicalConfig;
    }

    private List<Double> unpackTuples(List<Pair<Double, Double>> tuples) {
        List<Double> unpackedValues = new ArrayList<>();
        for (Pair<Double, Double> tuple : tuples) {
            unpackedValues.add(tuple.getFirst());
            unpackedValues.add(tuple.getSecond());
        }
        return unpackedValues;
    }

    public void reportToDashboard() {
        SmartDashboard.putNumber("swerve/moduleCount", swerveDrive.numModules());
        
        List<Pair<Double, Double>> wheelLocations = new ArrayList<>();
        for (Iswervemodule module : swerveDrive.orderedModules()) {
            wheelLocations.add(new Pair<>(module.getLocation().getX(), module.getLocation().getY()));
        }
        SmartDashboard.putNumberArray("swerve/wheelLocations", unpackTuples(wheelLocations));
        
        List<Pair<Double, Double>> measuredStates = new ArrayList<>();
        for (Iswervemodule module : swerveDrive.orderedModules()) {
            measuredStates.add(new Pair<>(module.getMeasuredState().getAngle().getDegrees(), module.getMeasuredState().getSpeed()));
        }
        SmartDashboard.putNumberArray("swerve/measuredStates", unpackTuples(measuredStates));
        
        List<Pair<Double, Double>> desiredStates = new ArrayList<>();
        for (Iswervemodule module : swerveDrive.orderedModules()) {
            desiredStates.add(new Pair<>(module.getDesiredState().getAngle().getDegrees(), module.getDesiredState().getSpeed()));
        }
        SmartDashboard.putNumberArray("swerve/desiredStates", unpackTuples(desiredStates));
        
        SmartDashboard.putNumber("swerve/robotRotation", 0);
        SmartDashboard.putNumber("swerve/maxSpeed", physicalConfig.getMaxDriveSpeed());
        SmartDashboard.putString("swerve/rotationUnit", "degrees");
        // Add other telemetry values as needed
    }
}
