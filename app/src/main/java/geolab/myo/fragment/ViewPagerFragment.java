package geolab.myo.fragment;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.Serializable;

import geolab.myo.ExerciseDetailActivity;
import geolab.myo.adpaters.ListViewAdapter;
import geolab.myo.model.MyoTutorial;
import geolab.myo.R;
import geolab.myo.model.TutorialDummyData;


public class ViewPagerFragment extends android.support.v4.app.Fragment{

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    private ListView TutorialListView;
    public static View rootView;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int lastTopValue = 0;
    //onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_tutorial_items_list, container, false);

        TutorialListView = (ListView) rootView.findViewById(R.id.myoTutorialList);

        //swipe refresh
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        TutorialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ExerciseDetailActivity.class);

                MyoTutorial myoTutorial = (MyoTutorial) parent.getAdapter().getItem(position);
                intent.putExtra("MYO", (Serializable) myoTutorial);
                Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in_up, R.anim.slide_out_up).toBundle();
                getActivity().startActivity(intent, bundle);
            }
        });

        ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(), TutorialDummyData.insertList());
        TutorialListView.setAdapter(listViewAdapter);

        return rootView;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static ViewPagerFragment newInstance(String text) {

        ViewPagerFragment f = new ViewPagerFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

}
