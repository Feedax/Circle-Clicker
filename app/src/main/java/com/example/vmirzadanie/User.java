package com.example.vmirzadanie;

public class User {

    private String scoreSD, emailSD, goldGainedSD, nameSD, photoUrlSD;

    public User() {
    }

    public User(String scoreSD, String emailSD, String goldGainedSD, String nameSD, String photoUrlSD) {
        this.scoreSD = scoreSD;
        this.emailSD = emailSD;
        this.goldGainedSD = goldGainedSD;
        this.nameSD = nameSD;
        this.photoUrlSD = photoUrlSD;

    }

    public String getScoreSD() {
        return scoreSD;
    }

    public String getEmailSD() {
        return emailSD;
    }

    public String getGoldGainedSD() {
        return goldGainedSD;
    }

    public String getNameSD() {
        return nameSD;
    }

    public String getPhotoUrlSD() {
        return photoUrlSD;
    }


}
