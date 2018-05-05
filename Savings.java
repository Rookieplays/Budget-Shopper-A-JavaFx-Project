import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.*;
import javafx.scene.control.ScrollPane.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.effect.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import java.io.*;
import javafx.scene.input.*;
import javax.swing.JOptionPane;
import javafx.animation.*;
import java.time.*;
import javafx.util.Duration;
import java.util.*;
import javafx.scene.media.AudioClip;
import javafx.collections.*;
import javafx.beans.value.*;
import java.text.*;
class Savings
{
	/*
		user enters a balance
	*/
	private String user;
	private LocalDate date;
	private LocalTime time;
	private int indexOfUser;
	private double balance;
	private ArrayList<ArrayList<String>> SavingsList=new ArrayList<ArrayList<String>>();
	public Savings()throws IOException
	{
		this.user="unknown";
		this.date=date.now();
		this.time=time.now();
		this.balance=0.00;
		loadSavings();
	}
	public Savings(String user)throws IOException
	{

		System.out.println("__>"+user);
		this.user=user;
		this.date=date.now();
		this.time=time.now();
		this.balance=0.00;
		loadSavings();
		indexOfUser=SavingsList.get(0).indexOf(user);
	}
	public Savings(String user,double balance)throws IOException
	{
		System.out.println("__>"+user);
		this.user=user;
		this.date=date.now();
		this.time=time.now();
		this.balance=balance;
		loadSavings();
		indexOfUser=SavingsList.get(0).indexOf(user);
	}
	public void setBalnce(String balance,String s)
	{
		this.balance=Double.parseDouble(balance);

	}
	public void setBalance(double balance)
	{
		this.balance=balance;
	}
	public void loadSavings()throws IOException
	{
		String[]temp;
		Scanner fileReader;
		for(int i=0;i<3;i++)
			SavingsList.add(new ArrayList<String>());
		File file=new File("Savings.txt");
		if(file.exists())
		{
			fileReader=new Scanner(file);
			while(fileReader.hasNext())
			{
				temp=fileReader.nextLine().split(":");
				for (int i=0; i<SavingsList.size();i++ ) 
				{
					SavingsList.get(i).add(temp[i]);	
				}
			}fileReader.close();
		}
		else
			writeToSavingsFile("Savings.txt","User:0.00:last updated");
	}
	public void writeToSavingsFile(String filename,String info)throws IOException
	{
		FileWriter writer=new FileWriter(filename,true);
		writer.write(info);
		writer.close();

	}
	public void addNewUserToSavings()throws IOException
	{
		if(!(SavingsList.get(0).contains(this.user)))
		{
			writeToSavingsFile("Savings.txt",this.user+":"+this.balance+":"+"Last Updated on "+date+" at "+time);
		}
	}
	public void changeBalance()throws IOException
	{
		int i=0;
		PrintWriter write=new PrintWriter("Savings.txt");
		if(SavingsList.get(0).contains(this.user))
		{
		SavingsList.get(1).set(indexOfUser,this.balance+"");
		while(i < SavingsList.get(0).size())
		{
			write.println(SavingsList.get(0).get(i)+":"+SavingsList.get(1).get(i)+":"+"Last Updated on "+date+" at "+time); //overriding the files previous information with updated information
			i++; 
		}write.close();
	
		}
	}public String getsBalance()throws IOException
	{

		double bal=0;
		NumberFormat nf=new DecimalFormat("##.##");

          String finalbal="";
          bal=getBalance();
          if(bal<1000&&bal>=0||(bal<-1000&&bal>-1000))
          {
          	finalbal=nf.format(bal);
          }
          else if(bal>=1000&&bal<1000000||(bal<=-1000&&bal>-1000000))
          {
            finalbal=nf.format(bal/1000)+"K";
            System.out.println(finalbal);
          }
          else if(bal>=1000000&&bal<1000000000||(bal<=-1000000&&bal>-1000000000))
          {
          	finalbal=nf.format(bal/1000000)+"M";
            System.out.println(finalbal);
          }
          else
            finalbal=nf.format(bal/1000000000)+"B";System.out.println(finalbal);
        return finalbal;
	}
	public double getBalance()throws IOException
	{
		SavingsList.clear();
		loadSavings();
		if(SavingsList.get(0).contains(this.user))
		{
			balance=Double.parseDouble(SavingsList.get(1).get(indexOfUser));
			return Double.parseDouble(SavingsList.get(1).get(indexOfUser));
		}
		else return balance;
	}
	public static void main(String[] args)throws IOException {
		Savings wal=new Savings(args[0],2.00);
		wal.addNewUserToSavings();
		wal.changeBalance();
		System.out.println(wal.getBalance());

	}
	public void SavingsDialog(Label curency,Label oldBal1)throws IOException
	{
		DropShadow ds= new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);

		Dialog dialog=new Dialog();
			dialog.setTitle(user+"'s Savings");
		dialog.setHeaderText("Welcome to Your Savings Account"+user+"\nHere you can set how much you want to Save");
		dialog.setGraphic(new ImageView(new Image("Assets/wallet.png")));
		//dialog.getStyleClass().add("wallet_Dialog");

		ButtonType loginButtonType=new ButtonType("\u2713",ButtonData.OK_DONE);
		//loginButtonType.getStyleClass().add("wallet_DialogBtn");
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType,ButtonType.CANCEL);
		//dialog.setEffecf(ds);
		GridPane grid=new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20,200,10,10));

		Label cW=new Label("Current Savings");
		cW.getStyleClass().add("cW");
		cW.setFont(Font.font("Lagdon", FontWeight.BOLD,24));
		cW.setTextFill(Color.rgb(102,253,253));
		
		Label cWamount=new Label(curency.getText()+" "+getsBalance());
		cWamount.setFont(Font.font("Lagdon", FontWeight.BOLD,24));
		cWamount.setTextFill(Color.rgb(102,253,253));
		cW.setEffect(ds);
		cWamount.getStyleClass().add("cW");
		
		VBox main=new VBox();
		main.setStyle("-fx-background-color:#00394d;");

		Label nW=new Label("New Savings:(in digits)");nW.setEffect(ds);
		nW.setFont(Font.font("Lagdon", FontWeight.BOLD,16));
		nW.setTextFill(Color.rgb(102,253,253));
		TextField newBalance=new TextField();
		newBalance.getStyleClass().add("newBal");
		newBalance.setStyle("-fx-background-color:transparent;-fx-min-width: 160px;-fx-min-height: 45px;  -fx-max-width: 270px; -fx-max-height: 45px;-fx-prompt-font-size:15px;-fx-border-width: 0 0 5 0;-fx-text-fill:white;-fx-border-color:blue;-fx-font: normal bold 20 Langdon;");
		newBalance.setEffect(ds);
		newBalance.setPromptText(curency.getText()+" Enter new Balance");
		main.getChildren().addAll(cW,nW,newBalance);
		grid.add(cW,0,0);
		grid.add(cWamount,1,0);
		grid.add(nW,0,1);
		grid.add(newBalance,1,1);
		grid.setStyle("-fx-background-color:green;");
		final String p1="(([0-9]+)|(([0-9]+)*[.])|([-]*([0-9]+))|(([-]*([0-9]+))*[.]))+";

		Node confirmButton=dialog.getDialogPane().lookupButton(loginButtonType);
		confirmButton.setDisable(true);
		newBalance.textProperty().addListener((observable, oldValue, newValue) -> {
    	confirmButton.setDisable(!(newValue.trim().matches(p1)));
    	newBalance.setOnAction(e->{
    		if(!(newValue.trim().matches(p1)))
    		{
    			newBalance.setStyle("-fx-background-color:transparent;-fx-min-width: 160px;-fx-min-height: 45px;  -fx-max-width: 270px;-fx-prompt-font-size:15px; -fx-max-height: 45px;-fx-border-width: 0 0 5 0;-fx-text-fill:white;-fx-border-color:red;-fx-font: normal bold 20 Langdon;");
	
    			newBalance.clear();
    		}
    		else
    			newBalance.setStyle("-fx-background-color:transparent;-fx-min-width: 160px;-fx-min-height: 45px;  -fx-max-width: 270px;-fx-prompt-font-size:15px; -fx-max-height: 45px;-fx-border-width: 0 0 5 0;-fx-text-fill:white;-fx-border-color:green;-fx-font: normal bold 20 Langdon;");
	
    	});
		});
		
		dialog.getDialogPane().setContent(grid);
		 //dialog.getButtonTypes().setAll(loginButtonType,ButtonType.CANCEL);

		
		  Optional<ButtonType> result = dialog.showAndWait();
              if (result.get() == loginButtonType)
                	{
                		
                		if(newBalance.getText().matches(p1))
                			{
                				this.balance=Double.parseDouble(newBalance.getText().trim());
                			     changeBalance();

					             
					             	oldBal1.setText(getsBalance());
                			     	//oldBal2.setText(getsBalance());
       
                			    }
                			 
                			
                		else{
                			newBalance.clear();
                			newBalance.setStyle("-fx-border-color:red;");
                			confirmButton.setDisable(true);
                		}
                	}
              else {
                  // ... user chose CANCEL or closed the dialog
              }
           		  DialogPane dialogPane=dialog.getDialogPane();
             dialogPane.getStylesheets().add(
   getClass().getResource("user.css").toExternalForm());
dialogPane.getStyleClass().add("wallet_Dialog");
            }

		
}

