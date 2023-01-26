package com.gitlab.schoolsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.ViewHolder> {

    private static final String TAG = "AssessAdapter";
    private Context context;
    private List<AssessmentModel> assessmentModelList;

    public AssessmentAdapter(Context context, List<AssessmentModel> assessmentModelList){
        this.context = context;
        this.assessmentModelList = assessmentModelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assessment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AssessmentModel assessmentModel = assessmentModelList.get(position);
        holder.assessment_name.setText(assessmentModel.getName());
        holder.assessment_due_date.setText(assessmentModel.getDue_date());
        Button done_btn = holder.itemView.findViewById(R.id.done_btn);
        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.assessment_name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Operation cannot be undone.");
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        assessmentModelList.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.assessment_name.getContext(), "Done canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position_ = holder.getAdapterPosition();
                AssessmentModel model = assessmentModelList.get(position_);
                AssessmentDialog.getInstance().buildDialog(context, AssessmentAdapter.this, model, position_).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return assessmentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView assessment_name, assessment_due_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            assessment_name = itemView.findViewById(R.id.assessment_title);
            assessment_due_date = itemView.findViewById(R.id.due_date);
        }
    }
}
