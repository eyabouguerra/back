package backAgil.example.back.models;

import java.util.List;

public class OrderInput {
    private String fullName;
    private String fullAddress;
    private String contactNumber;
    private List<OrderProductQuantity> orderProductQuantityList;


    public OrderInput(String fullAddress, String fullName, String contactNumber, List<OrderProductQuantity> orderProductQuantityList) {
        this.fullAddress = fullAddress;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.orderProductQuantityList = orderProductQuantityList;

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<OrderProductQuantity> getOrderProductQuantityList() {
        return orderProductQuantityList;
    }

    public void setOrderProductQuantityList(List<OrderProductQuantity> orderProductQuantityList) {
        this.orderProductQuantityList = orderProductQuantityList;
    }


}
