package com.example.qrcode.bean;

/**
 * @author relax
 * @version 1.0
 * @功能描述：
 * @date 2021/10/8 22:55
 */
public class DoctorOrder {

    private Long doctorID;
    private String doctorName;
    private String doctorPhone;
    private String doctorTags;

    public DoctorOrder(Long doctorID, String doctorName, String doctorPhone, String doctorTags) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.doctorTags = doctorTags;
    }

    public Long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getDoctorTags() {
        return doctorTags;
    }

    public void setDoctorTags(String doctorTags) {
        this.doctorTags = doctorTags;
    }

    @Override
    public String toString() {
        return "DoctorOrder{" +
                "doctorID=" + doctorID +
                ", doctorName='" + doctorName + '\'' +
                ", doctorPhone='" + doctorPhone + '\'' +
                ", doctorTags='" + doctorTags + '\'' +
                '}';
    }
}
