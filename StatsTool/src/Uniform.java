public class Uniform extends ContinuousProbDist {
    // Models uniform distribution U(a,b)
    private double a;
    private double b;

    public Uniform(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double pdf(double x) {
        if (x >= a && x <= b) {
            return 1/(b-a);
        } else {
            return 0;
        }
    }

    @Override
    public double cdf(double x) {
        if (x >= a && x <= b) {
            return (x-a)/(b-a);
        }
        return 0;
    }

    @Override
    public double expectation() {
        return (b-a)/2;
    }

    @Override
    public double variance() {
        return Math.pow(b-a,2)/12;
    }

    @Override
    public String toString() {
        return "U" + "[" + a + "," + b + "]";
    }

}