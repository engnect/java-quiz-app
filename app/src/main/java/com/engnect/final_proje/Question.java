package com.engnect.final_proje;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Question extends AppCompatActivity {
    Intent getIntent, backToCategories;
    TextView kategoriAdTV, questionTV;
    ImageView soruIV;
    String kategoriAd, question, optionAtext, optionBtext, optionCtext, optionDtext,
            selectedOption, correctAns;
    String bg_kategori_renk;
    int soruResimIV;
    Button optionA, optionB, optionC, optionD, backButton, endButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getSupportActionBar().hide();
        kategoriAdTV = (TextView) findViewById(R.id.kategoriAdıTV);
        questionTV = (TextView) findViewById(R.id.questionTV);
        soruIV = (ImageView) findViewById(R.id.questionIV);
        optionA = (Button) findViewById(R.id.ButtonA);
        optionB = (Button) findViewById(R.id.ButtonB);
        optionC = (Button) findViewById(R.id.ButtonC);
        optionD = (Button) findViewById(R.id.ButtonD);
        backButton = (Button) findViewById(R.id.backButton);
        endButton = (Button) findViewById(R.id.endYarisma);

        getIntent = getIntent();
        kategoriAd = getIntent.getStringExtra("kategori_adi");
        question = getIntent.getStringExtra("sorular");
        soruResimIV = getIntent.getIntExtra("soru_resim", 0);
        optionAtext = getIntent.getStringExtra("optionAtext");
        optionBtext = getIntent.getStringExtra("optionBtext");
        optionCtext = getIntent.getStringExtra("optionCtext");
        optionDtext = getIntent.getStringExtra("optionDtext");
        correctAns = getIntent.getStringExtra("correctAnswers");
        bg_kategori_renk = getIntent.getStringExtra("bg_categories_color");

        kategoriAdTV.setText(kategoriAd);
        questionTV.setText(question);

        if(soruResimIV != 0){
            soruIV.getLayoutParams().height = 500;
        }
        soruIV.setImageResource(soruResimIV);
        optionA.setText(optionAtext);
        optionB.setText(optionBtext);
        optionC.setText(optionCtext);
        optionD.setText(optionDtext);

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = optionAtext;
                checkAns(selectedOption, correctAns);

            }
        });
        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = optionBtext;
                checkAns(selectedOption, correctAns);
            }
        });
        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = optionCtext;
                checkAns(selectedOption, correctAns);
            }
        });
        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = optionDtext;
                checkAns(selectedOption, correctAns);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question.super.onBackPressed();
            }
        });
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Question.this);
                builder.setCancelable(false);
                builder.setTitle("YARIŞMA BİTİR");
                builder.setMessage("Yarışmayı sonlandırmaya emin misiniz?");
                builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        backToCategories = new Intent(Question.this, Categories.class);
                        endContest(true);
                        setResult(RESULT_OK, backToCategories);
                        finish();

                    }
                });

                builder.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            }
        });
    }
    private void checkAns(String selectedOpt, String correctAnswer) {
        Log.d("check", "check ans içerisi");

        AlertDialog.Builder builder = new AlertDialog.Builder(Question.this);
        Log.d(selectedOption, "selectedOption");
        Log.d(optionAtext, "correctAnswer");

        if (selectedOpt.equals(correctAnswer)){

            builder.setCancelable(false);
            builder.setTitle("DOĞRU CEVAP");
            builder.setMessage("Soruyu doğru cevapladınız.");


            Log.d("Doğru_öncesi", "Doğru cevap öncesi");

            builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    backToCategories = new Intent(Question.this, Categories.class);
                    sendResult(1);
                    setResult(RESULT_OK, backToCategories);
                    finish();

                }
            });
            builder.show();
        }
        else{
            builder.setCancelable(false);
            builder.setTitle("YANLIŞ CEVAP");
            builder.setMessage("Soruyu yanlış cevapladınız.");

            builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    backToCategories = new Intent(Question.this, Categories.class);
                    sendResult(2);
                    setResult(RESULT_OK, backToCategories);
                    finish();
                }
            });
            builder.show();
        }
    }

    private void sendResult(int isDataValid) {
        Intent intent = new Intent();
        intent.putExtra("sonucAnahtari", isDataValid);
        setResult(RESULT_OK, intent);
        finish();
    }
    private void endContest(boolean endYarisma){
        Intent intent = new Intent();
        intent.putExtra("endYarisma", endYarisma);
        setResult(RESULT_OK, intent);
        finish();
    }
    public void onBackPressed(){
        //super.onBackPressed();
    }

}