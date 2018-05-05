import java.util.*;
import java.io.*;
class Encryptor
{
	private String password,name;
	private char originalChars[];
 	private int oCharPos[];
 	private int indexOfname,indexOfname2;
 	static ArrayList<ArrayList<String>>decoder=new ArrayList<ArrayList<String>>();
 	static ArrayList<ArrayList<String>>decoder_Dic=new ArrayList<ArrayList<String>>();

	public Encryptor(String password,String name)throws IOException
	{
		decoder.clear();
		decoder_Dic.clear();
		loadDecoder();
		loadDecoder_Dic();
		this.password=password;
		this.name=name;
		oCharPos=new int[3];
	 		originalChars=new char[3];
	 		indexOfname= decoder.get(0).indexOf(name);
	 			indexOfname2= decoder_Dic.get(0).indexOf(name);

	}
	private static void loadDecoder()throws IOException
	{
		String temp[];
		File file=new File("Decoder.txt");
		Scanner fileReader;
		decoder.add(new ArrayList<String>());
		decoder.add(new ArrayList<String>());
		decoder.add(new ArrayList<String>());
		decoder.add(new ArrayList<String>());
		if(file.exists())
		{
			fileReader=new Scanner(file);
			while(fileReader.hasNext())
			{
				temp=fileReader.nextLine().split("->");
				for(int i=0;i<decoder.size();i++)
				{	decoder.get(i).add(temp[i]);System.out.println(decoder);}
			}fileReader.close();
		}
		else writeFile("Decoder.txt","name->0->0->0\n");
	}
	private static void loadDecoder_Dic()throws IOException
	{
		String temp[];
		File file=new File("Decoder_Dictionary.txt");
		Scanner fileReader;
		decoder_Dic.add(new ArrayList<String>());
		decoder_Dic.add(new ArrayList<String>());
		decoder_Dic.add(new ArrayList<String>());
		decoder_Dic.add(new ArrayList<String>());
		if(file.exists())
		{
			fileReader=new Scanner(file);
			while(fileReader.hasNext())
			{
				temp=fileReader.nextLine().split("->");
				for(int i=0;i<decoder_Dic.size();i++)
				{	decoder_Dic.get(i).add(temp[i]);}
			}fileReader.close();
		}
		else writeFile("Decoder_Dictionary.txt","name->0->0->0\n");
	}
	public String decryptPassword()
	 	{
	 		boolean isEncrypted;
	 		
	 		StringBuilder oPassword=new  StringBuilder(this.password);
	 		int i=0;
	 		

	 		if(decoder.get(0).contains(name))
		 	{oPassword.reverse();
		 		System.out.println("Decrpting password "+this.password+"....");
		 		for (int j=0;j<oCharPos.length;j++ ) 
		 		{
		 			oCharPos[j]=Integer.parseInt(decoder.get(j+1).get(indexOfname));
		 		}
		 		for (int j=0;j<originalChars.length;j++ ) 
		 		{
		 			System.out.println(decoder_Dic.get(j+1).get(indexOfname2).charAt(0));
		 			originalChars[j]=decoder_Dic.get(j+1).get(indexOfname2).charAt(0);
		 		}
		 		for (int j=0;j<originalChars.length;j++ ) 
		 		{
		 			
		 			System.out.println("->"+originalChars[i]);
		 		}
		 		if(this.password.contains("$"))
		 			isEncrypted=true;
		 		else isEncrypted=false;
		 		if(isEncrypted)
		 		{System.out.println(oPassword);
		 			System.out.println(oCharPos.length);
		 				while(i<oCharPos.length)
		 				{
		 					System.out.print(i+". ");
		 						System.out.println(oCharPos[i]+ "->"+originalChars[i]);
		 		 				oPassword.setCharAt(oCharPos[i],originalChars[i]);
		 		 				 		 				System.out.println(oPassword);
		 		 			i++;
		 		
		 		 		}
		 		 		
		 		 }
		 		 System.out.println("Result of Decription: "+oPassword);
	 			return  oPassword+"";
	 		}
	 		else return oPassword+"";
	 	}
	 	
	 	 //More advanced but needs some type of storage*/
	 	public String encryptPassword()throws IOException
	 	{
	 		if(!(decoder.get(0).contains(name)))
	 		{	
	 			System.out.println("Encrpting password "+this.password+"....");
	 			
		 		String encryptedPassword="";
		 		StringBuilder ep=new StringBuilder(this.password);
		 		
		 		//oCharPos=new int[3];
		 		ArrayList<Integer>bucket=new ArrayList<Integer>();
		 		for(int i=0;i<this.password.length();i++ )
		 			bucket.add(i);
		 		Collections.shuffle(bucket);
		 		
		 		for (int i=0;i<originalChars.length;i++ ) 
		 		{
		 			
		 			oCharPos[i]=bucket.get(i);
		 			char a=ep.charAt(oCharPos[i]);
		 			originalChars[i]=a;
		 			ep.replace(oCharPos[i],oCharPos[i]+1,"$");
		 			


		 		}
		 		decoder.get(0).add(name);
		 		decoder_Dic.get(0).add(name);
		 		for (int i=1;i<decoder.size();i++ ) 
		 		{
		 			decoder.get(i).add(oCharPos[i-1]+"");
		 			decoder_Dic.get(i).add(originalChars[i-1]+"");
		 		}
		 		ep.reverse();
		 			encryptedPassword=ep+"";
		 			System.out.println("Result of encryption: "+encryptedPassword);
		 		this.password=encryptedPassword;
		 		System.out.println("Decoding to File....");
	 			writeFile("Decoder.txt",name+"->"+oCharPos[0]+"->"+oCharPos[1]+"->"+oCharPos[2]+"\n");
		 		writeFile("Decoder_Dictionary.txt",name+"->"+originalChars[0]+"->"+originalChars[1]+"->"+originalChars[2]+"\n");

	 		return encryptedPassword;
	 		}
	 		else return null;
	 	}
	 	private static void writeFile(String filename,String info)throws IOException
	 	{
	 		FileWriter writer=new FileWriter(filename,true);
	 		writer.write(info);
	 		writer.close();
	 	}
	 	public static void main(String[] args)throws IOException {
	 		Scanner sc=new Scanner(System.in);
	 		System.out.println("name");
	 		String input=sc.nextLine();
	 		System.out.println("password");
	 		String p=sc.nextLine();
	 		Encryptor encode=new Encryptor(p,input);
	 		encode.encryptPassword();
	 		encode.decryptPassword();
	 	}
}