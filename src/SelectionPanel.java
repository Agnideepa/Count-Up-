/**
 * The class represents the SelectionPanel used to select the operation.
 * There is no MVC design pattern for the SelectionPanel because it is essentially a JPanel with some specific
 * functionality. It is meant to be a component of some application.
 * @Agnideepa
 * @14.12.2018
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectionPanel extends JPanel{
    /**
     * createSelection - Function that adds all the SelectionButtons to the SelectionPanel
     * @param selectionInfo - the Icon(key) will be displayed on the buttons while the String(value) represents the
     *                      operation to be performed.
     * @param controller - I have decided not to add a Controlling object to the SelectionPanel as a field. Instead, I
     *                   am going to pass it down through the constructors so that it can be modified..
     */


    private void createSelection(Map<Icon, String> selectionInfo, Controlling controller) {
        for (Map.Entry<Icon, String> entry : selectionInfo.entrySet()) {
            add(new SelectionButton(entry.getKey(), entry.getValue(),controller));
        }
    }

    /**
     * This is a constructor for the SelectionPanel that also accepts a Controlling object and modifies it.
     * @param calculations - Represents all the operations that the SelectionPanel must include
     * @param controller - I have decided not to add a Controlling object to the SelectionPanel as a field. Instead, I
     *                   am going to pass it down through the constructors so that it can be modified..
     */

    SelectionPanel(List<Calculation> calculations, Controlling controller) {

        //Adding a GridLayout to the panel. All the operations are to fit in one row
        GridLayout layout = new GridLayout(1, calculations.size(), 10, 10);
        setLayout(layout);

        Map<Icon, String> selectionInfo = new HashMap<>();
        //Adding the icons and text to the Collection selectionInfo
        for (Calculation i : calculations) {
            selectionInfo.put(i.getIcon(), i.getName());
        }
        //Adding the SelectionButtons to the SelectionPanel object
        createSelection(selectionInfo,controller);
    }

    /**
     * In this case, we can extend JButton since the only purpose of a SelectionButton is to be clicked. It does not
     * need to display text once it is clicked.
     */
    private class SelectionButton extends JButton {

        /**
         * This is a constructor for the SelectionButton that also accepts a Controlling object and modifies it.
         * @param icon - the Icon on the Button
         * @param operation - the operation the Button represents (taken as a String)
         * @param controller - I have decided not to add a Controlling object to the SelectionPanel as a field. Instead, I
         *                   am going to pass it down through the constructors so that it can be modified..
         */

        SelectionButton(Icon icon, String operation, Controlling controller) {
            //Adding the icon, setting the background and adding an ActionListener object
            setIcon(icon);
            setBackground(new Color(153, 99, 44));

            addActionListener((ActionEvent e) -> {
                controller.operationSelected(operation);
            });
        }
    }
}
