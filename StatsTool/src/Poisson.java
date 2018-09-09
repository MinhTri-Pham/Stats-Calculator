public class Poisson extends DiscreteProbDist {
    // Models Poisson distribution Po(lambda)
    private double lambda;

    public Poisson(double lambda) {
        this.lambda = lambda;
    }

    @Override
    public double pmf(int x) {
        if (x >= 0) {
            return Math.pow(Math.E,-lambda) * Math.pow(lambda,x) / Helper.myFactorial(x);
        }
        return 0;
    }

    @Override
    public double expectation() {
        return lambda;
    }

    @Override
    public double variance() {
        return lambda;
    }

    @Override
    public String toString() {
        return "Poisson(" + lambda + ")";
    }
}
