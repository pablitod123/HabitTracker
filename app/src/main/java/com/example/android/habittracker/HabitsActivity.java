package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.habittracker.data.HabitsDbHelper;
import com.example.android.habittracker.data.HabitsContract.HabitEntry;

public class HabitsActivity extends AppCompatActivity {

    private HabitsDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);

        Button button = (Button) findViewById(R.id.addHabitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HabitsActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new HabitsDbHelper(this);
        displayDatabaseInfo();

    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

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

        TextView displayView = (TextView) findViewById(R.id.text_view_habits);

        try {
            displayView.setText("The table contains " + cursor.getCount() + " habits.\n\n");

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int titleColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TITLE);
            int daysColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DAYS_TIL_GOAL);

            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(idColumnIndex);
                String currentTitle = cursor.getString(titleColumnIndex);
                int currentDays = cursor.getInt(daysColumnIndex);

                displayView.append("\n" + "ID: " + currentId + "\n"
                        + " Habit to Track: " + currentTitle + "\n"
                        + " Days of Habit Maintained: " + currentDays);
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}
