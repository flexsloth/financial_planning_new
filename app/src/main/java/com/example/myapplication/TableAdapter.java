package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    private List<expence_details_item> dataList;
    public TableAdapter(List<expence_details_item> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        expence_details_item model = dataList.get(position);
        holder.name.setText(model.getName());
        holder.value.setText(model.getValue());
        // Set the ImageView directly from ExpenseDetailsItem
        holder.iv.setImageResource(model.getIv());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, value;
        ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.expence_name_item);
            value = itemView.findViewById(R.id.expense_val_item);
            iv = itemView.findViewById(R.id.expense_image_item);
        }
    }
}
