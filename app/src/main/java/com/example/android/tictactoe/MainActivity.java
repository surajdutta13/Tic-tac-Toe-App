package com.example.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] gamingState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer=0;
    boolean gameActive= true;
    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        Log.i("Tag ",counter.getTag().toString());
        int taggedCounter = Integer.parseInt(counter.getTag().toString());

        if(gamingState[taggedCounter]==2 && gameActive) {
            gamingState[taggedCounter] = activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1500).setDuration(500);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1500).setDuration(500);
                activePlayer = 0;
            }
            for (int[] winningPositions : winningPositions) {
                if (gamingState[winningPositions[0]] == gamingState[winningPositions[1]] && gamingState[winningPositions[1]] == gamingState[winningPositions[2]] && gamingState[winningPositions[0]] != 2) {
                    gameActive = false;

                    String winner = "";
                    if (activePlayer == 1)
                        winner = "Yellow";
                    else
                        winner = "Red";

                    TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
                    Button playAgainButton = (Button)findViewById(R.id.playAgainButton);

                    winnerTextView.setText(winner+ " Won!!");
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view)
    {
        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
        Button playAgainButton = (Button)findViewById(R.id.playAgainButton);

        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i=0; i<gamingState.length; i++) {

            gamingState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
