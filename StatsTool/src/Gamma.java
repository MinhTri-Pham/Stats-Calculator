public class Gamma extends ContinuousProbDist {
    // Models the Gamma distribution Gamma(alpha,beta) (using shape and rate parameters)
    private double alpha;
    private double beta;

    public Gamma(double alpha, double beta) {
        this.alpha = alpha;
        this.beta = beta;
    }

    // Computes x! for real x via the Stirling series (first 5 terms)
    public double myFactorial(double x) {
        return Math.sqrt(2*Math.PI*x) * Math.pow(x / Math.E,x) * (1 + 1/(12*x) + 1/(288*x*x)
                - 139/(51840 * Math.pow(x,3)) - 571 / (2488320 * Math.pow(x,4)));
    }

    // Computes the gamma function by Gamma(x) = (x-1)! using above approximation
    public double gammaFunction(double x) {
        return myFactorial(x-1);
    }

    @Override
    public double pdf(double x) {
        if (x > 0) {
            return (Math.pow(beta,alpha) / gammaFunction(alpha))*Math.pow(x,alpha-1)*Math.pow(Math.E,-beta*x);
    }
        return 0;
    }

    // Approximates the (lower) incomplete gamma function using (1/3) Simpson's rule
    public double incompleteGammaFunction(double s, double x) {
        int numDomains = 1000;
        double width = x / numDomains;
        double result = 0;
        for (int i = 0; i <= numDomains; i++) {
            if (i == 0 || i == numDomains) {
                result += Math.pow(i*width,s-1) * Math.pow(Math.E, -(i*width));
            } else if (i % 2 == 1) {
                result += 4 * Math.pow(i*width,s-1) * Math.pow(Math.E, -(i*width));
            } else {
                result += 2 * Math.pow(i*width,s-1) * Math.pow(Math.E, -(i*width));
            }
        }
        result *= width/3;
        return result;
    }

    @Override
    // There's no closed formula for the cdf, hence numeracial approximation has to be used
    // For more details, see https://en.wikipedia.org/wiki/Gamma_distribution
    public double cdf(double x) {
        return incompleteGammaFunction(alpha, beta*x) / gammaFunction(alpha);
    }

    @Override
    public double expectation() {
        return alpha/beta;
    }

    @Override
    public double variance() {
        return alpha/(beta*beta);
    }

    @Override
    public String toString() {
        return '\u0393' + "(" + alpha + "," + beta + ")";
    }
}
