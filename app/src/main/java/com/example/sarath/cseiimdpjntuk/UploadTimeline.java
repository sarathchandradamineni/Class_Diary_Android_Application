package com.example.sarath.cseiimdpjntuk;

public class UploadTimeline {
    String roll;
    String name_of_person;
    String imagedescription;
    String image_URL;

    public  UploadTimeline() {

    }

    public UploadTimeline(String roll, String name_of_person, String imagedescription, String image_URL) {
        this.roll = roll;
        this.name_of_person = name_of_person;
        this.imagedescription = imagedescription;
        this.image_URL = image_URL;
    }



    public void setRoll(String roll)
    {
        this.roll = roll;
    }
    public String getRoll()
    {
        return roll;
    }


    public void setName_of_person(String name_of_person)
    {
        this.name_of_person = name_of_person;
    }
    public String getName_of_person()
    {
        return name_of_person;
    }


    public void setImagedescription(String imagedescription)
    {
        this.imagedescription = imagedescription;
    }
    public String getImagedescription()
    {
        return imagedescription;
    }


    public void setImage_URL(String image_URL)
    {
        this.image_URL = image_URL;
    }
    public String getImage_URL()
    {
        return image_URL;
    }



}
