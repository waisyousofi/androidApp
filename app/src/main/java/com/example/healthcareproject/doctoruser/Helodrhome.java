package com.example.healthcareproject.doctoruser;

public class Helodrhome {

    int image;
    String pname,pcontactno,pemail,padds,pgender,visitingadd,visitday,tokenno;
    int ptuserid,tokenrowid;
    static int viewposition;



    public Helodrhome(){

    }
    public Helodrhome(int image, String pname, String pcontactno, String pgender, String pemail, String tokenno, String visitday, String visitingadd, int ptuserid, int tokenrowid) {
        this.image = image;
        this.pname=pname;
        this.pemail=pemail;
        this.pcontactno=pcontactno;
        this.padds=padds;
        this.pgender=pgender;
        this.tokenno=tokenno;
        this.visitingadd=visitingadd;
        this.visitday=visitday;
        this.ptuserid=ptuserid;
        this.tokenrowid=tokenrowid;
    }

    public Helodrhome(int ic_account_circle_black_24dp, String ptnamestr, String ptcontactstr, String ptptgenderstr, String ptemailstr, String tokennumber, String visitingdaystr, String visitingaddsstr, int ptuserid) {
    }

    public String getPname() {
        return pname;
    }

    public String getPemail() {
        return pemail;
    }

    public int getTokenrowid() {
        return tokenrowid;
    }
    public String getVisitday()
    {
        return visitday;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return pname;
    }
    public String getEmail() {
        return pemail;
    }

    public String getPcontactno() {
        return pcontactno;
    }
    public String getPadds() {
        return padds;
    }
    public String getPgender() {
        return pgender;
    }
    public String getVisitingadd() {
        return visitingadd;
    }
    public String getTokenno() {
        return tokenno;
    }
    public int getPtuserid(){
        return ptuserid;
    }

    public static int getViewposition() {
        return viewposition;
    }

    public void setViewposition(int viewposition) {
        this.viewposition = viewposition;
    }
}