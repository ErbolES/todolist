package com.example.todolist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class FragmentAddTask extends Fragment {
    private EditText etTitle, etDescription;
    private Button btnSave;



    public interface OnTaskAddedListener {
        void onTaskAdded();
    }

    private OnTaskAddedListener taskAddedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTaskAddedListener) {
            taskAddedListener = (OnTaskAddedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnTaskAddedListener");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        etTitle = view.findViewById(R.id.et_title);
        etDescription = view.findViewById(R.id.et_description);

        btnSave = view.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();

            if (!title.isEmpty()) {
                Task newTask = new Task(title, description);
                ((MainActivity) requireActivity()).addTask(newTask);

                taskAddedListener.onTaskAdded();
            } else {
                Toast.makeText(getContext(), "Введите заголовок", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

