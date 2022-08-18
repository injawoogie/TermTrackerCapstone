package com.example.termtracker.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termtracker.Entity.Term;
import com.example.termtracker.Entity.User;
import com.example.termtracker.R;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder>{

    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public void setTerms(List<Term> terms) {
        this.mTerms = terms;
        notifyDataSetChanged();
    }

    class TermViewHolder extends RecyclerView.ViewHolder {

        private final TextView termItemView;

        private TermViewHolder(View itemView){
            super(itemView);
            termItemView=itemView.findViewById(R.id.itemTextView);

            // TODO: Make lambda
            itemView.setOnClickListener(view -> {

                int position = getAdapterPosition();
                final Term current = mTerms.get(position);

                // Prepare information for next activity
                Intent intent = new Intent(context, TermDetail.class);
                intent.putExtra(Term.ID_KEY, current.getId());
                intent.putExtra(User.ID_KEY, current.getUserId_FK());
                context.startActivity(intent);

            });

        }
    }

    // Constructor
    public TermAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder: " + parent + " " + viewType);
        View itemView = mInflater.inflate(R.layout.row_item, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        System.out.println("onBindViewHolder: " + holder + " " + position);
        if(mTerms != null) {
            Term current = mTerms.get(position);
            String title = current.getTitle();
            holder.termItemView.setText(title);
        } else {
            holder.termItemView.setText("No term title");
        }
    }

    @Override
    public int getItemCount() {
        if (mTerms != null) {
            return mTerms.size();
        } else {
            return 0;
        }
    }

}
