import java.sql.*;
public class SqlTests
{
	private static final String USERNAME="root";
	private static final String PASSWORD="01100001";
	private static final String CONN="jdbc:mysql//localhost:3306/Budget_Shopper";
	public SqlTests()throws Exception
	{
		Connection con=DriverManager.getConnection(CONN,USERNAME,PASSWORD);
		System.out.println("Sucess...");
		//Statement myStatement=con.createStatement();
		//Resultset myResu
		con.close();
	}
	public static void main(String[] args)throws Exception {
		SqlTests sqls=new SqlTests();
	}
}
