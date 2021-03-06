package geolab.myo.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MyoTutorial implements Serializable, Parcelable {
    private String title;
    private String description;
    private String imgURL;

    public MyoTutorial(String title, String description, String videoURL) {
        this.title = title;
        this.description = description;
        this.imgURL = videoURL;
    }

    public MyoTutorial(){}

    public MyoTutorial(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.imgURL = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public String toString() {
        return "MyoTutorial{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imgURL='" + imgURL + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.imgURL);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MyoTutorial createFromParcel(Parcel in) {
            return new MyoTutorial(in);
        }

        public MyoTutorial[] newArray(int size) {
            return new MyoTutorial[size];
        }
    };
}
