package com.example.tobegood.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;
@DatabaseTable(tableName = "ExerciseTable_info")
public class ExerciseTable {
    @DatabaseField(columnName = "ExerciseId",generatedId = true)
    private int ExerciseId;
    //主键

    @DatabaseField(columnName = "ExerciseOneName")
    private String ExerciseOneName;
    //锻炼名字

    @DatabaseField(columnName = "ExerciseOnePic")
    private String ExerciseOnePic;
    //锻炼图片

    @DatabaseField(columnName = "ExerciseOneContent")
    private String ExerciseOneContent;
    //锻炼内容


    @DatabaseField(columnName = "ExerciseTwoName")
    private String ExerciseTwoName;
    //锻炼名字

    @DatabaseField(columnName = "ExerciseTwoPic")
    private String ExerciseTwoPic;
    //锻炼图片

    @DatabaseField(columnName = "ExerciseTwoContent")
    private String ExerciseTwoContent;
    //锻炼内容


    @DatabaseField(columnName = "ExerciseThreeName")
    private String ExerciseThreeName;
    //锻炼名字

    @DatabaseField(columnName = "ExerciseThreePic")
    private String ExerciseThreePic;
    //锻炼图片

    @DatabaseField(columnName = "ExerciseThreeContent")
    private String ExerciseThreeContent;
    //锻炼内容


    public ExerciseTable() {
    }

    public ExerciseTable(int exerciseId, String exerciseOneName, String exerciseOnePic,
                         String exerciseOneContent, String exerciseTwoName, String exerciseTwoPic,
                         String exerciseTwoContent, String exerciseThreeName, String exerciseThreePic,
                         String exerciseThreeContent) {
        ExerciseId = exerciseId;
        ExerciseOneName = exerciseOneName;
        ExerciseOnePic = exerciseOnePic;
        ExerciseOneContent = exerciseOneContent;
        ExerciseTwoName = exerciseTwoName;
        ExerciseTwoPic = exerciseTwoPic;
        ExerciseTwoContent = exerciseTwoContent;
        ExerciseThreeName = exerciseThreeName;
        ExerciseThreePic = exerciseThreePic;
        ExerciseThreeContent = exerciseThreeContent;
    }

    public int getExerciseId() {
        return ExerciseId;
    }

    public void setExerciseId(int exerciseId) {
        ExerciseId = exerciseId;
    }

    public String getExerciseOneName() {
        return ExerciseOneName;
    }

    public void setExerciseOneName(String exerciseOneName) {
        ExerciseOneName = exerciseOneName;
    }

    public String getExerciseOnePic() {
        return ExerciseOnePic;
    }

    public void setExerciseOnePic(String exerciseOnePic) {
        ExerciseOnePic = exerciseOnePic;
    }

    public String getExerciseOneContent() {
        return ExerciseOneContent;
    }

    public void setExerciseOneContent(String exerciseOneContent) {
        ExerciseOneContent = exerciseOneContent;
    }

    public String getExerciseTwoName() {
        return ExerciseTwoName;
    }

    public void setExerciseTwoName(String exerciseTwoName) {
        ExerciseTwoName = exerciseTwoName;
    }

    public String getExerciseTwoPic() {
        return ExerciseTwoPic;
    }

    public void setExerciseTwoPic(String exerciseTwoPic) {
        ExerciseTwoPic = exerciseTwoPic;
    }

    public String getExerciseTwoContent() {
        return ExerciseTwoContent;
    }

    public void setExerciseTwoContent(String exerciseTwoContent) {
        ExerciseTwoContent = exerciseTwoContent;
    }

    public String getExerciseThreeName() {
        return ExerciseThreeName;
    }

    public void setExerciseThreeName(String exerciseThreeName) {
        ExerciseThreeName = exerciseThreeName;
    }

    public String getExerciseThreePic() {
        return ExerciseThreePic;
    }

    public void setExerciseThreePic(String exerciseThreePic) {
        ExerciseThreePic = exerciseThreePic;
    }

    public String getExerciseThreeContent() {
        return ExerciseThreeContent;
    }

    public void setExerciseThreeContent(String exerciseThreeContent) {
        ExerciseThreeContent = exerciseThreeContent;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseTable)) return false;
        ExerciseTable that = (ExerciseTable) o;
        return getExerciseId() == that.getExerciseId() &&
                getExerciseOneName().equals(that.getExerciseOneName()) &&
                getExerciseOnePic().equals(that.getExerciseOnePic()) &&
                getExerciseOneContent().equals(that.getExerciseOneContent()) &&
                getExerciseTwoName().equals(that.getExerciseTwoName()) &&
                getExerciseTwoPic().equals(that.getExerciseTwoPic()) &&
                getExerciseTwoContent().equals(that.getExerciseTwoContent()) &&
                getExerciseThreeName().equals(that.getExerciseThreeName()) &&
                getExerciseThreePic().equals(that.getExerciseThreePic()) &&
                getExerciseThreeContent().equals(that.getExerciseThreeContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExerciseId(), getExerciseOneName(), getExerciseOnePic(), getExerciseOneContent(), getExerciseTwoName(), getExerciseTwoPic(), getExerciseTwoContent(), getExerciseThreeName(), getExerciseThreePic(), getExerciseThreeContent());
    }

    @Override
    public String toString() {
        return "ExerciseTable{" +
                "ExerciseId=" + ExerciseId +
                ", ExerciseOneName='" + ExerciseOneName + '\'' +
                ", ExerciseOnePic='" + ExerciseOnePic + '\'' +
                ", ExerciseOneContent='" + ExerciseOneContent + '\'' +
                ", ExerciseTwoName='" + ExerciseTwoName + '\'' +
                ", ExerciseTwoPic='" + ExerciseTwoPic + '\'' +
                ", ExerciseTwoContent='" + ExerciseTwoContent + '\'' +
                ", ExerciseThreeName='" + ExerciseThreeName + '\'' +
                ", ExerciseThreePic='" + ExerciseThreePic + '\'' +
                ", ExerciseThreeContent='" + ExerciseThreeContent + '\'' +
                '}';
    }

}
