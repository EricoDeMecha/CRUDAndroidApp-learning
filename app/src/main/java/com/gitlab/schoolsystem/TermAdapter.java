package com.gitlab.schoolsystem;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {
    Context context;
    List<TermModel> termList;
    private OnTermListener onTermListener;
    public TermAdapter(Context context, List<TermModel> termList, OnTermListener onTermListener){
        this.context = context;
        this.termList  = termList;
        this.onTermListener = onTermListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.term,parent, false);
        return new ViewHolder(view,onTermListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.term_name_view.setText(termList.get(position).getTerm_name());
        holder.start_date_view.setText(termList.get(position).getStart_date());
        holder.end_date_view.setText(termList.get(position).getEnd_date());

        Button delete_btn = holder.itemView.findViewById(R.id.deletebtn);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.term_name_view.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Operation cannot be undone.");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        termList.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.term_name_view.getContext(), "Delete canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return termList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView term_name_view, start_date_view , end_date_view;
        OnTermListener onTermListener;
        public ViewHolder(@NonNull View itemView, OnTermListener onTermListener) {
            super(itemView);
            term_name_view = itemView.findViewById(R.id.termname);
            start_date_view = itemView.findViewById(R.id.startdate);
            end_date_view = itemView.findViewById(R.id.enddate);

            this.onTermListener = onTermListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTermListener.onTermClicked(getAdapterPosition());
        }
    }

    public interface OnTermListener{
        void onTermClicked(int position);
    }
}

