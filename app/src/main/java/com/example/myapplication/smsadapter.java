package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class smsadapter extends RecyclerView.Adapter<smsadapter.myviewholder>{

    List<responsemodel> data;


    public smsadapter(List<responsemodel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.message.setText(data.get(position).getSms());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView message;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message);
        }
    }
}
