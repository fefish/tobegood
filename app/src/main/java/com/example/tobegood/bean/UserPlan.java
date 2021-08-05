package com.example.tobegood.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;
@DatabaseTable(tableName = "UserPlan_info")

public class UserPlan {
    @DatabaseField(id = true)
    private int id;

    @DatabaseField(columnName = "RecipeId")
    private int FirstRecipeId;

    @DatabaseField(columnName = "FirstRecipeComplete")
    private Boolean FirstRecipeComplete;

    @DatabaseField(columnName = "SecondRecipeComplete")
    private Boolean SecondRecipeComplete;

    @DatabaseField(columnName = "ThirdRecipeComplete")
    private Boolean ThirdRecipeComplete;

    @DatabaseField(columnName = "ExerciseId")
    private int FirstExerciseId;

    @DatabaseField(columnName = "FirstExerciseComplete")
    private Boolean FirstExerciseComplete;

    @DatabaseField(columnName = "SecondExerciseComplete")
    private Boolean SecondExerciseComplete;

    @DatabaseField(columnName = "ThirdExerciseComplete")
    private Boolean ThirdExerciseComplete;

    public UserPlan(){
    }

    public UserPlan(int id, int firstRecipeId, Boolean firstRecipeComplete, Boolean secondRecipeComplete, Boolean thirdRecipeComplete, int firstExerciseId, Boolean firstExerciseComplete, Boolean secondExerciseComplete, Boolean thirdExerciseComplete) {
        this.id = id;
        FirstRecipeId = firstRecipeId;
        FirstRecipeComplete = firstRecipeComplete;
        SecondRecipeComplete = secondRecipeComplete;
        ThirdRecipeComplete = thirdRecipeComplete;
        FirstExerciseId = firstExerciseId;
        FirstExerciseComplete = firstExerciseComplete;
        SecondExerciseComplete = secondExerciseComplete;
        ThirdExerciseComplete = thirdExerciseComplete;
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

    public int getFirstExerciseId() {
        return FirstExerciseId;
    }

    public void setFirstExerciseId(int firstExerciseId) {
        FirstExerciseId = firstExerciseId;
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
                getFirstExerciseId() == userPlan.getFirstExerciseId() &&
                getFirstRecipeComplete().equals(userPlan.getFirstRecipeComplete()) &&
                getSecondRecipeComplete().equals(userPlan.getSecondRecipeComplete()) &&
                getThirdRecipeComplete().equals(userPlan.getThirdRecipeComplete()) &&
                getFirstExerciseComplete().equals(userPlan.getFirstExerciseComplete()) &&
                getSecondExerciseComplete().equals(userPlan.getSecondExerciseComplete()) &&
                getThirdExerciseComplete().equals(userPlan.getThirdExerciseComplete());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstRecipeId(), getFirstRecipeComplete(), getSecondRecipeComplete(), getThirdRecipeComplete(), getFirstExerciseId(), getFirstExerciseComplete(), getSecondExerciseComplete(), getThirdExerciseComplete());
    }

    @Override
    public String toString() {
        return "UserPlan{" +
                "id=" + id +
                ", FirstRecipeId=" + FirstRecipeId +
                ", FirstRecipeComplete=" + FirstRecipeComplete +
                ", SecondRecipeComplete=" + SecondRecipeComplete +
                ", ThirdRecipeComplete=" + ThirdRecipeComplete +
                ", FirstExerciseId=" + FirstExerciseId +
                ", FirstExerciseComplete=" + FirstExerciseComplete +
                ", SecondExerciseComplete=" + SecondExerciseComplete +
                ", ThirdExerciseComplete=" + ThirdExerciseComplete +
                '}';
    }

}
