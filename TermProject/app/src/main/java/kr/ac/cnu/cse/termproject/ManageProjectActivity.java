package kr.ac.cnu.cse.termproject;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ManageProjectActivity extends AppCompatActivity {
    ActionBar abar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_project);

        abar = this.getSupportActionBar();
        abar.show();
        abar.setTitle("프로젝트 관리");

    }
}
