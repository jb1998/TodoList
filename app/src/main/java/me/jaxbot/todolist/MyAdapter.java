package me.jaxbot.todolist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pc on 2/12/2017.
 */

public class MyAdapter extends ArrayAdapter<Reminder> {

    Context mContext;
    ArrayList<Reminder> mRemainder;
    public MyAdapter(Context context, ArrayList<Reminder> objects) {
        super(context,0, objects);
        mContext=context;
        mRemainder=objects;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView=View.inflate(mContext,R.layout.myitemlook,null);
        }
        TextView title=(TextView)convertView.findViewById(R.id.title);
        TextView date=(TextView)convertView.findViewById(R.id.date);
        TextView time=(TextView)convertView.findViewById(R.id.time);
        Reminder reminder=mRemainder.get(position);
        title.setText(reminder.title);
        date.setText(reminder.date+"");
        time.setText(reminder.time+"");
        return convertView;
    }
}
