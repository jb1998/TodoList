package me.jaxbot.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static me.jaxbot.todolist.staticmyclass.*;

public class Main2Activity extends AppCompatActivity {
EditText title,description,date,time;
    Button submit2;
    String title2,description2,date2,time2,title2c,description2c,date2c,time2c;
    String id1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        submit2=(Button)findViewById(R.id.submit);
        title=(EditText)findViewById(R.id.title);
        description=(EditText)findViewById(R.id.description);
        date=(EditText)findViewById(R.id.date);
        time=(EditText)findViewById(R.id.time);
        Intent i = getIntent();
        id1=i.getStringExtra("id");
      //  Log.i("sample", id1);
        Log.d("idlala",id1);
        title2=i.getStringExtra("title1");
        description2=i.getStringExtra("description1");
        date2=i.getStringExtra("date1");
        time2=i.getStringExtra("time1");
        if(title2!=null)
        {
            title.setText(title2);
        }
        if(description2!=null)
        {
            description.setText(description2);
        }
        if(date2!=null)
        {
            date.setText(date2);
        }
        if(time2!=null)
        {
            time.setText(time2);
        }
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelperTodolist helperTodolist = new HelperTodolist(Main2Activity.this);
                SQLiteDatabase db = helperTodolist.getWritableDatabase();
                ContentValues cv = new ContentValues();

                cv.put(My_title, title2);
                cv.put(My_description, description2);
                cv.put(My_date, date2);
                cv.put(My_time, time2);
//                db.insert(staticmyclass.Table_Name,null,cv);
                Log.i("TTTT", title2);
                Log.i("TTTT", description2);
                Log.i("TTTT", date2);
                //Log.i("TTTT",title);

                int ia =db.update(staticmyclass.Table_Name, cv, "id= " +id1,null);
                Log.d("lalala",String.valueOf(ia));
                Intent i = new Intent();
               // Log.i("helllo", id1);

                setResult(Activity.RESULT_OK, i);
                finish();
//                title2c=title.getText().toString();
//                description2c=description.getText().toString();
//                date2c=date.getText().toString();
//                time2c=time.getText().toString();
//                Intent i= new Intent();
//                i.putExtra("title1",title2c);
//                i.putExtra("description1",description2c);
//                i.putExtra("date1",date2c);
//                i.putExtra("time1",time2c);
//                setResult(Activity.RESULT_OK,i);
//                finish();
            }
        });
    }

}
