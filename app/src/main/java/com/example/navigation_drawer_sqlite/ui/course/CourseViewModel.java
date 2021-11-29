package com.example.navigation_drawer_sqlite.ui.course;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CourseViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<String> mText;

    public CourseViewModel() {
        this.mText = new MutableLiveData<>();
        mText.setValue("This is Course fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }

}