/*
 * Caleb Wolin 
 * CST-239
 * Milestone 5
 */
package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;


public class StoreFront 
{
	// retrieve contents of store from JSON file
	private InventoryManager inventory = new InventoryManager();
	private ShoppingCart shoppingCart = new ShoppingCart();
	private static StoreFront store = new StoreFront();

	

	/**
	 * MAIN METHOD
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		
		// game loop
		int loop = 0;
		sortInventory();
		printInventory(store.inventory.getInventory(), store.shoppingCart.getShoppingCart());
		while(loop != 4)
		{
			System.out.println("(1) Add item to cart, (2) Remove Item From Cart, (3) Purchase Items in Cart, (4) Quit program");
			loop = s.nextInt();
			switch(loop)
			{
				case 1:		// add to cart
					System.out.println("Choose an item: ");
					int result1 = s.nextInt();
					addToCart(result1);
					break;
				case 2:		// remove from cart
					System.out.println("Choose an item to delete from shopping cart");
					int result2 = s.nextInt();
					removeFromCart(result2);
					break;
				case 3:		// Purchase items in cart
					printInventory(store.inventory.getInventory(), store.shoppingCart.getShoppingCart());
					System.out.println("Would you like to purchase these items?");
					System.out.println("#0 - YES");
					System.out.println("#1 - NO");
					int result3 = s.nextInt();
					purchaseItems(result3);
					break;
			}
			sortInventory();
			printInventory(store.inventory.getInventory(), store.shoppingCart.getShoppingCart());
			System.out.println();
		}
		s.close();
		
		saveInventoryToFile("test.txt", store.inventory.getInventory());
		
	}
	
	
	private static void sortInventory()
	{
		// sort by alphabetical order
		Collections.sort(store.inventory.getInventory(), (o1, o2) -> {
			return o1.getName().compareTo(o2.getName());
		});
		Collections.sort(store.shoppingCart.getShoppingCart(), (o1, o2) -> {
			return o1.getName().compareTo(o2.getName());
		});
		// sort by price
		Collections.sort(store.inventory.getInventory(), (o1, o2) -> {
			return Float.compare(o1.getPrice(), o2.getPrice());
		});
		Collections.sort(store.shoppingCart.getShoppingCart(), (o1, o2) -> {
			return Float.compare(o1.getPrice(), o2.getPrice());
		});
	}
	
	
	private static void saveInventoryToFile(String filename, ArrayList<Product> list)
	{
		// delete contents of file
		try
		{
			File file = new File(filename);
			FileWriter fw = new FileWriter(file, false);
			fw.close();
		}
		catch(IOException e)
		{
			e.getStackTrace();
		}
		
		// add inventory to file
		for(int i = 0; i < list.size(); i++)
		{
			saveToFile(filename, list.get(i), true);
		}
		
		
	}

	private static void addToCart(int result)
	{
		
		// if the shopping cart is empty, add item to first slot in shopping cart array
		if(store.shoppingCart.getShoppingCart().size() == 0)
		{ 
			store.shoppingCart.getShoppingCart().add((store.inventory.getInventory().get(result)));
			store.shoppingCart.getShoppingCart().get(store.shoppingCart.getShoppingCart().size() - 1).qty++;
		}
		// check if items match each other. if yes, qty++. if not, add new item
		else
		{
			int x = 0;
			boolean flag = false;
			while(x < store.shoppingCart.getShoppingCart().size())
			{
				// if the inventory item matches the shopping cart item, qty++
				if(store.inventory.getInventory().get(result) == store.shoppingCart.getShoppingCart().get(x))
				{
					store.shoppingCart.getShoppingCart().get(x).qty++;
					flag = true;
					break;
				}
				x++;
			}
			if(flag == false)
			{
				store.shoppingCart.getShoppingCart().add((store.inventory.getInventory().get(result)));
				store.shoppingCart.getShoppingCart().get(store.shoppingCart.getShoppingCart().size() - 1).qty++;
			}
		}
	}
	
	public static void removeFromCart(int result)
	{
		for(int x = 0; x < store.inventory.getInventory().size(); x++)
		{
			if(store.shoppingCart.getShoppingCart().get(result) == store.inventory.getInventory().get(x))
			{
				store.inventory.getInventory().get(x).qty = 0;	
			}
		}
		store.shoppingCart.getShoppingCart().remove(result);
	}
	
	public static void purchaseItems(int result)
	{
		if(result == 0) 
		{
			// purchase items
			store.shoppingCart.getShoppingCart().clear();
			System.out.println("You have purchased the items!");
		}
		else {}
	}
	
	/**
	 * Method to print inventory
	 * @param list
	 * @param shop
	 */
	public static void printInventory(ArrayList<Product> list, ArrayList<Product> shop)
	{

		System.out.println();
		System.out.println("INVENTORY:--------------------------------------------------------------+---------------------------------------------------------------------------");
		System.out.println("#      ITEM:          DESCRIPTION:                         PRICE:       |  SHOPPING CART:");
		
		int extraSpacesName = 0;
		int extraSpacesDesc = 0;
		int extraSpacesNameCart = 0;
		int extraSpacesDescCart = 0;
		int cartNum = 0;
		int shopSize = shop.size();
		
		for(int x = 0; x < list.size(); x++)
		{
			extraSpacesName = 10 - list.get(x).name.length();
			extraSpacesDesc = 30 - list.get(x).description.length();

			if(cartNum < shopSize)
			{
				extraSpacesNameCart = 10 - shop.get(cartNum).name.length();
				extraSpacesDescCart = 30 - shop.get(cartNum).description.length();
				// inventory
				System.out.printf("#%d     ", x);
				System.out.printf("%s%" + extraSpacesName + "s     ", list.get(x).name, ' ');
				System.out.printf("(%s)%" + extraSpacesDesc + "s     ", list.get(x).description, ' ');
				System.out.printf("$%.2f        |  ", list.get(x).price, list.get(x).qty);
				
				// shopping cart
				System.out.printf("#%d     ", cartNum);
				System.out.printf("%s%" + extraSpacesNameCart + "s     ", shop.get(cartNum).name, ' ');
				System.out.printf("(%s)%" + extraSpacesDescCart + "s     ", shop.get(cartNum).description, ' ');
				System.out.printf("$%.2f     %d\n", shop.get(cartNum).price, shop.get(cartNum).qty);
				cartNum++;
			}
			else
			{
				// inventory
				System.out.printf("#%d     ", x);
				System.out.printf("%s%" + extraSpacesName + "s     ", list.get(x).name, ' ');
				System.out.printf("(%s)%" + extraSpacesDesc + "s     ", list.get(x).description, ' ');
				System.out.printf("$%.2f        |     \n", list.get(x).price, list.get(x).qty);
			}
			
		}
		float total = 0;
		for(int x = 0; x < shop.size(); x++)
		{
			total += shop.get(x).price * (float)shop.get(x).qty;
		}
		
		System.out.printf("                                                                           TOTAL: $%.2f", total);
		System.out.println();
		System.out.println();
	}

	private static void saveToFile(String filename, Product product, boolean append) 
	{
		PrintWriter pw;
		try
		{
			// create a file File to write
			File file = new File(filename);
			FileWriter fw = new FileWriter(file, append);
			pw = new PrintWriter(fw);
			
			//write car as json
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(product);
			pw.println(json);
			
			//cleanup
			pw.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
//	saveToFile("test.txt", new Weapon("Sword", "A long piece of metal", 6.89f, 0), true);
//	saveToFile("test.txt", new Weapon("Shield", "A long protective barrier", 3.45f, 0), true);
//	saveToFile("test.txt", new Armor("Suit", "A full body protective gear", 1.54f, 0), true);
//	saveToFile("test.txt", new Health("Health", "Heals you to 100%", 2.34f, 0, 0), true);

}
