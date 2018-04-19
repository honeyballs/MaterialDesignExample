package de.thm.materialdesignexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Yannick Bals on 19.04.2018.
 */

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton button;

    private ArrayList<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_layout);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.floatingButton);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            list.add("Item no. " + i);
        }
    }
}
