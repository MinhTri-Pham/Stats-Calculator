public abstract class ContinuousProbDist {

    // Compute probability density function (pdf) and cumulative distribution function (cdf)
    // Depends on particular distribution, hence abstract method
    public abstract double pdf(double x);
    public abstract double cdf(double x);

    // Compute probability P(X >= a)
    public double rightTail(double x) {
        return 1-cdf(x);
    }

    // Compute expectation and variance
    // Depends on particular distribution's parameters, hence abstract method
    public abstract double expectation();
    public abstract double variance();

    // Calculate P(a <= X <= b) using numerical integration of pdf
    // Use (1/3) Simpson's rule, note that numDomains must be even
    // For details see, https://en.wikipedia.org/wiki/Simpson%27s_rule
    public double probability(double lower, double upper) {
        int numDomains = 1000;
        double width = (upper - lower) / numDomains;
        double result = 0;
        for (int i = 0; i <= numDomains; i++) {
            if (i == 0 || i == numDomains) {
                result += pdf(lower + i*width);
            } else if (i % 2 == 1) {
                result += 4* pdf(lower + i*width);
            } else {
                result += 2* pdf(lower + i*width);
            }

        }
        result *= width/3;
        return result;
    }

}
