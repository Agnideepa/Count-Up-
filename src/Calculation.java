/**
 * This is an abstract class Calculation. It represents the operations that the user of Count Up! is supposed to
 * perform. It helps in implementing a Strategy pattern. These operations will be able to work on two operands. One
 * operand can be supported by ignoring one of the parameters in perform(int,int).
 *
 * @Agnideepa
 * @14.12.2018
 */

import javax.swing.*;

abstract class Calculation {
    protected Icon icon;
    protected String symbol;

    //Each calculation will be performed differently, which is why this method is abstract.
    public abstract int perform(int a, int b);

    //Each operation will have its own icon to be displayed on the SelectionPanel.
    public Icon getIcon(){
        return icon;
    }

    //Each operation will have its own symbol to be displayed on the questionPanel
    public String getSymbol(){
        return symbol;
    }

    //Returns the name of the operating class. It is useful for storing the operation names as Strings when we do not
    //want to pass Calculation objects around.
    public String getName(){
        return getClass().getName();
    }

}
