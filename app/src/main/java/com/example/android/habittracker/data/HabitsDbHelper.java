package com.example.android.habittracker.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.habittracker.data.HabitsContract.HabitEntry;

/**
 * Created by paulstyslinger on 3/11/17.
 */

public class HabitsDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "habits.db";
    private static final int DATABASE_VERSTION = 1;

    private HabitsDbHelper mDbHelper;

    public HabitsDbHelper(Context context) {super(context, DATABASE_NAME, null, DATABASE_VERSTION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + "("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_TITLE + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_DAYS_TIL_GOAL + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor readAllHabits(SQLiteDatabase db) {
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_TITLE,
                HabitEntry.COLUMN_DAYS_TIL_GOAL
        };
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }


}
