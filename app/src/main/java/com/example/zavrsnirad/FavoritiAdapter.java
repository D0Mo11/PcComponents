package com.example.zavrsnirad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoritiAdapter extends RecyclerView.Adapter<FavoritiViewHolder> {

    private List<Component> komopnenteList=new ArrayList<>();
    private RemoveRecycler activity;

    public FavoritiAdapter(RemoveRecycler clickActivity) {this.activity=clickActivity;}
    @NonNull
    @Override
    public FavoritiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_favoriti_recycler,parent,false);
        return new FavoritiViewHolder(cellView,activity);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritiViewHolder holder, int position) {
        Component currentComponent=komopnenteList.get(position);
        holder.setProizvod(currentComponent.getBrand()+" "+currentComponent.getModel());
        holder.setTipProizvoda(currentComponent.getType());
    }

    @Override
    public int getItemCount() {
        return komopnenteList.size();
    }

    public void addData(List<Component>data){
        this.komopnenteList.clear();
        this.komopnenteList.addAll(data);
        notifyDataSetChanged();
    }

    public void addNewCell(Component name, int position){
        if(komopnenteList.size()>=position){
            komopnenteList.add(position,name);
            notifyItemInserted(position);
        }
    }

    public void removeCell(int position){
        if (komopnenteList.size()>position){
            komopnenteList.remove(position);
            notifyItemRemoved(position);
        }
    }


    public String getType(int position){
        return komopnenteList.get(position).getType();
    }
    public String getPartNumber(int position){ return komopnenteList.get(position).getPartNumber(); }
    public String getModel(int position){ return komopnenteList.get(position).getModel(); }
    public String getBrand(int position){
        return komopnenteList.get(position).getBrand();
    }
    public String getRank(int position){
        return komopnenteList.get(position).getRank();
    }
    public String getSamples(int position){
        return komopnenteList.get(position).getSamples();
    }
    public String getBenchmark(int position){
        return komopnenteList.get(position).getBenchmark();
    }
    public String getURL(int position){
        return komopnenteList.get(position).getUrl();
    }
}
