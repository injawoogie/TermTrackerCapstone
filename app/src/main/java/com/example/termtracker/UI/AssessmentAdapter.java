package com.example.termtracker.UI;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.termtracker.Entity.Assessment;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    private List<Assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflator;

    public void setAssessments(List<Assessment> assessments) {
        this.mAssessments = assessments;
        notifyDataSetChanged();
    }


    class AssessmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView assessmentItemView;

        private AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.itemTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessments.get(position);

                    // Prepare information for next activity
                    Intent intent = new Intent(context, AssessmentDetail.class);
                    intent.putExtra(Assessment.ID_KEY, current.getId());
                    intent.putExtra(Course.ID_KEY, current.getCourseId_FK());
                    context.startActivity(intent);
                }
            });
        }

    }


    public AssessmentAdapter(Context context) {
        this.context = context;
        mInflator = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.row_item, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(mAssessments != null) {
            Assessment current = mAssessments.get(position);
            String title = current.getTitle();
            holder.assessmentItemView.setText(title);
        } else {
            holder.assessmentItemView.setText("No term title");
        }
    }

    @Override
    public int getItemCount() {
        if(mAssessments != null) {
            return mAssessments.size();
        } else {
            return 0;
        }
    }
}