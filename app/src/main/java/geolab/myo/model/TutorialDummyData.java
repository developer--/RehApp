package geolab.myo.model;

import java.util.ArrayList;

/**
 * Created by Development on 9/26/2015.
 */
public class TutorialDummyData {
    public static ArrayList<MyoTutorial> dummyData = new ArrayList<>();
    public static ArrayList<MyoTutorial> insertList() {
        for (int i = 0; i < 20; i++) {
            MyoTutorial myoTutorial = new MyoTutorial("title # " + i, "agwera # " + i, "video file path # " + i);
            dummyData.add(myoTutorial);
        }
        return dummyData;
    }

}
