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
 * The type Course adapter.
 */
public class CourseAdapter  extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{
    private static final String TAG = "CourseAdapter";
    private Context context;
    private List<CourseModel> courseModelList;
    private OnCourseListener  onCourseListener;

    /**
     * Instantiates a new Course adapter.
     *
     * @param context          the context
     * @param courseModelList  the course model list
     * @param onCourseListener the on course listener
     */
    public CourseAdapter(Context context, List<CourseModel> courseModelList, OnCourseListener onCourseListener) {
        this.courseModelList = courseModelList;
        this.context = context;
        this.onCourseListener = onCourseListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course, parent, false);
        return  new ViewHolder(view, onCourseListener);
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

    /**
     * The type View holder.
     */
    public  class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * The Course title view.
         */
        TextView course_title_view, /**
         * The Course start date view.
         */
        course_start_date_view, /**
         * The Course end date view.
         */
        course_end_date_view, /**
         * The Course status view.
         */
        course_status_view, /**
         * The Course instructor view.
         */
        course_instructor_view;
        /**
         * The On course listener.
         */
        OnCourseListener onCourseListener;

        /**
         * Instantiates a new View holder.
         *
         * @param itemView         the item view
         * @param onCourseListener the on course listener
         */
        public ViewHolder(@NonNull View itemView, OnCourseListener onCourseListener) {
            super(itemView);
            course_title_view = itemView.findViewById(R.id.course_title);
            course_start_date_view = itemView.findViewById(R.id.course_start_date);
            course_end_date_view =  itemView.findViewById(R.id.course_end_date);
            course_status_view =  itemView.findViewById(R.id.course_status);
            course_instructor_view =  itemView.findViewById(R.id.course_instructor);
            // click listener
            this.onCourseListener = onCourseListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCourseListener.onCourseClicked(getAdapterPosition());
        }
    }

    /**
     * The interface On course listener.
     */
    public interface OnCourseListener{
        /**
         * On course clicked.
         *
         * @param position the position
         */
        void onCourseClicked(int position);
    }
}
