import java.util.*;
import java.io.*;
class EditItem
{
	private ArrayList<Item>items;
	private FileManager man;
	private String creator,listName,status;
	public EditItem(String creator,String listName)throws IOException
	{	
		System.out.println("I've entered AddItems");
		
		this.creator=creator;
		this.listName=listName;
		this.status=status;
		man=new FileManager(listName);
		man.setOwner(this.creator);
		man.setListname(this.listName);
		man.changeFilename();
		
		System.out.println("All items->"+items);
		man.loadList_Owners();
		
	}

	public void changeItemPrice(Item i,double newPrice)throws IOException
	{
		
		
		if(man.doesListExist()==true)
		{
			man.loadShoppingLists();
		items=new ArrayList<Item>();
		items=man.getShoppingLists();
			System.out.println("List found");
			System.out.println("There are "+items.size()+" in there");
			System.out.println("This is your item -> "+i);
			System.out.println("This is All the items->"+items);
			int indexOfItem=0;
			ArrayList<String>temp=new ArrayList<String>();
			for (Item it : items)
			{
					temp.add(it.getItemName());
			}
			indexOfItem=temp.indexOf(i.getItemName());
			System.out.println("Item entered is at: "+indexOfItem);
						System.out.println(temp);
						if(temp.contains(i.getItemName()))
						{	
							i.setPrice(newPrice);
						}
						else items.add(i);
					
					System.out.println("This All the items now-> "+items);
					
						if(man.saveItems(i,this.creator))
								System.out.println("New Item saved");
								else System.out.println("No Access to modify");
			}
		}
		public static void main(String[] args) throws IOException
		{
			EditItem editItem=new EditItem("ollie",args[0]);
			editItem.changeItemPrice(new Item(args[1],Double.parseDouble(args[2]),Integer.parseInt(args[3])),Double.parseDouble(args[2]));
		}
}