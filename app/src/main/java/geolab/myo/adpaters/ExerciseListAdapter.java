package geolab.myo.adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import geolab.myo.R;
import geolab.myo.model.Exercises;

public class ExerciseListAdapter extends BaseAdapter {

    private ArrayList<Exercises> exercisesArrayList;
    private Context context;
    private LayoutInflater inflater;

    private View rootView;


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


    private int lastPosition = -1;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView;
        ViewHolder viewHolder;

        if(view == null){
            itemView = inflater.inflate(R.layout.exercise_model_fragment, null);
            viewHolder = new ViewHolder();

            //scrolling animation
            Animation animation = AnimationUtils.loadAnimation(context, (i > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            itemView.startAnimation(animation);
            lastPosition = i;

            TextView exerciseNameView = (TextView) itemView.findViewById(R.id.exerciseNameTextViewId);
            TextView exerciseDescriptionView = (TextView) itemView.findViewById(R.id.exerciseDescriptionTextViewId);
            TextView exerciseDurationView = (TextView) itemView.findViewById(R.id.exerciseDurationTextViewId);

            ImageView exerciseImageView = (ImageView) itemView.findViewById(R.id.exerciseImageViewId);

            viewHolder.exerciseNameHolder = exerciseNameView;
            viewHolder.exerciseDescriptionHolder = exerciseDescriptionView;
            viewHolder.exerciseImageExampleHolder = exerciseImageView;
            viewHolder.exerciseDurationHolder = exerciseDurationView;

            itemView.setTag(viewHolder);

        }else{

            itemView = view;

            //scrolling animation
            Animation animation = AnimationUtils.loadAnimation(context, (i > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            itemView.startAnimation(animation);
            lastPosition = i;

            viewHolder = (ViewHolder) itemView.getTag();
        }

        Exercises exercises = (Exercises) getItem(i);

        viewHolder.exerciseNameHolder.setText(exercises.getName());
        viewHolder.exerciseDescriptionHolder.setText(exercises.getDescription());
        viewHolder.exerciseDurationHolder.setText(exercises.getDuration() + "");

        Picasso.with(context)
                .load(exercises.getExImageExampleURL())
                .resize(50, 50)
                .centerCrop()
                .into(viewHolder.exerciseImageExampleHolder);

        return itemView;

    }
    private class ViewHolder {
        TextView exerciseNameHolder, exerciseDurationHolder, exerciseDescriptionHolder;
        ImageView exerciseImageExampleHolder;
    }
}
