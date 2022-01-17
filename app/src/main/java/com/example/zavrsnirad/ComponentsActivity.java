package com.example.zavrsnirad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ComponentsActivity extends AppCompatActivity implements JumpActivity {

    private ComponentViewModel componentViewModel;
    private String type;
    private ComponentAdapter adapter;
    private List<Component> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components);

        Intent intent= getIntent();
        type=intent.getStringExtra("position");

        if (type.equals("Procesori")){
            type="CPU";
        }
        if (type.equals("Grafičke karte")){
            type="GPU";
        }
        if (type.equals("RAM memorija")){
            type="RAM";
        }
        if (type.equals("Napajanja")){
            type="PSU";
        }
        if (type.equals("Solid State diskovi")){
            type="SSD";
        }
        if (type.equals("Tvrdi diskovi")){
            type="HDD";
        }
        if (type.equals("Kučišta")){
            type="Case";
        }
        if (type.equals("Matične ploče")){
            type="Motherboard";
        }


        RecyclerView recyclerView=findViewById(R.id.recyclerComponents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new ComponentAdapter(this);
        recyclerView.setAdapter(adapter);



        componentViewModel= ViewModelProviders.of(this).get(ComponentViewModel.class);
        componentViewModel.getAllComponents().observe(this, new Observer<List<Component>>() {
            @Override
            public void onChanged(List<Component> components) {
                List<Component> componentsByType= new ArrayList<>();
                for(int i=0;i<components.size();i++){
                    if (components.get(i).getType().equals(type)){
                        Component component=new Component(components.get(i).getType(),components.get(i).getPartNumber(),components.get(i).getBrand(),
                                                        components.get(i).getModel(),components.get(i).getRank(),components.get(i).getBenchmark(),
                                                        components.get(i).getSamples(),components.get(i).getUrl());
                        componentsByType.add(component);
                    }
                }
                lista=new ArrayList<>(componentsByType);
                adapter.setComponents(componentsByType);
                adapter= new ComponentAdapter(lista,ComponentsActivity.this::jumpOnTextClick);
                recyclerView.setAdapter(adapter);
            }
        });


    }

    @Override
    public void jumpOnTextClick(int position) {
        Intent intent= new Intent(ComponentsActivity.this, SpecificComponentActivity.class);
        intent.putExtra("type",adapter.getType(position));
        intent.putExtra("partNumber",adapter.getPartNumber(position));
        intent.putExtra("model",adapter.getModel(position));
        intent.putExtra("brand",adapter.getBrand(position));
        intent.putExtra("rank",adapter.getRank(position));
        intent.putExtra("samples",adapter.getSamples(position));
        intent.putExtra("benchmark",adapter.getBenchmark(position));
        intent.putExtra("url",adapter.getURL(position));
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);

        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}