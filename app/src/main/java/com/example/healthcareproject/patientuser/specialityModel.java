package com.example.healthcareproject.patientuser;

public class specialityModel {

    int image,revnum2;
    private String drnamestr, drspecialitystr, dremailstr, drexperiancestr, drcitystr, drgenderstr, drcontactstr;
    private int druserid;

    public specialityModel() {}

    public specialityModel(int image, int druserid, String drnamestr, String drspecialitystr, String drexperiancestr, String drcitystr, String dremailstr, String drcontactstr, String drgenderstr,int revnum2) {
        this.image = image;
        this.drnamestr = drnamestr;
        this.drspecialitystr = drspecialitystr;
        this.dremailstr = dremailstr;
        this.drexperiancestr = drexperiancestr;
        this.drcitystr = drcitystr;
        this.drgenderstr = drgenderstr;
        this.drcontactstr = drcontactstr;
        this.druserid = druserid;
        this.revnum2=revnum2;
    }

    public int getRevnum2() {
        return revnum2;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDrnamestr() {
        return drnamestr;
    }

    public void setDrnamestr(String drnamestr) {
        this.drnamestr = drnamestr;
    }

    public String getDrspecialitystr() {
        return drspecialitystr;
    }

    public void setDrspecialitystr(String drspecialitystr) {
        this.drspecialitystr = drspecialitystr;
    }

    public String getDremailstr() {
        return dremailstr;
    }

    public void setDremailstr(String dremailstr) {
        this.dremailstr = dremailstr;
    }

    public String getDrexperiancestr() {
        return drexperiancestr;
    }

    public void setDrexperiancestr(String drexperiancestr) {
        this.drexperiancestr = drexperiancestr;
    }

    public String getDrcitystr() {
        return drcitystr;
    }

    public void setDrcitystr(String drcitystr) {
        this.drcitystr = drcitystr;
    }

    public String getDrgenderstr() {
        return drgenderstr;
    }

    public void setDrgenderstr(String drgenderstr) {
        this.drgenderstr = drgenderstr;
    }

    public String getDrcontactstr() {
        return drcontactstr;
    }

    public void setDrcontactstr(String drcontactstr) {
        this.drcontactstr = drcontactstr;
    }

    public int getDruserid() {
        return druserid;
    }

    public void setDruserid(int druserid) {
        this.druserid = druserid;
    }
}