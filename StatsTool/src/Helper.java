public class Helper {
    // Helper functions

    // Factorial and binomial coefficient
    public static int myFactorial(int n) {
        if (n<1) return 1;
        return n*myFactorial(n-1);
    }

    public static int myBinomCoef(int n, int x) {
        return myFactorial(n)/ (myFactorial(x) * myFactorial(n-x));
    }

    // Functions for input validation

    // Checks if input is a real number
    public static boolean isReal(String input) {
        try {
            double x = Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Checks if input is a positive real number
    public static boolean isPos(String input) {
        try {
            double x = Double.parseDouble(input);
            return x > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Checks if input is greater than another input
    // If any input not real, return false
    public static boolean isGreaterThan(String input, String compareVal) {
        try {
            double x = Double.parseDouble(input);
            double a = Double.parseDouble(compareVal);
            return x > a;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Checks if input is between two different inputs representing numbers
    // If any input not real, return false;
    public static boolean isBetween(String input, String lower, String upper) {
        try {
            double x = Double.parseDouble(input);
            double a = Double.parseDouble(lower);
            double b = Double.parseDouble(upper);
            return x >= a && x <= b;
        } catch (NumberFormatException e){
            return false;
        }
    }

    // Checks if input is a integer between 0 and another input
    // If another input not an integer, return false
    public static boolean isIntBetweenZeroAndN(String input, String compareInt) {
        try {
            int x = Integer.parseInt(input);
            int n = Integer.parseInt(compareInt);
            return x >= 0 && x <= n;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Checks if input is real value in range [0,1]
    public static boolean isProbability(String input) {
        try {
            double p = Double.parseDouble(input);
            return (p >= 0 && p <= 1);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Checks if input is real value in range (0,1]
    public static boolean isProbabilityNotZero(String input) {
        try {
            double p = Double.parseDouble(input);
            return p > 0 && p <= 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Checks if input is 0 or 1
    public static boolean isZeroOrOne(String input) {
        try {
            int x = Integer.parseInt(input);
            return x == 0 || x == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Checks if input is a non-negative integer
    public static boolean isNonNegInt(String input) {
        try {
            int x = Integer.parseInt(input);
            return x >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Checks if input is a positive integer
    public static boolean isPosInt(String input) {
        try {
            int x = Integer.parseInt(input);
            return x > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
