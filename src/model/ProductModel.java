package model;

public class ProductModel   {
    private static ProductModel instance;
    public static ProductModel getInstance() {
        if (instance == null) {
            instance = new ProductModel("",0.0,"","",0);
        }
        return instance;
    }
    public ProductModel(String name, double price, String imagePath, String sellerId, int productid) {
        this.productid = productid;
        this.productname = name;
        this.price = price;
        this.productimages = imagePath;
        this.sellername = sellerId;
    }


    public  String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductName() {
        return productname;
    }

    public  double getPrice() {
        return price;
    }

    public String getProductImage() {
        return productimages;
    }

    public String getSellerName() {
        return sellername;
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

    public  void setProductimages(String productimages) {
        this.productimages = productimages;
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
    private int productid;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getName() {
        return productname;
    }
}