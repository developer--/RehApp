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
        for(int i = 0; i < 10; i++){
            ExerciseModel model = new ExerciseModel(Calendar.getInstance().getTime());
            model.setType(i);
            model.setTitle("Exercise " + i);
            model.setNumberOfTries(3);
            model.setNumberOfWorkoutToDo(15);
            model.setTimeLimit(20000);//180000);
            exercises.add(model);
        }

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
