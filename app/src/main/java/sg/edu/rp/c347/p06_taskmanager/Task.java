package sg.edu.rp.c347.p06_taskmanager;

import java.io.Serializable;

/**
 * Created by 15004543 on 25/5/2017.
 */

public class Task implements Serializable{
    int id;
    String name, desc;
    int timer;

    public Task(int id, String name, String desc, int timer) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.timer = timer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getTimer() {
        return timer;
    }
}
