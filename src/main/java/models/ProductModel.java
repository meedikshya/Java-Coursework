package models;

import java.io.File;

import javax.servlet.http.Part;

import utils.StringUtils;

public class ProductModel {
	private int id;
	private String name;
	private String description;
	private double price;
    private String imageUrlFromPart;
    
	public ProductModel() {
		
	}
	
	public ProductModel(int id, String name, String description,double price, Part imagePart) {
		this.id = id;
		this.name =name;
		this.price = price;
		this.description = description;
        this.imageUrlFromPart = getImageUrl(imagePart);
		
	}
	
	public ProductModel(int id, String name, String description, double price, String imagePart) {
		this.id = id;
		this.name =name;
		this.description = description;
		this.price = price;
        this.imageUrlFromPart = imagePart;
		
	}


	

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrlFromPart() {
        return imageUrlFromPart;
    }

    public void setImageUrlFromPart(String imageUrlFromPart) {
        this.imageUrlFromPart = imageUrlFromPart;
    }

    public void setImageUrlFromPart(Part part) {
        this.imageUrlFromPart = getImageUrl(part);
    }

    public void setImageUrlFromDB(String imageUrl) {
        this.imageUrlFromPart = imageUrl;
    }
	
	@Override
    public String toString() {
        return "ProductModel{" + "id='" + id + '\'' + "name='" + name + '\'' + ", description='" + description + '\''
                + ", price=" + price + ", imageUrlFromPart='" + imageUrlFromPart + '\'' + '}';
    }

private String getImageUrl(Part part) {
	String savePath = StringUtils.IMAGE_DIR_PRODUCT;
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
			break;
		}
	}
	 // If no filename was extracted from the Content-Disposition header, set a default filename.
	if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
		imageUrlFromPart = "download.jpg";
	}
	return imageUrlFromPart;
}
}


	
	
