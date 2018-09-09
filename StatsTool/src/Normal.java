public class Normal extends ContinuousProbDist {
    // Models the Normal distribution N(mu,sigma^2)
    private double mu;
    private double sigmaSquared;

    public Normal(double mu, double sigmaSqr) {
        this.mu = mu;
        this.sigmaSquared = sigmaSqr;
    }

    @Override
    public double pdf(double x) {
        return 1/(Math.sqrt(2*Math.PI*sigmaSquared)) * Math.pow(Math.E, -Math.pow((x - mu)/sigmaSquared,2)/2);
    }

    // Approximates the error function (x>0) using Simpson's rule
    public double erf(double x) {
        int numDomains = 1000;
        double width = x / numDomains;
        double result = 0;
        for (int i = 0; i <= numDomains; i++) {
            if (i == 0 || i == numDomains) {
                result += Math.pow(Math.E, -(i*width)*(i*width));
            } else if (i % 2 == 1) {
                result += 4 * Math.pow(Math.E, -(i*width)*(i*width));
            } else {
                result += 2 * Math.pow(Math.E, -(i*width)*(i*width));
            }
        }
        result *= width/3 * 2/Math.sqrt(Math.PI);
        return result;
    }

    // Compute cdf using the error function above
    // For more details, see https://en.wikipedia.org/wiki/Normal_distribution#Cumulative_distribution_function
    @Override
    public double cdf(double x) {
        return 0.5 *(1 + erf((x-mu)/Math.sqrt(2*sigmaSquared)));
    }

    @Override
    public double expectation() {
        return mu;
    }

    @Override
    public double variance() {
        return sigmaSquared;
    }

    @Override
    public String toString() {
        return "N(" + mu + "," + sigmaSquared + ")";
    }

}
