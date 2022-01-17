package com.example.zavrsnirad;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ComponentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView tvName;
    JumpActivity activity;
    public ComponentViewHolder(@NonNull View itemView, JumpActivity activity) {
        super(itemView);
        this.activity=activity;
        tvName=itemView.findViewById(R.id.tvComponent);
        tvName.setOnClickListener(this::onClick);
    }

    public void setName(String name){
        tvName.setText(name);
    }

    @Override
    public void onClick(View v) {
        activity.jumpOnTextClick(getAdapterPosition());
    }
}
