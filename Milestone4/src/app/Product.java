package app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Weapon.class, name = "Weapon"),
    @JsonSubTypes.Type(value = Health.class, name = "Health")
}
)

public abstract class Product 
{
	public String name;
	public String description;
	public float price;
	public int qty;
	

	
	public Product(){}
	
	/**
	 * @param name
	 * @param description
	 * @param price
	 * @param qty
	 */
	public Product(String name, String description, float price, int qty) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.qty = qty;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}


	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}


	/**
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}


	/**
	 * @param qty the qty to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
