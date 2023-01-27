package com.gitlab.schoolsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.ViewHolder> {

    private static final String TAG = "AssessAdapter";

    private Context context;
    private List<AssessmentModel> assessmentModelList = new ArrayList<>();
    OnItemClicked listener;

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
    }

    @Override
    public int getItemCount() {
        return assessmentModelList.size();
    }

    public void setAssessmentModelList(List<AssessmentModel> assessmentModelList) {
        this.assessmentModelList = assessmentModelList;
        notifyDataSetChanged();
    }
    public AssessmentModel getModelAt(int position){
        return assessmentModelList.get(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView assessment_name,
        assessment_due_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            assessment_name = itemView.findViewById(R.id.assessment_title);
            assessment_due_date = itemView.findViewById(R.id.due_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int current_position = getAdapterPosition();
                    if(listener != null && current_position != RecyclerView.NO_POSITION){
                        listener.onItemClicked(assessmentModelList.get(current_position));
                    }
                }
            });
        }
    }
    public interface OnItemClicked{
        void onItemClicked(AssessmentModel assessmentModel);
    }
    public void setAssessmentListenerOnClick(OnItemClicked listener){
        this.listener = listener;
    }
}
