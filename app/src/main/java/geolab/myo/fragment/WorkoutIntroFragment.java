package geolab.myo.fragment;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import geolab.myo.R;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class WorkoutIntroFragment extends Fragment {

    public WorkoutIntroFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout_intro, container, false);

        GifImageView gif = (GifImageView) view.findViewById(R.id.gif);
        final GifDrawable gifDrawable = (GifDrawable) gif.getDrawable();

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
                WorkoutFragment workoutFragment = new WorkoutFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, workoutFragment, "intro");
                //   ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }.start();

        return view;
    }
}
