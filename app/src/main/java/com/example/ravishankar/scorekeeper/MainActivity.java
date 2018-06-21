package com.example.ravishankar.scorekeeper;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView resultBox;
    TextView commentBox;
    Toast toast;
    public int markForA = 0;
    int firstTimeScoreBy_A = 0;
    int firstTimeScoreBy_B = 0;
    public int markForB = 0;
    public int makeToast = 0;
    public String firstBox;
    public String secondBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        commentBox = findViewById(R.id.comment);
        firstBox = commentBox.getText().toString();
        resultBox = findViewById(R.id.result);
        secondBox = resultBox.getText().toString();
        outState.putInt("teamA_score", markForA);
        outState.putInt("teamB_score", markForB);
        outState.putInt("makeToast_pass", makeToast);
        outState.putString("com", firstBox);
        outState.putString("res", secondBox);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        markForA = savedInstanceState.getInt("teamA_score");
        markForB = savedInstanceState.getInt("teamB_score");
        displayMarkA(markForA);
        displayMarkB(markForB);
        makeToast = savedInstanceState.getInt("makeToast_pass");
        commentBox = findViewById(R.id.comment);
        String stringFromComment;
        String strFromResult;
        stringFromComment = savedInstanceState.getString("com");
        strFromResult = savedInstanceState.getString("res");
        commentBox.setText(stringFromComment);
        resultBox = findViewById(R.id.result);
        resultBox.setText(strFromResult);
    }

    public void forTeamA(View view) {
        if (makeToast == 1) {
            makeToast();
        } else {
            if (Integer.parseInt((String) view.getTag()) == 1) {
                markForA += 10;
                textView = findViewById(R.id.comment);
                textView.setText(R.string.quaffle_hit);
                textView = findViewById(R.id.result);
                if (firstTimeScoreBy_A == 0) {
                    textView.setText(R.string.points_10A);
                    firstTimeScoreBy_A = 1;
                } else
                    textView.setText(R.string.another_10);
            } else if (Integer.parseInt((String) view.getTag()) == 2) {
                markForA += 5;
                textView = findViewById((R.id.comment));
                textView.setText(R.string.bludger_hit);
                textView = findViewById(R.id.result);
                if (firstTimeScoreBy_A == 0) {
                    textView.setText(R.string.points_5A);
                    firstTimeScoreBy_A = 1;
                } else
                    textView.setText(R.string.another_5A);
            } else if (Integer.parseInt((String) view.getTag()) == 3) {
                markForA -= 2;
                textView = findViewById((R.id.comment));
                textView.setText(R.string.foul_);
                textView = findViewById(R.id.result);
                textView.setText(R.string.grifflose);
            }
            displayMarkA(markForA);
            checkForResult(markForA, 1);
        }
    }

    public void displayMarkA(int mark) {
        TextView textView = findViewById(R.id.score_forA);
        textView.setText(String.valueOf(mark));
    }

    public void forTeamB(View view) {
        if (makeToast == 1) {
            makeToast();
        } else {
            if (Integer.parseInt((String) view.getTag()) == 1) {
                markForB += 10;
                textView = findViewById(R.id.comment);
                textView.setText(R.string.quaffle_hit);
                textView = findViewById(R.id.result);
                if (firstTimeScoreBy_B == 0) {
                    textView.setText(R.string.points_10B);
                    firstTimeScoreBy_B = 1;
                } else
                    textView.setText(R.string.another_10B);
            } else if (Integer.parseInt((String) view.getTag()) == 2) {
                markForB += 5;
                textView = findViewById((R.id.comment));
                textView.setText(R.string.bludger_hit);
                textView = findViewById(R.id.result);
                if (firstTimeScoreBy_B == 0) {
                    textView.setText(R.string.points_5B);
                    firstTimeScoreBy_B = 1;
                } else
                    textView.setText(R.string.another_5B);
            } else if (Integer.parseInt((String) view.getTag()) == 3) {
                markForB -= 2;
                textView = findViewById((R.id.comment));
                textView.setText(R.string.foul);
                textView = findViewById(R.id.result);
                textView.setText(R.string.slytlose);
            }
            displayMarkB(markForB);
            checkForResult(markForB, 2);
        }
    }

    public void displayMarkB(int mark) {
        textView = findViewById(R.id.score_forB);
        textView.setText(String.valueOf(mark));
    }

    public void reset(View view) {
        markForA = 0;
        markForB = 0;
        firstTimeScoreBy_A = 0;
        firstTimeScoreBy_B = 0;
        displayMarkA(markForA);
        displayMarkB(markForB);
        resultBox = findViewById(R.id.result);
        resultBox.setText(R.string.start_again);
        commentBox = findViewById(R.id.comment);
        commentBox.setText("");
        makeToast = 0;
    }

    public void checkForResult(int marks, int team) {
        if (marks >= 150) {
            if (team == 1) {
                textView = findViewById(R.id.comment);
                textView.setText(R.string.reached_150);
                textView = findViewById((R.id.result));
                textView.setText(R.string.griff_won);
                makeToast = 1;

            } else if (team == 2) {
                textView = findViewById(R.id.comment);
                textView.setText(R.string.reached_150);
                textView = findViewById((R.id.result));
                textView.setText(R.string.slyt_won);
                makeToast = 1;
            }
        }
    }

    public void makeToast() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        String toast_message = getString(R.string.restartGame);
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, toast_message, duration);
        toast.show();
    }

    public void caughtSnitch(View view) {
        if (Integer.parseInt((String) view.getTag()) == 1 && makeToast == 0) {
            markForA += 150;
            displayMarkA(markForA);
            textView = findViewById(R.id.comment);
            textView.setText(R.string.snitch);
            textView = findViewById(R.id.result);
            textView.setText(R.string.griff_won);
            makeToast = 1;
        } else if (Integer.parseInt((String) view.getTag()) == 2 && makeToast == 0) {
            markForB += 150;
            displayMarkB(markForB);
            textView = findViewById(R.id.comment);
            textView.setText(R.string.snitch);
            textView = findViewById(R.id.result);
            textView.setText(R.string.slyt_won);
            makeToast = 1;
        } else
            makeToast();
    }
}