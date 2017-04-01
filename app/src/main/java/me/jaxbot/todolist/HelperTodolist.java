package me.jaxbot.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pc on 2/25/2017.
 */

public class HelperTodolist extends SQLiteOpenHelper {

    public HelperTodolist(Context context) {
        super(context,  "TodoDb",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      String createtable =" CREATE TABLE "+staticmyclass.Table_Name+" ( "+staticmyclass._Id+" INTEGER PRIMARY KEY AUTOINCREMENT, "
              +staticmyclass.My_title+" TEXT, "+staticmyclass.My_description+" TEXT, "+staticmyclass.My_date+" TEXT, " +staticmyclass.My_time+" TEXT); ";

        sqLiteDatabase.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
