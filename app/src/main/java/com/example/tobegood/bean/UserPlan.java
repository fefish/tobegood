package com.example.tobegood.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "UserPlan_info")

public class UserPlan {
    @DatabaseField(columnName = "id", id = true)
    private String id;

    @DatabaseField(columnName = "RecipeId")
    private int RecipeId;

    @DatabaseField(columnName = "FirstRecipeComplete")
    private boolean FirstRecipeComplete;

    @DatabaseField(columnName = "SecondRecipeComplete")
    private boolean SecondRecipeComplete;

    @DatabaseField(columnName = "ThirdRecipeComplete")
    private boolean ThirdRecipeComplete;

    @DatabaseField(columnName = "ExerciseId")
    private int ExerciseId;

    @DatabaseField(columnName = "FirstExerciseComplete")
    private boolean FirstExerciseComplete;

    @DatabaseField(columnName = "SecondExerciseComplete")
    private boolean SecondExerciseComplete;

    @DatabaseField(columnName = "ThirdExerciseComplete")
    private boolean ThirdExerciseComplete;

    public UserPlan() {
    }

    public UserPlan(String id, int recipeId, boolean firstRecipeComplete, boolean secondRecipeComplete, boolean thirdRecipeComplete, int exerciseId, boolean firstExerciseComplete, boolean secondExerciseComplete, boolean thirdExerciseComplete) {
        this.id = id;
        RecipeId = recipeId;
        FirstRecipeComplete = firstRecipeComplete;
        SecondRecipeComplete = secondRecipeComplete;
        ThirdRecipeComplete = thirdRecipeComplete;
        ExerciseId = exerciseId;
        FirstExerciseComplete = firstExerciseComplete;
        SecondExerciseComplete = secondExerciseComplete;
        ThirdExerciseComplete = thirdExerciseComplete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(int recipeId) {
        RecipeId = recipeId;
    }

    public boolean getFirstRecipeComplete() {
        return FirstRecipeComplete;
    }

    public void setFirstRecipeComplete(boolean firstRecipeComplete) {
        FirstRecipeComplete = firstRecipeComplete;
    }

    public boolean getSecondRecipeComplete() {
        return SecondRecipeComplete;
    }

    public void setSecondRecipeComplete(boolean secondRecipeComplete) {
        SecondRecipeComplete = secondRecipeComplete;
    }

    public boolean getThirdRecipeComplete() {
        return ThirdRecipeComplete;
    }

    public void setThirdRecipeComplete(boolean thirdRecipeComplete) {
        ThirdRecipeComplete = thirdRecipeComplete;
    }

    public int getExerciseId() {
        return ExerciseId;
    }

    public void setExerciseId(int exerciseId) {
        ExerciseId = exerciseId;
    }

    public boolean getFirstExerciseComplete() {
        return FirstExerciseComplete;
    }

    public void setFirstExerciseComplete(boolean firstExerciseComplete) {
        FirstExerciseComplete = firstExerciseComplete;
    }

    public boolean getSecondExerciseComplete() {
        return SecondExerciseComplete;
    }

    public void setSecondExerciseComplete(boolean secondExerciseComplete) {
        SecondExerciseComplete = secondExerciseComplete;
    }

    public boolean getThirdExerciseComplete() {
        return ThirdExerciseComplete;
    }

    public void setThirdExerciseComplete(boolean thirdExerciseComplete) {
        ThirdExerciseComplete = thirdExerciseComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPlan)) return false;
        UserPlan userPlan = (UserPlan) o;
        return getRecipeId() == userPlan.getRecipeId() &&
                getFirstRecipeComplete() == userPlan.getFirstRecipeComplete() &&
                getSecondRecipeComplete() == userPlan.getSecondRecipeComplete() &&
                getThirdRecipeComplete() == userPlan.getThirdRecipeComplete() &&
                getExerciseId() == userPlan.getExerciseId() &&
                getFirstExerciseComplete() == userPlan.getFirstExerciseComplete() &&
                getSecondExerciseComplete() == userPlan.getSecondExerciseComplete() &&
                getThirdExerciseComplete() == userPlan.getThirdExerciseComplete() &&
                getId().equals(userPlan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRecipeId(), getFirstRecipeComplete(), getSecondRecipeComplete(), getThirdRecipeComplete(), getExerciseId(), getFirstExerciseComplete(), getSecondExerciseComplete(), getThirdExerciseComplete());
    }

    @Override
    public String toString() {
        return "UserPlan{" +
                "id='" + id + '\'' +
                ", RecipeId=" + RecipeId +
                ", FirstRecipeComplete=" + FirstRecipeComplete +
                ", SecondRecipeComplete=" + SecondRecipeComplete +
                ", ThirdRecipeComplete=" + ThirdRecipeComplete +
                ", FirstExerciseId=" + ExerciseId +
                ", FirstExerciseComplete=" + FirstExerciseComplete +
                ", SecondExerciseComplete=" + SecondExerciseComplete +
                ", ThirdExerciseComplete=" + ThirdExerciseComplete +
                '}';
    }
}
