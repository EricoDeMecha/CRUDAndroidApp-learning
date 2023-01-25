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


import java.util.List;

public class CourseAdapter  extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{
    private static final String TAG = "CourseAdapter";
    private Context context;
    private List<CourseModel> courseModelList;

    public CourseAdapter(Context context, List<CourseModel> courseModelList) {
        this.courseModelList = courseModelList;
        this.context = context;
    }

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
                // TODO : add an option to update instructor's details
                int position = holder.getAdapterPosition();
                InstructorModel instructor  = courseModelList.get(position).getCourse_instructor();
                // create a dialog
                InstructorDialog.getInstance().buildDialog(context, CourseAdapter.this, instructor, position).show();
            }
        });

        ImageButton delete_btn = holder.itemView.findViewById(R.id.course_delete);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.course_title_view.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Operation cannot be undone.");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        courseModelList.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.course_title_view.getContext(), "Delete canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseModelList.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {
        TextView course_title_view, course_start_date_view, course_end_date_view, course_status_view, course_instructor_view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            course_title_view = itemView.findViewById(R.id.course_title);
            course_start_date_view = itemView.findViewById(R.id.course_start_date);
            course_end_date_view =  itemView.findViewById(R.id.course_end_date);
            course_status_view =  itemView.findViewById(R.id.course_status);
            course_instructor_view =  itemView.findViewById(R.id.course_instructor);
        }
    }
}
