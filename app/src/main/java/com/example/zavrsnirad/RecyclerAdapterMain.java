package com.example.zavrsnirad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterMain extends RecyclerView.Adapter<MainViewHolder> {

    private List<String> komopnenteList=new ArrayList<>();
    private JumpActivity activity;

    public RecyclerAdapterMain(JumpActivity clickActivity){ this.activity=clickActivity;}
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_recycler_main,parent,false);
        return new MainViewHolder(cellView,activity);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.setKomponente(komopnenteList.get(position));
    }

    @Override
    public int getItemCount() {
        return komopnenteList.size();
    }

    public void addData(List<String>data){
        this.komopnenteList.clear();
        this.komopnenteList.addAll(data);
        notifyDataSetChanged();
    }

    public String getPosition(int position){
        return komopnenteList.get(position);
    }
}
