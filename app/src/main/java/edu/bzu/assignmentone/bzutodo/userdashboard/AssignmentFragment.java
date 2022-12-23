package edu.bzu.assignmentone.bzutodo.userdashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.bzu.assignmentone.bzutodo.R;
import edu.bzu.assignmentone.bzutodo.models.TaskModel;

public class AssignmentFragment extends Fragment {

    private ArrayList<TaskModel> dummyTaskModel ;
    private FloatingActionButton addNewTask ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_assignment,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        addNewTask = view.findViewById(R.id.addNewTaskBtn);

        CalendarView calendarView = view.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                Toast.makeText(getContext(), "" + day, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
