package geolab.myo.model;

import java.util.ArrayList;

/**
 * Created by Development on 9/26/2015.
 */
public class TutorialDummyData {
    public static ArrayList<MyoTutorial> dummyData = new ArrayList<>();
    public static ArrayList<MyoTutorial> insertList() {
        for (int i = 0; i < 20; i++) {
            MyoTutorial myoTutorial = new MyoTutorial("title # " + i, "agwera # " + i, "http://r13---sn-aigllnsy.googlevideo.com/videoplayback?nh=IgpwcjAzLmxocjE0KgkxMjcuMC4wLjE&fexp=9406821%2C9408710%2C9409069%2C9409172%2C9415365%2C9415485%2C9416023%2C9416126%2C9416573%2C9417203%2C9417655%2C9417707%2C9418153%2C9418204%2C9418215%2C9418448%2C9419302%2C9420078%2C9420175%2C9420348%2C9420629%2C9421013%2C9421106%2C9421535&mime=video%2Fmp4&pl=33&itag=22&upn=DjcfDSH2WoM&signature=9A726BD9EE8842871DE8182D02A018C065BC774E.633E830D9E1A04F4A02D6005C371A2946F62B013&ipbits=0&sparams=dur%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cnh%2Cpl%2Cratebypass%2Csource%2Cupn%2Cexpire&initcwndbps=1138750&source=youtube&id=o-AEDnLo-YEpqB98UYeAeZoHJH6LwkeIp0ulkgeBHBfkz2&sver=3&expire=1443328055&dur=2712.183&lmt=1435290460256546&key=yt6&ip=2a02%3A2498%3Ae000%3A85%3A13%3A%3A2&ms=au&mv=m&mt=1443306368&ratebypass=yes&mn=sn-aigllnsy&mm=31&title=Android+Tutorial+Project_ShoppingList+part3+%28Dracula+Untold%29");
            dummyData.add(myoTutorial);
        }
        return dummyData;
    }

}
