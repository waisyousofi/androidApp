package com.example.healthcareproject.patientuser;

class Helonuts {

    String name,benefits;
    int image;

    public Helonuts(int image, String name, String benefits)
    {
        this.image=image;
        this.name=name;
        this.benefits=benefits;
    }

        public int getImage()
    {
        return image;
    }
    public String getName()
    {
        return name;
    }
    public String getBenefits()
    {
        return benefits;
    }
}
