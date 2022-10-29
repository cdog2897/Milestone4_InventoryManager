package app;

import java.util.*;
import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;


public class InventoryManager 
{

	private ArrayList<Product> inventory = new ArrayList<>();
	

	/**
	 * @param inventory
	 * constructor inventory manager
	 */
	public InventoryManager() 
	{
		try
		{
			File file = new File("test.txt");
			Scanner s = new Scanner(file);
			
			while(s.hasNext())
			{
				// read a string of JSON and convert to a Car
				String json = s.nextLine();
				ObjectMapper objectMapper = new ObjectMapper();
				
				Product product = objectMapper.readValue(json, Product.class);
				inventory.add(product);
			}
			s.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the inventory
	 */
	public ArrayList<Product> getInventory() {
		return inventory;
	}

	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(ArrayList<Product> inventory) {
		this.inventory = inventory;
	}
}
