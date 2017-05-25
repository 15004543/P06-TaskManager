package sg.edu.rp.c347.p06_taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int reqCode = 12345;
    ListView lv;
    TaskAdaptor adapter;
    ArrayList<Task> tasks;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        lv = (ListView)this.findViewById(R.id.lvList);

        tasks = new ArrayList<>();
        adapter = new TaskAdaptor(this, R.layout.row, tasks);
        lv.setAdapter(adapter);
        displayTask();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, tasks.get(position).getTimer());

                Intent intent = new Intent(MainActivity.this,
                        ScheduledNotificationReceiver.class);

                intent.putExtra("name", tasks.get(position).getName());
                intent.putExtra("desc", tasks.get(position).getDesc());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        AddActivity.class);
                // Start the new activity
                startActivityForResult(i, 9);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if(data != null){
                displayTask();
            }
        }
    }

    public void displayTask(){
        if(tasks.size() > 0) {
            tasks.clear();
        }
        DBHelper dbh = new DBHelper(this);
        ArrayList<Task> addStuff = dbh.getAllTask();
        for(int i = 0; i < addStuff.size(); i++){
            tasks.add(new Task(addStuff.get(i).getId(),addStuff.get(i).getName(),addStuff.get(i).getDesc(),addStuff.get(i).getTimer()));
        }
        dbh.close();
        adapter.notifyDataSetChanged();
    }
}
