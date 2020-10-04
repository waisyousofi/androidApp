package com.example.healthcareproject.patientuser;

public class Helo {

    int image;
    private String drnamestr, drspecialitystr, dremailstr, drexperiancestr, drcitystr, drgenderstr, drcontactstr;
    private int druserid;
    public Helo() {}
    private int reviewnum;
    public Helo(int image,int druserid,String drnamestr,String drspecialitystr,String drexperiancestr,String drcitystr,String dremailstr,String drcontactstr,String drgenderstr,int reviewnum) {
        this.image = image;
        this.drnamestr = drnamestr;
        this.drspecialitystr = drspecialitystr;
        this.dremailstr = dremailstr;
        this.drexperiancestr = drexperiancestr;
        this.drcitystr = drcitystr;
        this.drgenderstr = drgenderstr;
        this.drcontactstr = drcontactstr;
        this.druserid = druserid;
        this.reviewnum=reviewnum;
    }
    public int getReviewnum() {
        return reviewnum;
    }

    public int getImage() {
        return image;
    }

    public String getDrnamestr() {
        return drnamestr;
    }

    public String getDrspecialitystr() {
        return drspecialitystr;
    }

    public String getDremailstr() {
        return dremailstr;
    }

    public String getDrexperiancestr() {
        return drexperiancestr;
    }

    public String getDrcitystr() {
        return drcitystr;
    }

    public String getDrgenderstr() {
        return drgenderstr;
    }

    public String getDrcontactstr() {
        return drcontactstr;
    }

    public int getDruserid() {
        return druserid;
    }
}