package me.jaxbot.todolist;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
//import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import static android.R.attr.format;
import static android.R.attr.logo;
//import com.github.clans.fab.FloatingActionButton;
//import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HelperTodolist helper;
    static final String My_description = "des";
    FloatingActionMenu materialDesignFAM;
     FloatingActionButton fabreset, fababout, fabfeedback,fabadd;
    static String finaldate;

 MyAdapterRecycler adapter;
    ArrayList<Reminder> reminder;


//    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reminder = new ArrayList<>();
        fabadd = (FloatingActionButton) findViewById(R.id.add);
        fabreset = (FloatingActionButton) findViewById(R.id.resetdata);
        fabfeedback = (FloatingActionButton) findViewById(R.id.feedback);
        fababout = (FloatingActionButton) findViewById(R.id.about);
//        fam = (FloatingActionMenu) findViewById(R.id.fab_menu);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
//        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
//            @Override
//            public void onMenuToggle(boolean opened) {
//                if (opened) {
//
//                    showToast("Menu is opened");
//                } else {
//                    showToast("Menu is closed");
//                }
//            }
//        });
//fam.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        if (fam.isOpened()) {
//            fam.close(true);
//        }
//    }
//});
//        fam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (fam.isOpened()) {
//                    fam.close(true);
//                }
//            }
//        });
        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent();
                i1.setClass(MainActivity.this, Enteritem.class);
                startActivityForResult(i1, 2);
            }
        });
        fabreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Confirm");
                b.setIcon(android.R.drawable.ic_dialog_alert);
                b.setMessage("Are You Sure Without A Shadow Of Doubt?");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                        HelperTodolist helperTodolist = new HelperTodolist(MainActivity.this);
                        SQLiteDatabase db =helperTodolist.getWritableDatabase();
                        db.delete(staticmyclass.Table_Name,null,null);

                        while (reminder.size() != 0) {
                            reminder.remove(reminder.size() - 1);


                        }
                        adapter.notifyDataSetChanged();

                    }
                });

                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        dialogInterface.cancel();
                    }
                });


                b.show();



            }
        });
        fabfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:jatinbindra171998@gmail.com"));
                i.putExtra(Intent.EXTRA_SUBJECT, "feedback");
                startActivity(i);
            }
        });
        fababout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://jatinbindrablog.wordpress.com");
                i.setData(uri);
                startActivity(i);
            }
        });
//        fab.setOnClickListener(onButtonClickfabAdd.setOnClickListener(onButtonClick());());
//        fabEdit.setOnClickListener(onButtonClick());



        adapter = new MyAdapterRecycler(this,reminder);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        helper = new HelperTodolist(this);

//        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        setUpViews();




//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i1 = new Intent();
//                i1.setClass(MainActivity.this, Enteritem.class);
//                startActivityForResult(i1, 2);
//            }
//        });
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        Log.i("cccc", "onItemClick: ");
                        Intent ii = new Intent();
                        ii.setClass(MainActivity.this, Main2Activity.class);

                        String str = reminder.get(position).id + "";
                        ii.putExtra("id", str);




                        ii.putExtra("title1", reminder.get(position).title);
                        ii.putExtra("description1", reminder.get(position).description);
                        ii.putExtra("date1", reminder.get(position).date);
                        ii.putExtra("time1", reminder.get(position).time);


                        startActivityForResult(ii, 1);
                    }

                    @Override
                    public void onLongItemClick(View view, final int position1) {
                        // do whatever


                        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                        b.setTitle("Confirm");
                        b.setIcon(android.R.drawable.ic_dialog_alert);
                        b.setMessage("Are You Sure Without A Shadow Of Doubt?");
                        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int index) {
                                HelperTodolist helperTodolist = new HelperTodolist(MainActivity.this);
                                SQLiteDatabase db = helperTodolist.getWritableDatabase();
                                Reminder rev = reminder.get(position1);
                                int a = rev.id;


                                db.delete(staticmyclass.Table_Name, staticmyclass._Id + "=?",
                                        new String[]{String.valueOf(a)});

                                db.close();

                                reminder.remove(position1);
                                adapter.notifyDataSetChanged();

                            }
                        });

                        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                dialogInterface.cancel();
                            }
                        });


                        b.show();
                    }
                })
        );

    }


    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void setUpViews() {

        Log.i("TAG4", "setUpViews: ");
        SQLiteDatabase dp = helper.getReadableDatabase();
        reminder.clear();
        Cursor c = dp.query(staticmyclass.Table_Name, null, null, null, null, null, null);
        while (c.moveToNext()) {

            Log.i("TAG5", "setUpViews: ");
            int id = c.getInt(c.getColumnIndex(staticmyclass._Id));
            String title = c.getString(c.getColumnIndex(staticmyclass.My_title));
            String description = c.getString(c.getColumnIndex(staticmyclass.My_description));

            String date = c.getString(c.getColumnIndex(staticmyclass.My_date));
            String time = c.getString(c.getColumnIndex(staticmyclass.My_time));
//            Log.i("TAG5", "setUpViews: ");
            Log.i("TAG3",title);
            Log.i("TAG3",description);
            Log.i("TAG3",date);

            Reminder reminder2 = new Reminder(id,title, description, date, time);
            reminder.add(reminder2);


        }
        // Log.i(TAG, "setUpViews: ");
        adapter.notifyDataSetChanged();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //   View v = View.inflate(MainActivity.this, R.layout.myitemlook, null);

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                setUpViews();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("MainActivityTag", "Result Cancelled ");
            }
        }
        else
            if(requestCode==1)
            {
                if(resultCode==Activity.RESULT_OK)
                {
                    setUpViews();;
                }
                else
                    Log.i("ttttttttt", "onActivityResult: ");
            }
    }

//    @Override
//    public void onBackPressed() {
//
//
//        super.onBackPressed();
//    }
//
//    @Override
//    public void finish() {
//
//        super.finish();
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.my_menu, menu);
//        return true;
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int a = item.getItemId();
//        if (a == R.id.about) {
//
//            Intent i = new Intent();
//            i.setAction(Intent.ACTION_VIEW);
//            Uri uri = Uri.parse("http://codechef.com");
//            i.setData(uri);
//            startActivity(i);
//        } else if (a == R.id.feedback) {
//
//            Intent i = new Intent();
//            i.setAction(Intent.ACTION_SENDTO);
//            i.setData(Uri.parse("mailto:jatinbindra171998@gmail.com"));
//            i.putExtra(Intent.EXTRA_SUBJECT, "feedback");
//            startActivity(i);
//        } else if (a == R.id.resetdata) {
//            HelperTodolist helperTodolist = new HelperTodolist(MainActivity.this);
//            SQLiteDatabase db =helperTodolist.getWritableDatabase();
//             db.delete(staticmyclass.Table_Name,null,null);
//
//            while (reminder.size() != 0) {
//                reminder.remove(reminder.size() - 1);


            }
//            adapter.notifyDataSetChanged();
//        }

//        return true;

//    }
//}
