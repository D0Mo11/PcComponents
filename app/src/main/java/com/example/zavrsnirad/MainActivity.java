package com.example.zavrsnirad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JumpActivity {

    private RecyclerView recycler;
    private RecyclerAdapterMain adapterMain;
    private Button btnFavoriti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnFavoriti=findViewById(R.id.btnListaFavorita);

        recycler=findViewById(R.id.recylcerMain);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapterMain= new RecyclerAdapterMain(this);
        recycler.setAdapter(adapterMain);

        List<String> data = new ArrayList<>();
        data.add("Procesori");
        data.add("Grafičke karte");
        data.add("RAM memorija");
        data.add("Napajanja");
        data.add("Solid State diskovi");
        data.add("Tvrdi diskovi");
        data.add("Kučišta");
        data.add("Matične ploče");

        adapterMain.addData(data);

        btnFavoriti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FavoritiActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void jumpOnTextClick(int position) {
        Intent intent= new Intent(MainActivity.this, ComponentsActivity.class);
        intent.putExtra("position",adapterMain.getPosition(position));
        startActivity(intent);
    }
}