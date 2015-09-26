package geolab.myo.model;


public class Exercises {

    private long duration;
    private String name;
    private String description;
    private String exImageExampleURL;
    boolean status;

    public enum ExerciseTypes {
        FINGERS_SPREAD,
        FIST,
        REST,
        UNKNOWN,
        DOUBLE_TAP,
        WAVE_IN,
        WAVE_OUT
    }

    public Exercises(long duration, String name,String description, String exImageExampleURL, boolean status) {
        this.duration = duration;
        this.name = name;
        this.description = description;
        this.exImageExampleURL = exImageExampleURL;
        this.status = status;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExImageExampleURL() {
        return exImageExampleURL;
    }

    public void setExImageExampleURL(String exImageExampleURL) {
        this.exImageExampleURL = exImageExampleURL;
    }

    public boolean isStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
