package geolab.myo.activities;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import geolab.myo.MainActivity;
import geolab.myo.R;
import geolab.myo.model.MyoTutorial;



public class TutorialDetailActivity extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener{


    private boolean bVideoIsBeingTouched = false;
    private Handler mHandler = new Handler();
    private ImageView play_icon;

    private TextView tutorialTitleTextView;
    private TextView tutorialDescriptionTextView;


    private VideoView myVideoView;
    private int position = 0;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;

    private DrawerLayout mDrawerLayout;

    private Animation textAnimation, fadeIn, fadeOut;

    private Context context;
    public int toolbarColorResId, tabLayoutResColorId, statusBarColorResId;
//    on Create View
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutorial_item_detail);
        context = this;

        // get model item data
        final MyoTutorial myoTutorial = (MyoTutorial) getIntent().getSerializableExtra("TutorialModel");


        play_icon = (ImageView) findViewById(R.id.play_bg_id);
        play_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myoTutorial.getImgURL()));
                intent.setDataAndType(Uri.parse(myoTutorial.getImgURL()), "video/*");
                startActivity(intent);
            }
        });

        //initialize the VideoView
        myVideoView = (VideoView) findViewById(R.id.detailTutVideoViewId);



        tutorialTitleTextView = (TextView) findViewById(R.id.detailTutTitleTextViewId);
        tutorialDescriptionTextView = (TextView) findViewById(R.id.detailTutDescriptionTextViewId);

        tutorialTitleTextView.setText(myoTutorial.getTitle());
        tutorialDescriptionTextView.setText(myoTutorial.getDescription());

        //get selected item detail

        fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);






        textAnimation = AnimationUtils.loadAnimation(context,R.anim.text_animation);


        Window window = this.getWindow();

        toolbarColorResId = 0; tabLayoutResColorId = 0; statusBarColorResId = 0;
        LoadSettings();

        //Set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //change style
        changeStyle(toolbar,window, toolbarColorResId, statusBarColorResId);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //set back button icon
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open,
                R.string.close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mDrawerToggle.setDrawerIndicatorEnabled(false);
        //set home as up indicator
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.arrow_left);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }


    //
    //change style
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void changeStyle(Toolbar toolbar, Window window, int toolbarResID, int statusbarResId){
        int k = 0;
//        LoadSettings(toolbarResID,k,statusbarResId );
        toolbar.setBackgroundColor(this.getResources().getColor(toolbarResID));
        window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(this.getResources().getColor(statusbarResId));


    }


    // load user settings
    private void LoadSettings(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        toolbarColorResId = sharedPreferences.getInt("toolbarColor", R.color.toolbar_color) ;
        tabLayoutResColorId = sharedPreferences.getInt("tabLayoutColor", R.color.tab_layout) ;
        statusBarColorResId = sharedPreferences.getInt("statusBarColor", R.color.status_bar_color ) ;

        //save settings;
        MainActivity.SaveUserSettings(getApplicationContext(), toolbarColorResId, tabLayoutResColorId, statusBarColorResId);

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

    }

    private ActionBarDrawerToggle mDrawerToggle;


    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //we use onSaveInstanceState in order to store the video playback position for orientation change
//        outState.putInt("Position", myVideoView.getCurrentPosition());
//        myVideoView.pause();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //we use onRestoreInstanceState in order to play the video playback from the stored position
//        position = savedInstanceState.getInt("Position");
//        myVideoView.seekTo(position);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.navigation_item_1:
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.navigation_item_2:
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_graphite_item_detail, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.menu_item_share) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
