package com.example.ravishankar.scorekeeper;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView result_box;
    TextView comment_box;
    Toast toast;
    public int markforA = 0;
    int f = 0;
    int g = 0;
    public int markforB = 0;
    public int maketoast = 0;
    public String firstbox;
    public String secondbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        comment_box = findViewById(R.id.comment);
        firstbox = comment_box.getText().toString();

        result_box = findViewById(R.id.result);
        secondbox = result_box.getText().toString();

        outState.putInt("teamA_score", markforA);
        outState.putInt("teamB_score", markforB);
        outState.putInt("maketoast_pass", maketoast);
        outState.putString("com", firstbox);
        outState.putString("res", secondbox);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        markforA = savedInstanceState.getInt("teamA_score");
        markforB = savedInstanceState.getInt("teamB_score");
        displayMarkA(markforA);
        displayMarkB(markforB);
        maketoast = savedInstanceState.getInt("maketoast_pass");
        comment_box = findViewById(R.id.comment);
        String str1 = savedInstanceState.getString("com");
        String str2 = savedInstanceState.getString("res");
        comment_box.setText(str1);
        result_box = findViewById(R.id.result);
        result_box.setText(str2);
    }

    public void forTeamA(View view) {
        if (maketoast == 1) {
            maketoast();
        } else {

            if (Integer.parseInt((String) view.getTag()) == 1) {
                markforA += 10;
                textView = findViewById(R.id.comment);
                textView.setText(R.string.quaffle_hit);
                textView = findViewById(R.id.result);
                if (f == 0) {
                    textView.setText(R.string.points_10A);
                    f = 1;
                } else
                    textView.setText(R.string.another_10);
            } else if (Integer.parseInt((String) view.getTag()) == 2) {
                markforA += 5;
                textView = findViewById((R.id.comment));
                textView.setText(R.string.bludger_hit);
                textView = findViewById(R.id.result);
                if (f == 0) {
                    textView.setText(R.string.points_5A);
                    f = 1;
                } else
                    textView.setText(R.string.another_5A);
            } else if (Integer.parseInt((String) view.getTag()) == 3) {
                markforA -= 2;
                textView = findViewById((R.id.comment));
                textView.setText(R.string.foul_);
                textView = findViewById(R.id.result);

                textView.setText(R.string.grifflose);

            }
            displayMarkA(markforA);

            checkForResult(markforA, 1);
        }

    }

    public void displayMarkA(int mark) {
        TextView textView = findViewById(R.id.score_forA);
        textView.setText("" + mark);
    }


    public void forTeamB(View view) {
        if (maketoast == 1) {
            maketoast();
        } else {

            if (Integer.parseInt((String) view.getTag()) == 1) {
                markforB += 10;
                textView = findViewById(R.id.comment);
                textView.setText(R.string.quaffle_hit);
                textView = findViewById(R.id.result);
                if (g == 0) {
                    textView.setText(R.string.points_10B);
                    g = 1;
                } else
                    textView.setText(R.string.another_10B);
            } else if (Integer.parseInt((String) view.getTag()) == 2) {
                markforB += 5;
                textView = findViewById((R.id.comment));
                textView.setText(R.string.bludger_hit);
                textView = findViewById(R.id.result);
                if (g == 0) {
                    textView.setText(R.string.points_5B);
                    g = 1;
                } else
                    textView.setText(R.string.another_5B);
            } else if (Integer.parseInt((String) view.getTag()) == 3) {
                markforB -= 2;
                textView = findViewById((R.id.comment));
                textView.setText(R.string.foul);
                textView = findViewById(R.id.result);

                textView.setText(R.string.slytlose);

            }
            displayMarkB(markforB);
            checkForResult(markforB, 2);
        }


    }


    public void displayMarkB(int mark) {
        textView = findViewById(R.id.score_forB);
        textView.setText("" + mark);

    }

    public void reset(View view) {
        markforA = 0;
        markforB = 0;
        f = 0;
        g = 0;

        displayMarkA(markforA);
        displayMarkB(markforB);

        result_box = findViewById(R.id.result);
        result_box.setText(R.string.start_again);
        comment_box = findViewById(R.id.comment);
        comment_box.setText("");


        maketoast = 0;


    }

    public void checkForResult(int marks, int team) {
        if (marks >= 150) {
            if (team == 1) {
                textView = findViewById(R.id.comment);
                textView.setText(R.string.reached_150);
                textView = findViewById((R.id.result));
                textView.setText(R.string.griff_won);
                maketoast = 1;

            } else if (team == 2) {
                textView = findViewById(R.id.comment);
                textView.setText(R.string.reached_150);
                textView = findViewById((R.id.result));
                textView.setText(R.string.slyt_won);
                maketoast = 1;

            }
        }
    }

    public void maketoast() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        String toast_message = "Click the reset to start the match again";
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, toast_message, duration);
        toast.show();

    }

    public void caughtsnitch(View view) {
        if (Integer.parseInt((String) view.getTag()) == 1 && maketoast == 0) {
            markforA += 150;
            displayMarkA(markforA);
            textView = findViewById(R.id.comment);
            textView.setText(R.string.snitch);
            textView = findViewById(R.id.result);
            textView.setText(R.string.griff_won);
            maketoast = 1;
        } else if (Integer.parseInt((String) view.getTag()) == 2 && maketoast == 0) {
            markforB += 150;
            displayMarkB(markforB);
            textView = findViewById(R.id.comment);
            textView.setText(R.string.snitch);
            textView = findViewById(R.id.result);
            textView.setText(R.string.slyt_won);
            maketoast = 1;
        } else
            maketoast();
    }


}
