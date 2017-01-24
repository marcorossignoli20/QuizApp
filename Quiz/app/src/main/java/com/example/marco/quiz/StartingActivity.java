package com.example.marco.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class StartingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent backIntent = getIntent();
        boolean win = backIntent.getBooleanExtra("points", false);
        if(win){
                Toast.makeText(this, "You have won!",
                        Toast.LENGTH_LONG).show();
        } else {
                Toast.makeText(this, "You have lost!",
                        Toast.LENGTH_LONG).show();
        }
        Button btnCarsQuiz = (Button) findViewById(R.id.btnCarsQuiz);
        btnCarsQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(StartingActivity.this, QuizActivity.class);
                String value = "cars";
                myIntent.putExtra("quiz", value); //Optional parameters
                StartingActivity.this.startActivity(myIntent);
            }
        });

        Button btnMotorbikesQuiz = (Button) findViewById(R.id.btnMotorbikesQuiz);
        btnMotorbikesQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(StartingActivity.this, QuizActivity.class);
                String value = "motorbikes";
                myIntent.putExtra("quiz", value); //Optional parameters
                StartingActivity.this.startActivity(myIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_starting, menu);
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
