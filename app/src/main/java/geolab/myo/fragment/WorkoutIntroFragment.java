package geolab.myo.fragment;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import geolab.myo.R;
import geolab.myo.activities.MyoDeviceActivity;
import geolab.myo.activities.WorkoutActivity;
import geolab.myo.model.ExerciseModel;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class WorkoutIntroFragment extends Fragment {

    private ExerciseModel workout;
    private GifDrawable gifDrawable;

    public WorkoutIntroFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workout = (ExerciseModel) getArguments().getSerializable("Model");

//        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        toolbar.setBackgroundColor(workout.getColor());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout_intro, container, false);

      //  view.setBackgroundColor(workout.getColor());


        TextView titleView = (TextView) view.findViewById(R.id.workoutTitle);
        titleView.setText(workout.getTitle());

        try {
            switch(workout.getType()){
                case 0:
                    gifDrawable = new GifDrawable(getResources(), R.drawable.fist_gif);
                    break;
                case 1:
                    gifDrawable = new GifDrawable(getResources(), R.drawable.finger_spread_gif);
                    break;
                case 2:
                    gifDrawable = new GifDrawable(getResources(), R.drawable.wave_out_gif);
                    break;
                case 3:
                    gifDrawable = new GifDrawable(getResources(), R.drawable.wave_in_gif);
                    break;
                case 4:
                    gifDrawable = new GifDrawable(getResources(), R.drawable.double_tap_gif);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        GifImageView gif = (GifImageView) view.findViewById(R.id.gif);
        gif.setImageDrawable(gifDrawable);
      //  final GifDrawable gifDrawable = (GifDrawable) gif.getDrawable();

        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gifDrawable.isPlaying()) {
                    gifDrawable.stop();
                } else {
                    gifDrawable.start();
                }
            }
        });

        final TextView countDownView = (TextView) view.findViewById(R.id.countDownView);

        new CountDownTimer(6000, 1000) {

            public void onTick(long millisUntilFinished) {
                countDownView.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                //countDownView.setText("done!");
                if(getActivity() != null) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                    if (fragmentManager != null) {
                        WorkoutFragment workoutFragment = new WorkoutFragment();
                        Bundle newBundle = new Bundle();
                        newBundle.putSerializable("Model", workout);
                        workoutFragment.setArguments(newBundle);
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.setCustomAnimations(R.anim.fragment_enter_anim, R.anim.fragment_exit_anim);
//                        ft.setCustomAnimations(
//                                R.anim.card_flip_right_in, R.anim.card_flip_right_out,
//                                R.anim.card_flip_left_in, R.anim.card_flip_left_out);
                        ft.replace(R.id.fragment_container, workoutFragment, "workout");
                        //   ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                        ((WorkoutActivity) getActivity()).setWorkoutFragment(workoutFragment);

                        ft.commit();
                    }
                }
            }
        }.start();

        return view;
    }
}
