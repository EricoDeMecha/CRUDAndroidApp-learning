package com.gitlab.schoolsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private static final String TAG = "NoteAdapter";

    private List<NoteModel> noteModelList = new ArrayList<>();
    private OnItemListener listener;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteModel noteModel = noteModelList.get(position);
        holder.note_title_view.setText(noteModel.getNote_title());
        holder.note_body_view.setText(noteModel.getNode_body());
        holder.note_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteClicked(noteModelList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteModelList.size();
    }

    public void setNoteModelList(List<NoteModel> noteModelList){
        this.noteModelList = noteModelList;
        notifyDataSetChanged();
    }
    public NoteModel getAt(int position){
        return noteModelList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView note_title_view,
        note_body_view;
        ImageButton note_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            note_title_view = itemView.findViewById(R.id.note_title_view);
            note_body_view = itemView.findViewById(R.id.note_body_view);
            note_delete = itemView.findViewById(R.id.note_delete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int current_position = getAdapterPosition();
                    if(listener != null && current_position != RecyclerView.NO_POSITION){
                        listener.onItemClicked(noteModelList.get(current_position));
                    }
                }
            });
        }
    }
    public interface OnItemListener{
        void onItemClicked(NoteModel note);
        void onDeleteClicked(NoteModel note);
    }
    public void setNoteListener(OnItemListener listener){
        this.listener = listener;
    }
}
