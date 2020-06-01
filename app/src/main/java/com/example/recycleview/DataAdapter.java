package com.example.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    List<Data> dataList;
    Context context;

    public DataAdapter(List<Data> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data, parent, false);
        DataViewHolder dataViewHolder = new DataViewHolder(view);

        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, final int position) {
        Data data = dataList.get(position);

        holder.tvName.setText(data.getName());
        holder.tvRealName.setText(data.getRealName());
        holder.tvTeam.setText(data.getTeam());
        holder.tvBio.setText(data.getBio());
        holder.tvBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvRealName,tvTeam,tvBio;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvRealName = itemView.findViewById(R.id.tvRealName);
            tvTeam = itemView.findViewById(R.id.tvTeam);
            tvBio = itemView.findViewById(R.id.tvBio);
        }
    }
}
