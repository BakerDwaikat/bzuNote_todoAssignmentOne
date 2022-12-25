package edu.bzu.assignmentone.bzutodo.userdashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import edu.bzu.assignmentone.bzutodo.Controllers.Tasks_RecycleViewAdapter;
import edu.bzu.assignmentone.bzutodo.R;
import edu.bzu.assignmentone.bzutodo.models.TaskModel;

public class InboxFragment extends Fragment {

    private ArrayList<TaskModel> dummyTaskModel ;
    private FloatingActionButton addNewTask ;
    private RecyclerView byDateRecyclerView;
    private Tasks_RecycleViewAdapter adapter;
    private CalendarView calendarView;
    private Calendar calender;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dummyTaskModel = new ArrayList<>();
        return inflater.inflate(R.layout.fragment_inbox,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addNewTask = view.findViewById(R.id.addNewTaskBtn);
         calendarView = view.findViewById(R.id.calendarView);
        byDateRecyclerView = view.findViewById(R.id.byDateRecyclerView);

        calender = Calendar.getInstance();

        iniateDataSource( );

         adapter = new Tasks_RecycleViewAdapter(view.getContext(),dummyTaskModel);
        byDateRecyclerView.setAdapter(adapter);
        byDateRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        calendarView.setOnDateChangeListener((calendarView, year, month, day) -> Toast.makeText(getContext(), "" + day, Toast.LENGTH_SHORT).show());

        addNewTask.setOnClickListener( e -> addNewTask ());

    }
    private void iniateDataSource( ) {

        dummyTaskModel.add(new TaskModel("Testing project android","Xhskdkciedllvdlc","4 Jan 2023"));
        dummyTaskModel.add(new TaskModel("Software engineer midterm exam","Exam material is chapter 1, 2, 4\n" +
                "\n","21 Aug 2022"));
        dummyTaskModel.add(new TaskModel("Algorithm midterm exam","all chaps ","16:00 PM"));
        dummyTaskModel.add(new TaskModel("Android Development midterm exam","10/1/2023 at  Shaheen162","15:50 PM"));
        dummyTaskModel.add(new TaskModel("Software engineer Assignment 2","10/1/2023 at  Shaheen162","15:50 PM"));
        dummyTaskModel.add(new TaskModel("GAME DEVELOPMENT exam ","chap 2,3,4","15:50 PM"));

    }

    private void addNewTask ()  {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View addTaskView = inflater.inflate(R.layout.insert_item_component,null);
        EditText newTaskName = addTaskView.findViewById(R.id.newTaskNameID);
        EditText newTaskDesc = addTaskView.findViewById(R.id.newTaskDescID);
        TextView todayTimeTV = addTaskView.findViewById(R.id.today_dateTimeID);
        String currentDate = time(0);
        String currentTime = time(1);

        todayTimeTV.setText("Today @ "+ currentTime);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setView(addTaskView);
        alertDialog.setPositiveButton("Add Task", (dialog, id) -> {

            String newTitle = newTaskName.getText().toString();
            String newDesc = newTaskDesc.getText().toString();
            dummyTaskModel.add(new TaskModel(newTitle,newDesc,"" + currentDate));
            adapter.notifyItemChanged(dummyTaskModel.size() - 1);
            Toast.makeText(getContext(), "Task Added !", Toast.LENGTH_SHORT).show();

            dialog.dismiss();

        }).setNegativeButton("Cancel", (dialog, id) -> {

            Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
            dialog.dismiss();

        });
        alertDialog.create();
        alertDialog.show().getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(getContext(),R.drawable.shadow2));

    }

    private String time (int format) {

        String s ="";
        switch (format) {
            case 0:
                s = DateFormat.getDateInstance(DateFormat.FULL).format(calender.getTime());
                break;
            case 1:
                s = SimpleDateFormat.getTimeInstance().format(calender.getTime());
                break;
        }
        return s;
    }
}
