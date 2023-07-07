package com.engnect.final_proje;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Rules extends AppCompatActivity {
    TextView tv_rules;
    Button readyButton;
    Intent intent_rules;
    String rules = "- Her bir doğru cevap skora 5 puan ekler.\n" +
            "- Eğer yanlış yapılırsa o sorudan puan kazanılmaz.\n\n" +
            "   Hazırsan aşağıdaki HAZIRIM butonuna tıkla.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        getSupportActionBar().hide();
        tv_rules = (TextView) findViewById(R.id.textView);
        readyButton = (Button) findViewById(R.id.readyButton);
        tv_rules.setText(rules);
        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Rules.this);

                builder.setCancelable(false);
                builder.setTitle("HAZIRIM");
                builder.setMessage("Yarışmaya başlamaya emin misin?");

                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent_rules = new Intent(Rules.this, Categories.class);
                        startActivity(intent_rules);

                    }
                });
                builder.show();
            }
        });

    }
}