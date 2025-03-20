package com.example.kostki;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView rollResultTv;
    TextView globalResultTv;

    ImageView[] dices;
    int[] drawableDices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.rollResultTv = findViewById(R.id.rollResultText);
        this.globalResultTv = findViewById(R.id.gameResultText);

       this.dices = new ImageView[] {findViewById(R.id.dice1),findViewById(R.id.dice2),findViewById(R.id.dice3),
                findViewById(R.id.dice4),findViewById(R.id.dice5)};
        this.drawableDices = new int[] {R.drawable.k1, R.drawable.k2, R.drawable.k3, R.drawable.k4, R.drawable.k5, R.drawable.k6 };

    }

    Integer globalResult = 0;
    Integer rollResult = 0;


    public void rollDiceBtnClick(View v) {
        Random randomGenerator = new Random();
        for(int i = 0; i < 5; i++) {
            int randomInt = randomGenerator.nextInt(6) + 1;
            rollResult += randomInt;
            dices[i].setImageResource(drawableDices[randomInt-1]);
        }

        globalResult += rollResult;
        setResultOnTextView(rollResultTv, "Wynik tego losowania:", rollResult);
        setResultOnTextView(globalResultTv, "Wynik gry:", globalResult);
        rollResult = 0;
    }
    public void resetResultBtnClick(View v) {
        globalResult = 0;
        rollResult = 0;
        setResultOnTextView(rollResultTv, "Wynik tego losowania:", rollResult);
        setResultOnTextView(globalResultTv, "Wynik gry:", globalResult);
    }
    void setResultOnTextView(TextView textView, String pretext, Integer result) {
        textView.setText(String.format("%s%d",pretext,result));
    }


}