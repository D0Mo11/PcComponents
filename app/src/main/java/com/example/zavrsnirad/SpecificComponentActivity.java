package com.example.zavrsnirad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SpecificComponentActivity extends AppCompatActivity {
    private TextView tvProizvod;
    private TextView tvProizvodac;
    private TextView tvModel;
    private TextView tvSpecifikacije;
    private Button btnJeftinije;
    private Button btnNabava;
    private Button btnFavorit;
    private String proizvod;
    private String jeftinijeURL;
    private String nabavaURL;
    protected ComponentDatabase db;
    private String brand;
    private String model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_component);
        this.tvProizvod=findViewById(R.id.tvProizvod);
        this.tvProizvodac=findViewById(R.id.tvProizvodac);
        this.tvModel=findViewById(R.id.tvModel);
        this.tvSpecifikacije=findViewById(R.id.tvSpecifikacije);
        this.btnJeftinije=findViewById(R.id.btnJeftinije);
        this.btnNabava=findViewById(R.id.btnNabava);
        this.btnFavorit=findViewById(R.id.btnFavorit);
        db= ComponentDatabase.getInstance(this);

        Intent intent=getIntent();
        proizvod=intent.getStringExtra("brand")+" "+intent.getStringExtra("model");
        tvProizvod.setText(proizvod);
        tvProizvodac.setText(intent.getStringExtra("brand"));
        tvModel.setText(intent.getStringExtra("model"));
        tvSpecifikacije.setText(intent.getStringExtra("url"));
        brand=intent.getStringExtra("brand");
        model=intent.getStringExtra("model");

        jeftinijeURL="https://www.jeftinije.hr/Trazenje/Proizvodi?q="+proizvod;
        nabavaURL="https://www.nabava.net/search.php?q="+proizvod+"&cod=&cdo=";

        btnJeftinije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl(jeftinijeURL);
            }
        });

        btnNabava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl(nabavaURL);
            }
        });

        btnFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.componentDao().makeFavorite(brand,model);
                makeToast();
            }


        });
    }
    private void goToUrl(String s) {
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    private void makeToast() {
        Toast.makeText(SpecificComponentActivity.this,"Dodano u favorite!",Toast.LENGTH_SHORT).show();
    }
}