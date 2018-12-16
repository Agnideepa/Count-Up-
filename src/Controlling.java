/**This is essentially an interface to represent a controller where the View makes use of a SelectionPanel, AnswerPanel
 * and a ChoicePanel. There has to be a start method and the basic idea must remain the same : 2 choices selected, an
 * answer selected and an operation selected. It is up to the implementing class to decide what to do once any of these
 * events has occurred.
 * @Agnideepa
 * @14.12.2018
 */

public interface Controlling {
    void firstChoiceSelected(String firstChoice);

    void secondChoiceSelected(String secondChoice);

    void correctAnswerSelected();

    void incorrectAnswerSelected();

    void operationSelected(String calculation);

    void start();
}