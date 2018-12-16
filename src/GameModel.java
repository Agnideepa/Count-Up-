/**
 * This class represents the model in an MVC pattern. It stores all the information about the current state of the
 * application.
 * @Agnideepa
 * @14/12/2018
 */

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameModel {
    private Calculation currentCalculation;
    private String calculationKey;

    private String question;
    private String comment;

    private List<Integer> choices = new LinkedList<>();

    private List<Integer> answers = new LinkedList<>();
    private int correctIndex;

    private Map<String,Expertise> calculationState = new HashMap<>();

    private int operand1;
    private int operand2;

    private List<Calculation>  calculations;

    public GameModel() {
        calculations = new LinkedList<>();
        calculations.add(new Addition());
        calculations.add(new Subtraction());
        calculations.add(new Multiplication());

        for(Calculation i : calculations)
        {
            calculationState.put(i.getName(),new Expertise());
        }

        System.out.println(calculationState.toString());

        comment = "Welcome!Select two numbers. Then select an answer from the options that will appear..";
        currentCalculation = new Addition();
        calculationKey = "Addition";

        question = currentCalculation.getSymbol();

        operand1 = 0;
        operand2 = 0;
    }

    //In case the user would like to add Calculation objects themselves
    public GameModel(List<Calculation> selection){
        calculations = selection;

        for(Calculation i : calculations)
        {
            calculationState.put(i.getName(),new Expertise());
        }

        comment = "Welcome!Select two numbers. Then select an answer from the options that will appear..";
        currentCalculation = new Addition();
        calculationKey = "Addition";

        question = currentCalculation.getSymbol();

        operand1 = 0;
        operand2 = 0;
    }

    /**
     * The comment tells the user how to use the application at start time and otherwise simply tells the user their
     * level and score.
     */
    public String getComment() {
        return comment;
    }

    /**
     * The currentCalculation is a Calculation object representing the current operation that the user is working on.
     */
    public Calculation getCurrentCalculation() {
        return currentCalculation;
    }

    /**
     * The calculationKey is the name of the current operation that the user is working on.
     */
    public String getCalculationKey() {
        return calculationKey;
    }

    /**
     * The question contains the current status of the question the user is making and displays any selected operands
     * and the symbol of the current operation the user is working on.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * The choices is basically a list of the choices that will be displayed to the user.
     */
    public List<Integer> getChoices() {
        return choices;
    }

    /**
     * The answers is basically a list of the answers that will be displayed to the user.
     */
    public List<Integer> getAnswers() {
        return answers;
    }

    /**
     * The correctIndex represents the index of the correct answer in answers
     */
    public int getCorrectIndex() {
        return correctIndex;
    }

    /**
     * The calculationState contains the names of the operations as keys and Expertise objects as values representing
     * their proficiency in that particular operation.
     */
    public Map<String, Expertise> getCalculationState() {
        return calculationState;
    }

    /**
     * The first operand to be worked on is operand1.
     */
    public int getOperand1() {
        return operand1;
    }

    /**
     * The seoond operand to be worked on is operand2.
     */
    public int getOperand2() {
        return operand2;
    }

    /**
     * calculations is a list of the available Calculation objects.
     */
    public List<Calculation> getCalculations() {
        return calculations;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCurrentCalculation(Calculation currentCalculation) {
        this.currentCalculation = currentCalculation;
    }

    public void setCalculationKey(String calculationKey) {
        this.calculationKey = calculationKey;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    //A method to create new choices and then store them in choice using a RandomChoiceGenerator
    public void setChoices(){
        this.choices = RandomChoiceGenerator.calculate(calculationState.get(calculationKey).getLevel());
    }

    //A method to create new choices and new correct index and then update state using a RandomAnswerGenerator
    public void setAnswers() {
        this.correctIndex = (int)(Math.random()*8);
        int correctAnswer = currentCalculation.perform(operand1,operand2);
        this.answers = RandomAnswerGenerator.calculate(correctAnswer, correctIndex);
    }

    public void setCalculationState(Map<String, Expertise> calculationState) {
        this.calculationState = calculationState;
    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public void setCalculations(List<Calculation> calculations) {
        this.calculations = calculations;
    }

    //Method to update the comment whenever there is some action
    public void commentUpdate(){
        comment = "You are at level "+ calculationState.get(calculationKey).getLevel()+" and your score is "+calculationState.get(calculationKey).getScore()+".";
    }

    //Method to update state when the first choice is selected.
    public void firstChoiceSelected(String firstChoice){
        //Updating operand1 to take the value of the first choice
        try{
            operand1 = Integer.parseInt(firstChoice);
        }
        catch(NumberFormatException e){
            System.out.println("Enter a number.");
        }

        //Updating the question and comment
        question = operand1+" "+currentCalculation.getSymbol();
        commentUpdate();
    }

    //Method to update state when the second choice is selected.
    public void secondChoiceSelected(String secondChoice){
        //Updating operand2 to take the value of the second choice
        try{
            operand2 = Integer.parseInt(secondChoice);
        }
        catch(NumberFormatException e){
            System.out.println("Enter a number.");
        }
        //Creating new answers
        setAnswers();

        //Updating the question and comment
        question = operand1+" "+currentCalculation.getSymbol()+" "+operand2;
        commentUpdate();
    }

    //Method to update state when the correct answer is selected.
    public void correctAnswerSelected(){
        //Updating the Expertise object linked to the current operation
        calculationState.get(calculationKey).correctAnswer();

        //Creating new choices
        setChoices();

        //Restoring values of operands to default
        operand1 = 0;
        operand2 = 0;

        //Updating the question and comment
        question = currentCalculation.getSymbol();
        commentUpdate();
    }

    //Method to update state when the incorrect answer is selected.
    public void incorrectAnswerSelected(){
        ////Updating the Expertise object linked to the current operation
        calculationState.get(calculationKey).incorrectAnswer();

        //Updating the comment
        commentUpdate();
    }

    //Method to update state when a new operation is selected.
    public void operationSelected(String calculation){
        try{
            //Updating currentCalculation and calculationKey
            currentCalculation = (Calculation) Class.forName(calculation).getDeclaredConstructor().newInstance();
            calculationKey = calculation;

            //Restoring values of operands to default
            operand1 = 0;
            operand2 = 0;
        }
        catch(ClassNotFoundException|NoSuchMethodException|InstantiationException|IllegalAccessException| InvocationTargetException exception){
            System.out.println("These exceptions will not occur.");
        }

        ////Updating the question and comment
        question = currentCalculation.getSymbol();
        commentUpdate();
    }
}
