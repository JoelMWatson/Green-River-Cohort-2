package joel.greenrivertech.net.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent myIntent = getIntent();
        String yourScore = myIntent.getStringExtra("score");
        String praiseText = myIntent.getStringExtra("praise");

        TextView finalScore = (TextView) findViewById(R.id.yourScore);
        finalScore.setText(yourScore);
    }
}
