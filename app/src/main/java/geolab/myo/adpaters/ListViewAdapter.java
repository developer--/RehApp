package geolab.myo.adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import geolab.myo.R;
import geolab.myo.model.MyoTutorial;


public class ListViewAdapter extends BaseAdapter {
    private ArrayList<MyoTutorial> myoTutsArrayList;
    private Context context;
    private LayoutInflater inflater;


    public ListViewAdapter(Context context, ArrayList<MyoTutorial> tutorialArrayList){
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


            TextView imgTitleView = (TextView) itemView.findViewById(R.id.imgTitle);
            TextView littleDescriptionView = (TextView) itemView.findViewById(R.id.little_description);

            viewHolder.imgDescriptionView = littleDescriptionView;
            viewHolder.imgTitleView = imgTitleView;


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

        viewHolder.imgTitleView.setText(myoTutorial.getTitle());
        viewHolder.imgDescriptionView.setText(myoTutorial.getDescription());

        String url = myoTutorial.getImgURL();

//        Picasso.with(context)
//                .load(url)
//                .fit()
//                .centerCrop()
//                .into(viewHolder.imgView);

        return itemView;
    }


    private class ViewHolder {
        TextView imgTitleView, imgDescriptionView, uploadDateTimeView, authorView;
        ImageView imgView;
    }


}
