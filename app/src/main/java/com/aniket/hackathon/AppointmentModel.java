package com.aniket.hackathon;

public class AppointmentModel {

    public  String appointedto="";
    public String date="";
    public String reason="";

    public AppointmentModel(String appointedto, String date,String reason) {
        this.appointedto = appointedto;
        this.date = date;
        this.reason=reason;
    }
}
