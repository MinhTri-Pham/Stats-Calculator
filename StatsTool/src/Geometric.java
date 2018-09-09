public class Geometric extends DiscreteProbDist {
    // Models Geometric distribution Geom(p)
    private double p;

    public Geometric(double p) {
        this.p = p;
    }

    @Override
    public double pmf(int x) {
        if (x > 0) {
            return p * Math.pow(1-p,x-1);
        }
        return 0;
    }

    @Override
    public double expectation() {
        return 1/p;
    }

    @Override
    public double variance() {
        return (1-p) / (p * p);
    }

    @Override
    public String toString() {
        return "Geom(" + p + ")";
    }
}
