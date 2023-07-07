package com.engnect.final_proje;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Categories extends AppCompatActivity {

    GridView gridView;
    Intent categories_intent;
    MyAdapter adpt;
    String kategoriler [] = {"Bilim", "Sanat", "Genel Kültür", "Coğrafya", "Tarih", "Sağlık", "Spor", "Film",
    "Teknoloji", "Edebiyat"};

    int [] kategori_resim = {R.drawable.bilim_bg,R.drawable.sanat_bg,R.drawable.genelkultur_bg, R.drawable.cografya_bg,R.drawable.tarih_bg,R.drawable.saglik_bg,
            R.drawable.spor_bg,R.drawable.film_bg,R.drawable.teknoloji_bg,R.drawable.edebiyat_bg };

    String sorular [] ={"İlk telefonu kim icat etti?",
            "Resimdeki sanat eseri hangi ressamın eseridir?",
            "Dünyanın ilk kadın savaş pilotu kimdir?",
            "Hangi iki ilimiz komşudur?",
            "Bağımsız bir Rum Devleti kurmak amacıyla çıkartılan isyan hangisidir?",
            "Aşağıdakilerden hangisi ruh sağlığını etkileyen kişisel faktörlerden biri değildir?",
            "2018 Dünya Kupası'nı hangi ülke kazandı?",
            "Fotoğraftaki karakter hangi filmde oynamaktadır?",
            "WWW(World Wide Web) nerede icat edildi?",
            "Kurtuluş Savaşı üzerine yazılan ilk roman hangisidir"};

    int [] soru_resim = {0,R.drawable.yildizli_gece,0,0,0,0,0,R.drawable.star_wars,0,0};

    String optionA [] = {"Alexander Graham Bell","Claude Monet","Raymonde de Laroche","Çorum - Ankara","Ermeni İsyanı","Yaş","Rusya","The Usual Suspects (Olağan Şüpheliler)","ABD","Ateşten Gömlek"};
    String optionB [] = {"Thomas Edison","Vincent van Gogh","Bedriye Tahir","Kırıkkale - Nevşehir","Rum İsyanı","Aile","Hırvatistan","The Lord Of The Rings (Yüzüklerin Efendisi)","Almanya","Türk'ün Ateşle İmtihanı"};
    String optionC [] = {"Nikola Tesla","Pablo Picasso ","Sabiha Gökçen","İstanbul - Yalova","Şeyh Said Ayaklanması","Meslek","Brezilya","Star Wars (Yıldız Savaşları)","İngiltere","Yaban"};
    String optionD [] = {"Isaac Newton","Paul Cezanne ","Bonnie Tiburzi","Rize - Erzurum","Şeyh Bedreddin İsyanı","Cinsiyet","Fransa","Interstellar (Yıldızlararası)","İsviçre","Reşat Nuri Güntekin"};
    String correctOpt [] = {"Alexander Graham Bell","Vincent van Gogh","Sabiha Gökçen","Rize - Erzurum","Rum İsyanı","Aile","Fransa","Star Wars (Yıldız Savaşları)","İsviçre","Ateşten Gömlek"};

    private int clickedItemPosition = -1; // Varsayılan olarak -1
    int skor=0, cevaplanan_soru_sayisi=0;
    Button skorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().hide();
        skorButton = (Button) findViewById(R.id.buttonSkor);
        gridView = (GridView) findViewById(R.id.gridView);
        adpt = new MyAdapter(kategori_resim, kategoriler, this);
        gridView.setAdapter(adpt);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                clickedItemPosition = i;
                categories_intent = new Intent(Categories.this, Question.class);
                categories_intent.putExtra("kategori_adi", kategoriler[i]);
                categories_intent.putExtra("sorular", sorular[i]);
                categories_intent.putExtra("optionAtext", optionA[i]);
                categories_intent.putExtra("optionBtext", optionB[i]);
                categories_intent.putExtra("optionCtext", optionC[i]);
                categories_intent.putExtra("optionDtext", optionD[i]);
                categories_intent.putExtra("correctAnswers", correctOpt[i]);
                categories_intent.putExtra("cevaplananSoruSayisi",cevaplanan_soru_sayisi);

                if(soru_resim[i] != 0){
                    categories_intent.putExtra("soru_resim", soru_resim[i]);
                }

                startActivityForResult(categories_intent, 1);
            }
        });

        skorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Categories.this);
                builder.setCancelable(false);
                builder.setTitle("SKOR");
                builder.setMessage("Skorunuz "+ skor);

                builder.setPositiveButton("KAPAT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Veriyi al
            int isDataValid = data.getIntExtra("sonucAnahtari", 0);
            boolean endYarisma = data.getBooleanExtra("endYarisma", false);
            cevaplanan_soru_sayisi = cevaplanan_soru_sayisi + 1;

            View clickedView = gridView.getChildAt(clickedItemPosition);
            // Doğru olup olmadığını kontrol et
            if (isDataValid == 1 && clickedItemPosition != -1) {
                // Tıklanan öğenin View'ini al
                //View clickedView = gridView.getChildAt(clickedItemPosition);
                skor = skor + 5;
                // Arka plan rengini değiştir
                clickedView.setBackgroundColor(Color.GREEN); // İstediğiniz rengi kullanabilirsiniz
                clickedView.setEnabled(false);
                clickedView.setOnClickListener(null);
                Log.d("Sonuc", String.valueOf(skor));
            }
            else if(isDataValid == 2 && clickedItemPosition!=-1){
                clickedView.setBackgroundColor(Color.RED);
                clickedView.setEnabled(false);
                clickedView.setOnClickListener(null);
            }


            if(endYarisma || cevaplanan_soru_sayisi == 10){
                AlertDialog.Builder builder = new AlertDialog.Builder(Categories.this);
                builder.setCancelable(false);
                builder.setTitle("SKOR");
                builder.setMessage("Skorunuz "+ skor);
                builder.setPositiveButton("KAPAT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent backToHomeScreen = new Intent(Categories.this, MainActivity.class);
                        startActivity(backToHomeScreen);
                    }
                });

                builder.show();
            }



        }

    }
}