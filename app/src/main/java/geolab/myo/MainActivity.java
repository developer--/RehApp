package geolab.myo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import geolab.myo.animation.DepthPageTransformer;
import geolab.myo.fragment.ViewPagerFragment;

import static geolab.myo.fragment.ExerciseFragments.newInstance;


public class MainActivity extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    private Context context;
    private DrawerLayout mDrawerLayout;
    private Activity activity;
    public TabLayout tabLayout;

    public static Toolbar toolbar;
    public int toolbarColorResId,tabLayoutResColorId,statusBarColorResId;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        activity = this;

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tutorials"));
        tabLayout.addTab(tabLayout.newTab().setText("Exercises"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        final PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
            }
        };

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Burger menu
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open,
                R.string.close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        // Instantiate toolbarColorResId ViewPager and toolbarColorResId PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);

        // animation styles
//        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPager.setPageTransformer(true, new DepthPageTransformer());


        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);


        //        status bar color
        Window window = activity.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(activity.getResources().getColor(R.color.status_bar_color));

        //laod old settings
        LoadSettings();
        //set settings
        changeStyle(toolbar,tabLayout,window,toolbarColorResId,tabLayoutResColorId,statusBarColorResId);

    }


    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }



    //change style
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void changeStyle(Toolbar toolbar,TabLayout tabLayout, Window window, int toolbarResID, int tablayoutResId, int statusbarResId){

        toolbar.setBackgroundColor(activity.getResources().getColor(toolbarResID));
        tabLayout.setBackgroundColor(activity.getResources().getColor(tablayoutResId));
        window = activity.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(activity.getResources().getColor(statusbarResId));

    }

    public void LoadSettings() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        toolbarColorResId = sharedPreferences.getInt("toolbarColor", R.color.toolbar_color);
        tabLayoutResColorId = sharedPreferences.getInt("tabLayoutColor", R.color.tab_layout);
        statusBarColorResId = sharedPreferences.getInt("statusBarColor", R.color.status_bar_color);
    }


    //SharedPreferences for save
    public static void SaveUserSettings(Context context, int toolbarColorResId, int tabLayoutColorResId, int statusBarColorResId){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("toolbarColor", toolbarColorResId);
        editor.putInt("tabLayoutColor", tabLayoutColorResId);
        editor.putInt("statusBarColor", statusBarColorResId);
        editor.commit();
    }


    //   end of camera code
    private ActionBarDrawerToggle mDrawerToggle;

    //NavigationItemSelected
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return true;
    }

    public SettingsFragment palleteFrag;
    /**
     * A simple pager adapter that represents 2 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 1: return newInstance("viewPager");
//                case 1: return TestFrag.newInstance("testFrag");
            }
            return new ViewPagerFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_pallete:
                SettingsFragment palleteFrag = new SettingsFragment();
                palleteFrag.show(MainActivity.this.getFragmentManager(),"Pallete_fragment");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindDrawables(findViewById(R.id.frameLayout));
        System.gc();
    }


    @SuppressLint("ValidFragment")
    private class SettingsFragment extends DialogFragment implements View.OnClickListener {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){

            View view = inflater.inflate(R.layout.settings,null);



            ImageView redStyle = (ImageView) view.findViewById(R.id.redStyleImageView);
                        redStyle.setOnClickListener(this);
            ImageView purpleStyle = (ImageView) view.findViewById(R.id.purpleStyleIamgeView);
                        purpleStyle.setOnClickListener(this);
            ImageView darkStyle = (ImageView) view.findViewById(R.id.darkStyleImageView);
                        darkStyle.setOnClickListener(this);
            ImageView grayStyle = (ImageView) view.findViewById(R.id.grayStyleImageView);
                        grayStyle.setOnClickListener(this);
            ImageView blueStyle = (ImageView) view.findViewById(R.id.blueStyleImageView);
                        blueStyle.setOnClickListener(this);

            return view;
        }







        int toolbarColor, tabLyoutColor,statusBarColor;
        public void initResColors(int a, int b, int c){
            toolbarColor = a; tabLyoutColor = b; statusBarColor = c;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.redStyleImageView:

                    changeStyle(toolbar, tabLayout, activity.getWindow(), R.color.red_toolbar_color, R.color.red_tab_layout, R.color.red_status_bar_color);
                    initResColors(R.color.red_toolbar_color, R.color.red_tab_layout, R.color.red_status_bar_color);
                    SaveUserSettings(getApplicationContext(), R.color.red_toolbar_color, R.color.red_tab_layout, R.color.red_status_bar_color);
                    palleteFrag.dismiss();
                    break;
                case R.id.purpleStyleIamgeView:
                    changeStyle(toolbar,tabLayout, activity.getWindow(),R.color.purple_toolbar_color, R.color.purple_tab_layout, R.color.purple_status_bar_color);
                    initResColors(R.color.purple_toolbar_color, R.color.purple_tab_layout, R.color.purple_status_bar_color);
                    SaveUserSettings(getApplicationContext(), R.color.purple_toolbar_color, R.color.purple_tab_layout, R.color.purple_status_bar_color);
                    palleteFrag.dismiss();
                    break;
                case R.id.blueStyleImageView:
                    changeStyle(toolbar,tabLayout, activity.getWindow(),R.color.blue_toolbar_color, R.color.blue_tab_layout, R.color.blue_status_bar_color);
                    initResColors(R.color.blue_toolbar_color, R.color.blue_tab_layout, R.color.blue_status_bar_color);
                    SaveUserSettings(getApplicationContext(), R.color.blue_toolbar_color, R.color.blue_tab_layout, R.color.blue_status_bar_color);
                    palleteFrag.dismiss();
                    break;
                case R.id.darkStyleImageView:
                    changeStyle(toolbar,tabLayout, activity.getWindow(),R.color.dark_toolbar_color, R.color.dark_tab_layout, R.color.dark_status_bar_color);
                    initResColors(R.color.dark_toolbar_color, R.color.dark_tab_layout, R.color.dark_status_bar_color);
                    SaveUserSettings(getApplicationContext(), R.color.dark_toolbar_color, R.color.dark_tab_layout, R.color.dark_status_bar_color);
                    palleteFrag.dismiss();
                    break;
                case R.id.grayStyleImageView:
                    changeStyle(toolbar,tabLayout, activity.getWindow(),R.color.toolbar_color, R.color.tab_layout, R.color.status_bar_color);
                    initResColors(R.color.toolbar_color, R.color.tab_layout, R.color.status_bar_color);
                    SaveUserSettings(getApplicationContext(), R.color.toolbar_color, R.color.tab_layout, R.color.status_bar_color);
                    palleteFrag.dismiss();
                    break;
                default:
                    changeStyle(toolbar,tabLayout, activity.getWindow(),R.color.toolbar_color, R.color.tab_layout, R.color.status_bar_color);
                    SaveUserSettings(getApplicationContext(), toolbarColor,tabLyoutColor,statusBarColor);
                    break;
            }
        }
    }

}