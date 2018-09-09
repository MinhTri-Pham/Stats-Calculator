public class Binomial extends DiscreteProbDist {
    // Models Binomial distribution Bin(n,p)
    private int n;
    private double p;

    public Binomial(int n, double p) {
        this.n = n;
        this.p = p;
    }

    @Override
    public double pmf(int x) {
        if (x >= 0 && x <= n) {
            return Helper.myBinomCoef(n,x) * Math.pow(p,x) * Math.pow(1-p,n-x);
        }
        return 0;
    }

    @Override
    public double cdf(int x) {
        double result = 0;
        for (int i = 0; i <= x; i++) {
            result += pmf(i);
        }
        return result;
    }

    @Override
    public double expectation() {
        return n*p;
    }

    @Override
    public double variance() {
        return n*p*(1-p);
    }

    @Override
    public String toString() {
        return "Bin(" + n + "," + p + ")";
    }
}
