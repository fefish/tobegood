package com.example.tobegood.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


import java.util.Objects;
@DatabaseTable(tableName = "UserPlan_info")
public class UserPlan {
    @DatabaseField(id = true)
    private int id;

    @DatabaseField(columnName = "FirstRecipeId")
    private int FirstRecipeId;

    @DatabaseField(columnName = "FirstRecipeComplete")
    private Boolean FirstRecipeComplete;


    @DatabaseField(columnName = "SecondRecipeId")
    private int SecondRecipeId;

    @DatabaseField(columnName = "SecondRecipeComplete")
    private Boolean SecondRecipeComplete;

    @DatabaseField(columnName = "ThirdRecipeId")
    private int ThirdRecipeId;

    @DatabaseField(columnName = "ThirdRecipeComplete")
    private Boolean ThirdRecipeComplete;

    @DatabaseField(columnName = "FirstExerciseId")
    private int FirstExerciseId;

    @DatabaseField(columnName = "FirstExerciseComplete")
    private Boolean FirstExerciseComplete;

    @DatabaseField(columnName = "SecondExerciseId")
    private int SecondExerciseId;

    @DatabaseField(columnName = "SecondExerciseComplete")
    private Boolean SecondExerciseComplete;

    @DatabaseField(columnName = "ThirdExerciseId")
    private int ThirdExerciseId;

    @DatabaseField(columnName = "ThirdExerciseComplete")
    private Boolean ThirdExerciseComplete;

    public UserPlan(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstRecipeId() {
        return FirstRecipeId;
    }

    public void setFirstRecipeId(int firstRecipeId) {
        FirstRecipeId = firstRecipeId;
    }

    public int getSecondRecipeId() {
        return SecondRecipeId;
    }

    public void setSecondRecipeId(int secondRecipeId) {
        SecondRecipeId = secondRecipeId;
    }

    public int getThirdRecipeId() {
        return ThirdRecipeId;
    }

    public void setThirdRecipeId(int thirdRecipeId) {
        ThirdRecipeId = thirdRecipeId;
    }

    public int getFirstExerciseId() {
        return FirstExerciseId;
    }

    public void setFirstExerciseId(int firstExerciseId) {
        FirstExerciseId = firstExerciseId;
    }

    public int getSecondExerciseId() {
        return SecondExerciseId;
    }

    public void setSecondExerciseId(int secondExerciseId) {
        SecondExerciseId = secondExerciseId;
    }

    public int getThirdExerciseId() {
        return ThirdExerciseId;
    }

    public void setThirdExerciseId(int thirdExerciseId) {
        ThirdExerciseId = thirdExerciseId;
    }

    public Boolean getFirstRecipeComplete() {
        return FirstRecipeComplete;
    }

    public void setFirstRecipeComplete(Boolean firstRecipeComplete) {
        FirstRecipeComplete = firstRecipeComplete;
    }

    public Boolean getSecondRecipeComplete() {
        return SecondRecipeComplete;
    }

    public void setSecondRecipeComplete(Boolean secondRecipeComplete) {
        SecondRecipeComplete = secondRecipeComplete;
    }

    public Boolean getThirdRecipeComplete() {
        return ThirdRecipeComplete;
    }

    public void setThirdRecipeComplete(Boolean thirdRecipeComplete) {
        ThirdRecipeComplete = thirdRecipeComplete;
    }

    public Boolean getFirstExerciseComplete() {
        return FirstExerciseComplete;
    }

    public void setFirstExerciseComplete(Boolean firstExerciseComplete) {
        FirstExerciseComplete = firstExerciseComplete;
    }

    public Boolean getSecondExerciseComplete() {
        return SecondExerciseComplete;
    }

    public void setSecondExerciseComplete(Boolean secondExerciseComplete) {
        SecondExerciseComplete = secondExerciseComplete;
    }

    public Boolean getThirdExerciseComplete() {
        return ThirdExerciseComplete;
    }

    public void setThirdExerciseComplete(Boolean thirdExerciseComplete) {
        ThirdExerciseComplete = thirdExerciseComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPlan)) return false;
        UserPlan userPlan = (UserPlan) o;
        return getId() == userPlan.getId() &&
                getFirstRecipeId() == userPlan.getFirstRecipeId() &&
                getSecondRecipeId() == userPlan.getSecondRecipeId() &&
                getThirdRecipeId() == userPlan.getThirdRecipeId() &&
                getFirstExerciseId() == userPlan.getFirstExerciseId() &&
                getSecondExerciseId() == userPlan.getSecondExerciseId() &&
                getThirdExerciseId() == userPlan.getThirdExerciseId() &&
                getFirstRecipeComplete().equals(userPlan.getFirstRecipeComplete()) &&
                getSecondRecipeComplete().equals(userPlan.getSecondRecipeComplete()) &&
                getThirdRecipeComplete().equals(userPlan.getThirdRecipeComplete()) &&
                getFirstExerciseComplete().equals(userPlan.getFirstExerciseComplete()) &&
                getSecondExerciseComplete().equals(userPlan.getSecondExerciseComplete()) &&
                getThirdExerciseComplete().equals(userPlan.getThirdExerciseComplete());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstRecipeId(), getFirstRecipeComplete(), getSecondRecipeId(), getSecondRecipeComplete(), getThirdRecipeId(), getThirdRecipeComplete(), getFirstExerciseId(), getFirstExerciseComplete(), getSecondExerciseId(), getSecondExerciseComplete(), getThirdExerciseId(), getThirdExerciseComplete());
    }

    @Override
    public String toString() {
        return "UserPlan{" +
                "id=" + id +
                ", FirstRecipeId=" + FirstRecipeId +
                ", FirstRecipeComplete=" + FirstRecipeComplete +
                ", SecondRecipeId=" + SecondRecipeId +
                ", SecondRecipeComplete=" + SecondRecipeComplete +
                ", ThirdRecipeId=" + ThirdRecipeId +
                ", ThirdRecipeComplete=" + ThirdRecipeComplete +
                ", FirstExerciseId=" + FirstExerciseId +
                ", FirstExerciseComplete=" + FirstExerciseComplete +
                ", SecondExerciseId=" + SecondExerciseId +
                ", SecondExerciseComplete=" + SecondExerciseComplete +
                ", ThirdExerciseId=" + ThirdExerciseId +
                ", ThirdExerciseComplete=" + ThirdExerciseComplete +
                '}';
    }
}
