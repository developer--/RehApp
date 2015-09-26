package geolab.myo.adpaters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import geolab.myo.fragment.ExerciseFragments;
import geolab.myo.fragment.ViewPagerFragment;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private final int NUM_PAGES = 2;
    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 2: return ExerciseFragments.newInstance("viewPager");
            case 1: return ViewPagerFragment.newInstance("testFrag");
        }
        return new ExerciseFragments();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position++;
        return container;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
