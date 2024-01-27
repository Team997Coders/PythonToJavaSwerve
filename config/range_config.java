// Range class
public class Range {
    private final double min;
    private final double max;

    public Range(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}

// OptionalRange class
public class OptionalRange {
    private final Double min;
    private final Double max;

    public OptionalRange(Double min, Double max) {
        this.min = min;
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }
}