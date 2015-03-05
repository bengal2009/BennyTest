package com.example.blin.bennytest;

/**
 * Created by blin on 2015/3/5.
 */
public class PersonItem {
    private String FamilyName;
    private String Birthday;
    private String Gender;

    @Override
    public String toString() {
//        return super.toString();
        return "[ FamilyName=" + FamilyName + ", Gender=" +
                Gender + " , date=" + Birthday + "]";
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String familyName) {
        FamilyName = familyName;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}

