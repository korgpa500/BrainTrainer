package com.example.yousryelwrdany.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int num1;
    int num2;
    int sum;
    int locationOfCorrectAnswer;
    int numTries;
    int numSuccess;
    int second = 30;

    TextView txtSecond;
    TextView txtQuestion;
    TextView txtResult;
    TextView txtAnswer;
    CountDownTimer countDownTimer;
    ArrayList<Integer> answer;
    Button Btn0;
    Button Btn1;
    Button Btn2;
    Button Btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSecond = findViewById(R.id.txtSecond);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtResult = findViewById(R.id.txtResult);
        txtAnswer = findViewById(R.id.txtAnswer);
        Btn0 = findViewById(R.id.Btn0);
        Btn1 = findViewById(R.id.Btn1);
        Btn2 = findViewById(R.id.Btn2);
        Btn3 = findViewById(R.id.Btn3);
        numTries = 0;
        numSuccess = 0;

        newTask();

    }

    public void newTask() {

        Random r = new Random();
        num1 = r.nextInt(11);
        num2 = r.nextInt(11);
        sum = num1 + num2;
        answer = new ArrayList<>();

        txtQuestion.setText(num1 + "+" + num2);
        txtAnswer.setText(numSuccess + " / " + numTries);
        locationOfCorrectAnswer = r.nextInt(4);

        for (int i = 0; i < 4; i++) {
            if (locationOfCorrectAnswer == i) {
                answer.add(sum);
            } else {
                int inCorrectAnswer = r.nextInt(21);
                while (inCorrectAnswer == sum) {
                    inCorrectAnswer = r.nextInt(21);
                }
                answer.add(inCorrectAnswer);
            }
        }

        Btn0.setText(Integer.toString(answer.get(0)));
        Btn1.setText(Integer.toString(answer.get(1)));
        Btn2.setText(Integer.toString(answer.get(2)));
        Btn3.setText(Integer.toString(answer.get(3)));

        countDownTimer = new CountDownTimer(32000, 1000) {
            @Override
            public void onTick(long l) {
                txtSecond.setText(second + " s ");
                second--;
            }

            @Override
            public void onFinish() {
                txtResult.setText("Time is out");
                countDownTimer.cancel();
                second = 30;
                numTries++;
                newTask();
            }
        }.start();
    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            txtResult.setText("Correct");
            numSuccess++;
        } else {
            txtResult.setText("Wrong!!");
        }
        countDownTimer.cancel();
        second = 30;
        numTries++;
        newTask();
    }
}
