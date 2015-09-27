package geolab.myo.fragment;

import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import java.util.ArrayList;

import geolab.myo.R;
import geolab.myo.model.Exercises;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import geolab.myo.R;
import geolab.myo.adpaters.ExercisesAdapter;
import geolab.myo.model.ExerciseModel;


public class ExerciseFragments extends android.support.v4.app.Fragment{

    ListView exerciseListView;
    ArrayList<Exercises> exercisesArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_exercises, container, false);

        // Perform any camera updates here


        ArrayList<ExerciseModel> exercises = new ArrayList<>();
//        for(int i = 0; i < 10; i++){
//            ExerciseModel model = new ExerciseModel(Calendar.getInstance().getTime());
//            model.setType(i);
//            model.setTitle("Exercise " + i);
//            model.setNumberOfTries(3);
//            model.setNumberOfWorkoutToDo(15);
//            model.setTimeLimit(180000);//180000);
//            exercises.add(model);
//        }

        ExerciseModel model1 = new ExerciseModel(Calendar.getInstance().getTime());
        model1.setType(0);
        model1.setTitle("Fist Exercise");
        model1.setNumberOfTries(3);
        model1.setNumberOfWorkoutToDo(15);
        model1.setTimeLimit(180000);//180000);
        model1.setImageResource(R.drawable.mov1);
        exercises.add(model1);

        ExerciseModel model2 = new ExerciseModel(Calendar.getInstance().getTime());
        model2.setType(0);
        model2.setTitle("Finger Spread Exercise");
        model2.setNumberOfTries(3);
        model2.setNumberOfWorkoutToDo(15);
        model2.setTimeLimit(180000);//180000);
        model2.setImageResource(R.drawable.mov2);
        exercises.add(model2);

        ExerciseModel model3 = new ExerciseModel(Calendar.getInstance().getTime());
        model3.setType(0);
        model3.setTitle("Wave Out Exercise");
        model3.setNumberOfTries(3);
        model3.setNumberOfWorkoutToDo(15);
        model3.setTimeLimit(180000);//180000);
        model3.setImageResource(R.drawable.mov3);
        exercises.add(model3);

        ExerciseModel model4 = new ExerciseModel(Calendar.getInstance().getTime());
        model4.setType(0);
        model4.setTitle("Double Tap Exercise");
        model4.setNumberOfTries(3);
        model4.setNumberOfWorkoutToDo(15);
        model4.setTimeLimit(180000);//180000);
        model4.setImageResource(R.drawable.mov4);
        exercises.add(model4);


        ExercisesAdapter adapter = new ExercisesAdapter(getActivity(), exercises);
        ListView exercisesList = (ListView) v.findViewById(R.id.exercisesList);
        exercisesList.setAdapter(adapter);

//        exercisesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Click", Toast.LENGTH_LONG).show();
//            }
//        });



        return v;
    }



    public static ExerciseFragments newInstance(String text) {

        ExerciseFragments f = new ExerciseFragments();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_map, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                return true;
            case R.id.action_settings:
                return true;
            default:
                break;
        }

        return false;
    }

}
