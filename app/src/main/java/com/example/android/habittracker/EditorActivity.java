package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.habittracker.data.HabitsContract;
import com.example.android.habittracker.data.HabitsContract.HabitEntry;
import com.example.android.habittracker.data.HabitsDbHelper;


/**
 * Created by paulstyslinger on 3/11/17.
 */

public class EditorActivity extends AppCompatActivity{

    /** EditText field to enter the pet's name */
    private EditText mHabitTitleEditText;

    /** EditText field to enter the pet's breed */
    private EditText mDaysTilGoalEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_habits);

        // Find all relevant views that we will need to read user input from
        mHabitTitleEditText = (EditText) findViewById(R.id.edit_habit_title);
        mDaysTilGoalEditText = (EditText) findViewById(R.id.edit_days_til_goal);

        Button button = (Button) findViewById(R.id.add_habit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertHabit();
                finish();
            }
        });
    }

    private void insertHabit(){
        String titleString = mHabitTitleEditText.getText().toString().trim();
        String daysString = mDaysTilGoalEditText.getText().toString().trim();
        int days = Integer.parseInt(daysString);

        HabitsDbHelper mDbHelper = new HabitsDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_TITLE, titleString);
        values.put(HabitEntry.COLUMN_DAYS_TIL_GOAL, days);

        db.insert(HabitEntry.TABLE_NAME, null, values);
    }


}
