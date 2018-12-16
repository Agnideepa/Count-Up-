/**
 * This is a class which has one static function that is meant to generate choices of a certain difficulty levek.
 * @Agnideepa
 * @14/12/2018
 */

import java.util.LinkedList;
import java.util.List;

//There is no need to create an object of this class
public abstract class RandomChoiceGenerator {
    static List<Integer> calculate(int level){
        List<Integer> generatedChoices = new LinkedList<>();
        //Math.random() is used to generate the choices and put them in the list
        for(int i = 1;i<=9;i++)
        {
            /**
             * The level is directly proportional to the mode of the number of digits in the choices. The reason I have
             * subtracted 10^(level+1) is because I also want to generate negatve numbers.
             */
            generatedChoices.add((int)(Math.random()*Math.pow(10,level+1)*2 - Math.pow(10,level+1)));
        }
        return generatedChoices;
    }
}
