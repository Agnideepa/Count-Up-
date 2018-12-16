/**
 * This is a list of classes implementing the abstract class Calculation. This is not an exhaustive list and is open to
 * extension, which is why the GameModel has a parameterized constructor that accepts a list of Calculation objects.
 *
 * @Agnideepa
 * @14.12.2018
 */

import javax.swing.*;

class Addition extends Calculation {
    Addition(){
        icon = new ImageIcon("add.png");
        symbol = "+";
    }

    public int perform(int a, int b){
        return a+b;
    }
}

class Subtraction extends Calculation {
    Subtraction(){
        icon = new ImageIcon("subtract.png");
        symbol = "-";
    }

    public int perform(int a, int b){
        return a-b;
    }
}

class Multiplication extends Calculation {
    Multiplication(){
        icon = new ImageIcon("multiply.png");
        symbol = "*";
    }

    public int perform(int a, int b){
        return a*b;
    }
}

