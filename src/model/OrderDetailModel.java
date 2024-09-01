package model;

public class OrderDetailModel {
    private String productName;
    private String sellerName;
    private int orderQty;
    private Double individualPrice;
    private Double totalIndividualPrice;
    private Double totalAmount;
    private String paymentMethod;
    private String orderDate;
    private String customerName;

    public String getProductName() {
        return productName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public Double getIndividualPrice() {
        return individualPrice;
    }

    public Double getTotalIndividualPrice() {
        return totalIndividualPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderDetailModel(String customerName, String orderDate, String paymentMethod, Double totalAmount,
                            Double totalIndividualPrice, Double individualPrice, int orderQty, String sellerName,
                            String productName) {
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.totalIndividualPrice = totalIndividualPrice;
        this.individualPrice = individualPrice;
        this.orderQty = orderQty;
        this.sellerName = sellerName;
        this.productName = productName;
    }
}
