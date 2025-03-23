package com.example.backtrack_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultText = findViewById(R.id.resultText);
        Button restartButton = findViewById(R.id.restartButton);

        // Get score from the previous activity
        int score = getIntent().getIntExtra("score", 0);
        resultText.setText("Вы набрали " + score + " из 5!");

        // Restart quiz when button is clicked
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, com.example.backtrack_quiz.MainActivity.class);
                startActivity(intent);
                finish(); // Close current activity
            }
        });
    }
}
