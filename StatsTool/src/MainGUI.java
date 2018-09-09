import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainGUI extends Application {

    private Text warningMessage = new Text(); // Warning message if input not correct

    public static void main(String[] args) {
        launch(args);
    }

    // Start screen
    @Override
    public void start(Stage window) throws FileNotFoundException {
        window.setTitle("Stats Tool");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(window);
        });

        Text welcomeText = new Text("Welcome to StatsTool");
        welcomeText.setFont(new Font(14));
        Button startButton = new Button("Start");
        Button exitButton = new Button("Exit");

        // Set up image in start screen - source: https://pt.coursera.org/learn/bayesian
        Image startLogo = new Image(new FileInputStream("stats_tool.png"));
        ImageView startLogoView = new ImageView(startLogo);
        startLogoView.setFitHeight(100);
        startLogoView.setFitWidth(120);
        startLogoView.setPreserveRatio(true);
        Group logoGroup = new Group(startLogoView);

        // Lambda expression - more concise but note that you need Java 8 minimum
        // Another solution would be an anonymous inner class
        // Two options: proceed to choosing distribution type or end program
        startButton.setOnAction(e -> chooseDistributionType(window));
        exitButton.setOnAction(e -> closeProgram(window));

        // Set up how screen looks
        VBox startMenuLayout = new VBox(10);
        startMenuLayout.getChildren().addAll(welcomeText,logoGroup,startButton,exitButton);
        startMenuLayout.setAlignment(Pos.CENTER);

        Scene startScene = new Scene(startMenuLayout,230,220);
        window.setScene(startScene);
        window.show();
    }

    // Screen for choosing distribution type(discrete/continuous) or going back to start screen
    private void chooseDistributionType(Stage window) {
        window.setTitle("Distribution type");
        GridPane distTypeLayout = new GridPane();

        Text chooseDistType = new Text("Choose a distribution type");
        GridPane.setConstraints(chooseDistType,0,0);

        // Distribution type (discrete/continuous) options where only one can be selected by user
        ToggleGroup distType = new ToggleGroup();
        RadioButton discreteRB = new RadioButton("Discrete");
        GridPane.setConstraints(discreteRB,0,1);
        discreteRB.setToggleGroup(distType);
        RadioButton continuousRB = new RadioButton("Continuous");
        GridPane.setConstraints(continuousRB,0,2);
        continuousRB.setToggleGroup(distType);

        GridPane.setConstraints(warningMessage,0,3);

        // Buttons to confirm choice or go to start screen
        Button selectButton = new Button("Select");
        GridPane.setConstraints(selectButton,0,4);
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton,2,4);

        // Switch to screen for choosing a specific distribution or warn user if no option was selected
        selectButton.setOnAction(e -> {
            if (discreteRB.isSelected())
                chooseDiscrete(window);
            if (continuousRB.isSelected())
                chooseContinuous(window);
            if (!discreteRB.isSelected() && !continuousRB.isSelected())
                warningMessage.setText("Please select one option!");
        });
        backButton.setOnAction(e -> {
            try {
                start(window);
            } catch (FileNotFoundException exc){
                exc.printStackTrace();
            }
        });

        // Set up how screen looks
        distTypeLayout.setPadding(new Insets(10));
        distTypeLayout.setVgap(10);
        distTypeLayout.setHgap(10);
        distTypeLayout.getChildren().addAll(chooseDistType,discreteRB,continuousRB,warningMessage,selectButton,backButton);
        warningMessage.setText(""); // Reset invalid input warning
        Scene chooseDistTypeScene = new Scene(distTypeLayout,260,150);
        window.setScene(chooseDistTypeScene);
        window.show();
    }

    // Screen for choosing a specific discrete distribution
    private void chooseDiscrete(Stage window) {
        window.setTitle("Discrete distribution");
        GridPane discreteDistLayout = new GridPane();

        Text chooseDiscreteDist = new Text("Choose a discrete distribution");
        GridPane.setConstraints(chooseDiscreteDist,0,0);

        // Different discrete distributions where only one can be selected by user
        ToggleGroup discreteDists = new ToggleGroup();
        RadioButton bernoulliRB = new RadioButton("Bernoulli");
        bernoulliRB.setToggleGroup(discreteDists);
        GridPane.setConstraints(bernoulliRB,0,1);
        RadioButton binomialRB = new RadioButton("Binomial");
        binomialRB.setToggleGroup(discreteDists);
        GridPane.setConstraints(binomialRB,0,2);
        RadioButton poissonRB = new RadioButton("Poisson");
        poissonRB.setToggleGroup(discreteDists);
        GridPane.setConstraints(poissonRB,0,3);
        RadioButton geometricRB = new RadioButton("Geometric");
        geometricRB.setToggleGroup(discreteDists);
        GridPane.setConstraints(geometricRB,0,4);

        GridPane.setConstraints(warningMessage,0,5);

        // Buttons to confirm distribution choice or go to distribution type choice screen
        Button selectButton = new Button("Select");
        Button backButton = new Button("Back");
        GridPane.setConstraints(selectButton,0,6);
        GridPane.setConstraints(backButton,1,6);

        // Switch to chosen distribution evaluation screen or warn user if no option was selected
        selectButton.setOnAction(e -> {
            if (bernoulliRB.isSelected())
                bernoulliDist(window);
            if (binomialRB.isSelected())
                binomialDist(window);
            if (poissonRB.isSelected())
                poissonDist(window);
            if (geometricRB.isSelected())
                geometricDist(window);
            if(!bernoulliRB.isSelected() && !binomialRB.isSelected() && !poissonRB.isSelected() && !geometricRB.isSelected())
                warningMessage.setText("Please select one option!");
        });
        backButton.setOnAction(e -> chooseDistributionType(window));

        // Set up how screen looks
        discreteDistLayout.setPadding(new Insets(10));
        discreteDistLayout.setVgap(10);
        discreteDistLayout.setHgap(10);
        discreteDistLayout.getChildren().addAll(chooseDiscreteDist,bernoulliRB,binomialRB,poissonRB,
                geometricRB,warningMessage,selectButton,backButton);
        warningMessage.setText(""); // Reset invalid input warning
        Scene chooseDiscreteScene = new Scene(discreteDistLayout,280,210);
        window.setScene(chooseDiscreteScene);
        window.show();
    }

    private void chooseContinuous(Stage window) {
        window.setTitle("Continuous distribution");
        GridPane continuousDistLayout = new GridPane();

        Text chooseContinuousDist = new Text("Choose a continuous distribution");
        GridPane.setConstraints(chooseContinuousDist,0,0);

        // Different continuous distributions where only one can be selected by user
        ToggleGroup continuousDists = new ToggleGroup();
        RadioButton uniformRB = new RadioButton("Uniform");
        uniformRB.setToggleGroup(continuousDists);
        GridPane.setConstraints(uniformRB,0,1);
        RadioButton exponentialRB = new RadioButton("Exponential");
        exponentialRB.setToggleGroup(continuousDists);
        GridPane.setConstraints(exponentialRB,0,2);
        RadioButton normalRB = new RadioButton("Normal");
        normalRB.setToggleGroup(continuousDists);
        GridPane.setConstraints(normalRB,0,3);
        RadioButton gammaRB = new RadioButton("Gamma");
        gammaRB.setToggleGroup(continuousDists);
        GridPane.setConstraints(gammaRB,0,4);

        GridPane.setConstraints(warningMessage,0,5);

        // Buttons to confirm distribution choice or go to distribution type choice screen
        Button selectButton = new Button("Select");
        Button backButton = new Button("Back");
        GridPane.setConstraints(selectButton,0,6);
        GridPane.setConstraints(backButton,1,6);

        // Switch to chosen distribution evaluation screen or warn user if no option was selected
        selectButton.setOnAction(e -> {
            if (uniformRB.isSelected())
                uniformDist(window);
            if (exponentialRB.isSelected())
                exponentialDist(window);
            if (normalRB.isSelected())
                normalDist(window);
            if (gammaRB.isSelected())
                gammaDist(window);
            if (!(uniformRB.isSelected() || exponentialRB.isSelected() || normalRB.isSelected() || gammaRB.isSelected()))
                warningMessage.setText("Please select one option!");
        });
        backButton.setOnAction(e -> chooseDistributionType(window));

        // Set up how screen looks
        continuousDistLayout.setPadding(new Insets(10));
        continuousDistLayout.setVgap(10);
        continuousDistLayout.setHgap(10);
        continuousDistLayout.getChildren().addAll(chooseContinuousDist,uniformRB,exponentialRB,normalRB,
                gammaRB,warningMessage,selectButton,backButton);
        warningMessage.setText("");
        Scene chooseContinuousScene = new Scene(continuousDistLayout,300,210);
        window.setScene(chooseContinuousScene);
        window.show();
    }

    // Evaluation screen for Bernoulli distribution
    private void bernoulliDist(Stage window) {
        warningMessage.setText(""); // Reset invalid input warning
        GridPane bernoulliLayout = new GridPane();

        // Set up instructions for user, input fields for parameters, output fields for probabilities to be calculated
        // and buttons to evaluate query or to choose a different discrete distribution
        Text instructions = new Text("Enter a value in the first two fields.\n" +
                "Click the Calculate button to compute probabilities.");
        GridPane.setConstraints(instructions,0,0);
        Label paramLabel = new Label("Probability of success (p)");
        GridPane.setConstraints(paramLabel,0,1);
        TextField paramInput = new TextField();
        GridPane.setConstraints(paramInput,1,1);
        Label rvLabel = new Label("Bernoulli variable (x)");
        GridPane.setConstraints(rvLabel,0,2);
        TextField rvInput = new TextField();
        GridPane.setConstraints(rvInput,1,2);
        Label exactProbLabel = new Label("Exact probability P(X = x)");
        GridPane.setConstraints(exactProbLabel,0,3);
        TextField exactProbOutput = new TextField();
        GridPane.setConstraints(exactProbOutput,1,3);
        Label leftCumProbLabel = new Label("Left cumulative probability P(X <= x)");
        GridPane.setConstraints(leftCumProbLabel,0,4);
        TextField leftCumProbOutput = new TextField();
        GridPane.setConstraints(leftCumProbOutput,1,4);
        Label rightCumProbLabel = new Label("Right cumulative probability P(X >= x)");
        GridPane.setConstraints(rightCumProbLabel,0,5);
        TextField rightCumProbOutput = new TextField();
        GridPane.setConstraints(rightCumProbOutput,1,5);
        Button calculateButton = new Button("Calculate");
        GridPane.setConstraints(calculateButton,0,7);
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton,1,7);

        GridPane.setConstraints(warningMessage,0,6);

        // Make output fields uneditable for user
        exactProbOutput.setEditable(false);
        leftCumProbOutput.setEditable(false);
        rightCumProbOutput.setEditable(false);

        Border defaultBorder = paramInput.getBorder();

        calculateButton.setOnAction(e -> {
            // Reset colours of input borders, output values and warning message
            warningMessage.setText("");
            exactProbOutput.setText("");
            leftCumProbOutput.setText("");
            rightCumProbOutput.setText("");
            paramInput.setBorder(defaultBorder);
            rvInput.setBorder(defaultBorder);

            // Validate all user input parameters before evaluation
            // All input fields must be filled with real values/integers satisfying extra constrains
            // For clarity, make all fields with invalid input red
            String paramInputText = paramInput.getText();
            String rvInputText = rvInput.getText();
            boolean compulsoryFieldsFilled = !paramInputText.equals("") && !rvInputText.equals("");
            boolean paramInputValid = Helper.isProbability(paramInputText);
            boolean rvInputValid = Helper.isZeroOrOne(rvInputText);
            if (!compulsoryFieldsFilled) {
                warningMessage.setText("Error: Enter values in the first two fields!");
                if (paramInputText.equals(""))
                    setRedBorder(paramInput);
                if (rvInputText.equals(""))
                    setRedBorder(rvInput);
            } else if (!paramInputValid && !rvInputValid) {
                warningMessage.setText("Error: Probability of success must be in range [0,1]!\n" +
                        "Error: Bernoulli variable must be 0 or 1!");
                setRedBorder(paramInput);
                setRedBorder(rvInput);
            } else if(!paramInputValid) {
                warningMessage.setText("Error: Probability of success must be in range [0,1]!");
                setRedBorder(paramInput);
            } else if (!rvInputValid) {
                warningMessage.setText("Error: Bernoulli variable must be 0 or 1!");
                setRedBorder(rvInput);
            } else {
                double p = Double.parseDouble(paramInputText);
                int x = Integer.parseInt(rvInputText);
                Bernoulli queryDist = new Bernoulli(p);
                // Show results to 3 decimal digits
                exactProbOutput.setText(String.format("%.03f",queryDist.pmf(x)));
                leftCumProbOutput.setText(String.format("%.03f",queryDist.cdf(x)));
                rightCumProbOutput.setText(String.format("%.03f",queryDist.rightTail(x)));
            }
        });

        // Choose a different discrete distribution
        backButton.setOnAction(e -> {
            chooseDiscrete(window);
        });

        // Setting up how screen looks
        bernoulliLayout.setPadding(new Insets(10));
        bernoulliLayout.setVgap(8);
        bernoulliLayout.setHgap(10);
        bernoulliLayout.getChildren().addAll(instructions,paramLabel,paramInput,
                rvLabel,rvInput,exactProbLabel,exactProbOutput,leftCumProbLabel,leftCumProbOutput,
                rightCumProbLabel,rightCumProbOutput,warningMessage,calculateButton,backButton);
        Scene bernoulliScene = new Scene(bernoulliLayout,400,300);
        window.setScene(bernoulliScene);
        window.setTitle("Bernoulli");
        window.show();
    }

    // Evaluation screen for Binomial distribution
    private void binomialDist(Stage window) {
        warningMessage.setText(""); // Reset invalid input warning
        GridPane binomialLayout = new GridPane();

        // Set up instructions for user, input fields for parameters, output fields for probabilities to be calculated
        // and buttons to evaluate query or to choose a different discrete distribution
        Text instructions = new Text("Enter a value in the first three fields.\n" +
                "Click the Calculate button to compute probabilities.");
        GridPane.setConstraints(instructions,0,0);
        Label numTrialsLabel = new Label("Number of trials (n)");
        GridPane.setConstraints(numTrialsLabel,0,1);
        TextField numTrialsInput = new TextField();
        GridPane.setConstraints(numTrialsInput,1,1);
        Label probSuccessLabel = new Label("Probability of success (p)");
        GridPane.setConstraints(probSuccessLabel,0,2);
        TextField probSuccessInput = new TextField();
        GridPane.setConstraints(probSuccessInput,1,2);
        Label rvLabel = new Label("Number of successes (x)");
        GridPane.setConstraints(rvLabel,0,3);
        TextField rvInput = new TextField();
        GridPane.setConstraints(rvInput,1,3);
        Label exactProbLabel = new Label("Exact probability P(X = x)");
        GridPane.setConstraints(exactProbLabel,0,4);
        TextField exactProbOutput = new TextField();
        GridPane.setConstraints(exactProbOutput,1,4);
        Label leftCumProbLabel = new Label("Left cumulative probability P(X <= x)");
        GridPane.setConstraints(leftCumProbLabel,0,5);
        TextField leftCumProbOutput = new TextField();
        GridPane.setConstraints(leftCumProbOutput,1,5);
        Label rightCumProbLabel = new Label("Right cumulative probability P(X >= x)");
        GridPane.setConstraints(rightCumProbLabel,0,6);
        TextField rightCumProbOutput = new TextField();
        GridPane.setConstraints(rightCumProbOutput,1,6);
        Button calculateButton = new Button("Calculate");
        GridPane.setConstraints(calculateButton,0,8);
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton,1,8);

        GridPane.setConstraints(warningMessage,0,7);

        // Make output fields uneditable for user
        exactProbOutput.setEditable(false);
        leftCumProbOutput.setEditable(false);
        rightCumProbOutput.setEditable(false);

        Border defaultBorder = probSuccessInput.getBorder();
        calculateButton.setOnAction(e -> {
            // Reset input borders, output values and warning message
            warningMessage.setText("");
            exactProbOutput.setText("");
            leftCumProbOutput.setText("");
            rightCumProbOutput.setText("");
            probSuccessInput.setBorder(defaultBorder);
            numTrialsInput.setBorder(defaultBorder);
            rvInput.setBorder(defaultBorder);

            // Validate all user input parameters before evaluation
            // All input fields must be filled with real values/integers satisfying extra constrains
            // For clarity, make all fields with invalid input red
            String numTrialsInputText = numTrialsInput.getText();
            String probSuccessInputText = probSuccessInput.getText();
            String rvInputText = rvInput.getText();
            boolean compulsoryFieldsFilled = !numTrialsInputText.equals("") && !probSuccessInputText.equals("") && !rvInputText.equals("");
            boolean numTrialsInputValid = Helper.isPosInt(numTrialsInputText);
            boolean probSuccessInputValid = Helper.isProbability(probSuccessInputText);
            boolean rvInputValid = Helper.isIntBetweenZeroAndN(rvInputText, numTrialsInputText);
            boolean inputValid = compulsoryFieldsFilled && numTrialsInputValid && probSuccessInputValid && rvInputValid;
            if (inputValid) {
                int n = Integer.parseInt(numTrialsInputText);
                double p = Double.parseDouble(probSuccessInputText);
                int x = Integer.parseInt(rvInputText);
                Binomial queryDist = new Binomial(n,p);
                // Show results to 3 decimal digits
                exactProbOutput.setText(String.format("%.03f",queryDist.pmf(x)));
                leftCumProbOutput.setText(String.format("%.03f",queryDist.cdf(x)));
                rightCumProbOutput.setText(String.format("%.03f",queryDist.rightTail(x)));
            } else {
                String warningMsgText = "";
                if (!compulsoryFieldsFilled) {
                    warningMsgText = "Enter a value in the first three fields!\n";
                    if (numTrialsInputText.equals(""))
                        setRedBorder(numTrialsInput);
                    if (rvInputText.equals(""))
                        setRedBorder(rvInput);
                    if (probSuccessInputText.equals(""))
                        setRedBorder(probSuccessInput);
                } else {
                    if (!numTrialsInputValid) {
                        warningMsgText += "Error: Number of trials must be a positive integer!\n" +
                                "Hence number of successes can't be valid.\n";
                        setRedBorder(numTrialsInput);
                        setRedBorder(rvInput);
                    } else
                        if (!rvInputValid) {
                            warningMsgText += "Error: Number of successes must be an integer from 0 to n!\n";
                            setRedBorder(rvInput);
                        }
                    if (!probSuccessInputValid) {
                        warningMsgText += "Error: Probability of success must be in range [0,1]!\n";
                        setRedBorder(probSuccessInput);
                    }
                }
                warningMsgText = warningMsgText.substring(0,warningMsgText.length()-1);
                warningMessage.setText(warningMsgText);
            }
        });

        // Choose a different discrete distribution
        backButton.setOnAction(e -> {
            chooseDiscrete(window);
        });

        // Setting up how screen looks
        binomialLayout.setPadding(new Insets(10));
        binomialLayout.setVgap(8);
        binomialLayout.setHgap(10);
        binomialLayout.getChildren().addAll(instructions,numTrialsLabel,numTrialsInput,probSuccessLabel,probSuccessInput,
                rvLabel,rvInput,exactProbLabel,exactProbOutput,leftCumProbLabel,leftCumProbOutput,
                rightCumProbLabel,rightCumProbOutput,warningMessage,calculateButton,backButton);
        Scene binomialScene = new Scene(binomialLayout,430,350);
        window.setScene(binomialScene);
        window.setTitle("Binomial");
        window.show();
    }


    // Evaluation screen for Poisson distribution
    private void poissonDist(Stage window) {
        warningMessage.setText(""); // Reset invalid input warning
        GridPane poissonLayout = new GridPane();

        // Set up instructions for user, input fields for parameters, output fields for probabilities to be calculated
        // and buttons to evaluate query or to choose a different discrete distribution
        Text instructions = new Text("Enter a value in the first two fields.\n" +
                "Click the Calculate button to compute probabilities.");
        GridPane.setConstraints(instructions,0,0);
        Label paramLabel = new Label("Average rate of occurrence (\u03bb)");
        GridPane.setConstraints(paramLabel,0,1);
        TextField paramInput = new TextField();
        GridPane.setConstraints(paramInput,1,1);
        Label rvLabel = new Label("Poisson variable (x)");
        GridPane.setConstraints(rvLabel,0,2);
        TextField rvInput = new TextField();
        GridPane.setConstraints(rvInput,1,2);
        Label exactProbLabel = new Label("Exact probability P(X = x)");
        GridPane.setConstraints(exactProbLabel,0,3);
        TextField exactProbOutput = new TextField();
        GridPane.setConstraints(exactProbOutput,1,3);
        Label leftCumProbLabel = new Label("Left cumulative probability P(X <= x)");
        GridPane.setConstraints(leftCumProbLabel,0,4);
        TextField leftCumProbOutput = new TextField();
        GridPane.setConstraints(leftCumProbOutput,1,4);
        Label rightCumProbLabel = new Label("Right cumulative probability P(X >= x)");
        GridPane.setConstraints(rightCumProbLabel,0,5);
        TextField rightCumProbOutput = new TextField();
        GridPane.setConstraints(rightCumProbOutput,1,5);
        Button calculateButton = new Button("Calculate");
        GridPane.setConstraints(calculateButton,0,7);
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton,1,7);

        GridPane.setConstraints(warningMessage,0,6);

        // Make output fields uneditable for user
        exactProbOutput.setEditable(false);
        leftCumProbOutput.setEditable(false);
        rightCumProbOutput.setEditable(false);

        Border defaultBorder = paramInput.getBorder();
        calculateButton.setOnAction(e -> {
            // Reset input borders, output values and warning message
            warningMessage.setText("");
            exactProbOutput.setText("");
            leftCumProbOutput.setText("");
            rightCumProbOutput.setText("");
            paramInput.setBorder(defaultBorder);
            rvInput.setBorder(defaultBorder);

            // Validate all user input parameters before evaluation
            // All input fields must be filled with real values/integers satisfying extra constrains
            // For clarity, make all fields with invalid input red
            String paramInputText = paramInput.getText();
            String rvInputText = rvInput.getText();
            boolean compulsoryFieldsFilled = !paramInputText.equals("") && !rvInputText.equals("");
            boolean paramInputValid = Helper.isPos(paramInputText);
            boolean rvInputValid = Helper.isNonNegInt(rvInputText);
            if (!compulsoryFieldsFilled) {
                warningMessage.setText("Error: Enter values in first two fields!");
                if (paramInputText.equals(""))
                    setRedBorder(paramInput);
                if (rvInputText.equals(""))
                    setRedBorder(rvInput);
            } else if (!paramInputValid && !rvInputValid) {
                warningMessage.setText("Error: Average rate must be positive!\n" +
                        "Error: Poisson variable must be a nonnegative integer!");
                setRedBorder(paramInput);
                setRedBorder(rvInput);
            } else if(!paramInputValid) {
                warningMessage.setText("Error: Average rate must be positive!");
                setRedBorder(paramInput);
            } else if (!rvInputValid) {
                warningMessage.setText("Error: Poisson variable must be a non-negative integer!");
                setRedBorder(rvInput);
            } else {
                warningMessage.setText("");
                double lambda = Double.parseDouble(paramInputText);
                int x = Integer.parseInt(rvInputText);
                Poisson queryDist = new Poisson(lambda);
                // Show results to 3 decimal digits
                exactProbOutput.setText(String.format("%.03f",queryDist.pmf(x)));
                leftCumProbOutput.setText(String.format("%.03f",queryDist.cdf(x)));
                rightCumProbOutput.setText(String.format("%.03f",queryDist.rightTail(x)));
            }
        });
        // Choosing a different discrete distribution
        backButton.setOnAction(e -> {
            chooseDiscrete(window);
        });
        // Setting up how screen looks
        poissonLayout.setPadding(new Insets(10));
        poissonLayout.setVgap(8);
        poissonLayout.setHgap(10);
        poissonLayout.getChildren().addAll(instructions,paramLabel,paramInput,
                rvLabel,rvInput,exactProbLabel,exactProbOutput,leftCumProbLabel,leftCumProbOutput,
                rightCumProbLabel,rightCumProbOutput,warningMessage,calculateButton,backButton);

        Scene poissonScene = new Scene(poissonLayout,400,300);
        window.setScene(poissonScene);
        window.setTitle("Poisson");
        window.show();
    }

    // Evaluation screen for Geometric distribution
    private void geometricDist(Stage window) {
        warningMessage.setText(""); // Reset invalid input warning
        GridPane geometricLayout = new GridPane();

        // Set up instructions for user, input fields for parameters, output fields for probabilities to be calculated
        // and buttons to evaluate query or to choose a different discrete distribution
        Text instructions = new Text("Enter a value in the first two fields.\n" +
                "Click the Calculate button to compute probabilities.");
        GridPane.setConstraints(instructions,0,0);
        Label paramLabel = new Label("Probability of success (p)");
        GridPane.setConstraints(paramLabel,0,1);
        TextField paramInput = new TextField();
        GridPane.setConstraints(paramInput,1,1);
        Label rvLabel = new Label("Trial of first success (x)");
        GridPane.setConstraints(rvLabel,0,2);
        TextField rvInput = new TextField();
        GridPane.setConstraints(rvInput,1,2);
        Label exactProbLabel = new Label("Exact probability P(X = x)");
        GridPane.setConstraints(exactProbLabel,0,3);
        TextField exactProbOutput = new TextField();
        GridPane.setConstraints(exactProbOutput,1,3);
        Label leftCumProbLabel = new Label("Left cumulative probability P(X <= x)");
        GridPane.setConstraints(leftCumProbLabel,0,4);
        TextField leftCumProbOutput = new TextField();
        GridPane.setConstraints(leftCumProbOutput,1,4);
        Label rightCumProbLabel = new Label("Right cumulative probability P(X >= x)");
        GridPane.setConstraints(rightCumProbLabel,0,5);
        TextField rightCumProbOutput = new TextField();
        GridPane.setConstraints(rightCumProbOutput,1,5);
        Button calculateButton = new Button("Calculate");
        GridPane.setConstraints(calculateButton,0,7);
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton,1,7);

        GridPane.setConstraints(warningMessage,0,6);

        // Make output fields uneditable for user
        exactProbOutput.setEditable(false);
        leftCumProbOutput.setEditable(false);
        rightCumProbOutput.setEditable(false);

        Border defaultBorder = paramInput.getBorder();
        calculateButton.setOnAction(e -> {
            // Reset input borders, output values and warning message
            warningMessage.setText("");
            exactProbOutput.setText("");
            leftCumProbOutput.setText("");
            rightCumProbOutput.setText("");
            paramInput.setBorder(defaultBorder);
            rvInput.setBorder(defaultBorder);

            // Validate all user input parameters before evaluation
            // All input fields must be filled with real values/integers satisfying extra constrains
            // For clarity, make all fields with invalid input red
            String paramInputText = paramInput.getText();
            String rvInputText = rvInput.getText();
            boolean compulsoryFieldsFilled = !paramInputText.equals("") && !rvInputText.equals("");
            boolean paramInputValid = Helper.isProbabilityNotZero(paramInputText);
            boolean rvInputValid = Helper.isPosInt(rvInputText);
            if (!compulsoryFieldsFilled) {
                warningMessage.setText("Error: Enter a value in the first two fields!");
                if (paramInputText.equals(""))
                   setRedBorder(paramInput);
                if (rvInputText.equals(""))
                    setRedBorder(rvInput);
            } else if (!paramInputValid && !rvInputValid) {
                warningMessage.setText("Error: Probability of success must be in range (0,1]!\n" +
                        "Error: Trial of first success must be a positive integer!");
                setRedBorder(paramInput);
                setRedBorder(rvInput);
            } else if(!paramInputValid) {
                warningMessage.setText("Error: Probability of success must be in range (0,1]!");
                setRedBorder(paramInput);
            } else if (!rvInputValid) {
                warningMessage.setText("Error: Trial of first success must be a positive integer!");
                setRedBorder(rvInput);
            } else {
                warningMessage.setText("");
                double p = Double.parseDouble(paramInputText);
                int x = Integer.parseInt(rvInputText);
                Geometric queryDist = new Geometric(p);
                // Show results to 3 decimal digits
                exactProbOutput.setText(String.format("%.03f",queryDist.pmf(x)));
                leftCumProbOutput.setText(String.format("%.03f",queryDist.cdf(x)));
                rightCumProbOutput.setText(String.format("%.03f",queryDist.rightTail(x)));
            }
        });

        // Choose a different discrete distribution
        backButton.setOnAction(e -> {
            chooseDiscrete(window);
        });
        // Setting up how screen looks
        geometricLayout.setPadding(new Insets(10));
        geometricLayout.setVgap(8);
        geometricLayout.setHgap(10);
        geometricLayout.getChildren().addAll(instructions,paramLabel,paramInput,
                rvLabel,rvInput,exactProbLabel,exactProbOutput,leftCumProbLabel,leftCumProbOutput,
                rightCumProbLabel,rightCumProbOutput,warningMessage,calculateButton,backButton);

        Scene geometricScene = new Scene(geometricLayout,400,300);
        window.setScene(geometricScene);
        window.setTitle("Geometric");
        window.show();
    }

    // Evaluation screen for Uniform distribution
    private void uniformDist(Stage window) {
        warningMessage.setText(""); // Reset invalid input warning
        GridPane uniformLayout = new GridPane();

        // Set up instructions for user, input fields for parameters, output fields for probabilities to be calculated
        // and buttons to evaluate query or to choose a different continuous distribution
        Text instructions = new Text("Enter a value in the first three fields.\n" +
                "Click the Calculate button to compute probabilities.");
        GridPane.setConstraints(instructions,0,0);
        Label lowBoundLabel = new Label("Lower bound (a)");
        GridPane.setConstraints(lowBoundLabel,0,1);
        TextField lowBoundInput = new TextField();
        GridPane.setConstraints(lowBoundInput,1,1);
        Label upBoundLabel = new Label("Upper bound (b)");
        GridPane.setConstraints(upBoundLabel,0,2);
        TextField upBoundInput = new TextField();
        GridPane.setConstraints(upBoundInput,1,2);
        Label rvLabel = new Label("Uniform variable (x)");
        GridPane.setConstraints(rvLabel,0,3);
        TextField rvInput = new TextField();
        GridPane.setConstraints(rvInput,1,3);
        Label leftCumProbLabel = new Label("Left cumulative probability P(X <= x)");
        GridPane.setConstraints(leftCumProbLabel,0,4);
        TextField leftCumProbOutput = new TextField();
        GridPane.setConstraints(leftCumProbOutput,1,4);
        Label rightCumProbLabel = new Label("Right cumulative probability P(X >= x)");
        GridPane.setConstraints(rightCumProbLabel,0,5);
        TextField rightCumProbOutput = new TextField();
        GridPane.setConstraints(rightCumProbOutput,1,5);
        Button calculateButton = new Button("Calculate");
        GridPane.setConstraints(calculateButton,0,7);
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton,1,7);

        GridPane.setConstraints(warningMessage,0,6);

        // Make output fields uneditable for user
        leftCumProbOutput.setEditable(false);
        rightCumProbOutput.setEditable(false);

        Border defaultBorder = lowBoundInput.getBorder();
        calculateButton.setOnAction(e -> {
            // Reset input borders and output values
            leftCumProbOutput.setText("");
            rightCumProbOutput.setText("");
            lowBoundInput.setBorder(defaultBorder);
            upBoundInput.setBorder(defaultBorder);
            rvInput.setBorder(defaultBorder);

            // Validate all user input parameters before evaluation
            // All input fields must be filled with real values and lower bound must be less than upper bound
            // For clarity, make all fields with invalid input red
            String lowBoundInputText = lowBoundInput.getText();
            String upBoundInputText = upBoundInput.getText();
            String rvInputText = rvInput.getText();
            boolean compulsoryFieldsFilled = !lowBoundInputText.equals("") && !upBoundInputText.equals("") && !rvInputText.equals("");
            boolean lowBoundInputValid = Helper.isReal(lowBoundInputText);
            boolean upBoundInputValid = Helper.isGreaterThan(upBoundInputText, lowBoundInputText);
            boolean rvInputValid = Helper.isBetween(rvInputText,lowBoundInputText,upBoundInputText);
            boolean inputValid = compulsoryFieldsFilled && lowBoundInputValid && upBoundInputValid && rvInputValid;
            if (inputValid) {
                double a = Double.parseDouble(lowBoundInputText);
                double b = Double.parseDouble(upBoundInputText);
                double x = Double.parseDouble(rvInputText);
                Uniform queryDist = new Uniform(a,b);
                // Show results to 3 decimal digits
                leftCumProbOutput.setText(String.format("%.03f",queryDist.cdf(x)));
                rightCumProbOutput.setText(String.format("%.03f",queryDist.rightTail(x)));
            } else {
                String warningMsgText = "";
                if (!compulsoryFieldsFilled) {
                    warningMsgText = "Error: Enter a value in the first three fields!\n";
                    if (lowBoundInputText.equals(""))
                        setRedBorder(lowBoundInput);
                    if (upBoundInputText.equals(""))
                        setRedBorder(upBoundInput);
                    if (rvInputText.equals(""))
                        setRedBorder(rvInput);
                } else {
                    // If any bound is not a real number, it makes no sense to check upper bound > lower bound
                    // Make border of both input fields red
                    if (!lowBoundInputValid) {
                        warningMsgText += "Error: Lower bound must be a real number!\nHence upper bound " +
                                "and uniform variable can't be checked for validity properly.\n";
                        setRedBorder(lowBoundInput);
                        setRedBorder(upBoundInput);
                        setRedBorder(rvInput);
                    } else {
                        if (!upBoundInputValid) {
                            warningMsgText += "Error: Upper bound must be a real number greater than lower bound!\n" +
                                    "Hence uniform variable can't be checked for validity properly.\n";
                            setRedBorder(upBoundInput);
                            setRedBorder(rvInput);
                        } else {
                            // It must be the case that uniform variable input is not valid
                            warningMsgText += "Error: Uniform variable must be a real number between the two bounds!\n";
                            setRedBorder(rvInput);
                        }
                    }
                }
                warningMsgText = warningMsgText.substring(0,warningMsgText.length()-1);
                warningMessage.setText(warningMsgText);
            }
        });

        // Choose a different continuous distribution
        backButton.setOnAction(e -> {
            chooseContinuous(window);
        });
        // Setting up how screen looks
        uniformLayout.setPadding(new Insets(10));
        uniformLayout.setVgap(8);
        uniformLayout.setHgap(10);
        uniformLayout.getChildren().addAll(instructions,lowBoundLabel,lowBoundInput,upBoundLabel,upBoundInput,
                rvLabel,rvInput,leftCumProbLabel,leftCumProbOutput,
                rightCumProbLabel,rightCumProbOutput,warningMessage,calculateButton,backButton);

        Scene uniformScene = new Scene(uniformLayout,520,300);
        window.setScene(uniformScene);
        window.setTitle("Uniform");
        window.show();
    }

    // Evaluation screen for Exponential distribution
    private void exponentialDist(Stage window) {
        warningMessage.setText(""); // Reset invalid input warning
        // Setting up layout
        GridPane exponentialLayout = new GridPane();

        // Set up instructions for user, input fields for parameters, output fields for probabilities to be calculated
        // and buttons to evaluate query or to choose a different continuous distribution
        Text instructions = new Text("Enter a value in the first three fields.\n" +
                "Click the Calculate button to compute probabilities.");
        GridPane.setConstraints(instructions,0,0);
        Label paramLabel = new Label("Rate parameter (\u03bb)");
        GridPane.setConstraints(paramLabel,0,1);
        TextField paramInput = new TextField();
        GridPane.setConstraints(paramInput,1,1);
        Label rvLabel = new Label("Exponential variable (x)");
        GridPane.setConstraints(rvLabel,0,2);
        TextField rvInput = new TextField();
        GridPane.setConstraints(rvInput,1,2);
        Label leftCumProbLabel = new Label("Left cumulative probability P(X <= x)");
        GridPane.setConstraints(leftCumProbLabel,0,3);
        TextField leftCumProbOutput = new TextField();
        GridPane.setConstraints(leftCumProbOutput,1,3);
        Label rightCumProbLabel = new Label("Right cumulative probability P(X >= x)");
        GridPane.setConstraints(rightCumProbLabel,0,4);
        TextField rightCumProbOutput = new TextField();
        GridPane.setConstraints(rightCumProbOutput,1,4);
        Button calculateButton = new Button("Calculate");
        GridPane.setConstraints(calculateButton,0,6);
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton,1,6);

        GridPane.setConstraints(warningMessage,0,5);

        // Make output fields uneditable for user
        leftCumProbOutput.setEditable(false);
        rightCumProbOutput.setEditable(false);

        Border defaultBorder = paramInput.getBorder();
        calculateButton.setOnAction(e -> {
            // Reset input borders, output values and warning message
            warningMessage.setText("");
            leftCumProbOutput.setText("");
            rightCumProbOutput.setText("");
            paramInput.setBorder(defaultBorder);
            rvInput.setBorder(defaultBorder);

            // Validate all user input parameters before evaluation
            // All input fields must be filled with positive real numbers
            // For clarity, make all fields with invalid input red
            String paramInputText = paramInput.getText();
            String rvInputText = rvInput.getText();
            boolean compulsoryFieldsFilled = !paramInputText.equals("") && !rvInputText.equals("");
            boolean paramInputValid = Helper.isPos(paramInputText);
            boolean rvInputValid = Helper.isPos(rvInputText);
            if (!compulsoryFieldsFilled) {
                warningMessage.setText("Error: Enter a value in the first two fields!");
                if (paramInputText.equals(""))
                    setRedBorder(paramInput);
                if (rvInputText.equals(""))
                    setRedBorder(rvInput);
            } else if (!paramInputValid && !rvInputValid) {
                warningMessage.setText("Error: Rate parameter must be positive!\n" +
                        "Error: Exponential variable must be positive!");
                setRedBorder(paramInput);
                setRedBorder(rvInput);
            } else if(!paramInputValid) {
                warningMessage.setText("Error: Rate parameter must be positive!");
                setRedBorder(paramInput);
            } else if (!rvInputValid) {
                warningMessage.setText("Error: Exponential variable must be positive!");
                setRedBorder(rvInput);
            } else {
                warningMessage.setText("");
                double lambda = Double.parseDouble(paramInputText);
                int x = Integer.parseInt(rvInputText);
                Poisson queryDist = new Poisson(lambda);
                // Show results to 3 decimal digits
                leftCumProbOutput.setText(String.format("%.03f",queryDist.cdf(x)));
                rightCumProbOutput.setText(String.format("%.03f",queryDist.rightTail(x)));
            }
        });
        backButton.setOnAction(e -> {
            chooseContinuous(window);
        });
        // Setting up how screen looks
        exponentialLayout.setPadding(new Insets(10));
        exponentialLayout.setVgap(8);
        exponentialLayout.setHgap(10);

        exponentialLayout.getChildren().addAll(instructions,paramLabel,paramInput,
                rvLabel,rvInput,leftCumProbLabel,leftCumProbOutput,
                rightCumProbLabel,rightCumProbOutput,warningMessage,calculateButton,backButton);

        Scene exponentialScene = new Scene(exponentialLayout,370,270);
        window.setScene(exponentialScene);
        window.setTitle("Exponential");
        window.show();
    }

    // Evaluation screen for Gamma distribution
    private void gammaDist(Stage window) {
        warningMessage.setText(""); // Reset invalid input warning
        GridPane gammaLayout = new GridPane();

        // Set up instructions for user, input fields for parameters, output fields for probabilities to be calculated
        // and buttons to evaluate query or to choose a different continuous distribution
        Text instructions = new Text("Enter a value in the first three fields.\n" +
                "Click the Calculate button to compute probabilities.");
        GridPane.setConstraints(instructions,0,0);
        Label shapeLabel = new Label("Shape parameter (\u03b1)");
        GridPane.setConstraints(shapeLabel,0,1);
        TextField shapeInput = new TextField();
        GridPane.setConstraints(shapeInput,1,1);
        Label rateLabel = new Label("Rate parameter (\u03b2)");
        GridPane.setConstraints(rateLabel,0,2);
        TextField rateInput = new TextField();
        GridPane.setConstraints(rateInput,1,2);
        Label rvLabel = new Label("Gamma variable (x)");
        GridPane.setConstraints(rvLabel,0,3);
        TextField rvInput = new TextField();
        GridPane.setConstraints(rvInput,1,3);
        Label leftCumProbLabel = new Label("Left cumulative probability P(X <= x)");
        GridPane.setConstraints(leftCumProbLabel,0,4);
        TextField leftCumProbOutput = new TextField();
        GridPane.setConstraints(leftCumProbOutput,1,4);
        Label rightCumProbLabel = new Label("Right cumulative probability P(X >= x)");
        GridPane.setConstraints(rightCumProbLabel,0,5);
        TextField rightCumProbOutput = new TextField();
        GridPane.setConstraints(rightCumProbOutput,1,5);
        Button calculateButton = new Button("Calculate");
        GridPane.setConstraints(calculateButton,0,7);
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton,1,7);

        GridPane.setConstraints(warningMessage,0,6);

        // Make output fields uneditable for user
        leftCumProbOutput.setEditable(false);
        rightCumProbOutput.setEditable(false);

        Border defaultBorder = shapeInput.getBorder();
        calculateButton.setOnAction(e -> {
            // Reset input borders, output values and warning message
            warningMessage.setText("");
            leftCumProbOutput.setText("");
            rightCumProbOutput.setText("");
            shapeInput.setBorder(defaultBorder);
            rateInput.setBorder(defaultBorder);
            rvInput.setBorder(defaultBorder);

            // Validate all user input parameters before evaluation
            // All input fields must be filled with positive real values
            // For clarity, make all fields with invalid input red
            String shapeInputText = shapeInput.getText();
            String rateInputText = rateInput.getText();
            String rvInputText = rvInput.getText();
            boolean compulsoryFieldsFilled = !shapeInputText.equals("") && !rateInputText.equals("") && !rvInputText.equals("");
            boolean shapeInputValid = Helper.isPos(shapeInputText);
            boolean rateInputValid = Helper.isPos(rateInputText);
            boolean rvInputValid = Helper.isPos(rvInputText);
            boolean inputValid = compulsoryFieldsFilled && shapeInputValid && rateInputValid && rvInputValid;
            if (inputValid) {
                double alpha = Double.parseDouble(shapeInputText);
                double beta = Double.parseDouble(rateInputText);
                double x = Double.parseDouble(rvInputText);
                Gamma queryDist = new Gamma(alpha,beta);
                // Show results to 3 decimal digits
                leftCumProbOutput.setText(String.format("%.03f",queryDist.cdf(x)));
                rightCumProbOutput.setText(String.format("%.03f",queryDist.rightTail(x)));
            } else {
                String warningMsgText = "";
                if (!compulsoryFieldsFilled) {
                    warningMsgText = "Error: Enter a value in the first three fields!\n";
                    if (shapeInputText.equals(""))
                        setRedBorder(shapeInput);
                    if (rateInputText.equals(""))
                        setRedBorder(rateInput);
                    if (rvInputText.equals(""))
                        setRedBorder(rvInput);
                } else {
                    if (!shapeInputValid) {
                        warningMsgText += "Error: Shape parameter must be positive!\n";
                        setRedBorder(shapeInput);
                    }
                    if (!rateInputValid) {
                        warningMsgText += "Error: Rate parameter must be positive!\n";
                        setRedBorder(rateInput);
                    }
                    if (!rvInputValid) {
                        warningMsgText += "Error: Gamma variable must be positive!\n";
                        setRedBorder(rvInput);
                    }
                }
                warningMsgText = warningMsgText.substring(0,warningMsgText.length()-1);
                warningMessage.setText(warningMsgText);
            }
        });
        backButton.setOnAction(e -> {
            chooseContinuous(window);
        });

        // Setting up how screen looks
        gammaLayout.setPadding(new Insets(10));
        gammaLayout.setVgap(8);
        gammaLayout.setHgap(10);
        gammaLayout.getChildren().addAll(instructions,shapeLabel,shapeInput,rateLabel,rateInput,
                rvLabel,rvInput,leftCumProbLabel,leftCumProbOutput,
                rightCumProbLabel,rightCumProbOutput,warningMessage,calculateButton,backButton);

        Scene gammaScene = new Scene(gammaLayout,370,320);
        window.setScene(gammaScene);
        window.setTitle("Gamma");
        window.show();
    }

    private void normalDist(Stage window) {
        warningMessage.setText(""); // Reset invalid input warning
        GridPane normalLayout = new GridPane();

        // Set up instructions for user, input fields for parameters, output fields for probabilities to be calculated
        // and buttons to evaluate query or to choose a different continuous distribution
        Text instructions = new Text("Enter a value in the first three fields.\n" +
                "Click the Calculate button to compute probabilities.");
        GridPane.setConstraints(instructions,0,0);
        Label expectationLabel = new Label("Expectation");
        GridPane.setConstraints(expectationLabel,0,1);
        TextField expectationInput = new TextField();
        GridPane.setConstraints(expectationInput,1,1);
        Label varianceLabel = new Label("Variance");
        GridPane.setConstraints(varianceLabel,0,2);
        TextField varianceInput = new TextField();
        GridPane.setConstraints(varianceInput,1,2);
        Label rvLabel = new Label("Normal variable (x)");
        GridPane.setConstraints(rvLabel,0,3);
        TextField rvInput = new TextField();
        GridPane.setConstraints(rvInput,1,3);
        Label leftCumProbLabel = new Label("Left cumulative probability P(X <= x)");
        GridPane.setConstraints(leftCumProbLabel,0,4);
        TextField leftCumProbOutput = new TextField();
        GridPane.setConstraints(leftCumProbOutput,1,4);
        Label rightCumProbLabel = new Label("Right cumulative probability P(X >= x)");
        GridPane.setConstraints(rightCumProbLabel,0,5);
        TextField rightCumProbOutput = new TextField();
        GridPane.setConstraints(rightCumProbOutput,1,5);
        Button calculateButton = new Button("Calculate");
        GridPane.setConstraints(calculateButton,0,7);
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton,1,7);

        GridPane.setConstraints(warningMessage,0,6);

        // Make output fields uneditable for user
        leftCumProbOutput.setEditable(false);
        rightCumProbOutput.setEditable(false);

        Border defaultBorder = expectationInput.getBorder();
        calculateButton.setOnAction(e -> {
            // Reset input borders, output values and warning message
            warningMessage.setText("");
            leftCumProbOutput.setText("");
            rightCumProbOutput.setText("");
            expectationInput.setBorder(defaultBorder);
            varianceInput.setBorder(defaultBorder);
            rvInput.setBorder(defaultBorder);

            // Validate all user input parameters before evaluation
            // All input fields must be filled with real values
            // For clarity, make all fields with invalid input red
            String expectationInputText = expectationInput.getText();
            String varianceInputText = varianceInput.getText();
            String rvInputText = rvInput.getText();
            boolean compulsoryFieldsFilled = !expectationInputText.equals("") && !varianceInputText.equals("") && !rvInputText.equals("");
            boolean expectationInputValid = Helper.isReal(expectationInputText);
            boolean varianceInputValid = Helper.isPos(varianceInputText);
            boolean rvInputValid = Helper.isReal(rvInputText);
            boolean inputValid = compulsoryFieldsFilled && expectationInputValid && varianceInputValid && rvInputValid;
            if (inputValid) {
                double exp = Double.parseDouble(expectationInputText);
                double var = Double.parseDouble(varianceInputText);
                double x = Double.parseDouble(rvInputText);
                Normal queryDist = new Normal(exp,var);
                // Show results to 3 decimal digits
                leftCumProbOutput.setText(String.format("%.03f",queryDist.cdf(x)));
                rightCumProbOutput.setText(String.format("%.03f",queryDist.rightTail(x)));
            } else {
                String warningMsgText = "";
                if (!compulsoryFieldsFilled) {
                    warningMsgText = "Error: Enter a value in the first three fields!\n";
                    if (expectationInputText.equals(""))
                        setRedBorder(expectationInput);
                    if (varianceInputText.equals(""))
                        setRedBorder(varianceInput);
                    if (rvInputText.equals(""))
                        setRedBorder(rvInput);
                } else {
                    if (!expectationInputValid) {
                        warningMsgText += "Error: Expectation must be a real number!\n";
                        setRedBorder(expectationInput);
                    }
                    if (!varianceInputValid) {
                        warningMsgText += "Error: Variance must be positive!\n";
                        setRedBorder(varianceInput);
                    }
                    if (!rvInputValid) {
                        warningMsgText += "Error: Normal variable must be a real number!\n";
                        setRedBorder(rvInput);
                    }
                }
                warningMsgText = warningMsgText.substring(0,warningMsgText.length()-1);
                warningMessage.setText(warningMsgText);
            }
        });

        // Choose a different continuous distribution
        backButton.setOnAction(e -> {
            chooseContinuous(window);
        });
        // Setting how screen looks
        normalLayout.setPadding(new Insets(10));
        normalLayout.setVgap(8);
        normalLayout.setHgap(10);
        normalLayout.getChildren().addAll(instructions,expectationLabel,expectationInput,varianceLabel,varianceInput,
                rvLabel,rvInput,leftCumProbLabel,leftCumProbOutput,
                rightCumProbLabel,rightCumProbOutput,warningMessage,calculateButton,backButton);

        Scene normalScene = new Scene(normalLayout,370,320);
        window.setScene(normalScene);
        window.setTitle("Normal");
        window.show();
    }

    // Confirm that user really wants to quit program before doing so
    private void closeProgram(Stage window) {
        boolean answer = ConfirmWindow.display("Confirm exit", "Are you sure you want to exit?");
        if (answer) {
            window.close();
        }
    }

    // Set red border of textfield (with invalid input)
    private void setRedBorder(TextField field) {
        field.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }
}
