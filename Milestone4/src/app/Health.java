package app;

public class Health extends Product
{
	
	public int potentcy;
	
	public Health()
	{
		
	}

	/**
	 * Parameterized constructor
	 * @param name
	 * @param description
	 * @param price
	 * @param qty
	 */
	public Health(String name, String description, float price, int qty, int potentcy) {
		super(name, description, price, qty);
		this.potentcy = potentcy;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the potentcy
	 */
	public int getPotentcy() {
		return potentcy;
	}

	/**
	 * @param potentcy the potentcy to set
	 */
	public void setPotentcy(int potentcy) {
		this.potentcy = potentcy;
	}

	@Override
	public String toString() {
		return "Health [potentcy=" + potentcy + "]";
	}
	
	
}
