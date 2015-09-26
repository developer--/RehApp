package geolab.myo.adpaters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.andexert.library.RippleView;

import java.util.ArrayList;

import geolab.myo.R;
import geolab.myo.activities.TutorialDetailActivity;
import geolab.myo.model.ExerciseModel;
import geolab.myo.model.MyoTutorial;


public class TutorialListViewAdapter extends BaseAdapter {
    private ArrayList<MyoTutorial> myoTutsArrayList;
    private Context context;
    private LayoutInflater inflater;

    private ListView TutorialListView;
    private View rootView;

    public TutorialListViewAdapter(Context context, ArrayList<MyoTutorial> tutorialArrayList){
        this.context = context;
        this.myoTutsArrayList = tutorialArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myoTutsArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return myoTutsArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    private int lastPosition = -1;
    @Override
    public View getView(int index, View convertView, ViewGroup parentView) {

        View itemView;
        ViewHolder viewHolder;

        if(convertView == null) {
            itemView = inflater.inflate(R.layout.tutorial_cardview_layout,null);
            viewHolder = new ViewHolder();

            //scrolling animation
            Animation animation = AnimationUtils.loadAnimation(context, (index > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            itemView.startAnimation(animation);
            lastPosition = index;

            final CardView container = (android.support.v7.widget.CardView) itemView.findViewById(R.id.cardview);
            TextView littleDescriptionView = (TextView) itemView.findViewById(R.id.little_description);
            VideoView video = (VideoView) itemView.findViewById(R.id.videoViewID);
            RippleView rippleView = (RippleView) itemView.findViewById(R.id.card_view_tut_ripple);

            viewHolder.imgDescriptionView = littleDescriptionView;
            viewHolder.videoView = video;
            viewHolder.rippleView = rippleView;


            viewHolder.rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                @Override
                public void onComplete(RippleView rippleView) {
                    Toast.makeText(context, "tut", Toast.LENGTH_LONG).show();
                    Intent tutorialDetail = new Intent(context, TutorialDetailActivity.class);
                    Bundle extras = new Bundle();
                    extras.putSerializable("TutorialModel", (MyoTutorial) getItem(lastPosition));
                    tutorialDetail.putExtras(extras);
                    context.startActivity(tutorialDetail);
                }
            });

            itemView.setTag(viewHolder);
        }
        else{
            itemView = convertView;
            //scrolling animation
            Animation animation = AnimationUtils.loadAnimation(context, (index > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            itemView.startAnimation(animation);
            lastPosition = index;

            viewHolder = (ViewHolder) itemView.getTag();
        }

        MyoTutorial myoTutorial = (MyoTutorial) getItem(index);

        viewHolder.imgDescriptionView.setText(myoTutorial.getDescription());

        return itemView;
    }


    private class ViewHolder {
        TextView  imgDescriptionView;
        VideoView videoView;
        RippleView rippleView;
    }


}
