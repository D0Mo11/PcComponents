package com.example.zavrsnirad;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ComponentAdapter extends RecyclerView.Adapter<ComponentViewHolder> implements Filterable {

    private List<Component> components= new ArrayList<>();
    private JumpActivity activity;
    private List<Component> componentsFull;

    public ComponentAdapter(JumpActivity clickActivity) {this.activity=clickActivity;}

    public ComponentAdapter(List<Component> components, JumpActivity clickActivity) {
        this.components=components;
        componentsFull=new ArrayList<>(components);
        this.activity=clickActivity;
    }



    @NonNull
    @Override
    public ComponentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ComponentViewHolder(itemView, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull ComponentViewHolder holder, int position) {
        Component currentComponent=components.get(position);
        holder.setName(currentComponent.getBrand()+" "+currentComponent.getModel());
    }

    @Override
    public int getItemCount() {
        return components.size();
    }

    public void setComponents(List<Component> components){
        this.components=components;
        notifyDataSetChanged();
    }

    public String getType(int position){
        return components.get(position).getType();
    }
    public String getPartNumber(int position){
        return components.get(position).getPartNumber();
    }
    public String getModel(int position){
        return components.get(position).getModel();
    }
    public String getBrand(int position){
        return components.get(position).getBrand();
    }
    public String getRank(int position){
        return components.get(position).getRank();
    }
    public String getSamples(int position){
        return components.get(position).getSamples();
    }
    public String getBenchmark(int position){
        return components.get(position).getBenchmark();
    }
    public String getURL(int position){
        return components.get(position).getUrl();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Component> filteredList=new ArrayList<>();

            if (constraint==null || constraint.length()==0){
                filteredList.addAll(componentsFull);
            }
            else {
                String filterPattern=constraint.toString().toLowerCase().trim();

                for (Component item : componentsFull){
                    if (item.getBrand().toLowerCase().contains(filterPattern) || item.getModel().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results= new FilterResults();
            results.values=filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            components.clear();
            components.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
