package com.example.todolist;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentAddTask.OnTaskAddedListener {
    private final ArrayList<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new FragmentTaskList())
                .commit();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int position) {
        if(position >= 0 && position < taskList.size()){
            taskList.remove(position);
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }




    @Override
    public void onTaskAdded() {
        getSupportFragmentManager().popBackStack();

    }
}
