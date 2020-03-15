package joel.greenrivertech.net.quizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class BeginActivity extends AppCompatActivity {

    private static QuestionsModel model = new QuestionsModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String question = model.getQuestion();
        String score = model.getScore();


        TextView questionsTextView = (TextView) findViewById(R.id.questionTextView);
        questionsTextView.setText(question);
        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        if (scoreTextView != null) {
            scoreTextView.setText(score);
        }
    }

    /**
     * This method handles the click event on the left (true) button,
     * it then toasts if answer is correct
     *
     * @param view
     *          -the view of the button is being passed to this method
     */
    public void leftTrueButtonHandler(View view){
        boolean result = model.play(QuestionsModel.LEFT_BUTTON);
        Context context = getApplicationContext();
        CharSequence correct = context.getString(R.string.correct);
        CharSequence incorrect= context.getString(R.string.incorrect);
        int duration = Toast.LENGTH_SHORT;
        if (result) {
            Toast.makeText(context, correct, duration).show();
        } else {
            Toast.makeText(context, incorrect, duration).show();
        }
    }

    /**
     * This method handles the click event on the right (false) button,
     * it then toasts if answer is correct
     *
     * @param view
     *          -the view of the button is being passed to this method
     */
    public void rightTrueButtonHandler(View view) {
        boolean result = model.play(QuestionsModel.RIGHT_BUTTON);
        Context context = getApplicationContext();
        CharSequence correct = context.getString(R.string.correct);
        CharSequence incorrect= context.getString(R.string.incorrect);
        int duration = Toast.LENGTH_SHORT;
        if (result) {
            Toast.makeText(context, correct, duration).show();
        } else {
            Toast.makeText(context, incorrect, duration).show();
        }

    }

    /**
     * This method handles the click event on the next button and runs
     * the nextQuestion() method. if it returns true, the next question
     * appear on the screen, or it will reach the last question and move
     * to the gameOver screen.
     *
     * @param view
     *          -the view of the next button or the questions text box
     *           is passed to this method
     */
    public void nextButtonHandler(View view){
        boolean result = model.nextQuestion();
        if (result) {
            Intent myIntent = new Intent(this, BeginActivity.class);
            myIntent.putExtra("score", model.getScore());
            myIntent.putExtra("question", model.getQuestion());
            startActivity(myIntent);
        } else {
            Intent myIntent = new Intent(this, GameOver.class);
            myIntent.putExtra("score", model.getScore());
            Context context = App.getContext();
            startActivity(myIntent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_begin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
