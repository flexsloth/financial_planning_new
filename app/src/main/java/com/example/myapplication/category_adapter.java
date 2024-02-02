package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class category_adapter extends RecyclerView.Adapter<category_adapter.ViewHolder> {

    private List<catagery_item_obj> dataList;

    public category_adapter(List<catagery_item_obj> dataList) {
        this.dataList = dataList != null ? dataList : new ArrayList<>();
    }


    @NonNull
    @Override
    public category_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new category_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull category_adapter.ViewHolder holder, int position) {
        catagery_item_obj model = dataList.get(position);
        holder.name.setText(model.getCatname());
        holder.icon_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.icon_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView iv,icon_edit,icon_del;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.category_name_item);
            icon_edit = itemView.findViewById(R.id.category_options2);
            icon_del = itemView.findViewById(R.id.category_options);
           // iv = itemView.findViewById(R.id.category_image_item);
        }
    }
}