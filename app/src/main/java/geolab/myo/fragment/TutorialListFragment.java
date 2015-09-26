package geolab.myo.fragment;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;

import geolab.myo.TutorialDetailActivity;
import geolab.myo.adpaters.TutorialListViewAdapter;
import geolab.myo.model.MyoTutorial;
import geolab.myo.R;
import geolab.myo.model.TutorialDummyData;


public class TutorialListFragment extends android.support.v4.app.Fragment{


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    private ListView TutorialListView;
    private View rootView;

    private SwipeRefreshLayout mSwipeRefreshLayout;
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
                Toast.makeText(getActivity(),"tut",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), TutorialDetailActivity.class);

                MyoTutorial myoTutorial = (MyoTutorial) parent.getAdapter().getItem(position);
                intent.putExtra("MYO", (Serializable) myoTutorial);
                Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in_up, R.anim.slide_out_up).toBundle();
                getActivity().startActivity(intent, bundle);
            }
        });

        TutorialListViewAdapter tutorialListViewAdapter = new TutorialListViewAdapter(getActivity(), TutorialDummyData.insertList());
        TutorialListView.setAdapter(tutorialListViewAdapter);

        return rootView;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static TutorialListFragment newInstance(String text) {

        TutorialListFragment f = new TutorialListFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

}
