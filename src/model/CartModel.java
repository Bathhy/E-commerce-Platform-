package model;

public class CartModel   {
    public CartModel(int prodid, String productname,double price, String productimages, String sellername,int quantity,
                     double total, int cartid, double totalamount) {
        this.productid = prodid;
        this.quantity = quantity;
        this.total = total;
        this.price = price;
        this.productname = productname;
        this.productimages = productimages;
        this.sellername = sellername;
        this.cartid = cartid;
        this.totalcart_amount = totalamount;
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
    private int cartid;
    private int productid;
    private double totalcart_amount;

    public double getTotalcart_amount() {
        return totalcart_amount;
    }

    public int getProductid() {
        return productid;
    }

    public int getCartid() {
        return cartid;
    }



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
