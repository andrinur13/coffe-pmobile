package com.example.responsi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HalamanAdmin extends AppCompatActivity {

    ImageView baristaBtn, tokoKopiBtn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_admin);


        baristaBtn = (ImageView)findViewById(R.id.list_barista);
        tokoKopiBtn = (ImageView)findViewById(R.id.list_toko_kopi);

        baristaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanAdmin.this, ListKaryawanAdmin.class);
                startActivity(intent);
            }
        });

        tokoKopiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HalamanAdmin.this, "list toko kopi diklik", Toast.LENGTH_SHORT).show();
            }
        });

    }
}