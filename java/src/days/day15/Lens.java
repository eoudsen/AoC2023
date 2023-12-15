package days.day15;

public class Lens {

    private final String label;
    private final int focalStrength;

    public Lens(final String label, final int focalStrength) {
        this.label = label;
        this.focalStrength = focalStrength;
    }

    public String getLabel() {
        return this.label;
    }

    public int getFocalStrength() {
        return this.focalStrength;
    }

}
