package com.example.healthcareproject.patientuser;

class Helohospital {

    String hospadds,hosptype,hospmobno,emailid;
    int revnum,drid;
    public Helohospital(String hospadds,String hosptype,String hospmobno,String emailid,int revnum,int drid)
    {
        this.hospadds=hospadds;
        this.hosptype=hosptype;
        this.hospmobno=hospmobno;
        this.emailid=emailid;
        this.revnum=revnum;
        this.drid=drid;
    }

    public int getRevnum()
    {
        return revnum;
    }
    public String getHospadds()
    {
        return hospadds;
    }
    public String getHosptype()
    {
        return hosptype;
    }

    public int getDrid() {
        return drid;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getHosmobno() {
        return hospmobno;
    }
}
