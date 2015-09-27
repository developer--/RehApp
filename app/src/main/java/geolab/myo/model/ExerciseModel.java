package geolab.myo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jay on 9/26/2015.
 */
public class ExerciseModel implements Serializable {
    private Date date;
    private int id, type;
    private String title;
    private long timeLimit;
    private int numberOfTries, numberOfTriesDone, numberOfWorkoutToDo, numberOfWorkOutDone = 0;
    private int imageResource;

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ExerciseModel(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getNumberOfTries() {
        return numberOfTries;
    }

    public void setNumberOfTries(int numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    public int getNumberOfWorkoutToDo() {
        return numberOfWorkoutToDo;
    }

    public void setNumberOfWorkoutToDo(int numberOfWorkoutToDo) {
        this.numberOfWorkoutToDo = numberOfWorkoutToDo;
    }

    public int getNumberOfWorkOutDone() {
        return numberOfWorkOutDone;
    }

    public void setNumberOfWorkOutDone(int numberOfWorkOutDone) {
        this.numberOfWorkOutDone = numberOfWorkOutDone;
    }

    public int getNumberOfTriesDone() {
        return numberOfTriesDone;
    }

    public void setNumberOfTriesDone(int numberOfTriesDone) {
        this.numberOfTriesDone = numberOfTriesDone;
    }

    @Override
    public String toString() {
        return "ExerciseModel{" +
                "date=" + date +
                ", timeLimit=" + timeLimit +
                ", numberOfTries=" + numberOfTries +
                ", numberOfWorkoutToDo=" + numberOfWorkoutToDo +
                ", numberOfWorkOutDone=" + numberOfWorkOutDone +
                '}';
    }
}
