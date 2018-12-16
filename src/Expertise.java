/**
 * This is a class that represents how good someone is at a certain operation. It may be used by any application having
 * correct/incorrect answers to gauge expertise. Different operation objects should be linked with different Expertise
 * objects.
 *
 * @Agnideepa
 * @14.12.2018
 */

public class Expertise {
    private int level = 0;
    private double score = 0.0;

    /**
     * This function deducts 0.5 from the score whenever an incorrectAnswer is recorded and then updates level according
     * to the present score.
     */
    void incorrectAnswer(){
        score = score - 0.5;
        if((Math.pow(2,level+1)-1.0)>score){
            level = Math.max(level-1,0);
        }
    }

    /**
     * This function increases the score by 1.0 whenever a correctAnswer is recorded and then updates level according
     * to the present score.
     */
    void correctAnswer(){
        score = score + 1;
        if((Math.pow(2,level+1)-1.0)<=score){
            level = level+1;
        }
    }

    /**
     * The level is used to set the difficulty of the questions.
     * @return level
     */
    int getLevel(){
        return level;
    }

    /**
     * The score is simply the cumulative points gained since the beginning of the game for a particular operation.
     * @return score
     */
    double getScore(){
        return score;
    }
}
