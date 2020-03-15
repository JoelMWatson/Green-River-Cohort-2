package joel.greenrivertech.net.quizapp;

import android.content.Context;

/**
 * Created by Joel on 4/24/2016.
 */
public class QuestionsModel {

    private static final int MAX = 9;
    private int score;
    private Context context;
    private String question;
    private boolean isCorrect;
    private String[] questions = new String[10];
    private Boolean[] answers = new Boolean[10];
    private int i=0;

    /**
     * This constructor creates a QuestionsModel object set to the first
     * question and answer from the arrays
     */
    public QuestionsModel() {
        context = App.getContext();
        generateQuestions(context);
        question = questions[i];
        isCorrect = answers[i];
        score = 0;
    }

    /**
     * This method moves the pointer int i to the next position in the array
     * and returns true if the pointer was moved to the next or false if the
     * pointer has reached the end of the list
     *
     * @return true if the pointer was moved to the next position of false if
     * it has reached the end
     */
    public boolean nextQuestion() {
        if (i == MAX) {
            return false;
        }
        else {
            i++;
            question = questions[i];
            isCorrect = answers[i];
            return true;
        }

    }

    /**
     * This method fills the answers and questions arrays with answers and questions
     *
     * @param context
     *         -this requires the application context, retrieved by App.getContext();
     */
    public void generateQuestions(Context context) {
        questions[0] = context.getString(R.string.q1);
        answers[0] = false;
        questions[1] = context.getString(R.string.q2);
        answers[1] = false;
        questions[2] = context.getString(R.string.q3);
        answers[2] = true;
        questions[3] = context.getString(R.string.q4);
        answers[3] = false;
        questions[4] = context.getString(R.string.q5);
        answers[4] = false;
        questions[5] = context.getString(R.string.q6);
        answers[5] = false;
        questions[6] = context.getString(R.string.q7);
        answers[6] = true;
        questions[7] = context.getString(R.string.q8);
        answers[7] = true;
        questions[8] = context.getString(R.string.q9);
        answers[8] = true;
        questions[9] = context.getString(R.string.q10);
        answers[9] = true;
    }

    /**
     * This method gets the string field question
     *
     * @return the question string field
     */
    public String getQuestion() {
        return question;
    }

    /**
     * This method gets the score int field and changes it to a string
     *
     * @return a string representation of the score int field
     */
    public String getScore() {
        String stringScore = Integer.toString(score);
        return stringScore;
    }

    public static final boolean LEFT_BUTTON = true;
    public static final boolean RIGHT_BUTTON = false;

    /**
     * This method checks if the response from the user is equal
     * to the answer for the question
     *
     * @param buttonChoice
     *          -buttonChoice is a boolean based on which button the
     *          user chose
     *
     * @return true if the answer is equal to the users buttonChoice
     */
    public boolean play(boolean buttonChoice) {

        if (buttonChoice == isCorrect) {
            score++;
            return true;
        }
        else {
            return false;
        }

    }
}
