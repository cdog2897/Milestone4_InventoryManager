package app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Armor.class, name = "Armor"),
}
) 

public class Weapon extends Product
{
	
	public Weapon()
	{}

	/**
	 * Parameterized constructor
	 * @param name
	 * @param description
	 * @param price
	 * @param qty
	 */
	public Weapon(String name, String description, float price, int qty) {
		super(name, description, price, qty);
		// TODO Auto-generated constructor stub
	}
	
}
