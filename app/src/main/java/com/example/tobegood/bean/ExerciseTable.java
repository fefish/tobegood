package com.example.tobegood.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

public class ExerciseTable {
    @DatabaseField(columnName = "ExerciseName",generatedId = true)
    private int ExerciseId;
    //主键

    @DatabaseField(columnName = "ExerciseName")
    private String ExerciseName;
    //锻炼名字

    @DatabaseField(columnName = "ExercisePic")
    private String ExercisePic;
    //锻炼图片

    @DatabaseField(columnName = "ExerciseContent")
    private String ExerciseContent;
    //锻炼内容

    @DatabaseField(columnName = "ExerciseType")
    private int ExerciseType;
    //锻炼类别


    public int getExerciseId() {
        return ExerciseId;
    }

    public void setExerciseId(int exerciseId) {
        ExerciseId = exerciseId;
    }

    public String getExerciseName() {
        return ExerciseName;
    }

    public void setExerciseName(String exerciseName) {
        ExerciseName = exerciseName;
    }

    public String getExercisePic() {
        return ExercisePic;
    }

    public void setExercisePic(String exercisePic) {
        ExercisePic = exercisePic;
    }

    public String getExerciseContent() {
        return ExerciseContent;
    }

    public void setExerciseContent(String exerciseContent) {
        ExerciseContent = exerciseContent;
    }

    public int getExerciseType() {
        return ExerciseType;
    }

    public void setExerciseType(int exerciseType) {
        ExerciseType = exerciseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseTable)) return false;
        ExerciseTable that = (ExerciseTable) o;
        return getExerciseId() == that.getExerciseId() &&
                getExerciseType() == that.getExerciseType() &&
                getExerciseName().equals(that.getExerciseName()) &&
                getExercisePic().equals(that.getExercisePic()) &&
                getExerciseContent().equals(that.getExerciseContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExerciseId(), getExerciseName(), getExercisePic(), getExerciseContent(), getExerciseType());
    }

    @Override
    public String toString() {
        return "ExerciseTable{" +
                "ExerciseId=" + ExerciseId +
                ", ExerciseName='" + ExerciseName + '\'' +
                ", ExercisePic='" + ExercisePic + '\'' +
                ", ExerciseContent='" + ExerciseContent + '\'' +
                ", ExerciseType=" + ExerciseType +
                '}';
    }
}
