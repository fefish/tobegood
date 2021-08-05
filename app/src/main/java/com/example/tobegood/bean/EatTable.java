package com.example.tobegood.bean;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "EatTable_info")

public class EatTable {

    @DatabaseField(columnName = "RecipeId",generatedId = true)
    private int RecipeId;
    //主键

    @DatabaseField(columnName = "RecipeOneName")
    private String RecipeOneName;
    //菜谱名字

    @DatabaseField(columnName = "RecipeOnePic")
    private String RecipeOnePic;
    //菜谱图片

    @DatabaseField(columnName = "RecipeOneContent")
    private String RecipeOneContent;
    //菜谱内容

    @DatabaseField(columnName = "RecipeTwoName")
    private String RecipeTwoName;
    //菜谱名字

    @DatabaseField(columnName = "RecipeTwoPic")
    private String RecipeTwoPic;
    //菜谱图片

    @DatabaseField(columnName = "RecipeTwoContent")
    private String RecipeTwoContent;
    //菜谱内容

    @DatabaseField(columnName = "RecipeThreeName")
    private String RecipeThreeName;
    //菜谱名字

    @DatabaseField(columnName = "RecipeThreePic")
    private String RecipeThreePic;
    //菜谱图片

    @DatabaseField(columnName = "RecipeThreeContent")
    private String RecipeThreeContent;
    //菜谱内容

    public EatTable(){

    }

    public EatTable(int recipeId, String recipeOneName, String recipeOnePic, String recipeOneContent,
                    String recipeTwoName, String recipeTwoPic, String recipeTwoContent,
                    String recipeThreeName, String recipeThreePic, String recipeThreeContent) {
        RecipeId = recipeId;
        RecipeOneName = recipeOneName;
        RecipeOnePic = recipeOnePic;
        RecipeOneContent = recipeOneContent;
        RecipeTwoName = recipeTwoName;
        RecipeTwoPic = recipeTwoPic;
        RecipeTwoContent = recipeTwoContent;
        RecipeThreeName = recipeThreeName;
        RecipeThreePic = recipeThreePic;
        RecipeThreeContent = recipeThreeContent;
    }

    public int getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(int recipeId) {
        RecipeId = recipeId;
    }

    public String getRecipeOneName() {
        return RecipeOneName;
    }

    public void setRecipeOneName(String recipeOneName) {
        RecipeOneName = recipeOneName;
    }

    public String getRecipeOnePic() {
        return RecipeOnePic;
    }

    public void setRecipeOnePic(String recipeOnePic) {
        RecipeOnePic = recipeOnePic;
    }

    public String getRecipeOneContent() {
        return RecipeOneContent;
    }

    public void setRecipeOneContent(String recipeOneContent) {
        RecipeOneContent = recipeOneContent;
    }

    public String getRecipeTwoName() {
        return RecipeTwoName;
    }

    public void setRecipeTwoName(String recipeTwoName) {
        RecipeTwoName = recipeTwoName;
    }

    public String getRecipeTwoPic() {
        return RecipeTwoPic;
    }

    public void setRecipeTwoPic(String recipeTwoPic) {
        RecipeTwoPic = recipeTwoPic;
    }

    public String getRecipeTwoContent() {
        return RecipeTwoContent;
    }

    public void setRecipeTwoContent(String recipeTwoContent) {
        RecipeTwoContent = recipeTwoContent;
    }

    public String getRecipeThreeName() {
        return RecipeThreeName;
    }

    public void setRecipeThreeName(String recipeThreeName) {
        RecipeThreeName = recipeThreeName;
    }

    public String getRecipeThreePic() {
        return RecipeThreePic;
    }

    public void setRecipeThreePic(String recipeThreePic) {
        RecipeThreePic = recipeThreePic;
    }

    public String getRecipeThreeContent() {
        return RecipeThreeContent;
    }

    public void setRecipeThreeContent(String recipeThreeContent) {
        RecipeThreeContent = recipeThreeContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EatTable)) return false;
        EatTable eatTable = (EatTable) o;
        return getRecipeId() == eatTable.getRecipeId() &&
                getRecipeOneName().equals(eatTable.getRecipeOneName()) &&
                getRecipeOnePic().equals(eatTable.getRecipeOnePic()) &&
                getRecipeOneContent().equals(eatTable.getRecipeOneContent()) &&
                getRecipeTwoName().equals(eatTable.getRecipeTwoName()) &&
                getRecipeTwoPic().equals(eatTable.getRecipeTwoPic()) &&
                getRecipeTwoContent().equals(eatTable.getRecipeTwoContent()) &&
                getRecipeThreeName().equals(eatTable.getRecipeThreeName()) &&
                getRecipeThreePic().equals(eatTable.getRecipeThreePic()) &&
                getRecipeThreeContent().equals(eatTable.getRecipeThreeContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecipeId(), getRecipeOneName(), getRecipeOnePic(), getRecipeOneContent(), getRecipeTwoName(), getRecipeTwoPic(), getRecipeTwoContent(), getRecipeThreeName(), getRecipeThreePic(), getRecipeThreeContent());
    }

    @Override
    public String toString() {
        return "EatTable{" +
                "RecipeId=" + RecipeId +
                ", RecipeOneName='" + RecipeOneName + '\'' +
                ", RecipeOnePic='" + RecipeOnePic + '\'' +
                ", RecipeOneContent='" + RecipeOneContent + '\'' +
                ", RecipeTwoName='" + RecipeTwoName + '\'' +
                ", RecipeTwoPic='" + RecipeTwoPic + '\'' +
                ", RecipeTwoContent='" + RecipeTwoContent + '\'' +
                ", RecipeThreeName='" + RecipeThreeName + '\'' +
                ", RecipeThreePic='" + RecipeThreePic + '\'' +
                ", RecipeThreeContent='" + RecipeThreeContent + '\'' +
                '}';
    }
}

