package com.example.navigation_drawer_sqlite.ui.course;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation_drawer_sqlite.DBHandler;
import com.example.navigation_drawer_sqlite.MainActivity;
import com.example.navigation_drawer_sqlite.R;
import com.example.navigation_drawer_sqlite.ViewCourses;
import com.example.navigation_drawer_sqlite.databinding.FragmentCourseBinding;


public class CourseFragment extends Fragment {

    private CourseViewModel courseViewModel;
    private FragmentCourseBinding binding;

    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;
    private Button addCourseBtn, readCourseBtn;
    private DBHandler dbHandler;

    public static CourseFragment newInstance() {
        return new CourseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course, container, false);
        // initializing all our variables.
        courseNameEdt = view.findViewById(R.id.idEdtCourseName);
        courseTracksEdt = view.findViewById(R.id.idEdtCourseTracks);
        courseDurationEdt = view.findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = view.findViewById(R.id.idEdtCourseDescription);
        addCourseBtn = view.findViewById(R.id.idBtnAddCourse);
        readCourseBtn = view.findViewById(R.id.idBtnReadCourse);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(getActivity());

        // below line is to add on click listener for our add course button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "onClick", Toast.LENGTH_SHORT).show();

                // below line is to get data from all edit text fields.
                String courseName = courseNameEdt.getText().toString();
                String courseTracks = courseTracksEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);

                // after adding the data we are displaying a toast message.
                Toast.makeText(getActivity(), "Course has been added.", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseTracksEdt.setText("");
                courseDescriptionEdt.setText("");
            }
        });

        readCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "onClick", Toast.LENGTH_SHORT).show();
                // opening a new activity via a intent.
                Intent i = new Intent(getActivity(), ViewCourses.class);
                startActivity(i);
            }
        });



        // initializing all our variables.
//        courseNameEdt = binding.idEdtCourseName;
//        courseTracksEdt = binding.idEdtCourseTracks;
//        courseDurationEdt = binding.idEdtCourseDuration;
//        courseDescriptionEdt = binding.idEdtCourseDescription;
//        addCourseBtn = binding.idBtnAddCourse;
//        readCourseBtn = binding.idBtnReadCourse;
//
//        // creating a new dbhandler class
//        // and passing our context to it.
////        dbHandler = new DBHandler(MainActivity.this);
//
//
//        // below line is to add on click listener for our add course button.
//        addCourseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // below line is to get data from all edit text fields.
//                String courseName = courseNameEdt.getText().toString();
//                String courseTracks = courseTracksEdt.getText().toString();
//                String courseDuration = courseDurationEdt.getText().toString();
//                String courseDescription = courseDescriptionEdt.getText().toString();
//
//                // validating if the text fields are empty or not.
//                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
////                    Toast.makeText(SqlitewithOpenHelper.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // on below line we are calling a method to add new
//                // course to sqlite data and pass all our values to it.
//                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);
//
//                // after adding the data we are displaying a toast message.
////                Toast.makeText(SqlitewithOpenHelper.this, "Course has been added.", Toast.LENGTH_SHORT).show();
//                courseNameEdt.setText("");
//                courseDurationEdt.setText("");
//                courseTracksEdt.setText("");
//                courseDescriptionEdt.setText("");
//            }
//        });
//
////        readCourseBtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                // opening a new activity via a intent.
////                Intent i = new Intent(CourseFragment.this, ViewCourses.class);
////                startActivity(i);
////            }
////        });

        courseViewModel =
                new ViewModelProvider(this).get(CourseViewModel.class);
        binding = FragmentCourseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCourse;
        courseViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}