class Messages
{
	private String name;
	public Messages()
	{
		
	}
	public Messages(String name)
	{
		this.name=name;
	}
	public String getMessage(String type)
	{
		if(type.equalsIgnoreCase("eMBP"))
			return errorMessageWhenBadPassWord();
		else if(type.equalsIgnoreCase("eMBU"))
			return errorMessageWhenBadUsername();
		else if(type.equalsIgnoreCase("gM"))
			return greatingMessage();
		else if(type.equalsIgnoreCase("eFU"))
			return errorFindingUser();
		else if(type.equalsIgnoreCase("lWM"))
			return loginWelcomeMessage();
		else if(type.equalsIgnoreCase("eIM"))
			return emptyInputMessage();
		else if(type.equalsIgnoreCase("eMP"))
			return errorMatchingPassWord();
		else if(type.equalsIgnoreCase("pC"))
			return passwordCriteria();
		else if(type.equalsIgnoreCase("uC"))
			return userCriteria();
		else if(type.equalsIgnoreCase("eLFU"))
			return errorLookingForUser();
		else if(type.equalsIgnoreCase("lMs"))
			return loadingMessages();
		else if(type.equalsIgnoreCase("plMs"))
			return profileloadingMessages();
		else if(type.equalsIgnoreCase("plT"))
			return profileloadingTitle();
		else return "This is Budget shopper | "+name;
	}
	private String profileloadingTitle()
	{
		return "Budget Shopper | "+name;
	}
	private String loadingMessages()
	{
		String msgs[]={"Hold on just a Moment!..","Just a sec or few..","loading...","Please wait..."};
		int randStr=(int)(Math.random()*msgs.length);
		return msgs[randStr];

	}
	private String profileloadingMessages()
	{
		String msgs[]={"Hi! "+name+"\nJust a Moment..","Welcome back! "+name,"Just getting you set up "+name+"!" ,"We've Missed you! "+name,"Please wait..."};
		int randStr=(int)(Math.random()*msgs.length);
		return msgs[randStr];

	}
	private String errorMessageWhenBadPassWord()
	{
		
	
       return  "Your Password Was Weak. Hover over ! for more info";
		
	}
	private String passwordCriteria()
	{
		String msg= "Your password most be 6 letters or more.\nMust contain an Uppercase character and Lowercase letters.\nContain a letter";
		
       return msg;
		
	}
	private String userCriteria()
	{
		String msg= "Your Username most be at Least 3  characters.";
       return msg;
		
	}
	private String errorMatchingPassWord()
	{
		return "Your passwords don't match, Try again!";
	}
	private String errorMessageWhenBadUsername()
	{
		 return "Your Username Was Incorrect. Hover over ! for more info";
	}
	private String greatingMessage()
	{
		return "Welcome | "+name;
	}
	private String errorLookingForUser()
	{
		return "This name "+name+" is not familiar.";
	}
	private String errorFindingUser()
	{
		return name+" are you sure you haven't done this before?";
	}
	private String loginWelcomeMessage()
	{
		return "Welcome back! "+name;
	}
	private String emptyInputMessage()
	{
		return "Oops You Didn't Write anything that we could use!";
	}
	public static void main(String[] args) {
		Messages msg=new Messages();
		
		System.out.println(msg.getMessage("pC"));
	}

}