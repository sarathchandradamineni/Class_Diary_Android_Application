package com.example.sarath.cseiimdpjntuk;


public class Subjects_files_upload{
    String name_of_file,uri,random_name;
    public Subjects_files_upload()
    {

    }
    public Subjects_files_upload(String uri,String name_of_file,String random_name)

    {
        this.uri = uri;
        this.name_of_file = name_of_file;
        this.random_name = random_name;
    }
    public String getUri()
    {
        return uri;
    }
    public String getName_of_file()
    {
        return name_of_file;
    }

    public String getRandom_name() {
        return random_name;
    }
}
