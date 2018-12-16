/**
 * This class represents the controller in an MVC pattern. An object of this class is passed around to the ChoicePanel
 * and AnswerPanel objects in order for them to update/notify it.
 * @Agnideepa
 * @14/12/2018
 */

public class GameController implements Controlling{
    private GameView view;
    private GameModel model;

    /**
     * The constructors takes in a view and a model and starts the application
     * @param view - The user interface
     * @param model - The state
     */
    public GameController(GameView view, GameModel model) {
        this.view = view;
        this.model = model;
        start();
    }

    /**
     * Updates model and view when the first choice is selected.
     * @param firstChoice - The choice that is selected. It represents operand1 in the model.
     */
    public void firstChoiceSelected(String firstChoice){
        //The model is notified when the first choice is selected.
        model.firstChoiceSelected(firstChoice);
        //The view updates its questions and comments accordingly.
        view.setQuestion(model.getQuestion());
        view.setComment(model.getComment());
    }

    /**
     * Updates model and view when the second choice is selected.
     * @param secondChoice - The choice that is selected. It represents operand2 in the model.
     */
    public void secondChoiceSelected(String secondChoice){
        //The model is notified when the second choice is selected and is asked to create answers since it is time to
        //to an AnswerPanel.
        model.secondChoiceSelected(secondChoice);
        model.setAnswers();
        //The view is made to create an AnswerPanel and update its components accordingly.
        view.setAnswerPanel(model.getAnswers(),model.getCorrectIndex(),this);
        view.setQuestion(model.getQuestion());
        view.setComment(model.getComment());
    }

    /**
     * Updates model and view when the correct answer is selected.
     * */
    public void correctAnswerSelected(){
        //The model updates accordingly and then creates new choices since it is now time to switch to a ChoicePanel.
        model.correctAnswerSelected();
        model.setChoices();
        //The view is made to create a ChoicePanel and update its components accordingly.
        view.setChoicePanel(model.getChoices(),this);
        view.setQuestion(model.getQuestion());
        view.setComment(model.getComment());
    }

    /**
     * Updates model and view when the incorrect answer is selected.
     * */
    public void incorrectAnswerSelected(){
        //The model updates accordingly.
        model.incorrectAnswerSelected();
        //The view updates its commentPanel since it must display the new score.
        view.setComment(model.getComment());
    }

    /**
     * Updates model and view when the user decides to switch to a different operation
     * @param calculation - Represents the current operation the user wants to work on
     */
    public void operationSelected(String calculation){
        //The model is notified that the operation has changed and that it has to set new choices.
        model.operationSelected(calculation);
        model.setChoices();
        //The view cretaes a new ChoicePsnel and updates its components accordingly.
        view.setChoicePanel(model.getChoices(),this);
        view.setQuestion(model.getQuestion());
        view.setComment(model.getComment());
    }

    /**
     * Gets the model and view to start up.
     */
    public void start(){
        //The model is made to update its choices.
        model.setChoices();
        //The view is made to update all its components and then start up.
        view.setQuestion(model.getQuestion());
        view.setChoicePanel(model.getChoices(),this);
        view.setSelectionPanel(model.getCalculations(),this);
        view.setComment(model.getComment());
        view.start();
    }
}
