package edu.bzu.assignmentone.bzutodo.userdashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import edu.bzu.assignmentone.bzutodo.Controllers.Tasks_RecycleViewAdapter;
import edu.bzu.assignmentone.bzutodo.R;
import edu.bzu.assignmentone.bzutodo.models.TaskModel;

public class TodayFragment extends Fragment {
    ArrayList<TaskModel> todayTasks = new ArrayList<>();
    RecyclerView todayTasksView;
    private FirebaseFirestore firebasedb = FirebaseFirestore.getInstance();
    private CollectionReference dailyTasks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseApp.initializeApp(this.getContext());
        todayTasksView = view.findViewById(R.id.todayListResView);
        iniateDataSource();
    }

    private void iniateDataSource() {
        dailyTasks = firebasedb.collection("dailyTasks");
        dailyTasks.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot querySnapshot : queryDocumentSnapshots) {
                    todayTasks.add(new TaskModel(querySnapshot.getString("TaskTitle"), querySnapshot.getString("soleganText"), querySnapshot.getString("completeTaskDate")
                    ));
            }
                updateUI();
        }});


//        todayTasks.add(new TaskModel("Comp433 Study","study for second phase","5:00pm"));
//        todayTasks.add(new TaskModel("Comp438 Assignment-1","Hand In Assignment-1","10:00pm"));
//        todayTasks.add(new TaskModel("Comp336 project","Work on project","10:00am"));
//        todayTasks.add(new TaskModel("Comp438 Lecture","watch lectures","7:30pm"));
//        todayTasks.add(new TaskModel("Comp3332 Task","Unity script tasks","8:00am"));
//        todayTasks.add(new TaskModel("Comp431 Short exam","Short exam at Bamieh102","3:50pm"));
//        todayTasks.add(new TaskModel("Comp438 JSON","Revise JSON lecture","11:00pm"));
    }

    public void updateUI(){
        Tasks_RecycleViewAdapter adapter = new Tasks_RecycleViewAdapter(this.getContext(), todayTasks);
        todayTasksView.setAdapter(adapter);
        todayTasksView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter.setOnItemClickListener(pos -> {
            todayTasks.remove(pos);
            adapter.notifyItemRemoved(pos);
            Toast.makeText(getContext(), "Task Completed", Toast.LENGTH_SHORT).show();
        });
    }

}

