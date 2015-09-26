package geolab.myo.adpaters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import geolab.myo.fragment.ExerciseFragments;


public class TabsPagerAdapter extends FragmentStatePagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 2: return ExerciseFragments.newInstance("map");
//            case 1: return TestFrag.newInstance("testFrag");
        }
        return new ExerciseFragments();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
