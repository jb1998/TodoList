package me.jaxbot.todolist;

import java.sql.Time;
import java.util.Date;

/**
 * Created by pc on 2/12/2017.
 */

public class Reminder
{
    String title;
    int id;
    String description;
    String date;
     String time;
    Reminder(int id,String title,String description,String date,String time)
    {
        this.id=id;
        this.title=title;
        this.description=description;
        this.date=date;
        this.time=time;
    }
}
