public class Bernoulli extends DiscreteProbDist {
    // Models the Bernoulli distribution Bernoulli(p)
    private double p;

    public Bernoulli(double p) {
        this.p = p;
    }

    @Override
    public double pmf(int x) {
        if (x == 0 || x == 1) {
            return Math.pow(p,x) * Math.pow(1-p, 1-x);
        }
        return 0;
    }

    @Override
    public double cdf(int x) {
        if (x < 0) {
            return 0;
        } else if (x < 1) {
            return 1-p;
        }
        return 1;
    }

    @Override
    public double expectation() {
        return p;
    }

    @Override
    public double variance() {
        return p*(1-p);
    }

    @Override
    public String toString() {
        return "Bernoulli(" + p + ")";
    }

}
