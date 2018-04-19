package de.thm.materialdesignexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        listView.setOnItemClickListener(new ListItemListener());
        button = findViewById(R.id.floatingButton);
        button.setOnClickListener(new FABListener());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutItem:
                Toast.makeText(this, "Logout pressed", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settingsItem:
                Toast.makeText(this, "Settings pressed", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            list.add("Item no. " + i);
        }
    }

    class FABListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Add Button clicked", Toast.LENGTH_SHORT).show();
        }
    }

    class ListItemListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, list.get(position) + " clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
