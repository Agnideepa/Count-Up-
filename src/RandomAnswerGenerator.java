/**
 * This is a class which has one static function that is meant to generate answer options containing a certain correct
 * answer ar a particular position while making sure that the answer option are in appropriate order of magnitude.
 * @Agnideepa
 * @14/12/2018
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//There is no need to create an object of this class
public abstract class RandomAnswerGenerator {
    static List<Integer> calculate(int correctAnswer,int correctIndex){
        Set<Integer> wrongAnswers = new HashSet<>();

        //answer is solely to increase readability of the code. It stores the current answer that is being added to the
        // set wrongAnswers
        int answer = 0;

        //To figure out the number of digits in the correct answer. It gives an idea about the range of the answer
        //options.
        int digits = 0;
        for(digits = 1;digits>0;digits++)
        {
            if(Math.pow(10,digits)>Math.abs(correctAnswer))
                break;
        }

        //I have chosen a set because a set cannot contain duplicate elements and I do not want any two answer options
        //the same.
        while(wrongAnswers.size()<8)
        {
            answer = (int)(Math.random()*Math.pow(10,digits)*2 - Math.pow(10,digits) );
            //Making sure that the correct answer is not included in the set of wrong answers
            if(answer!=correctAnswer)
                wrongAnswers.add(answer);
        }


        List<Integer> generatedAnswers = new LinkedList<>();
        //listIndex stores the current index of the list that is being modifies. It is used to insert the correct answer
        // at the correct position.
        int listIndex = 0;
        for (int i : wrongAnswers)
        {
           if(listIndex==correctIndex)
           {
               generatedAnswers.add(correctAnswer);
               listIndex++;
           }
            generatedAnswers.add(i);
            listIndex++;
        }

        //generatedAnswers is returned.
        return generatedAnswers;
    }
}
