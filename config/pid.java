import com.example.config.range.Range;
import com.example.config.range.OptionalRange;

public class PIDConfig {
    private final double p;
    private final double i;
    private final double d;
    private final OptionalRange wrapping;

    public PIDConfig(double p, double i, double d, OptionalRange wrapping) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.wrapping = wrapping;
    }

    // Add getters and setters as needed

    public double getP() {
        return p;
    }

    public double getI() {
        return i;
    }

    public double getD() {
        return d;
    }

    public OptionalRange getWrapping() {
        return wrapping;
    }
}

