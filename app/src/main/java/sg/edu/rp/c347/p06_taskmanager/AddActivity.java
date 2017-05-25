package sg.edu.rp.c347.p06_taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText etName, etDesc, etTimer;
    Button btnAdd, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = (EditText)findViewById(R.id.etAddName);
        etDesc = (EditText)findViewById(R.id.etAddDesc);
        etTimer = (EditText)findViewById(R.id.etAddTimer);

        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnAdd = (Button)findViewById(R.id.btnAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();
                int timer = Integer.parseInt(etTimer.getText().toString());

                DBHelper dbh = new DBHelper(AddActivity.this);
                long row_affected = dbh.insertTask(name, desc, timer);
                dbh.close();

                if (row_affected != -1){
                    Toast.makeText(AddActivity.this, "Added successful",
                            Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent();
                i.putExtra("name", name);

                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
