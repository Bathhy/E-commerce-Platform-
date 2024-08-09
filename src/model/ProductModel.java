package model;

public class ProductModel {
    public ProductModel(String name, double price, int quantity, String imagePath, String sellerId) {
        this.productname = name;
        this.price = price;
        this.quantity = quantity;
        this.productimages = imagePath;
        this.sellername = sellerId;
    }

    public  String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public  double getPrice() {
        return price;
    }

    public  void setPrice(double price) {
       this.price = price;
    }

    public  int getQuantity() {
        return quantity;
    }

    public  void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public  String getProductimages() {
        return productimages;
    }

    public  void setProductimages(String productimages) {
        this.productimages = productimages;
    }

    public  String getSellername() {
        return sellername;
    }
    public void increaseQuantity(){
        this.quantity++;
    }
    public void decreaseQuantity(){
        if(quantity > 0){
            this.quantity--;
        }
    }
    public  void setSellername(String sellername) {
        this.sellername = sellername;
    }

    private  String productname;
    private  double price;
    private  int quantity;
    private  String productimages;
    private  String sellername;
}
