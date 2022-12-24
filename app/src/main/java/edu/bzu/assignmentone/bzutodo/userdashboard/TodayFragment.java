package edu.bzu.assignmentone.bzutodo.userdashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.bzu.assignmentone.bzutodo.Controllers.Tasks_RecycleViewAdapter;
import edu.bzu.assignmentone.bzutodo.R;
import edu.bzu.assignmentone.bzutodo.models.TaskModel;

public class TodayFragment extends Fragment {
    ArrayList<TaskModel> todayTasks = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView todayTasksView = view.findViewById(R.id.todayListResView);
        iniateDataSource( );
        Tasks_RecycleViewAdapter adapter = new Tasks_RecycleViewAdapter(view.getContext(),todayTasks);
        todayTasksView.setAdapter(adapter);
        todayTasksView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }

    private void iniateDataSource( ) {

        todayTasks.add(new TaskModel("Comp433 Study","study for second phase","5:00pm"));
        todayTasks.add(new TaskModel("Comp438 Assignment-1","Hand In Assignment-1","10:00pm"));
        todayTasks.add(new TaskModel("Comp336 project","Work on project","10:00am"));
        todayTasks.add(new TaskModel("Comp438 Lecture","watch lectures","7:30pm"));
        todayTasks.add(new TaskModel("Comp3332 Task","Unity script tasks","8:00am"));
        todayTasks.add(new TaskModel("Comp431 Short exam","Short exam at Bamieh102","3:50pm"));
        todayTasks.add(new TaskModel("Comp438 JSON","Revise JSON lecture","11:00pm"));


    }

}
