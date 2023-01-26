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

import java.util.List;

/**
 * The type Note adapter.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private static final String TAG = "NoteAdapter";
    private Context context;
    private List<NoteModel> noteModelList;

    /**
     * Instantiates a new Note adapter.
     *
     * @param context       the context
     * @param noteModelList the note model list
     */
    public NoteAdapter(Context context, List<NoteModel> noteModelList){
        this.context = context;
        this.noteModelList = noteModelList;
    }
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
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.note_title_view.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Operation cannot be undone.");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        noteModelList.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.note_title_view.getContext(), "Delete canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        holder.note_body_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                NoteModel model = noteModelList.get(position);
                NoteDialog.getInstance().buildDialog(context, NoteAdapter.this, model, position).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteModelList.size();
    }

    /**
     * The type View holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Note title view.
         */
        TextView note_title_view, /**
         * The Note body view.
         */
        note_body_view;
        /**
         * The Note delete.
         */
        ImageButton note_delete;

        /**
         * Instantiates a new View holder.
         *
         * @param itemView the item view
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            note_title_view = itemView.findViewById(R.id.note_title_view);
            note_body_view = itemView.findViewById(R.id.note_body_view);
            note_delete = itemView.findViewById(R.id.note_delete);
        }
    }
}
