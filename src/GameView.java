/**
 * The view class that represents the user interface in an MVC pattern.
 * @Agnideepa
 * @14/12/2018
 */

import javax.swing.*;
import java.awt.Color;
import java.util.List;

public class GameView extends JFrame {
    private JPanel topPanel = new JPanel();

    private JPanel bottomPanel = new JPanel();

    private JPanel commentPanel = new JPanel();
    private JLabel comment = new JLabel();

    private JPanel questionPanel = new JPanel();
    private JLabel question = new JLabel();

    /**
     * Creates a new ChoicePanel and stores it in topPanel.
     * @param choices - Represents the choices that should be displayed on the ChoicePanel
     * @param controller - Passes the controller object that is updating the view to the ChoicePanel constructor to be
     *                   modified.
     */
    public void setChoicePanel(List<Integer> choices, Controlling controller){
        ChoicePanel newPanel = new ChoicePanel(choices,controller);
        setTopPanel(newPanel);
    }

    /**
     * Creates a new AnswerPanel and stores it in topPanel.
     * @param answers - Represents the options that should be displayed on the AnswerPanel
     * @param correctIndex - Represents the position of the correctIndex in the Collection answers.
     * @param controller - Passes the controller object that is updating the view to the ChoicePanel constructor to be
     *                   modified.
     */
    public void setAnswerPanel(List<Integer> answers, int correctIndex, Controlling controller){
        AnswerPanel newPanel = new AnswerPanel(answers, correctIndex, controller);
        setTopPanel(newPanel);
    }

    /**
     * Function to update topPanel. setSize() and pack() have been used so that the frame constantly refreshes.
     * @param topPanelContent - the content to be put in the topPanel
     */
    public void setTopPanel(JPanel topPanelContent) {
        this.topPanel.removeAll();
        this.topPanel.add(topPanelContent);

        setSize(500,500);
        pack();
    }

    /**
     * Creates a new SelectionPanel and stores it in bottomPanel.
     * @param selection - Contains the Calculation objects whose icons have to be displayed on the SelectionPanel.
     * @param controller - Passes the controller object that is updating the view to the ChoicePanel constructor to be
     *      *              modified.
     */
    public void setSelectionPanel(List<Calculation> selection, Controlling controller){
            SelectionPanel selectionPanel = new SelectionPanel(selection,controller);
            setBottomPanel(selectionPanel);
    }

    /**
     * Function to update bottomPanel. setSize() and pack() have been used so that the frame constantly
     * refreshes.
     * @param bottomPanelContent - the content to be put in the bottomPanel
     */
    public void setBottomPanel(JPanel bottomPanelContent) {
        this.bottomPanel.removeAll();
        this.bottomPanel.add(bottomPanelContent);

        setSize(500,500);
        pack();
    }

    public String getComment() {
        return comment.getText();
    }

    /**
     * Function to update commentPanel. setSize() and pack() have been used so that the frame constantly
     * refreshes.
     * @param commentText - the content to be displayed in the commentPanel
     */
    public void setComment(String commentText) {
        this.commentPanel.removeAll();
        this.comment.setText(commentText);
        this.commentPanel.add(comment);

        setSize(500,500);
        pack();
    }

    public String getQuestion() {
        return question.getText();
    }

    /**
     * Function to update questionPanel. setSize() and pack() have been used so that the frame constantly
     * refreshes.
     * @param questionText - the content to be displayed in the questionPanel
     */
    public void setQuestion(String questionText) {
        this.questionPanel.removeAll();
        this.question.setText(questionText);
        this.questionPanel.add(question);

        setSize(500,500);
        pack();
    }

    /**
     * Getting started with the view
     */
    public void start(){
        //Using a BoxLayout to display all the contents
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        //Setting backgrounds for the commentPanel and questionPanel
        commentPanel.setBackground(new Color(22, 206, 203));
        questionPanel.setBackground(new Color(237, 182, 255));

        //Adding all the components to the contentPane
        getContentPane().add(questionPanel);
        getContentPane().add(topPanel);
        getContentPane().add(bottomPanel);
        getContentPane().add(commentPanel);

        setTitle("Count Up!");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
}
