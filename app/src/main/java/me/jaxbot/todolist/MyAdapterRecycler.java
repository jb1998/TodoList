package me.jaxbot.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pc on 3/18/2017.
 */

public class MyAdapterRecycler extends RecyclerView.Adapter<MyAdapterRecycler.ViewHolder> {

    Context mContext;
    ArrayList<Reminder> reminders = new ArrayList<>();
    public MyAdapterRecycler(ArrayList<Reminder> reminders)
    {
        this.reminders=reminders;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.myitemlook, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    public MyAdapterRecycler(Context context,ArrayList<Reminder> reminder)
    {
        this.mContext=context;
        this.reminders=reminder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(reminders.get(position).title);
        holder.date.setText(reminders.get(position).date);
        holder.time.setText(reminders.get(position).time);

    }






    @Override
    public int getItemCount() {
        return reminders.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView ;
        TextView title,date,time;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageview);
            title=(TextView)itemView.findViewById(R.id.title);
            date=(TextView)itemView.findViewById(R.id.date);
            time=(TextView)itemView.findViewById(R.id.time);
        }
    }
}
