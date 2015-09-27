package geolab.myo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Arm;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.XDirection;
import com.thalmic.myo.scanner.ScanActivity;

import geolab.myo.R;
import geolab.myo.fragment.WorkoutFragment;
import geolab.myo.fragment.WorkoutIntroFragment;

public class WorkoutActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WorkoutFragment workoutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setVisibility(View.GONE);

//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setDisplayHomeAsUpEnabled(true);

        FrameLayout container = (FrameLayout) findViewById(R.id.fragment_container);

        WorkoutIntroFragment introfragment = new WorkoutIntroFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, introfragment, "intro");
     //   ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();



        Hub hub = Hub.getInstance();
//        if (!hub.init(this, getPackageName())) {
//            // We can't do anything with the Myo device if the Hub can't be initialized, so exit.
//            Toast.makeText(this, "Couldn't initialize Hub", Toast.LENGTH_SHORT).show();
//            finish();
//            return;
//        }

        // Next, register for DeviceListener callbacks.
        hub.addListener(mListener);
        hub.setLockingPolicy(Hub.LockingPolicy.NONE);

    }

    public void setWorkoutFragment(WorkoutFragment workoutFragment){
        this.workoutFragment = workoutFragment;
    }


    private void onScanActionSelected() {
        // Launch the ScanActivity to scan for Myos to connect to.
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (R.id.action_scan == id) {
            onScanActionSelected();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private DeviceListener mListener = new AbstractDeviceListener() {

        // onConnect() is called whenever a Myo has been connected.
        @Override
        public void onConnect(Myo myo, long timestamp) {
            // Set the text color of the text view to cyan when a Myo connects.
//            mTextView.setTextColor(Color.CYAN);
        }

        // onDisconnect() is called whenever a Myo has been disconnected.
        @Override
        public void onDisconnect(Myo myo, long timestamp) {
            // Set the text color of the text view to red when a Myo disconnects.
//            mTextView.setTextColor(Color.RED);
        }

        // onArmSync() is called whenever Myo has recognized a Sync Gesture after someone has put it on their
        // arm. This lets Myo know which arm it's on and which way it's facing.
        @Override
        public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection) {
//            mTextView.setText(myo.getArm() == Arm.LEFT ? R.string.arm_left : R.string.arm_right);
        }

        // onArmUnsync() is called whenever Myo has detected that it was moved from a stable position on a person's arm after
        // it recognized the arm. Typically this happens when someone takes Myo off of their arm, but it can also happen
        // when Myo is moved around on the arm.
        @Override
        public void onArmUnsync(Myo myo, long timestamp) {
//            mTextView.setText(R.string.hello_world);
        }

        Vector3 v = new Vector3();

        // onUnlock() is called whenever a synced Myo has been unlocked. Under the standard locking
        // policy, that means poses will now be delivered to the listener.
        @Override
        public void onUnlock(Myo myo, long timestamp) {
            Toast.makeText(getApplicationContext(), "unlocked", Toast.LENGTH_SHORT).show();
//            mLockStateView.setText(R.string.unlocked);
        }

        // onLock() is called whenever a synced Myo has been locked. Under the standard locking
        // policy, that means poses will no longer be delivered to the listener.
        @Override
        public void onLock(Myo myo, long timestamp) {
            Toast.makeText(getApplicationContext(), "locked", Toast.LENGTH_SHORT).show();
//            mLockStateView.setText(R.string.locked);
        }


        @Override
        public void onGyroscopeData(Myo myo, long timestamp, Vector3 gyro) {
//            Log.d("x ---- ", gyro.x() + "");
//            Log.d("y ---- ", gyro.y() + "");
//            Log.d("z ---- ", gyro.z() + "");
        }
        // onOrientationData() is called whenever a Myo provides its current orientation,
        // represented as a quaternion.
        @Override
        public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
            // Calculate Euler angles (roll, pitch, and yaw) from the quaternion.
            float roll = (float) Math.toDegrees(Quaternion.roll(rotation));
            float pitch = (float) Math.toDegrees(Quaternion.pitch(rotation));
            float yaw = (float) Math.toDegrees(Quaternion.yaw(rotation));

            // Adjust roll and pitch for the orientation of the Myo on the arm.
            if (myo.getXDirection() == XDirection.TOWARD_ELBOW) {
                roll *= -1;
                pitch *= -1;
            }

            // Next, we apply a rotation to the text view using the roll, pitch, and yaw.
//            mTextView.setRotation(roll);
//            mTextView.setRotationX(pitch);
//            mTextView.setRotationY(yaw);
        }


        // onPose() is called whenever a Myo provides a new pose.
        Pose current = Pose.UNKNOWN;
        @Override
        public void onPose(Myo myo, long timestamp, Pose pose) {
            // catches two combination

//            if( current == Pose.FINGERS_SPREAD && (pose == Pose.FIST || pose == Pose.REST)){
//                mTextView.setText(getString(R.string.result));
////                Log.d("result", mTextView.getText().toString());
//                current = Pose.UNKNOWN;
//            }else{
//                current = pose;
//            }
//            Log.d("garage", pose.toString());
//            Log.d("current", current.toString());

//            mLockStateView.setText(pose.toString());
//            if(pose == Pose.FINGERS_SPREAD ) {
//                if (pose == Pose.FIST) {
//                    mTextView.setText(getString(R.string.hello_world));
//                }
//            }

            // Handle the cases of the Pose enumeration, and change the text of the text view
            // based on the pose we receive.
            switch (pose) {
                case UNKNOWN:
//                    mTextView.setText(getString(R.string.hello_world));
                    break;
                case REST:
                case DOUBLE_TAP:
                    int restTextId = R.string.hello_world;
                    switch (myo.getArm()) {
                        case LEFT:
                            restTextId = R.string.arm_left;
                            break;
                        case RIGHT:
                            restTextId = R.string.arm_right;
                            break;
                    }
//                    mTextView.setText(getString(restTextId));
                    break;
                case FIST: // check fist counts
//                    mTextView.setText(getString(R.string.pose_fist));
//                    fistCounter++;
                    Toast.makeText(getApplicationContext(),"fist",Toast.LENGTH_SHORT).show();
                    sendGesture(1);
                    break;
                case WAVE_IN:
//                    mTextView.setText(getString(R.string.pose_wavein));
                    Toast.makeText(getApplicationContext()," left",Toast.LENGTH_SHORT).show();
                    break;
                case WAVE_OUT:
//                    mTextView.setText(getString(R.string.pose_waveout));
                    Toast.makeText(getApplicationContext()," right",Toast.LENGTH_SHORT).show();
                    break;
                case FINGERS_SPREAD:
//                    mTextView.setText(getString(R.string.pose_fingersspread));
                    Toast.makeText(getApplicationContext(),"თითების გაშლა",Toast.LENGTH_SHORT).show();
                    sendGesture(2);
                    break;
            }

            if (pose != Pose.UNKNOWN && pose != Pose.REST) {
                // Tell the Myo to stay unlocked until told otherwise. We do that here so you can
                // hold the poses without the Myo becoming locked.
                myo.unlock(Myo.UnlockType.TIMED);

                // Notify the Myo that the pose has resulted in an action, in this case changing
                // the text on the screen. The Myo will vibrate.
                myo.notifyUserAction();
            } else {
                // Tell the Myo to stay unlocked only for a short period. This allows the Myo to
                // stay unlocked while poses are being performed, but lock after inactivity.
                myo.unlock(Myo.UnlockType.TIMED);
            }
        }
    };

    private void sendGesture(int type){
        if(workoutFragment != null){
            workoutFragment.updateRep(type);
        }
    }
}
