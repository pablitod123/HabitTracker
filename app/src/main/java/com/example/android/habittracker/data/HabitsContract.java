package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by paulstyslinger on 3/11/17.
 */

public class HabitsContract {
    public static abstract class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habits";
        public static final String _ID= BaseColumns._ID;
        public static final String COLUMN_HABIT_TITLE = "title";
        public static final String COLUMN_DAYS_TIL_GOAL = "days";


    }
}
