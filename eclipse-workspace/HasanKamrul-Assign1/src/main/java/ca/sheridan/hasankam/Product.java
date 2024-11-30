package ca.sheridan.hasankam;

public class Product {
	// Instance variables for product details
	private String productCode;
	private String productBrand;
	private int quantityInHand;
	private double unitPrice;
	
	//no-arg Constructor
	public Product() {
		
	}
	
	//Setters  for Product Code
	public void setProductCode(String productCode) {
		this.productCode =productCode;		
	}
	
	// Setter for Product Brand
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	
	// Setter for Quantity in Hand
	public void setQuantityInHand(int quantityInHand) {
		this.quantityInHand = quantityInHand;
	}
	
	// Setter for Unit Price
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	//Getters for Product Code
	public String getProductCode() {
		return this.productCode;
	}
	
	// Getter for Product Brand
	public String getProductBrand() {
		return this.productBrand;
	}
	
	 // Getter for Quantity in Hand
	public int getQuantityInHand() {
		return this.quantityInHand;
	}
	
	// Getter for Unit Price
	public double getUnitPrice() {
		return this.unitPrice;
	}
	

}
