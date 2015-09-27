package geolab.myo.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import geolab.myo.R;


public class WorkoutFragment extends Fragment {

    public WorkoutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        final TextView countDownView = (TextView) view.findViewById(R.id.timer_view);

        new CountDownTimer(180000, 1000) {

            public void onTick(long millisUntilFinished) {
                String time = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                countDownView.setText(time);//("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countDownView.setText("done!");
            }
        }.start();

        return view;
    }

}
