package edu.bzu.assignmentone.bzutodo.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.bzu.assignmentone.bzutodo.R;
import edu.bzu.assignmentone.bzutodo.models.TaskModel;

public class Tasks_RecycleViewAdapter extends RecyclerView.Adapter<Tasks_RecycleViewAdapter.TaskViewHolder> {

    private Context context;
    private ArrayList<TaskModel> dummyTaskModels ;
    private IOnItemClickListener itemClickListener;

    public interface IOnItemClickListener {
        void onItemClick(int pos);
    }

    public void setOnItemClickListener(IOnItemClickListener clickListener) {
        this.itemClickListener = clickListener;

    }

    public Tasks_RecycleViewAdapter(Context context, ArrayList<TaskModel> dummyTaskModels) {
        this.context = context;
        this.dummyTaskModels = dummyTaskModels;
    }

    @NonNull
    @Override
    public Tasks_RecycleViewAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.task_item_design,parent,false);

        return new Tasks_RecycleViewAdapter.TaskViewHolder(view,itemClickListener);
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
        private CheckBox doneTaskBox;


        public TaskViewHolder(@NonNull View itemView,IOnItemClickListener itemClickListener) {
            super(itemView);

            taskTitleView = itemView.findViewById(R.id.taskTitleID);
            soleganDescriptionView = itemView.findViewById(R.id.soleganTitleID);
            dateView = itemView.findViewById(R.id.dateTitleID);
            doneTaskBox = itemView.findViewById(R.id.doneTaskBoxID);
            doneTaskBox.setChecked(false);

            doneTaskBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
