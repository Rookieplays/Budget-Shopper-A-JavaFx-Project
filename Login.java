import java.util.*;
import java.time.*;
import java.io.*;
public class Login
{
	static ArrayList<ArrayList<String>>loginCredentials=new ArrayList<ArrayList<String>>();
	private String userName,password;
 	private double wallet,savings;
 	private LocalDate date;
 	private LocalTime time;
 	
	
	public Login()throws IOException
	{
		loginCredentials.clear();
		loadUsers();
		
 		userName="username";
 		password="password";
 		wallet=0.00;
 		savings=0.00;
 		date=date.now();
 		time=time.now();
 		//updateLog();

	}
	public Login(String userName,String password)throws IOException
	{
		loginCredentials.clear();
		loadUsers();
		this.userName=userName;
		this.password=password;
		this.wallet=0.00;
		this.savings=0.00;
		this.date=date.now();
		this.time=time.now();
		//updateLog();
	}
	public Login(String userName,String password,int wallet)throws IOException
	{
		loginCredentials.clear();
		loadUsers();
		this.userName=userName;
		this.password=password;
		this.wallet=wallet;
		this.savings=0.00;
		this.date=date.now();
		this.time=time.now();
		////updateLog();
	}
	public Login(String userName,String password,int wallet,int savings)throws IOException
	{
		loginCredentials.clear();
		loadUsers();
		this.userName=userName;
		this.password=password;
		this.wallet=wallet;
		this.savings=savings;
		this.date=date.now();
		this.time=time.now();
		////updateLog();
	}

	public ArrayList<ArrayList<String>> listOfUsers()
	{
		return loginCredentials;
	}
	public void reloadListOfUsers()
	{
		 loginCredentials.clear();
	}
	/*public  Double updateWallet(double newWallet)throws IOException
	{
		String userInput="";
		double currentWallet=0;
		int dialogbtn=0;
		if (userInput==null)
			JOptionPane.showMessageDialog(null,"Closing application...");
		else
		{
			if(existingAccount(userInput)==true)
				{
					currentWallet =getAmountInWallet(userInput);
					dialogbtn=JOptionPane.showConfirmDialog(null,"Hi "+userInput+"\nCurrent Wallet: "+currentWallet+"\nDo you want to update your  Wallet?","Wallet",JOptionPane.YES_NO_OPTION);
					if (dialogbtn==JOptionPane.YES_OPTION)
					{updateAccount(userInput);
					currentWallet =getAmountInWallet(userInput);
					return currentWallet;
					}
					else if(dialogbtn==JOptionPane.NO_OPTION)
					return currentWallet;
				else
					return currentWallet;
		
		
				}
				else
				{
					JOptionPane.showMessageDialog(null,"We cannot find this user in our database.\n press ok to create an Account.","Wait...",2);
					register();loginCredentials.clear();

					loadUsers();
					currentWallet=getAmountInWallet(Name);
					return currentWallet;
				}
		}return currentWallet;
	}*/
	public String getUsername()
 	{

 		return userName;	
 	}
 	public String getPassword()
 	{
 		return password;
 	}
 	public void setUsername(String userName)
 	{
 		this.userName=userName;

 	}
 	public void setPassword(String password)
 	{
 		this.password=password;
 	}
 	public void setWallet(double wallet)
 	{
 		this.wallet=wallet;
 	}
 	public void setSavings(double savings)
 	{
 		this.savings=savings;
 	}
 	/*public double getSaving()
 	{
 		int userNameIndex=loginCredentials.get(0).indexOf(userName);
		System.out.println(userName+":"+userNameIndex);
		savings=Double.parseDouble(loginCredentials.get(3).get(userNameIndex));
		return savings;
 	}*/
 	public LocalDate getDate()
 	{
 		return date.now();
 	}
 	public LocalTime getTime()
 	{
 		return time.now();
 	}
 	

	/*public Double getWallet()
	{
		
		int userNameIndex=loginCredentials.get(0).indexOf(userName);
		System.out.println(userName+":"+userNameIndex);
		wallet=Double.parseDouble(loginCredentials.get(2).get(userNameIndex));
		return wallet;
	}*/
	public boolean validInput(String pattern,String input)
	{
		if(input==null)
			return true;
		else if (input!=null&&input.equals(""))
			return false;
		else if (input.matches(pattern))
			return true;
		else return false;
	}
	public boolean existingAccount()throws IOException
	{
		int indexOfUser=loginCredentials.get(0).indexOf(userName);

		System.out.println(userName+"-"+password);
		System.out.println(indexOfUser);
	if(indexOfUser!=-1)
	{
		Encryptor decode=new Encryptor(loginCredentials.get(1).get(indexOfUser),loginCredentials.get(0).get(indexOfUser));
		String p=decode.decryptPassword();System.out.println("Decryption Complete: "+p);
		System.out.println(loginCredentials.get(1).get(indexOfUser)+"/"+p+"/"+password);
			if (loginCredentials.get(0).contains(userName)&&p.equals(password))
				{
					System.out.println(userName+" Logged on: "+date+" on "+time);
					updateLog();
					return true;
				}
			
			else 
				return false;
	}
	else return false;
	}
	private void loadUsers()throws IOException
	{
		String temp[];
		Scanner filereader;
		loginCredentials.add(new ArrayList<String>());
		loginCredentials.add(new ArrayList<String>());
		loginCredentials.add(new ArrayList<String>());
		File file=new File("logBook.txt");

		if (file.exists())
		 {
			filereader=new Scanner(file);
			while(filereader.hasNext())
			{
				temp=filereader.nextLine().split("->");
				for (int i=0;i<loginCredentials.size();i++ )
				 {
					loginCredentials.get(i).add(temp[i]);//System.out.println(loginCredentials);
				}
			}filereader.close();
		}
		

	}
	public String toString()
 	{
 		return "Username: "+userName +" | Password: "+password+" | Wallet: "+wallet+" | Savings: "+savings+" | Date Last Logged in: "+date+" at "+time;
 	}
	private void updateLog()throws IOException
	{
		String info="";
		int indexOfUser=loginCredentials.get(0).indexOf(userName);
		loginCredentials.get(2).set(indexOfUser,date+" at "+time);
		Order orderedby=new Order(loginCredentials);
		loginCredentials=orderedby.ascendingOrder("name");
		//System.out.println(loginCredentials);
		for (int i=0; i<loginCredentials.get(0).size();i++) 
		{		

				info+=loginCredentials.get(0).get(i)+"->"+loginCredentials.get(1).get(i)+"->"+loginCredentials.get(2).get(i)+"\n";

		}
		overWriteFile("logBook.txt",info);

	}
	private void writeToFile(String filename,String info)throws IOException
	{
		FileWriter writer=new FileWriter(filename,true);
		writer.write(info);
		writer.close();
	}
	private void overWriteFile(String filename,String info)throws IOException
	{
		FileWriter writer=new FileWriter(filename);
		writer.write(info);
		writer.close();
	}

	public static void main(String[] args)throws IOException
	 {
			Login l=new Login("ollie","Sonyerics12");
			System.out.println(l);
			System.out.println(l.getPassword());
			if(l.existingAccount())
			{
				System.out.println("Logged in");
				//l.//updateLog();
				//System.out.println(l.encryptPassword()+"\n"+l.decryptPassword());
			}
			else System.out.println("user not found");


	}
}
/*

*/