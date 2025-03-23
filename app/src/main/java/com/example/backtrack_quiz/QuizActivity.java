package com.example.backtrack_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private TextView questionText;
    private RadioGroup optionsGroup;
    private Button nextButton;

    private String[] questions = {
            "Когда была битва при Аварайре?",
            "Кто был царем Армении в 301 году?",
            "Какой город был столицей Великой Армении?",
            "Какое древнее царство предшествовало Армении?",
            "Как называется национальный эпос Армении?"
    };

    private String[][] options = {
            {"451 г.", "301 г.", "189 г.", "1045 г."},
            {"Тигран Великий", "Трдат III", "Арташес I", "Ашот II"},
            {"Двин", "Ани", "Артаксата", "Ереван"},
            {"Урарту", "Персия", "Хетты", "Вавилон"},
            {"Сасна Црер", "Давид Сасунский", "Шахнаме", "Эпос о Гильгамеше"}
    };

    private int[] correctAnswers = {0, 1, 2, 0, 0}; // Индексы правильных ответов
    private int currentQuestion = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        nextButton = findViewById(R.id.nextButton);

        loadQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            questionText.setText(questions[currentQuestion]);
            optionsGroup.clearCheck();

            for (int i = 0; i < 4; i++) {
                ((RadioButton) optionsGroup.getChildAt(i)).setText(options[currentQuestion][i]);
            }
        } else {
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    private void checkAnswer() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            int selectedIndex = optionsGroup.indexOfChild(findViewById(selectedId));
            if (selectedIndex == correctAnswers[currentQuestion]) {
                score++;
            }
            currentQuestion++;
            loadQuestion();
        }
    }
}
