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

public class CourseAdapter  extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{
    private static final String TAG = "CourseAdapter";

    private List<CourseModel> courseModelList = new ArrayList<>();
    private OnCourseListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseModel model = courseModelList.get(position);
        holder.course_title_view.setText(model.getCourse_title());
        holder.course_start_date_view.setText(model.getCourse_start_date());
        holder.course_end_date_view.setText(model.getCourse_end_date());
        holder.course_status_view.setText(model.getCourse_status().toString());
        holder.course_instructor_view.setText(model.getCourse_instructor().getName());

        // listeners
        holder.course_instructor_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current_position = holder.getAdapterPosition();
                listener.onInstructorClicked(courseModelList.get(current_position));
            }
        });
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current_position = holder.getAdapterPosition();
                if(listener != null && current_position != RecyclerView.NO_POSITION){
                    listener.onDeleteClicked(courseModelList.get(current_position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseModelList.size();
    }
    public void setCourseModelList(List<CourseModel> courseModels){
        this.courseModelList = courseModels;
        notifyDataSetChanged();
    }
    public CourseModel getCourseAt(int position) { return  courseModelList.get(position); }
    public  class  ViewHolder extends RecyclerView.ViewHolder {
        TextView course_title_view,
        course_start_date_view,
        course_end_date_view,
        course_status_view,
        course_instructor_view;
        ImageButton delete_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            course_title_view = itemView.findViewById(R.id.course_title);
            course_start_date_view = itemView.findViewById(R.id.course_start_date);
            course_end_date_view =  itemView.findViewById(R.id.course_end_date);
            course_status_view =  itemView.findViewById(R.id.course_status);
            course_instructor_view =  itemView.findViewById(R.id.course_instructor);
            delete_btn = itemView.findViewById(R.id.course_delete);
            // click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int current_position = getAdapterPosition();
                    if(listener != null && current_position != RecyclerView.NO_POSITION){
                        listener.onCourseClicked(courseModelList.get(current_position));
                    }
                }
            });
        }
    }

    public interface OnCourseListener{
        void onCourseClicked(CourseModel course);
        void onDeleteClicked(CourseModel course);
        void onInstructorClicked(CourseModel course);
    }
    public void setCourseListerOnClick(OnCourseListener listener){
        this.listener = listener;
    }
}
