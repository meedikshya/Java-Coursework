package models;

public class ProductModel {
	private int id;
	private String name;
	private String description;
	private double price;
	private String image;
	
	public ProductModel() {
		
	}
	
	public ProductModel(int id,String description, String name, double price, String image) {
		this.id = id;
		this.name =name;
		this.price = price;
		this.image = image;
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + "]";
	}
	

}
