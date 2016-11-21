package com.luxary_team.storiolearn;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{

    private List<Student> mStudents;

    public void setStudents(final List<Student> list) {
        mStudents = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(
                        parent.getContext()).
                        inflate(R.layout.list_item_student, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Student student = mStudents.get(position);

        holder.mNameTextView.setText(student.name());
        holder.mAverageTextView.setText(
                        String.valueOf(student.average()));
    }

    @Override
    public int getItemCount() {
        return mStudents == null ? 0 : mStudents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_view_name_item)
        TextView mNameTextView;
        @BindView(R.id.text_view_average_item)
        TextView mAverageTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
