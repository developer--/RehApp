package geolab.myo.adpaters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.andexert.library.RippleView;

import java.util.ArrayList;

import geolab.myo.R;
import geolab.myo.model.ExerciseModel;
import geolab.myo.model.MyoTutorial;

/**
 * Created by Jay on 9/27/2015.
 */
public class ExercisesAdapter extends BaseAdapter {

    private ArrayList<ExerciseModel> workouts;
    private Context context;

    public ExercisesAdapter(Context context, ArrayList<ExerciseModel> workouts) {
        this.context = context;
        this.workouts = workouts;
    }

    @Override
    public int getCount() {
        return this.workouts.size();
    }

    @Override
    public Object getItem(int position) {
        return this.workouts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView;
        ViewHolder viewHolder;
        int lastPosition = -1;

        if(convertView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.exercises_list_item_view, null);
            viewHolder = new ViewHolder();

            //scrolling animation
            Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            itemView.startAnimation(animation);
            lastPosition = position;

            CardView container = (CardView) itemView.findViewById(R.id.card_view);
            TextView titleView = (TextView) itemView.findViewById(R.id.title);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
            RippleView rippleView = (RippleView) itemView.findViewById(R.id.card_view_ripple);


            viewHolder.container = container;
            viewHolder.workoutTitle = titleView;
            viewHolder.workoutImage = imageView;
            viewHolder.rippleView = rippleView;

            viewHolder.rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                @Override
                public void onComplete(RippleView rippleView) {
                    Toast.makeText(context, "Click", Toast.LENGTH_LONG).show();
                }
            });

            itemView.setTag(viewHolder);
        } else {
            itemView = convertView;
            //scrolling animation
            Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            itemView.startAnimation(animation);
            lastPosition = position;
            viewHolder = (ViewHolder) itemView.getTag();
        }

        ExerciseModel workOut = (ExerciseModel) getItem(position);

        viewHolder.workoutTitle.setText(workOut.getTitle());

        return itemView;
    }

    private class ViewHolder {
        RippleView rippleView;
        CardView container;
        TextView workoutTitle;
        ImageView workoutImage;
        TextView workoutDescription;
    }

}
