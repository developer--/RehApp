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


//        TextView date = (TextView) findViewById(R.id.calendar);
//        date.setText(workout.getDate().toString());
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


        return super.onOptionsItemSelected(item);
    }
}
