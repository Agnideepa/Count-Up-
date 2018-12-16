/**
 * The class represents the AnswerPanel used to select the answer.
 * The AnswerPanel has 9 miniAnswerPanels(which use CardLayout) which will tell the user when they have selected
 * that particular answer whether it is correct or not. At present there is no MVC pattern. However, this class will
 * be modified later on to accommodate a variable number of answers. Each AnswerPanel object has a correctButton. The
 * reasoning behind this is that this particular button has a different function to the rest of thr buttons in the
 * miniAnswerPanels.
 * @Agnideepa
 * @14.12.2018
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerPanel extends JPanel{
    private JButton correctButton;

    /**
     * Function that adds the 9 miniAnswerPanels to the AnswerPanel.
     * @param answerInfo - Contains the text on the miniAnswerPanel and its correctness ("Correct!" or "Try Again!")
     * @param controller - I have decided not to add a Controlling object to the AnswerPanel as a field. Instead, I
     *                     am going to pass it down through the constructors so that it can be modified..
     */
    private void createAnswers(Map<String, String> answerInfo,Controlling controller) {
        for (Map.Entry<String, String> entry : answerInfo.entrySet()) {
            add(new miniAnswerPanel(entry.getKey(), entry.getValue(),controller));
        }
    }

    /**
     * This is a constructor for the AnswerPanel that also accepts a Controlling object and modifies it.
     * @param numbers - This represents all the numbers that are going to be displayed to the user.
     * @param correctIndex - Stores the correct index, that is, the position of the correct answer (NOTE: it does not
     *                     matter which order the answers are placed in on the AnswerPanel. The correctIndex is stored
     *                     by way of the correctButton.
     * @param controller - The Controlling object that needs to be modified.
     */
    AnswerPanel(List<Integer> numbers, int correctIndex, Controlling controller) {

        //A new GridLayout is set. It is meant to be a 3 by 3 grid. Later on, this will be modified to include a
        //variable number of answer options.
        GridLayout layout = new GridLayout(3, 3, 10, 10);
        setLayout(layout);

        Map<String, String> answerInfo = new HashMap<>();
        //We create the answerInfo with the answer options and their correctness
        for (int i = 0; i < 9; i++) {
            if (i == correctIndex)
                answerInfo.put(Integer.toString(numbers.get(i)), "Correct!");
            else
                answerInfo.put(Integer.toString(numbers.get(i)), "Try Again!");
        }

        createAnswers(answerInfo,controller);
    }

    /**
     * In this case, we need a Container since we want to implement CardLayout. Therefore, the MiniAnswerPanel is a
     * JPanel. These panels consist of a button and a label that may or may not be visible once the button is clicked.
     * The main purpose of the text on the label is to store the answer's correctness.
     */
    private class miniAnswerPanel extends JPanel{
        miniAnswerPanel(String answer, String correctness, Controlling controller) {

            //A CardLayout works well here since we need the panel to change here and simply changing the text on the
            //button will not do as the button will still be Click-able.
            CardLayout cards = new CardLayout();
            setLayout(cards);

            //Adding the text to the button and label, and formatting them with backgrounds and adding ActionListener
            //to the button
            JButton button = new JButton(answer);
            button.setBackground(new Color(187, 159, 128));

            JLabel label = new JLabel(correctness);

            label.setHorizontalAlignment(JLabel.CENTER);

            if (correctness.equals("Correct!")) {
                //We check for correctness and identify the correctButton so that we can change to a new ChoicePanel
                //once this button has been clicked.
                setBackground(new Color(85, 198, 88));
                correctButton = button;
            } else
                setBackground(new Color(198, 50, 39));

            //The controller updates model and view according to the correctness of the answer option selected
            button.addActionListener((ActionEvent e) -> {
                if (e.getSource().equals(correctButton)) {
                    controller.correctAnswerSelected();
                }
                else{
                    cards.next(this);
                    controller.incorrectAnswerSelected();
                }
            });

            //The button and label are added to the MiniAnswerPanel
            add(button);
            add(label);
        }
    }
}
