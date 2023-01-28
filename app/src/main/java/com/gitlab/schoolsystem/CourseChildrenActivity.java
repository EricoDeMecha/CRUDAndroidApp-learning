package com.gitlab.schoolsystem;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBar;
import androidx.core.app.NavUtils;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.gitlab.schoolsystem.ui.main.SectionsPagerAdapter;
import com.gitlab.schoolsystem.databinding.ActivityCourseChildrenBinding;

public class CourseChildrenActivity extends AppCompatActivity {

    private ActivityCourseChildrenBinding binding;
    private CourseModel courseModel;
    public static final String TITLE2 =
            "com.gitlab.schoolsystem.CourseModel";
    private static String term_name;
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = NavUtils.getParentActivityIntent(this);
        if(intent != null){
            intent.putExtra(TITLE2, "Term" );
        }
        NavUtils.navigateUpTo(this, intent);
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
        if(getIntent().hasExtra(TITLE2)){
            courseModel = getIntent().getParcelableExtra(TITLE2);
        }

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(courseModel.getCourse_title());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public CourseModel getCourseModel(){ return this.courseModel; }

}