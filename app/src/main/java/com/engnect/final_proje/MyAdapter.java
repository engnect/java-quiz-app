package com.engnect.final_proje;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter<String> {

    int [] kategori_resim;
    String [] kategoriler;
    TextView kategoriTV;
    ImageView image_view;
    Context context;
    LinearLayout bg_change;

    public MyAdapter(int [] kategori_resim, String [] kategoriler, Context context){
        super(context, R.layout.mytextview, kategoriler);
        this.kategori_resim=kategori_resim;
        this.kategoriler=kategoriler;
        this.context=context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View view = LayoutInflater.from(context).inflate(R.layout.mytextview, null);
        if(view!=null){
            bg_change = view.findViewById(R.id.bg_change);
            kategoriTV = view.findViewById(R.id.kategoriTV);
            image_view = view.findViewById(R.id.imageView);
            kategoriTV.setText((String.valueOf(kategoriler[position])));
            image_view.setImageResource(kategori_resim[position]);
        }
        return view;
    }
}
