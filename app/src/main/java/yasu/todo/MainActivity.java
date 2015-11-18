package yasu.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    ArrayAdapter<String> taskAdapter;
    ListView lv;
    static final int PICK_CONTACT_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        String[] taskArray = {
//                "Homework 1 - tmr",
//                "Homework 2 - later",
//                "HW 3 - idk",
//                "HW 4 - yesterday",
//                "Groceries - soon",
//                "Onani - NOW"};

        String[] taskArray = {"-------------------------------------"};

        final ArrayList<String> taskList = new ArrayList<>(Arrays.asList(taskArray));

        lv = (ListView) findViewById(R.id.listview_tasks);


        taskAdapter = new ArrayAdapter<String>(
                this,
                R.layout.task_layout,
                R.id.task_item,
                taskList);

        lv.setAdapter(taskAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                taskAdapter.remove(taskAdapter.getItem(position));
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getBaseContext(), TaskActivity.class);
                intent.putStringArrayListExtra("tasks", taskList);
//                startActivity(intent);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                // A contact was picked.  Here we will just display it
                // to the user.

            }
            System.out.println("refreshing data");
//            ArrayList al = data.getStringArrayListExtra("result");
//            ArrayAdapter taskAdapter1 = new ArrayAdapter<String>(
//                    this,
//                    R.layout.task_layout,
//                    R.id.task_item,
//                    al
//            );
//            lv = (ListView)findViewById(R.id.listview_tasks);
//            lv.setAdapter(taskAdapter1);

            String str = data.getStringExtra("result");
            if(!str.equals(""))
                taskAdapter.add(str);
            taskAdapter.notifyDataSetChanged();
        }
    }
}
