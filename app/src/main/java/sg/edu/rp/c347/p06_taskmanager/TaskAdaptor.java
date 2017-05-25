package sg.edu.rp.c347.p06_taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15004543 on 25/5/2017.
 */

public class TaskAdaptor extends ArrayAdapter<Task> {
    Context context;
    ArrayList<Task> tasks;
    int resource;
    TextView tvName, tvDesc, tvID;

    public TaskAdaptor(Context context, int resource, ArrayList<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables

        tvID = (TextView) rowView.findViewById(R.id.tvID);
        tvName = (TextView) rowView.findViewById(R.id.tvName);
        tvDesc = (TextView) rowView.findViewById(R.id.tvDesc);

        Task task = tasks.get(position);

        int intID = task.getId();
        String id = "" + intID;
        tvID.setText(id);
        tvName.setText(task.getName());
        tvDesc.setText(task.getDesc());

        return rowView;
    }
}
