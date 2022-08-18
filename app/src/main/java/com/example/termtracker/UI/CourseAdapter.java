package com.example.termtracker.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{

    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public void setCourses(List<Course> courses) {
        this.mCourses = courses;
        notifyDataSetChanged();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        private final TextView courseItemView;

        private CourseViewHolder(View itemView){
            super(itemView);
            courseItemView =itemView.findViewById(R.id.itemTextView);

            // TODO: Make lambda
            itemView.setOnClickListener(view -> {

                int position = getAdapterPosition();
                final Course current = mCourses.get(position);

                // Prepare information for next activity
                Intent intent = new Intent(context, CourseDetail.class);
                intent.putExtra(Course.ID_KEY, current.getId());
                intent.putExtra(Term.ID_KEY, current.getTermId_FK());
                context.startActivity(intent);

            });

        }
    }

    // Constructor
    public CourseAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.row_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        if(mCourses != null) {
            Course current = mCourses.get(position);
            String title = current.getTitle();
            holder.courseItemView.setText(title);
        } else {
            holder.courseItemView.setText("No term title");
        }
    }

    @Override
    public int getItemCount() {
        if (mCourses != null) {
            return mCourses.size();
        } else {
            return 0;
        }
    }

}
