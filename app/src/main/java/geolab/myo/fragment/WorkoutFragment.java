package geolab.myo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import geolab.myo.R;
import geolab.myo.activities.MyoDeviceActivity;
import geolab.myo.activities.WorkoutActivity;
import geolab.myo.model.ExerciseModel;


public class WorkoutFragment extends Fragment {

    private ExerciseModel workout;
    private int reps = 0;

    public WorkoutFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workout = (ExerciseModel) getArguments().getSerializable("Model");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        final TextView countDownView = (TextView) view.findViewById(R.id.timer_view);

        new CountDownTimer(workout.getTimeLimit(), 1000) {

            public void onTick(long millisUntilFinished) {
                String time = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                countDownView.setText(time);//("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
              //  countDownView.setText("done!");

                if(getActivity() != null) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                    if (fragmentManager != null) {
                        WorkoutCompleteFragment workoutCompleteFragment = new WorkoutCompleteFragment();
                        Bundle newBundle = new Bundle();
                        newBundle.putSerializable("Model", workout);
                        newBundle.putInt("NumberOfRepsDone", reps);
                        workoutCompleteFragment.setArguments(newBundle);
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.fragment_container, workoutCompleteFragment, "workout");
                        //   ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                        ft.commit();
                    }
                }
            }
        }.start();

        Button cancelButton = (Button) view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.cancel_button){
                    getActivity().onBackPressed();
                }
            }
        });

        return view;
    }

    public void updateRep(int type){
        View parentView = getView();
        if(parentView == null)
            return;
        if(type == workout.getType()){
            reps++;
            TextView repsView = (TextView) parentView.findViewById(R.id.number_of_reps);
            repsView.setText("" + reps);
        }
    }

}
