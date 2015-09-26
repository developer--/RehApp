package geolab.myo.adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import geolab.myo.model.Exercises;

public class ExerciseListAdapter extends BaseAdapter {

    private ArrayList<Exercises> exercisesArrayList;
    private Context context;
    private LayoutInflater inflater;



    @Override
    public int getCount() {
        return exercisesArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return exercisesArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return null;
    }
}
