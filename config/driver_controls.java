public class DriverControlsConfig {
    private final double xDeadband;
    private final double yDeadband;
    private final double thetaDeadband;

    public DriverControlsConfig(double xDeadband, double yDeadband, double thetaDeadband) {
        this.xDeadband = xDeadband;
        this.yDeadband = yDeadband;
        this.thetaDeadband = thetaDeadband;
    }

    public double getXDeadband() {
        return xDeadband;
    }

    public double getYDeadband() {
        return yDeadband;
    }

    public double getThetaDeadband() {
        return thetaDeadband;
    }
}
