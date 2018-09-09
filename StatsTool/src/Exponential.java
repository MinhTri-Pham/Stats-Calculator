public class Exponential extends ContinuousProbDist {
    // Models the continuous distribution Exp(lambda)
    private double lambda;

    public Exponential(double lambda) {
        this.lambda = lambda;
    }

    @Override
    public double pdf(double x) {
        if (x > 0) {
            return lambda*Math.pow(Math.E, -lambda * x);
        }
        return 0;
    }

    @Override
    public double cdf(double x) {
        return 1-Math.pow(Math.E, -lambda * x);
    }

    @Override
    public double expectation() {
        return 1/lambda;
    }

    @Override
    public double variance() {
        return 1/Math.pow(lambda,2);
    }

    @Override
    public String toString() {
        return "Exp(" + lambda + ")";
    }
}
