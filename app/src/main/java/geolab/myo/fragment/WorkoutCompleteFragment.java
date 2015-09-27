package geolab.myo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;
import geolab.myo.R;
import geolab.myo.activities.ExerciseDetailActivity;
import geolab.myo.model.ExerciseModel;

/**
 * Created by Jay on 9/27/2015.
 */
public class WorkoutCompleteFragment extends Fragment {
    private ExerciseModel workout;
    private int numberOfRepsDone = 0;

    public WorkoutCompleteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workout = (ExerciseModel) getArguments().getSerializable("Model");
        numberOfRepsDone = getArguments().getInt("NumberOfRepsDone");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout_complete, container, false);

        TextView resultView = (TextView) view.findViewById(R.id.workoutResult);
        TextView durationView = (TextView) view.findViewById(R.id.durationView);
        TextView numberOfRepsDoneView = (TextView) view.findViewById(R.id.numberOfRepsDone);

        durationView.setText(TimeUnit.MILLISECONDS.toMinutes(workout.getTimeLimit()) + " Minutes");
        numberOfRepsDoneView.setText(numberOfRepsDone + " # Reps");

        Button backButton = (Button) view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWorkout(v);
            }
        });

        return view;
    }

    public void finishWorkout(View v){
        if(v.getId() == R.id.backButton){
            Intent exerciseDetail = new Intent(getActivity(), ExerciseDetailActivity.class);
            Bundle extras = new Bundle();
            extras.putSerializable("Model", workout);
            exerciseDetail.putExtras(extras);
            getActivity().startActivity(exerciseDetail);
        }
    }
}
