package yasu.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    EditText et_task, et_due;
    ArrayList list;
    Intent result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        list =  intent.getStringArrayListExtra("tasks");
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i).toString());
        }

        result = new Intent(this, MainActivity.class);

        et_task = (EditText)findViewById(R.id.edit_task);
        et_due = (EditText)findViewById(R.id.edit_due);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                String s1 = et_task.getText().toString();
                String s2 = et_due.getText().toString();
                String s3 = s1 + " - " + s2;
                result.putExtra("result", s3);
                setResult(0, result);
                end();
            }
        });

    }

    public void end(){
        this.finish();
    }

}
