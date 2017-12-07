package kr.ac.cnu.cse.termproject;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PortfolioGenerateActivity extends AppCompatActivity {
    ActionBar abar;
    Button generation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_generate);

        abar = this.getSupportActionBar();
        abar.show();
        abar.setTitle("포트폴리오 생성");

        ListView listView;
        CustomChoiceListViewAdapter adapter;

        adapter = new CustomChoiceListViewAdapter();

        listView = (ListView)findViewById(R.id.checkboxContainer);
        listView.setAdapter(adapter);

        adapter.addItem("첫번 째 프로젝트");
        adapter.addItem("두번 째 프로젝트");
        adapter.addItem("세번 째 프로젝트");

        generation = (Button)findViewById(R.id.generation);
        generation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"포트폴리오 완성",Toast.LENGTH_SHORT).show();
            }
        });
    }
}