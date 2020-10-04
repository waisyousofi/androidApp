package com.example.healthcareproject.patientuser;

class Helouser {

    int image,druserid,ptuserid,tokenrowid;
    String visitingday,visitingadds,pttokenno,drname;

    public Helouser(int image, int druserid, int ptuserid, String visitingday, String visitingadds, String pttokenno,int tokenrowid,String drname) {
        this.image = image;
        this.druserid = druserid;
        this.ptuserid = ptuserid;
        this.visitingday = visitingday;
        this.visitingadds = visitingadds;
        this.pttokenno = pttokenno;
        this.drname=drname;
        this.tokenrowid=tokenrowid;
    }

    public String getDrname() {
        return drname;
    }
    public int getImage() {
        return image;
    }

    public int getDruserid() {
        return druserid;
    }

    public int getPtuserid() {
        return ptuserid;
    }

    public int getTokenrowid() {
        return tokenrowid;
    }
    public String getVisitingday() {
        return visitingday;
    }

    public String getVisitingadds() {
        return visitingadds;
    }

    public String getPttokenno() {
        return pttokenno;
    }
}