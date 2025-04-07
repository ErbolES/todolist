package com.example.todolist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentTaskList extends Fragment {



    public interface OnTaskClickListener {
        void onTaskClick(Task task, int position);
    }

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private OnTaskClickListener taskClickListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        adapter = new TaskAdapter(mainActivity.getTaskList(), (task, position) -> {

            taskClickListener.onTaskClick(task, position);
        });
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_add_task);
        fab.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FragmentAddTask())
                    .addToBackStack(null)
                    .commit();
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

    public void updateList() {
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}


