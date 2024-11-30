package ca.sheridan.hasankam.model;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer id;
    private String productCode;
    private String productBrand;
    private int quantityInHand;
    private double unitPrice;

    public Product() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public String getProductBrand() { return productBrand; }
    public void setProductBrand(String productBrand) { this.productBrand = productBrand; }
    public int getQuantityInHand() { return quantityInHand; }
    public void setQuantityInHand(int quantityInHand) { this.quantityInHand = quantityInHand; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}
