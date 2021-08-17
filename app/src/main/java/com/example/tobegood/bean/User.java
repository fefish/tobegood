package com.example.tobegood.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

//配置表名
@DatabaseTable(tableName = "user_info")
public class User {
    @DatabaseField(id = true)
    private int id;
    //用户id
    @DatabaseField(columnName = "name")
    private String name;
    //用户名
    @DatabaseField(columnName = "password")
    private String password;
    //密码
    @DatabaseField(columnName = "sex")
    private boolean sex;
    //性别
    @DatabaseField(columnName = "vegan")
    private boolean vegan;
    //是否素食
    @DatabaseField(columnName = "height")
    private float height;
    //身高
    @DatabaseField(columnName = "weight")
    private float weight;
    //体重
    @DatabaseField(columnName = "eatdisorder")
    private boolean eatdisorder;
    //是否需要饮食障碍帮助

    @DatabaseField(columnName = "lastday")
    private int lastDay;

    @DatabaseField(columnName = "emergencynumber")
    private String emergencyNumber;

    @DatabaseField(columnName = "purpose")
    private int purpose;
    //空构造
    public User() {
    }


    public User(int id, String name, String password, boolean sex, boolean vegan, float height, float weight, boolean eatingDisorder, int lastDay, String emergencyNumber, int purpose) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.vegan = vegan;
        this.height = height;
        this.weight = weight;
        this.eatdisorder = eatingDisorder;
        this.lastDay = lastDay;
        this.emergencyNumber = emergencyNumber;
        this.purpose = purpose;
    }

    public int getPurpose() {
        return purpose;
    }

    public void setPurpose(int purpose) {

        this.purpose = purpose;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isEatdisorder() {
        return eatdisorder;
    }

    public void setEatdisorder(boolean eatdisorder) {
        this.eatdisorder = eatdisorder;
    }

    public int getLastDay() {
        return lastDay;
    }

    public void setLastDay(int lastDay) {
        this.lastDay = lastDay;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                isSex() == user.isSex() &&
                isVegan() == user.isVegan() &&
                Float.compare(user.getHeight(), getHeight()) == 0 &&
                Float.compare(user.getWeight(), getWeight()) == 0 &&
                isEatdisorder() == user.isEatdisorder() &&
                getLastDay() == user.getLastDay() &&
                getName().equals(user.getName()) &&
                getPassword().equals(user.getPassword()) &&
                getEmergencyNumber().equals(user.getEmergencyNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPassword(), isSex(), isVegan(), getHeight(), getWeight(), isEatdisorder(), getLastDay(), getEmergencyNumber());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", vegan=" + vegan +
                ", height=" + height +
                ", weight=" + weight +
                ", eatdisorder=" + eatdisorder +
                ", lastday=" + lastDay +
                ", emergencynumber='" + emergencyNumber + '\'' +
                '}';
    }
}

