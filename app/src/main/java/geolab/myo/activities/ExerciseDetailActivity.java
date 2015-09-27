package geolab.myo.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thalmic.myo.Hub;
import com.thalmic.myo.scanner.ScanActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import geolab.myo.MainActivity;
import geolab.myo.R;
import geolab.myo.model.ExerciseModel;

public class ExerciseDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ExerciseModel workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setVisibility(View.GONE);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);


        workout = (ExerciseModel) getIntent().getExtras().getSerializable("Model");

        Hub hub = Hub.getInstance();
        if (!hub.init(this, getPackageName())) {
            // We can't do anything with the Myo device if the Hub can't be initialized, so exit.
            Toast.makeText(this, "Couldn't initialize Hub", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TextView date = (TextView) findViewById(R.id.calendar);
        String dateFormat = new SimpleDateFormat("dd MMMM yyyy").format(Calendar.getInstance().getTime());
        date.setText(dateFormat);

        TextView numberOfTries = (TextView) findViewById(R.id.number_of_tries_view);
        numberOfTries.setText(workout.getNumberOfTriesDone() + "/" + workout.getNumberOfTries() + " Exercise");

        TextView numberOfWorkoutToDoView = (TextView) findViewById(R.id.number_of_workout_done_view);
        numberOfWorkoutToDoView.setText(workout.getNumberOfWorkoutToDo() + " Reps");

        TextView timeLimit = (TextView) findViewById(R.id.time_limit_view);
        timeLimit.setText("" + TimeUnit.MILLISECONDS.toMinutes(workout.getTimeLimit()) + " Minutes");
    }

    private void onScanActionSelected() {
        // Launch the ScanActivity to scan for Myos to connect to.
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    public void startWorkout(View v){
        if(v.getId() == R.id.start_button){
            Toast.makeText(this, "Start CLick", Toast.LENGTH_LONG).show();
            Intent workoutActivity = new Intent(this, WorkoutActivity.class);
            Bundle extras = new Bundle();
            extras.putSerializable("Model", workout);
            workoutActivity.putExtras(extras);
            startActivity(workoutActivity);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercise_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.action_pallete:
                return true;
        }

        if (R.id.action_scan == item.getItemId()) {
            onScanActionSelected();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
