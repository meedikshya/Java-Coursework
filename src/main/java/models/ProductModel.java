package models;

import javax.servlet.http.Part;

import utils.StringUtils;

import java.io.File;



public class ProductModel {
 
    private String name;
    private String category;
    private double price;
    private int quantity;
    private String imageUrlFromPart;
//    private Part image; // Change image type from String to Part

    // Constructor
    public ProductModel(String name, String category, double price, int quantity, Part imagePart) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.imageUrlFromPart = getImageUrl(imagePart);
//        this.image = imagePath;
    }

    
    // Constructor
    public ProductModel(String name, String category, double price, int quantity, String imagePart) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.imageUrlFromPart = imagePart;
//        this.image = imagePath;
    }

	// Getters
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setImageUrlFromPart(Part part) {
		this.imageUrlFromPart = getImageUrl(part);
	}
	
	public void setImageUrlFromDB(String imageUrl) {
		this.imageUrlFromPart = imageUrl;
	}
	
	 @Override
	    public String toString() {
	        return "ProductModel{" +
	                "name='" + name + '\'' +
	                ", category='" + category + '\'' +
	                ", price=" + price +
	                ", quantity=" + quantity +
	                ", imageUrlFromPart='" + imageUrlFromPart + '\'' +
	                '}';
	    }
	
	private String getImageUrl(Part part) {
		String savePath = StringUtils.IMAGE_DIR_USER;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "download.jpg";
		}
		return imageUrlFromPart;
	}

}