package com.example.tobegood.bean;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "EatTable_info")

public class EatTable {

    @DatabaseField(columnName = "RecipeName",generatedId = true)
    private int RecipeId;
    //主键

    @DatabaseField(columnName = "RecipeName")
    private String RecipeName;
    //菜谱名字

    @DatabaseField(columnName = "RecipePic")
    private String RecipePic;
    //菜谱图片

    @DatabaseField(columnName = "RecipeContent")
    private String RecipeContent;
    //菜谱内容

    @DatabaseField(columnName = "RecipeType")
    private int RecipeType;
    //菜谱类别

    public EatTable(){

    }

    public int getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(int recipeId) {
        RecipeId = recipeId;
    }

    public String getRecipePic() {
        return RecipePic;
    }

    public void setRecipePic(String recipePic) {
        RecipePic = recipePic;
    }

    public String getRecipeContent() {
        return RecipeContent;
    }

    public void setRecipeContent(String recipeContent) {
        RecipeContent = recipeContent;
    }

    public int getRecipeType() {
        return RecipeType;
    }

    public void setRecipeType(int recipeType) {
        RecipeType = recipeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EatTable)) return false;
        EatTable eatTable = (EatTable) o;
        return getRecipeId() == eatTable.getRecipeId() &&
                getRecipeType() == eatTable.getRecipeType() &&
                Objects.equals(RecipeName, eatTable.RecipeName) &&
                Objects.equals(getRecipePic(), eatTable.getRecipePic()) &&
                Objects.equals(getRecipeContent(), eatTable.getRecipeContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecipeId(), RecipeName, getRecipePic(), getRecipeContent(), getRecipeType());
    }

    @Override
    public String toString() {
        return "EatTable{" +
                "RecipeId=" + RecipeId +
                ", RecipeName='" + RecipeName + '\'' +
                ", RecipePic='" + RecipePic + '\'' +
                ", RecipeContent='" + RecipeContent + '\'' +
                ", RecipeType=" + RecipeType +
                '}';
    }


}

