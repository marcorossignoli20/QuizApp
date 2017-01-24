package com.example.marco.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    TextView textView2;
    Button btnTrue, btnFalse, btnClose;
    DBManager db = new DBManager(this);
    int points = 0;
    int count = 0;
    boolean answered = false;
    String question, answer;
    ArrayList questionsAL = new ArrayList();
    boolean win = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String value = intent.getStringExtra("quiz");
        try {
            db.createDataBase();
        } catch (IOException e) {
            System.out.println("Database doesn't work");
            e.printStackTrace();
        }
        questionsAL = db.getAllQuestions(value);
        startQuiz();

        btnTrue = (Button) findViewById(R.id.btnTrue);
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer.equals("true")){
                    count++;
                    points++;
                    startQuiz();
                } else {
                    count++;
                    startQuiz();
                }
            }
        });

        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer.equals("false")){
                    count++;
                    points++;
                    startQuiz();
                } else {
                    count++;
                    startQuiz();
                }
            }
        });
    }

    public void startQuiz(){
        if(count != questionsAL.size()) {
            textView2 = (TextView) findViewById(R.id.textView2);
            question = ((QuizClass) questionsAL.get(count)).getQuestion();
            answer = ((QuizClass) questionsAL.get(count)).getAnswer();
            textView2.setText(question);
        } else {
            if(points == questionsAL.size()){
                win = true;
            }
            textView2 = (TextView) findViewById(R.id.textView2);
            textView2.setText("Quiz finished, click the close button to return to the main screen");
            btnClose = (Button) findViewById(R.id.btnClose);
            btnTrue.setVisibility(View.INVISIBLE);
            btnFalse.setVisibility(View.INVISIBLE);
            btnClose.setVisibility(View.VISIBLE);
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(QuizActivity.this,StartingActivity.class);
                    myIntent.putExtra("points", win);
                    QuizActivity.this.startActivity(myIntent);
                    finish();
                }
            });
        }
    }


}
