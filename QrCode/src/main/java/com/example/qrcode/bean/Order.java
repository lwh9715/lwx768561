package com.example.qrcode.bean;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author relax
 * @version 1.0
 * @功能描述：
 * @date 2021/10/8 22:55
 */
public class Order {

    private Long orderID;
    private List<DoctorOrder> doctorOrderList;

    public Order(JsonNode nodes) {

        String orderID = nodes.path("orderID").asText();
        JsonNode doctorOrderList = nodes.path("doctorOrderList");

        setOrderID(Long.valueOf(orderID));
        List<DoctorOrder> list = new ArrayList<>();
        doctorOrderList.forEach((JsonNode node) -> {
            long doctorID = node.path("doctorID").asLong();
            String doctorName = node.path("doctorName").asText();
            String doctorPhone = node.path("doctorPhone").asText();
            String doctorTags = node.path("doctorTags").asText();
            DoctorOrder doctorOrder = new DoctorOrder(doctorID, doctorName, doctorPhone, doctorTags);
            list.add(doctorOrder);
        });
        setDoctorOrderList(list);
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public List<DoctorOrder> getDoctorOrderList() {
        return doctorOrderList;
    }

    public void setDoctorOrderList(List<DoctorOrder> doctorOrderList) {
        this.doctorOrderList = doctorOrderList;
    }

    public Order(Long orderID, List<DoctorOrder> doctorOrderList) {
        this.orderID = orderID;
        this.doctorOrderList = doctorOrderList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", doctorOrderList=" + doctorOrderList +
                '}';
    }
}
