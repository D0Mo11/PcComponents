package com.example.zavrsnirad;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView tvKomponente;
    JumpActivity activity;

    public MainViewHolder(@NonNull View itemView, JumpActivity activity) {
        super(itemView);
        this.activity=activity;
        tvKomponente=itemView.findViewById(R.id.tvKomponente);
        tvKomponente.setOnClickListener(this::onClick);
    }

    public void setKomponente(String name){
        tvKomponente.setText(name);
    }

    @Override
    public void onClick(View v) {
        activity.jumpOnTextClick(getAdapterPosition());
    }
}
