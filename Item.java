class Item
{
	private String itemName;
	private double price;
	private int amount;
	private String additionalInfo;
	public Item()
	{
		itemName="unknown";
		price=0.00;
		amount=0;
		additionalInfo="";
	}
	public Item(String itemName,double price,int amount)
	{
		this.itemName=itemName;
		this.price=price;
		this.amount=amount;
	}
	public Item(String itemName,double price,int amount,String additionalInfo)
	{
		this.itemName=itemName;
		this.price=price;
		this.amount=amount;
		this.additionalInfo=additionalInfo;
	}
	public String getItemName()
	{
		return itemName;
	}
	public void setItemName(String itemName)
	{
		this.itemName=itemName;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price=price;
	}
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amount)
	{
		this.amount=amount;
	}
	public static Item addNewItem(String lineFromText)
	{
		String[] temp = lineFromText.split(":");
		return new Item( temp[0], Double.parseDouble(temp[1]),Integer.parseInt(temp[2]) );	
	}
	public String toString()
	{
		return itemName+":"+price+":"+amount;
	}
}