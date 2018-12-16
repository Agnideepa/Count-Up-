/**
 * The class represents the ChoicePanel used to select the operands.
 * The ChoicePanel has 9 MiniChoicePanels(which use CardLayout) which will notify the user when they have selected
 * the number displayed on that particular MiniChoicePanel. At present there is no MVC pattern. However, this class will
 * be modified later on to accommodate a variable number of choices and operands.
 * @Agnideepa
 * @14.12.2018
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

public class ChoicePanel extends JPanel {
    /**
     * The ChoicePanel has only one field to represent its state -  numberOfClicks which determines whether it is
     * time to switch to an AnswerPanel or not. It stores the number of choices that have been selected. When
     * two choices have been selected, it is time to switch to an AnswerPanel.
     */
    private int numberOfClicks = 0;

    /**
     * createChoices adds the MiniChoicePanels onto the ChoicePanel.
     * @param choiceInfo - It is a List of the text that should go on the MiniChoicePanels - essentially the numbers.
     * @param controller -  I have decided not to add a Controlling object to the ChoicePanel as a field. Instead, I am
     *                      going to pass it down the constructors for modification.
     */
    private void createChoices(List<String> choiceInfo,Controlling controller) {
        for (String choice : choiceInfo) {
            add(new MiniChoicePanel(choice,controller));
        }
    }

    /**
     * This is a constructor for the ChoicePanel that also accepts a Controlling object and modifies it.
     * @param numbers - Represents all the choices that the user will see on the ChoicePanel
     * @param controller - I have decided not to add a Controlling object to the SelectionPanel as a field. Instead, I
     *                   am going to pass it down through the constructors so that it can be modified..
     */

    ChoicePanel(List<Integer> numbers, Controlling controller) {

        //Adding a GridLayout to the panel. It will be a 3 by 3 grid.
        GridLayout layout = new GridLayout(3, 3, 10, 10);
        setLayout(layout);

        List<String> choiceInfo = new LinkedList<>();

        //Adding the text to choiceInfo
        for (int i = 0; i < 9; i++) {
            choiceInfo.add(Integer.toString(numbers.get(i)));
        }

        createChoices(choiceInfo,controller);

    }

    /**
     * In this case, we need a Container since we want to implement CardLayout. Therefore, the MiniChoicePanel is a
     * JPanel. These panels consist of a button and a label that is visible once the button is clicked.
     */

    private class MiniChoicePanel extends JPanel{

        MiniChoicePanel(String choiceText,Controlling controller) {

            //A CardLayout works well here since we need the panel to change here and simply changing the text on the
            //button will not do as the button will still be Click-able.
            CardLayout cards = new CardLayout();
            setLayout(cards);

            //Adding the text to the button and label, and formatting them with backgrounds and adding ActionListener
            //to the button
            JButton button = new JButton(choiceText);
            button.setBackground(new Color(176, 171, 45));

            JLabel label = new JLabel("Selected!");
            label.setHorizontalAlignment(JLabel.CENTER);
            setBackground(new Color(85, 198, 88));

            button.addActionListener((ActionEvent e) -> {
                cards.next(this);
                //We increase the numberOfClicks
                numberOfClicks++;
                if (numberOfClicks == 1)
                {
                    //When the number of clicks is 1, only the first choice has been selected.
                    controller.firstChoiceSelected(button.getText());
                }
                else if (numberOfClicks == 2)
                {
                    //When the number of clicks is 2, the second choice has been selected.
                    controller.secondChoiceSelected(button.getText());
                }
            });

            //The button and label are added to the MiniChoicePanel
            add(button);
            add(label);
        }
    }
}
