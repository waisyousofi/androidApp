package com.example.healthcareproject.patientuser;

class Heloall {

    int image;
    String drspeciality,emailid,drname,drlocation,drexperiance;

    public Heloall(int image, String drname,String drspeciality,String emailid,String drexperiance,String drlocation)
    {
        this.image = image;
        this.drspeciality = drspeciality;
        this.emailid=emailid;
        this.drname=drname;
        this.drexperiance=drexperiance;
        this.drlocation=drlocation;

    }

    public String getDrlocation()
    {
        return drlocation;
    }
    public String getDrexperiance()
    {
        return drexperiance;
    }
    public int getImage() {
        return image;
    }

    public String getName() {
        return drname;
    }

    public String getEmail() {
        return emailid;
    }//only for test

    public String getDrspeciality() {
        return drspeciality;
    }
}