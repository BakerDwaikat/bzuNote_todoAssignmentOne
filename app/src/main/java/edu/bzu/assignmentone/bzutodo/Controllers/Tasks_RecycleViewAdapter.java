package edu.bzu.assignmentone.bzutodo.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.bzu.assignmentone.bzutodo.R;
import edu.bzu.assignmentone.bzutodo.models.TaskModel;

public class Tasks_RecycleViewAdapter extends RecyclerView.Adapter<Tasks_RecycleViewAdapter.TaskViewHolder> {

    private Context context;
    private ArrayList<TaskModel> dummyTaskModels ;

    public Tasks_RecycleViewAdapter(Context context, ArrayList<TaskModel> dummyTaskModels) {
        this.context = context;
        this.dummyTaskModels = dummyTaskModels;
    }

    @NonNull
    @Override
    public Tasks_RecycleViewAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.task_item_design,parent,false);

        return new Tasks_RecycleViewAdapter.TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Tasks_RecycleViewAdapter.TaskViewHolder holder, int position) {

        holder.taskTitleView.setText(dummyTaskModels.get(position).getTaskTitle());
        holder.soleganDescriptionView.setText(dummyTaskModels.get(position).getSoleganText());
        holder.dateView.setText(dummyTaskModels.get(position).getCompleteTaskDate());

    }

    @Override
    public int getItemCount() {
        return dummyTaskModels.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView taskTitleView , soleganDescriptionView , dateView ;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            taskTitleView = itemView.findViewById(R.id.taskTitleID);
            soleganDescriptionView = itemView.findViewById(R.id.soleganTitleID);
            dateView = itemView.findViewById(R.id.dateTitleID);


        }
    }
}
