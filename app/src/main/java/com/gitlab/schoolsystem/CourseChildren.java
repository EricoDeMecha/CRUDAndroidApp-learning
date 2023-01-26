package com.gitlab.schoolsystem;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBar;
import androidx.core.app.NavUtils;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gitlab.schoolsystem.ui.main.SectionsPagerAdapter;
import com.gitlab.schoolsystem.databinding.ActivityCourseChildrenBinding;

public class CourseChildren extends AppCompatActivity {

    private ActivityCourseChildrenBinding binding;
    private CourseModel courseModel;

    @Override
    public boolean onSupportNavigateUp() {
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCourseChildrenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        // read intent
        if(getIntent().hasExtra("CourseModel")){
            courseModel = getIntent().getParcelableExtra("CourseModel");
        }

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(courseModel.getCourse_title());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    public CourseModel getCourseModel(){ return this.courseModel; }
}