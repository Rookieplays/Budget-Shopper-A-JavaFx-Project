import java.util.*;
import java.time.*;
import java.io.*;
public class Registration
{
	static ArrayList<ArrayList<String>>loginCredentials=new ArrayList<ArrayList<String>>();
	private String userName="",password="";
 	private double wallet,savings;
 	private LocalDate date;
 	private LocalTime time;
 	static Encryptor encript;
 	
	
	public Registration()throws IOException
	{
		loginCredentials.clear();
		loadUsers();
		
 		userName="username";
 		password="password";
 		wallet=0.00;
 		savings=0.00;
 		date=date.now();
 		time=time.now();

		encript=new Encryptor(this.password,this.userName);
		

	}
	public Registration(String userName,String password)throws IOException
	{
		loginCredentials.clear();
		loadUsers();
		this.userName=userName;
		this.password=password;
		this.wallet=0.00;
		this.savings=0.00;
		this.date=date.now();
		this.time=time.now();
		encript=new Encryptor(this.password,this.userName);
	}
	public Registration(String userName,String password,int wallet)throws IOException
	{
		loginCredentials.clear();
		loadUsers();
		this.userName=userName;
		this.password=password;
		this.wallet=wallet;
		this.savings=0.00;
		this.date=date.now();
		this.time=time.now();
		encript=new Encryptor(this.password,this.userName);
	}
	public Registration(String userName,String password,int wallet,int savings)throws IOException
	{
		loginCredentials.clear();
		loadUsers();
		this.userName=userName;
		this.password=password;
		this.wallet=wallet;
		this.savings=savings;
		this.date=date.now();
		this.time=time.now();
		encript=new Encryptor(this.password,this.userName);
	}
	public ArrayList<ArrayList<String>> listOfUsers()
	{
		return loginCredentials;
	}
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
 	public boolean validUsername()
 	
 	{
 		System.out.println("Username: "+userName);
 		String format="[(([A-Z]+)*([a-z]+)*([0-9]+))]";
 		if(userName.length()>=3 || !(userName.contains("->")))
 			return true;
 		else return false;
 	}
 	public boolean validPassword()
 	{

 		 System.out.println(password);
        password=password.trim();
        password=password.replaceAll(" ","");
       // userDetails.add(new ArrayList<String>());
        int T=0,F=0;
       
        if (password.length()>=6) 
        {
          System.out.println("Test 1 complete.. Password: "+password+" length: "+password.length()+" >= 6");
            if (password.matches(".*[A-Z]+.*"))
            {
            	System.out.println("Test 2 complete.. Password: "+password+" has uppercase letters.");

            	if(password.matches(".*[a-z]+.*"))
            	{
            		System.out.println("Test 3 complete.. Password: "+password+" has lowerCase letters.");

            		if(password.matches(".*[0-9].*"))
            	           		 { 
            	           		 			System.out.println("Test 4 complete.. Password: "+password+" has  numbers.");
            	                            System.out.println("Good Password");
            	                            return true;
            	             	 }else {System.out.println("Test 4 Failed");return false;}
                }else {System.out.println("Test 3 Failed");return false;}
             }else {System.out.println("Test 2 Failed ");return false;}

         }
         else 
         {
         		System.out.println("Test 1 Failed .. Password: "+password+" length: "+password.length()+" !>= 6");return false;
         }
     }
     public String printErrorMsg(char type)
     {
     	if(type=='U')
     		return "Oops Username must be more than three charcters";
     	else if (type=='P')
     		return "The password must reach the following criteria:\nMust be 8 digits Long. \n Must have at Least 1 or more uppercase characters.(A-Z)\n 1 or more lowerCase characters.(a-z)\n Base 10 digits(0-9)"; 
     	else
     		return "Error!!";
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
 	public boolean saveDetails()throws IOException
 	{
 		if (!(loginCredentials.get(0).contains(userName)&&loginCredentials.get(1).contains(password)))
		{	password=encript.encryptPassword();
 			String info=userName+"->"+password+"->"+date+" at "+time+"\n";
 			String info2=userName+":"+0.00+":"+date+" at "+time+"\n";
 			String info3=userName+":"+0.00+":"+date+" at "+time+"\n";

 			writeToFile("logBook.txt",info);
 			writeToFile("Wallet.txt",info2);
 			writeToFile("Savings.txt",info3);

 			return true;
 		}
 		else return false;

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
	public boolean existingAccount()
	{
		System.out.println(userName+"-"+password);
		if (loginCredentials.get(0).contains(userName))
			{
				System.out.println(userName+" Was Last seen on: "+loginCredentials.get(2).get((loginCredentials.get(0).indexOf(userName))));
				return true;
			}
		
		else 
			return false;
	}
	private void loadUsers()throws IOException
	{
		String temp[];
		Scanner filereader;
		loginCredentials.add(new ArrayList<String>());
		loginCredentials.add(new ArrayList<String>());
		loginCredentials.add(new ArrayList<String>());
		File file=new File("logBook.txt");
		File file1=new File("Wallet.txt");
		File file2=new File("Savings.txt");

		if (file.exists()&&file1.exists()&&file2.exists())
		 {
			filereader=new Scanner(file);
			while(filereader.hasNext())
			{
				temp=filereader.nextLine().split("->");
				for (int i=1;i<loginCredentials.size();i++ )
				 {
					loginCredentials.get(i).add(temp[i]);//System.out.println(loginCredentials);
				}
			}filereader.close();
		}
		else
		{
			writeToFile("logBook.txt","Account Name->Password->dateLoged\n");
			writeToFile(" Wallet.txt","Account Name:Balance:Date Last Modified\n");
			writeToFile(" Savings.txt","Account Name:Balance:Date Last Modified\n");

		}

	}
	public String toString()
 	{
 		return "Username: "+userName +" | Password: "+password+" | Wallet: "+wallet+" | Savings: "+savings+" | Date Last Logged in: "+date+" at "+time;
 	}
	
	private void writeToFile(String filename,String info)throws IOException
	{
		FileWriter writer=new FileWriter(filename,true);
		writer.write(info);
		writer.close();
	}

	public static void main(String[] args)throws IOException
	 {
			Registration r=new Registration("ollie224","Sonyerics12");
			System.out.println(r);
			System.out.println(r.listOfUsers());
		if(r.validUsername())
		{
				if(r.validPassword())
		
			{
				if(r.saveDetails())
				{
					System.out.println("singned up");
					//Encryptor encript=new Encryptor(password);
					System.out.println(encript.decryptPassword());
			
				}
				else System.out.println("user already exits");
			}else System.out.println(r.printErrorMsg('P'));
		}
		else
			System.out.println(r.printErrorMsg('U'));



	}
}