package geolab.myo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jay on 9/26/2015.
 */
public class ExerciseModel implements Serializable {
    private Date date;
    private String title;
    private long timeLimit;
    private int numberOfTries, numberOfWorkoutToDo, numberOfWorkOutDone = 0;

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
