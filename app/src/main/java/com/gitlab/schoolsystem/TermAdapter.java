package com.gitlab.schoolsystem;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {
    private List<TermModel> termList = new ArrayList<>();
    private OnTermListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.term,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TermModel termModel = termList.get(position);
        holder.term_name_view.setText(termModel.getTerm_name());
        holder.start_date_view.setText(termModel.getStart_date());
        holder.end_date_view.setText(termModel.getEnd_date());

        holder.update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current_position = holder.getAdapterPosition();
                if(listener != null && current_position != RecyclerView.NO_POSITION){
                    listener.onUpdateButtonClicked(termList.get(current_position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return termList.size();
    }

    public void setTermList(List<TermModel> list){
        this.termList = list;
        notifyDataSetChanged();
    }

    public TermModel getTermAt(int position){
        return termList.get(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView term_name_view,
        start_date_view ,
        end_date_view;
        ImageButton update_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            term_name_view = itemView.findViewById(R.id.termname);
            start_date_view = itemView.findViewById(R.id.startdate);
            end_date_view = itemView.findViewById(R.id.enddate);
            update_btn = itemView.findViewById(R.id.updatebtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int current_position = getAdapterPosition();
                    if(listener != null && current_position != RecyclerView.NO_POSITION){
                        listener.onTermClicked(termList.get(current_position));
                    }
                }
            });
        }
    }

    public interface OnTermListener{
        void onTermClicked(TermModel term);
        void onUpdateButtonClicked(TermModel term);
    }
    public void setListenerOnClick(OnTermListener listener){
        this.listener = listener;
    }
}

