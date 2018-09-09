import java.util.Scanner;

public class MainConsole {

    // Starts the program by asking user to choose a discrete or a continuous distribution.
    // Also allow user to end program
    public static void start(Scanner scanner) {
        System.out.println("Choose type of distribution or end the program");
        System.out.println("1: Discrete distribution");
        System.out.println("2: Continuous distribution");
        System.out.println("3: End program");
        int chosenDistType;
        do {
            // Validate user input
            String input = scanner.nextLine();
            while (!isInt(input)) {
                input = scanner.nextLine();
            }
            chosenDistType = Integer.parseInt(input);
            if (chosenDistType == 1) {
                chooseDiscrete(scanner);
            } else if (chosenDistType == 2) {
                chooseContinuous(scanner);
            } else if (chosenDistType == 3) {
                System.out.println("Ending program");
                break;
            } else {
                System.out.println("This isn't one of required numbers. Please enter 1 (discrete), 2 (continous) or 3(end)!");
            }
        } while (chosenDistType != 1 && chosenDistType != 2);
    }

    // Ask user to choose a discrete distribution, validating input
    // Also allow user to go back to start and choose type of distribution
    public static void chooseDiscrete(Scanner scanner) {
        System.out.println("Choose a specific distribution by entering" +
                " one of numbers shown below. You can also go back to the start.");
        System.out.println("1: Bernoulli distribution");
        System.out.println("2: Binomial distribution");
        System.out.println("3: Poisson distribution");
        System.out.println("4: Geometric distribution");
        System.out.println("5: Go to start");
        int chosenDist;
        do {
            String input = scanner.nextLine();
            while (!isInt(input)) {
                input = scanner.nextLine();
            }
            chosenDist = Integer.parseInt(input);
            // Ask user to specify parameter(s) of chosen distribution
            // Check validity of the parameter(s) input - integer/real number and if necessary also value
            if (chosenDist == 1) {
                double p;
                System.out.println("You have chosen the Bernoulli distribution. Choose a value for the parameter p.");
                System.out.println("Note that 0 <= p <= 1");
                do {
                    System.out.print("p: ");
                    input = scanner.nextLine();
                    while (!isDouble(input)) {
                        System.out.print("p: ");
                        input = scanner.nextLine();
                    }
                    p = Double.parseDouble(input);
                    if (p < 0 || p > 1) System.out.println("Invalid value for p, must have 0 <= p <= 1! " +
                            "Enter a different value for p.");
                } while (p < 0 || p > 1);
                Bernoulli userDist = new Bernoulli(p);
                processDiscreteOpt(scanner,userDist);
            } else if (chosenDist == 2) {
                int n;
                double p;
                System.out.println("You have chosen the Binomial distribution. Choose values for the parameters n and p.");
                System.out.println("Note that n > 0 and 0 <= p <= 1");
                do {
                    System.out.print("n: ");
                    input = scanner.nextLine();
                    while (!isInt(input)) {
                        System.out.print("n: ");
                        input = scanner.nextLine();
                    }
                    n = Integer.parseInt(input);
                    if (n <= 0) System.out.println("Invalid value for n, must have n > 0! " +
                            "Enter a different value for n.");
                } while (n <= 0);
                do {
                    System.out.print("p: ");
                    input = scanner.nextLine();
                    while (!isDouble(input)) {
                        System.out.print("p: ");
                        input = scanner.nextLine();
                    }
                    p = Double.parseDouble(input);
                    if (p < 0 || p > 1) System.out.println("Invalid value for p, must have 0 <= p <= 1! " +
                            "Enter a different value for p.");
                } while (p < 0 || p > 1);
                Binomial userDist = new Binomial(n,p);
                processDiscreteOpt(scanner,userDist);
            } else if (chosenDist == 3) {
                double lambda;
                System.out.println("You have chosen the Poisson distribution. Choose a value for the parameter " +
                                '\u03bb' + "." );
                System.out.println("Note that " + '\u03bb' + " > 0");
                do {
                    System.out.print('\u03bb' + ": ");
                    input = scanner.nextLine();
                    while (!isDouble(input)) {
                        System.out.print('\u03bb' + ": ");
                        input = scanner.nextLine();
                    }
                    lambda = Double.parseDouble(input);
                    if (lambda <= 0) System.out.println("Invalid value for " + '\u03bb' + ", must have " + '\u03bb' + " > 0! "
                    + "Enter a different value for " + '\u03bb' + ".");
                } while (lambda <= 0);
                Poisson userDist = new Poisson(lambda);
                processDiscreteOpt(scanner,userDist);
            } else if (chosenDist == 4) {
                double p;
                System.out.println("You have chosen the Geometric distribution. Choose a value for the parameter p.");
                System.out.println("Note that 0 < p <= 1");
                do {
                    System.out.print("p: ");
                    input = scanner.nextLine();
                    while (!isDouble(input)) {
                        System.out.print("p: ");
                        input = scanner.nextLine();
                    }
                    p = Double.parseDouble(input);
                    if (p <= 0 || p > 1) System.out.println("Invalid value for p, must have 0 < p <= 1! " +
                            "Enter a different value for p.");
                } while (p <= 0 || p > 1);
                Geometric userDist = new Geometric(p);
                processDiscreteOpt(scanner, userDist);
            } else if (chosenDist == 5) {
                System.out.println("Going back to start.");
                start(scanner);
            } else {
                System.out.println("This isn't one of required numbers. " +
                        "Please enter 1 (Bernoulli), 2 (Binomial), 3 (Poisson), 4 (Geometric) or 5 (start)!");
            }
        } while (chosenDist != 1 && chosenDist != 2 && chosenDist != 3 && chosenDist != 4 && chosenDist != 5);
    }

    // Ask user to choose a continuous distribution, validating input
    // Also allow user to go back to start and choose type of distribution
    public static void chooseContinuous(Scanner scanner) {
        System.out.println("Choose a specific distribution by entering" +
                " one of numbers shown below. You can also go back to the start.");
        System.out.println("1: Uniform distribution");
        System.out.println("2: Exponential distribution");
        System.out.println("3: Normal distribution");
        System.out.println("4: Gamma distribution");
        System.out.println("5: Go to start (choosing discrete/continuous)");
        int chosenDist;
        do {
            String input = scanner.nextLine();
            while (!isInt(input)) {
                input = scanner.nextLine();
            }
            chosenDist = Integer.parseInt(input);
            if (chosenDist == 1) {
                // Ask user to specify parameter(s) of chosen distribution
                // Check validity of the parameter(s) input - integer/real number and if necessary also value
                double a;
                double b;
                System.out.println("You have chosen the Uniform distribution. Choose values for the parameters a and b.");
                System.out.println("Note that a < b");
                System.out.print("a: ");
                input = scanner.nextLine();
                while (!isDouble(input)) {
                    System.out.print("a: ");
                    input = scanner.nextLine();
                }
                a = Double.parseDouble(input);
                do {
                    System.out.print("b: ");
                    input = scanner.nextLine();
                    while (!isDouble(input)) {
                        System.out.print("b: ");
                        input = scanner.nextLine();
                    }
                    b = Double.parseDouble(input);
                    if (a >= b) System.out.println("Invalid value for b, must have a < b! " +
                            "Enter a different value for b.");

                } while (a >= b);
                Uniform userDist = new Uniform(a,b);
                System.out.println("Do not confuse a and b above with the distributions parameters.");
                processContinuousOpt(scanner, userDist);
            } else if (chosenDist == 2) {
                double lambda;
                System.out.println("You have chosen the Exponential distribution. Choose a value for the parameter " +
                        '\u03bb' + "." );
                System.out.println("Note that " + '\u03bb' + " > 0");
                do {
                    System.out.print('\u03bb' + ": ");
                    input = scanner.nextLine();
                    while (!isDouble(input)) {
                        System.out.print('\u03bb' + ": ");
                        input = scanner.nextLine();
                    }
                    lambda = Double.parseDouble(input);
                    if (lambda <= 0) System.out.println("Invalid value for " + '\u03bb' + ", must have " + '\u03bb' + " > 0! "
                            + "Enter a different value for " + '\u03bb' + ".");
                } while (lambda <= 0);
                Exponential userDist = new Exponential(lambda);
                processContinuousOpt(scanner, userDist);
            } else if (chosenDist == 3) {
                double mu;
                double var;
                System.out.println("You have chosen the Normal distribution. Choose values for expectation and variance.");
                System.out.println("Note that variance must be positive.");
                System.out.print("Expectation: ");
                input = scanner.nextLine();
                while (!isDouble(input)) {
                    System.out.print("Expectation: ");
                    input = scanner.nextLine();
                }
                mu = Double.parseDouble(input);
                do {
                    System.out.print("Variance: ");
                    input = scanner.nextLine();
                    while (!isDouble(input)) {
                        System.out.print("Variance: ");
                        input = scanner.nextLine();
                    }
                    var = Double.parseDouble(input);
                    if (var <= 0) System.out.println("Invalid value for variance, variance must be positive! " +
                            "Enter a different value for variance.");

                } while (var <= 0);
                Normal userDist = new Normal(mu,var);
                processContinuousOpt(scanner, userDist);
            } else if (chosenDist == 4) {
                double alpha;
                double beta;
                System.out.println("You have chosen the Gamma distribution. Choose values for the parameters "
                        + '\u03b1' + " and " + '\u03b2' + ".");
                System.out.println("Note that both " + '\u03b1' + " and " + '\u03b2' + " must be positive.");
                do {
                    System.out.print('\u03b1' + ": ");
                    input = scanner.nextLine();
                    while (!isDouble(input)) {
                        System.out.print('\u03b1' + ": ");
                        input = scanner.nextLine();
                    }
                    alpha = Double.parseDouble(input);
                    if (alpha <= 0) System.out.println("Invalid value for " + '\u03b1' + ", must have " + '\u03b1' +
                            " > 0!. Enter a different value for " + '\u03b1' + ".");
                } while (alpha <= 0);
                do {
                    System.out.print('\u03b2' + ": ");
                    input = scanner.nextLine();
                    while (!isDouble(input)) {
                        System.out.print('\u03b2' + ": ");
                        input = scanner.nextLine();
                    }
                    beta = Double.parseDouble(input);
                    if (beta <= 0) System.out.println("Invalid value for " + '\u03b2' + ", must have " + '\u03b2' +
                            " > 0!. Enter a different value for " + '\u03b2' + ".");
                } while (beta <= 0);
                Gamma userDist = new Gamma(alpha, beta);
                processContinuousOpt(scanner, userDist);
            } else if (chosenDist == 5) {
                System.out.println("Going back to start.");
                start(scanner);
            } else {
                System.out.println("This isn't one of required numbers. " +
                        "Please enter 1 (Uniform), 2 (Exponential), 3 (Normal), 4 (Gamma) or 5 (start)!");
            }
        } while (chosenDist != 1 && chosenDist != 2 && chosenDist != 3 && chosenDist != 4 && chosenDist != 5);
    }

    // Show possible queries for a discrete distribution and option to choose a different discrete distribution
    public static void discreteOpts() {
        System.out.println("What would you like to do? Choose an operation by entering one of the numbers shown below.");
        System.out.println("1: Exact - P(X = a)");
        System.out.println("2: Two-tailed - P(a <= X <= b)");
        System.out.println("3: Left-tailed - P(X <= a)");
        System.out.println("4: Right-tailed - P(X >= a)");
    }
    // Show possible queries for a continuous distribution and option to choose a different continuous distribution
    public static void continuousOpts() {
        System.out.println("What would you like to do? Choose an operation by entering one of the numbers shown below.");
        System.out.println("1: Two-tailed - P(a <= X <= b)");
        System.out.println("2: Left-tailed - P(X <= a)");
        System.out.println("3: Right-tailed - P(X >= a)");
    }

    // Ask user for probabilistic query and evaluate it, given a discrete distribution and show the result
    public static void processDiscreteOpt(Scanner scanner, DiscreteProbDist userDist) {
        discreteOpts();
        int chosenOpt;
        do {
            String input = scanner.nextLine();
            while (!isInt(input)) {
                input = scanner.nextLine();
            }
            chosenOpt = Integer.parseInt(input);
            if (chosenOpt == 1) {
                int a;
                System.out.println("Enter an integer value for a.");
                System.out.print("a: ");
                input = scanner.nextLine();
                // Check that input is an integer
                while (!isInt(input)) {
                    System.out.print("a: ");
                    input = scanner.nextLine();
                }
                a = Integer.parseInt(input);
                System.out.println("P(X = " + a + ") = " + userDist.pmf(a));
            } else if (chosenOpt == 2) {
                int a;
                System.out.println("Enter integer values for a and b");
                System.out.print("a: ");
                input = scanner.nextLine();
                // Check that input for a is an integer
                while (!isInt(input)) {
                    System.out.print("a: ");
                    input = scanner.nextLine();
                }
                a = Integer.parseInt(input);
                int b;
                do {
                    // Check that input for b is an integer and also satisfies b > a
                    System.out.print("b: ");
                    input = scanner.nextLine();
                    while (!isInt(input)) {
                        System.out.print("b: ");
                        input = scanner.nextLine();
                    }
                    b = Integer.parseInt(input);
                    if (a >= b) System.out.println("Invalid value for b, must have a < b! " +
                            "Enter a different value for b.");
                } while (a >= b);
                System.out.println("P(" + a + " <= X <= " + b + ") = " + userDist.probability(a,b));
            } else if (chosenOpt == 3){
                int a;
                System.out.println("Enter an integer value for a.");
                System.out.print("a: ");
                input = scanner.nextLine();
                // Check that input is an integer
                while (!isInt(input)) {
                    System.out.print("a: ");
                    input = scanner.nextLine();
                }
                a = Integer.parseInt(input);
                System.out.println("P(X <= " + a + ") = " + userDist.cdf(a));
            } else if (chosenOpt == 4) {
                int a;
                System.out.println("Enter an integer value for a.");
                System.out.print("a: ");
                input = scanner.nextLine();
                // Check that input is an integer
                while (!isInt(input)) {
                    System.out.print("a: ");
                    input = scanner.nextLine();
                }
                a = Integer.parseInt(input);
                System.out.println("P(X >= " + a + ") = " + userDist.rightTail(a));
            } else {
                System.out.println("This isn't one of required numbers. Please enter 1 (exact)," +
                        " 2 (two-tailed), 3 (left-tailed) or 4 (right-tailed)!");
            }
        } while (chosenOpt != 1 && chosenOpt != 2 && chosenOpt != 3 && chosenOpt != 4);
        int chosenEnd;
        // Afterwards, user can go to start or another query with same distribution
        endDiscrete(userDist);
        do {
            String input = scanner.nextLine();
            while (!isInt(input)) {
                input = scanner.nextLine();
            }
            chosenEnd = Integer.parseInt(input);
            if (chosenEnd == 1) {
                start(scanner);
            } else if (chosenEnd == 2) {
                processDiscreteOpt(scanner,userDist);
            } else {
                System.out.println("This isn't one of required numbers. Please enter 1 (start)" +
                        " or 2 (another query with same distribution)!");
            }
        } while (chosenEnd != 1 && chosenEnd != 2);
    }

    // Ask user for probabilistic query and evaluate it, given a continuous distribution and show the result
    public static void processContinuousOpt(Scanner scanner, ContinuousProbDist userDist) {
        continuousOpts();
        int chosenOpt;
        do {
            String input = scanner.nextLine();
            while (!isInt(input)) {
                input = scanner.nextLine();
            }
            chosenOpt = Integer.parseInt(input);
            if (chosenOpt == 1) {
                double a;
                System.out.println("Enter real values for a and b");
                System.out.print("a: ");
                input = scanner.nextLine();
                // Check that input for a is a real value
                while (!isDouble(input)) {
                    System.out.print("a: ");
                    input = scanner.nextLine();
                }
                a = Double.parseDouble(input);
                double b;
                // Check that input for b is a real value and satisfies b > a
                do {
                    System.out.print("b: ");
                    input = scanner.nextLine();
                    while (!isDouble(input)) {
                        System.out.print("b: ");
                        input = scanner.nextLine();
                    }
                    b = Double.parseDouble(input);
                    if (a >= b) System.out.println("Invalid value for b, must have a < b! " +
                            "Enter a different value for b.");
                } while (a >= b);
                System.out.println("P(" + a + " <= X <= " + b + ") = " + userDist.probability(a,b));
            } else if (chosenOpt == 2){
                double a;
                System.out.println("Enter a real value for a.");
                System.out.print("a: ");
                input = scanner.nextLine();
                // Check that input is a real value
                while (!isDouble(input)) {
                    System.out.print("a: ");
                    input = scanner.nextLine();
                }
                a = Double.parseDouble(input);
                System.out.println("P(X <= " + a + ") = " + userDist.cdf(a));
            } else if (chosenOpt == 3) {
                double a;
                System.out.println("Enter a real value for a.");
                System.out.print("a: ");
                input = scanner.nextLine();
                while (!isInt(input)) {
                    System.out.print("a: ");
                    input = scanner.nextLine();
                }
                a = Double.parseDouble(input);
                System.out.println("P(X >= " + a + ") = " + userDist.rightTail(a));
            } else {
                System.out.println("This isn't one of required numbers. Please enter" +
                        " 1 (two-tailed), 2 (left-tailed) or 3 (right-tailed)!");
            }
        } while (chosenOpt != 1 && chosenOpt != 2 && chosenOpt != 3);
        int chosenEnd;
        // Afterwards, user can go to start or another query with same distribution
        endContinuous(userDist);
        do {
            String input = scanner.nextLine();
            while (!isInt(input)) {
                input = scanner.nextLine();
            }
            chosenEnd = Integer.parseInt(input);
            if (chosenEnd == 1) {
                start(scanner);
            } else if (chosenEnd == 2) {
                processContinuousOpt(scanner,userDist);
            } else {
                System.out.println("This isn't one of required numbers. Please enter 1 (start)" +
                        " or 2 (another query with same distribution)!");
            }
        } while (chosenEnd != 1 && chosenEnd != 2);
    }

    // Show options after discrete query is finished
    private static void endDiscrete(DiscreteProbDist userDist) {
        System.out.println("1: Go to start");
        System.out.println("2: Another query with same distribution " + userDist.toString());
    }

    // Show options after continuous query is finished
    private static void endContinuous(ContinuousProbDist userDist) {
        System.out.println("1: Go to start");
        System.out.println("2: Another query with same distribution " + userDist.toString());
    }

    private static boolean isInt(String input) {
        try {
            int choice = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: Input is not an integer.");
            System.out.println("Choose different value");
            return false;
        }
    }

    private static boolean isDouble(String input) {
        try {
            double choice = Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: Input is not a real number.");
            System.out.println("Choose different value");
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        start(scanner);
        scanner.close();
    }


}
