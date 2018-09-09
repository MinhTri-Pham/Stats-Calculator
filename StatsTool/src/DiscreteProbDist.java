public abstract class DiscreteProbDist {

    // Compute probability mass function (pmf) (probability P(X = a)
    // Depends on particular distribution, hence abstract method
    public abstract double pmf(int x);

    // Compute cdf - sum of pmfs
    public double cdf(int x) {
        double result = 0;
        for (int i = 0; i <= x; i++) {
            result += pmf(i);
        }
        return result;
    }

    // Compute probability P(X >= a) (as 1 - P(X < a) + P(X = a))
    public double rightTail(int x) {
        return 1-cdf(x) + pmf(x);
    }

    // Compute expectation and variance - depends on particular distribution's parameters
    public abstract double expectation();
    public abstract double variance();

    // Calculate probability P(a <= X <= b) - sum of pmfs
    public double probability(int lower, int upper) {
        double prob = 0;
        for (int i = lower; i <= upper; i++) {
            prob += pmf(i);

        }
        return prob;
    }
}
