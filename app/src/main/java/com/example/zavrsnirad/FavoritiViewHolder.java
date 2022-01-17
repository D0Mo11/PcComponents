package com.example.zavrsnirad;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoritiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView tvProizvod;
    private TextView tvTipProizvoda;
    private ImageView ivDelete;
    RemoveRecycler activity;

    public FavoritiViewHolder(@NonNull View itemView, RemoveRecycler activity) {
        super(itemView);
        this.activity=activity;
        ivDelete=itemView.findViewById(R.id.ivDelete);
        tvProizvod=itemView.findViewById(R.id.tvFavoritiProizvod);
        tvTipProizvoda=itemView.findViewById(R.id.tvFavoritiProizvodTip);
        ivDelete.setOnClickListener(this::onClick);
        tvProizvod.setOnClickListener(this::onClick);
    }

    public void setProizvod(String name){
        tvProizvod.setText(name);
    }

    public void setTipProizvoda(String name){
        String tip;
        if(name.equals("CPU")){
            tip="Procesor";
            tvTipProizvoda.setText(tip);
        }
        if(name.equals("GPU")){
            tip="Grafička kartica";
            tvTipProizvoda.setText(tip);
        }
        if(name.equals("SSD")){
            tip="SSD";
            tvTipProizvoda.setText(tip);
        }
        if(name.equals("HDD")){
            tip="HDD";
            tvTipProizvoda.setText(tip);
        }
        if(name.equals("RAM")){
            tip="RAM memorija";
            tvTipProizvoda.setText(tip);
        }
        if(name.equals("PSU")){
            tip="Napajanje";
            tvTipProizvoda.setText(tip);
        }
        if(name.equals("Case")){
            tip="Kučište";
            tvTipProizvoda.setText(tip);
        }
        if(name.equals("Motherboard")){
            tip="Matična ploča";
            tvTipProizvoda.setText(tip);
        }
        /*else {
            tvTipProizvoda.setText(name);
        }*/
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==ivDelete.getId()){
            activity.onImageRemove(getAdapterPosition());
        }
        else if(v.getId()==tvProizvod.getId()){
            activity.onTextClick(getAdapterPosition());
        }

    }
}
