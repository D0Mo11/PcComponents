package com.example.zavrsnirad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FavoritiActivity extends AppCompatActivity implements RemoveRecycler {
    private RecyclerView recycler;
    private FavoritiAdapter adapter;
    protected ComponentDatabase db;
    private List<Component> favoriteComponents= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoriti);
        db= ComponentDatabase.getInstance(this);

        recycler=findViewById(R.id.recyclerFavoriti);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter= new FavoritiAdapter(this);
        recycler.setAdapter(adapter);

        favoriteComponents=db.componentDao().favoriteComponents();

        for (int i=0;i<favoriteComponents.size();i++){
            Component component= new Component(favoriteComponents.get(i).getType(),favoriteComponents.get(i).getPartNumber(),favoriteComponents.get(i)
                    .getBrand(),favoriteComponents.get(i).getModel(),favoriteComponents.get(i).getRank(),favoriteComponents.get(i).getBenchmark(),
                    favoriteComponents.get(i).getSamples(),favoriteComponents.get(i).getUrl());
            adapter.addNewCell(component,adapter.getItemCount());
        }

        }
    @Override
    public void onImageRemove(int position) {
        db.componentDao().removeFavorite(favoriteComponents.get(position).getBrand(),favoriteComponents.get(position).getModel());
        adapter.removeCell(position);
    }

    @Override
    public void onTextClick(int position) {
        Intent intent= new Intent(FavoritiActivity.this, SpecificComponentActivity.class);
        intent.putExtra("type",adapter.getType(position));
        intent.putExtra("partNumber",adapter.getPartNumber(position));
        intent.putExtra("brand",adapter.getBrand(position));
        intent.putExtra("model",adapter.getModel(position));
        intent.putExtra("rank",adapter.getRank(position));
        intent.putExtra("samples",adapter.getSamples(position));
        intent.putExtra("benchmark",adapter.getBenchmark(position));
        intent.putExtra("url",adapter.getURL(position));
        startActivity(intent);
    }
}