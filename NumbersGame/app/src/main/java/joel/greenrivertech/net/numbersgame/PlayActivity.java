package joel.greenrivertech.net.numbersgame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    private static LearningNumbersModel model = new LearningNumbersModel();

    public static LearningNumbersModel getModel() {
        return model;
    }

    public void handleLeftButtonClick(View view) {
        boolean result = model.play(LearningNumbersModel.LEFT_SIDE);
        model.generateNumbers();
        // update my left button text with model.getLeftNumber
        String left = "" + model.getLeftNumber();
        Button leftBtn = (Button) findViewById(R.id.leftButton);
        leftBtn.setText(left);
        // update my right button text with model.getRightNumber
        String right = "" + model.getRightNumber();
        Button rightBtn = (Button) findViewById(R.id.rightButton);
        rightBtn.setText(right);
        // update my score text field with model.getScore
        TextView wins = (TextView) findViewById(R.id.wins);
        wins.setText("Wins: "+ model.getGamesWon());
        // update my games played text field with model.getGamesPlayer
        TextView plays = (TextView) findViewById(R.id.plays);
        plays.setText("Plays: "+ model.getGamesPlayed());
        Context context = getApplicationContext();
        CharSequence correct = "Correct!";
        CharSequence incorrect= "Incorrect!";
        int duration = Toast.LENGTH_SHORT;
        if (result) {
            Toast.makeText(context, correct, duration).show();
        } else {
            Toast.makeText(context, incorrect, duration).show();
        }
    }

    public void handleMiddleButtonClick(View view) {
        boolean result = model.play(LearningNumbersModel.EQUAL);
        model.generateNumbers();
        // update my left button text with model.getLeftNumber
        String left = "" + model.getLeftNumber();
        Button leftBtn = (Button) findViewById(R.id.leftButton);
        leftBtn.setText(left);
        // update my right button text with model.getRightNumber
        String right = "" + model.getRightNumber();
        Button rightBtn = (Button) findViewById(R.id.rightButton);
        rightBtn.setText(right);
        // update my score text field with model.getScore
        TextView wins = (TextView) findViewById(R.id.wins);
        wins.setText("Wins: "+ model.getGamesWon());
        // update my games played text field with model.getGamesPlayer
        TextView plays = (TextView) findViewById(R.id.plays);
        plays.setText("Plays: "+ model.getGamesPlayed());
        Context context = getApplicationContext();
        CharSequence correct = "Correct!";
        CharSequence incorrect= "Incorrect!";
        int duration = Toast.LENGTH_SHORT;
        if (result) {
            Toast.makeText(context, correct, duration).show();
        } else {
            Toast.makeText(context, incorrect, duration).show();
        }
    }

    public void handleRightButtonClick(View view) {
        boolean result = model.play(LearningNumbersModel.RIGHT_SIDE);
        model.generateNumbers();
        // update my left button text with model.getLeftNumber
        String left = "" + model.getLeftNumber();
        Button leftBtn = (Button) findViewById(R.id.leftButton);
        leftBtn.setText(left);
        // update my right button text with model.getRightNumber
        String right = "" + model.getRightNumber();
        Button rightBtn = (Button) findViewById(R.id.rightButton);
        rightBtn.setText(right);
        // update my score text field with model.getScore
        TextView wins = (TextView) findViewById(R.id.wins);
        wins.setText("Wins: "+ model.getGamesWon());
        // update my games played text field with model.getGamesPlayer
        TextView plays = (TextView) findViewById(R.id.plays);
        plays.setText("Plays: "+ model.getGamesPlayed());
        Context context = getApplicationContext();
        CharSequence correct = "Correct!";
        CharSequence incorrect= "Incorrect!";
        int duration = Toast.LENGTH_SHORT;
        if (result) {
            Toast.makeText(context, correct, duration).show();
        } else {
            Toast.makeText(context, incorrect, duration).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String left = intent.getStringExtra(NumberComparison.LEFT_BUTTON);
        String right = intent.getStringExtra(NumberComparison.RIGHT_BUTTON);
        Button leftBtn = (Button) findViewById(R.id.leftButton);
        leftBtn.setText(left);
        Button rightBtn = (Button) findViewById(R.id.rightButton);
        rightBtn.setText(right);
        TextView wins = (TextView) findViewById(R.id.wins);
        wins.setText("Wins: "+ model.getGamesWon());
        TextView plays = (TextView) findViewById(R.id.plays);
        plays.setText("Plays: "+ model.getGamesPlayed());
    }

}
