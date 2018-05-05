import java.util.*;
import java.io.*;
class AddItems 
{
	private ArrayList<Item>items;
	private FileManager man;
	private String creator,listName,status;
	public AddItems(String creator,String listName)throws IOException
	{	
		System.out.println("I've entered AddItems");
		
		this.creator=creator;
		this.listName=listName;
		man=new FileManager(listName);
		man.setOwner(this.creator);
		man.setListname(this.listName);
		man.changeFilename();
		man.loadShoppingLists();
		items=new ArrayList<Item>();
		items=man.getShoppingLists();
		System.out.println("All items->"+items);
		
	}

	public void addNewItem(Item i)throws IOException
	{
		man.loadList_Owners();
		boolean duplicate=false;
		if(man.doesListExist()==true)
		{
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
							System.out.println("your item is duplicated");
							//items.add(it);
							duplicate=true;
						}
						else items.add(i);
					
					System.out.println("This All the items now-> "+items);
					if(duplicate==true)
						{
							if(man.saveItems(items,this.creator,indexOfItem,i))
								System.out.println("Item saved");
								else System.out.println("You have no Access to modify this list");
						}
					else
					{
						if(man.saveItems(i,this.creator))
								System.out.println("New Item saved");
								else System.out.println("No Access to modify");

					}

			
		}
	}
	
	public static void main(String[] args)throws IOException
	{
		CreateList list=new CreateList("ollie");
		if(list.createNewList())System.out.println("adding items");
		AddItems item=new AddItems("ollie",list.getName());
		item.addNewItem(new Item(args[1],Double.parseDouble(args[2]),Integer.parseInt(args[3])));
		/*CreateList list2=new CreateList(args[0],"jod","public");
		if(list2.createNewList())System.out.println("adding items");
		AddItems item2=new AddItems("jod",list2.getName());
		item2.addNewItem(new Item(args[1],Double.parseDouble(args[2]),Integer.parseInt(args[3])));
	*/

	}
}