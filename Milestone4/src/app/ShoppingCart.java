package app;

import java.util.ArrayList;

public class ShoppingCart 
{
	private ArrayList<Product> shoppingCart = new ArrayList<>();
	
	/**
	 * @return the shoppingCart
	 */
	public ArrayList<Product> getShoppingCart() {
		return shoppingCart;
	}

	/**
	 * @param shoppingCart the shoppingCart to set
	 */
	public void setShoppingCart(ArrayList<Product> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	
}
