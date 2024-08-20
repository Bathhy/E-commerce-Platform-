package model;

public class CartModel   {


    public CartModel( String productname,double price, String productimages, String sellername,int quantity, double total) {
        this.quantity = quantity;
        this.total = total;
        this.price = price;
        this.productname = productname;
        this.productimages = productimages;
        this.sellername = sellername;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }



    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductimages() {
        return productimages;
    }

    public void setProductimages(String productimages) {
        this.productimages = productimages;
    }


    public void setSellername(String sellername) {
        this.sellername = sellername;
    }


    private int quantity;
    private double total;
    private double price;
    private  String productname;
    private  String productimages;
    private  String sellername;

    public String getProductName() {
        return productname;
    }

    public double getPrice() {
        return price;
    }

    public String getProductImage() {
        return productimages;
    }

    public String getSellerName() {
        return sellername;
    }

    public int getQuantity() {
        return quantity;
    }


}
