package me.jaxbot.todolist;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.AlarmClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import static me.jaxbot.todolist.MainActivity.finaldate;

public class Enteritem extends AppCompatActivity {
    EditText title1, description1;
  Button select;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;

    Button time1, date1, set1;
    DatePickerDialog datePickerDialog;

    String title;

    private static int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enteritem);
        title1 = (EditText) findViewById(R.id.title);

        description1 = (EditText) findViewById(R.id.description);
        time1 = (Button) findViewById(R.id.time);
        date1 = (Button) findViewById(R.id.date);
        set1 = (Button) findViewById(R.id.set);
        select=(Button)findViewById(R.id.timer);

        list=new ArrayList<String>();
        list.add("5 minutes");
        list.add("10 minutes");
        list.add("30 minutes");
        list.add("1 day");
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item);


select.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(Enteritem.this);
        builderSingle.setIcon(R.drawable.ic_menu_camera);
        builderSingle.setTitle("Select a option:-");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Enteritem.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("5 min");
        arrayAdapter.add("10 min");
        arrayAdapter.add("30 min");
        arrayAdapter.add("1 hr");
        arrayAdapter.add("5 hrs");
        arrayAdapter.add("10 hrs");
        arrayAdapter.add("12 hrs");
        arrayAdapter.add("24 hrs");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                select.setText(strName);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(Enteritem.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected Timer is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();
    }
});
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Calendar mcurrenttime=Calendar.getInstance();
                int hour=mcurrenttime.get(Calendar.HOUR_OF_DAY);
                int minute=mcurrenttime.get(Calendar.MINUTE);
                TimePickerDialog timepicker = new TimePickerDialog(Enteritem.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedhour, int selectedminute) {
                        time1.setText(selectedhour + ":" + selectedminute);
                    }
                    },hour,minute,true);

             timepicker.setTitle("SELECT TIME");
                timepicker.show();

            }
        });
        date1.setOnClickListener(new View.OnClickListener() {
            //Log.i("tag1","hello");
            //Toast.makeText()

            //@Override
            public void onClick(View view) {
                Log.i("Tag1", "hello1");
                Calendar mcurrenttime=Calendar.getInstance();
                int year=mcurrenttime.get(Calendar.YEAR);
                int month=mcurrenttime.get(Calendar.MONTH);
                int day=mcurrenttime.get(Calendar.DAY_OF_WEEK);
                datePickerDialog = new DatePickerDialog(Enteritem.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int day) {


                        finaldate = day + "/" + (month + 1) + "/" + year;
                        date1.setText(finaldate);

                    }
                }, year, month, day);
                datePickerDialog.show();


            }
        });
        set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.i("Tag2", "hello2 ");


                 title = title1.getText().toString();


                Log.i("pppp", title);
                String description = description1.getText().toString();
                String date = date1.getText().toString();
                String time = time1.getText().toString();


                setAlarm(Enteritem.this);



                if (!title.equals("") && !description.equals("") && !date.equals("") && !time.equals("")) {
                    HelperTodolist helperTodolist = new HelperTodolist(Enteritem.this);
                    SQLiteDatabase db = helperTodolist.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(staticmyclass.My_title, title);
                    cv.put(staticmyclass.My_description, description);
                    cv.put(staticmyclass.My_date, date);
                    cv.put(staticmyclass.My_time, time);
                    Log.i("TTTT", title);
                    Log.i("TTTT", description);
                    Log.i("TTTT", date);
                    //Log.i("TTTT",title);

                    db.insert(staticmyclass.Table_Name, null, cv);
                    Intent i = new Intent();


                    setResult(Activity.RESULT_OK, i);
                    finish();

                }
                else
                {
                    Toast.makeText(Enteritem.this,"INCOMPLETE",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

long time_in_mili= TimeUnit.MINUTES.toMillis(1);

    public void setAlarm(Context context)
    {

        AlarmManager am = (AlarmManager) Enteritem.this.getSystemService(Context.ALARM_SERVICE);

        Intent i2 = new Intent(this,Alarm.class);

        i2.putExtra("ConstantTitle",title1.getText().toString());
        Log.i("hhh", title);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,i,i2, 0);
i++;
        am.set(AlarmManager.RTC, System.currentTimeMillis() + time_in_mili, pendingIntent);



    }
    private void populateViews() {
    }
}
