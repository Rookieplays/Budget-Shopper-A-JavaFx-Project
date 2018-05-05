 import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.*;
import javafx.scene.control.ScrollPane.*;
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
import javafx.scene.media.AudioClip;
import javafx.beans.value.*;
import java.util.*;
import javafx.collections.*;
class AddNewTab extends userMainPage
{
	private String listname,status;
	private CreateList listMan;
	private CreateList newList;
	private AddItems itemMan;
	private DropShadow ds;
	private String user;
	private FileManager man;
	private ArrayList<Item> itemsOnList;
	private userMainPage us;
	private Label currency;
	private StackPane stk,stack,st;
	private Label listMade;
	private VBox holder;
	private Wallet wal;
 	private Savings save;
 	private  Label currentWalletLbl;
 	private Glow glow;
 	private boolean viewer=true;
 	private Pane wrapperPane;
 	private ListView<String> listView;
 	private double fb;
 	 public static final ObservableList data = 
        FXCollections.observableArrayList();
       
 		public AddNewTab(String user)throws IOException
	{
		this.viewer=true;
		stack=new StackPane();
		st=new StackPane();
		this.user=user;
		this.status="public";
        ds= new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.web("#000000"));
		listname="";
		userMainPage us=new userMainPage();
		currency=us.getCurrency();
		listMade=new Label("");
	}
	public AddNewTab(String user,boolean viewer)throws IOException
	{
		this.viewer=viewer;
		stack=new StackPane();
		st=new StackPane();
		this.user=user;
		this.status="public";
        ds= new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.web("#000000"));
		listname="";
		
		listMade=new Label("");
	}
	//A Vbox containing the add textfield the public and private radio button and a confirm button.
	//A Vbox containing two hboxes and a hbox to act as a footer.
	//the first hbox should contain a vbox which has an hbox that contains the price editor(button and A text field),an item text field
	//and an amount modifier similar to the price modifier
	//the second should contain a vbox and a scrollpane which contains several vboxes;

	/*
		1. The two Hboxes are hidden until a valid list name is entered.
			when the user enteres a list that already exist a prompt shows up to tell them that they've already made 
			..this list before and gives the information about about that list and if the would like to see the contents of that list if they clicked no
			..they are presented with a label that the list entered exists.
			But if the list name is valid and a status is selected then the confirm button is enabled.
			when trhat button ius clicked a pop up asks if they are happy with the name of the list, if yes then the two hboxes transitions up otherwise repeat.
		2. The two hboxes slide up over the Creation section
			in vbox 1.hbox 1. Create two buttons - and + with a circular text filled in between (if you can set the prompt to the regional currency symbol) 
								when the left button is clicked over either the + or - a dropdown menu comes down with option to change the interval of change it to default which is 1;
					2.the textField item just simply takes in the item.
					3. 1. Create two buttons - and + with a circular text filled in between
								when the left button is clicked over either the + or - a dropdown menu comes down with option to change the interval of change it to default which is 1;
			in vbox 2. hbox 1.holds the name of the list that items are being added.

	*/
	public VBox mainVBox()throws IOException
	{
		 
		VBox main=new VBox();main.getStyleClass().add("mainVbox");
		
		main.getChildren().addAll(mainStack(),stack);
		
		main.setSpacing(20);
		return main;
	}
	public StackPane mainStack()throws IOException
	{
		
		stk=new StackPane();
		stk.getChildren().addAll(addListVBox());
		stk.setAlignment(addListVBox(),Pos.TOP_CENTER);
		
		return stk;
	}
	public VBox addListVBox()throws IOException
	{
		glow = new Glow(); 
      	glow.setLevel(0.9); 
      	 currentWalletLbl=new Label();
		 currentWalletLbl.getStyleClass().add("walletLbl");
          currentWalletLbl.setTextFill(Color.rgb(102,255,255));
          currentWalletLbl.setEffect(glow);
            currentWalletLbl.setTranslateX(-50);
		VBox main=new VBox();main.getStyleClass().add("addListVBox");
		main.setEffect(ds);
		HBox radioBtnsContainer= new HBox();
		TextField listName=new TextField();listName.getStyleClass().add("listName");
		Button X=new Button("X");
		X.getStyleClass().add("exit");
		X.setEffect(ds);
		listName.setPromptText("Enter the name of your List..");
		listName.setEffect(ds);

		
		final ToggleGroup statusGroup=new ToggleGroup();
		//statusGroup.getStyleClass().add("status");
		
		RadioButton privateButton=new RadioButton("private");privateButton.getStyleClass().add("radiobtns");
		privateButton.setToggleGroup(statusGroup);
		privateButton.setUserData("private");
		
		
		RadioButton publicButton=new RadioButton("public");publicButton.getStyleClass().add("radiobtns");
		publicButton.setToggleGroup(statusGroup);
		publicButton.setUserData("public");

		publicButton.setSelected(true);
		radioBtnsContainer.getChildren().addAll(privateButton,publicButton);
		radioBtnsContainer.setAlignment(Pos.CENTER);
		radioBtnsContainer.setSpacing(100);
		
		
		
		//listMade.setEffect(ds);
		listMan=new CreateList(user);

		Button confirmBtn=new Button("\u2713");
		confirmBtn.setEffect(ds);
		confirmBtn.getStyleClass().add("confirmBtn");
		confirmBtn.setDisable(true);
		//listName.textProperty().addListener((observable, oldValue, newValue) -> {
    	//confirmBtn.setDisable(listName.equals(""));});
		listName.setOnAction(e->{
			
					if(user.equals("Guest"))
					this.status="public";
					listname=listName.getText();System.out.println(user);
					statusGroup.selectedToggleProperty().addListener(
			    	new ChangeListener<Toggle>() 
			    	{
			      		public void changed(ObservableValue<? extends Toggle> ov,
			        	  Toggle old_toggle, Toggle new_toggle) {
			        	if (statusGroup.getSelectedToggle() != null) {
			        		status=statusGroup.getSelectedToggle().getUserData().toString();
			          	System.out.println(statusGroup.getSelectedToggle().getUserData().toString());
			        }
      	
      	} 		
      });
				try{
					newList=new CreateList(listname,user,status);
					if(newList.createNewList())
					{
						listName.setStyle("-fx-border-color:green;");
						listMade.setText("Your List | "+listname+" Was Succesfully Created.");
						listMade.setTextFill(Color.rgb(102,255,204));
						confirmBtn.setDisable(false);
					}
					else
					{
						listName.setStyle("-fx-border-color:red;");
						List<String> choices = new ArrayList<>();
						choices.add("Use List");
						choices.add("Edit List");
						choices.add("Delete List");

						ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
						dialog.setTitle("wait a sec..");
						dialog.setHeaderText("Check this out!");
						dialog.setContentText("Sorry "+user+", You've Made this List Before.\n"+newList.getListDetails());

						Optional<String> result = dialog.showAndWait();
						result.ifPresent(letter -> {
							if(letter.equals(choices.get(0)))
							{	listName.setStyle("-fx-border-color:green;");confirmBtn.setDisable(false);listMade.setText("Your List | "+listname+" Was Succesfully Created.");
						listMade.setTextFill(Color.rgb(102,255,204));}
							else if(letter.equals(choices.get(1)))
								{
									listMade.setText("Your List | "+listname+" Was NOT Created.");
									listMade.setTextFill(Color.rgb(255,102,0));editListPopUp();}
							else if(letter.equals(choices.get(2)))
								{
									listMade.setText("Your List | "+listname+" Was NOT Created.");
									listMade.setTextFill(Color.rgb(255,102,0));deleteListPopUp();}
						});
					}
				}

			catch(IOException ex)
			{
				System.out.println(ex);
			}
			
				});
				X.setOnAction(e->{
					stack.getChildren().clear();
				});
		confirmBtn.setOnAction(e->{
			try{
				confirmBtn.setDisable(true);
				us=new userMainPage();
		currency=us.getCurrency();
				HBox hb=itemsVBox();
				HBox hb2=itemsVBoxNoViewer();
				itemMan=new AddItems(user,newList.getName());
				this.tempbal=currentWalletLbl;
				if(viewer==true)
				stack.getChildren().addAll(hb,currentWalletLbl,X);
				else
					stack.getChildren().addAll(hb2,X);
				//X.setTranslateX(-300);
				//X.setTranslateY(-300);
				stack.setAlignment(hb,Pos.CENTER);
				stack.setAlignment(X,Pos.TOP_LEFT);
					fb=this.wal.getBalance();
				//stack.setAlignment(currentWalletLbl,Pos.BOTTOM_CENTER);
				currentWalletLbl.setTranslateY(355);
				//hb.setTranslateY(100);

				}
				catch(IOException eee){}
		});
		//itemMan=new AddItems(user,listMan.getName());
		 wal=new Wallet(this.user);
        save=new Savings(this.user);
		main.getChildren().addAll(listName,radioBtnsContainer,confirmBtn,listMade);
		main.setAlignment(Pos.CENTER);
		main.setSpacing(20);

		return main;
	}
	//stackpan!!!!
	public void editListPopUp()
	{

	}
	public void deleteListPopUp()
	{

	}
	public HBox itemsVBox()throws IOException
	{	wrapperPane=new Pane();
		HBox main=new HBox();

		main.getChildren().addAll(addItemsVBox(),itemViewer());
		main.setSpacing(20);
		main.setAlignment(Pos.CENTER);
		return main;
	}
	public HBox itemsVBoxNoViewer()throws IOException
	{
		HBox main=new HBox();
		main.getChildren().addAll(addItemsVBox());
		main.setSpacing(20);
		main.setAlignment(Pos.CENTER);
		return main;
	}
	public VBox addItemsVBox()throws IOException
	{

		
		//System.out.println("Here's itemsOnList"+itemsOnList);
		VBox main=new VBox();
		HBox priceModifier=new HBox();
		HBox itemModifier=new HBox();
		HBox amountModifier=new HBox();
		Label added=new Label("");
		added.getStyleClass().add("added");


		Button confirmBtn=new Button("\u2713");
		confirmBtn.setEffect(ds);
		confirmBtn.getStyleClass().add("confirmBtn");
		confirmBtn.setDisable(true);

		main.getChildren().addAll(priceModifier,itemModifier,amountModifier,confirmBtn,added);
		main.setSpacing(40);
		main.setAlignment(Pos.CENTER);
		main.getStyleClass().add("addItemsVBox");

		Button plusPrice=new Button("+");
		Button minusPrice=new Button("-");
		TextField items=new TextField();
		items.setPromptText("Enter Item");
		items.getStyleClass().add("items");

		TextField priceView=new TextField();

		priceView.setPromptText(currency.getText());
		priceView.setText("0.00");
		priceView.getStyleClass().add("priceview");
		priceView.setEffect(ds);
		minusPrice.getStyleClass().add("minus");
		plusPrice.getStyleClass().add("plus");
		plusPrice.setEffect(ds);
		minusPrice.setEffect(ds);

		priceModifier.getChildren().addAll(minusPrice,priceView,plusPrice);
		itemModifier.getChildren().add(items);
		priceModifier.setSpacing(20);
		priceModifier.setAlignment(Pos.CENTER);
		itemModifier.setAlignment(Pos.CENTER);
		Button plusItem=new Button("+");
		Button minusItem=new Button("-");
		TextField itemView=new TextField();

		itemView.setPromptText("#");
		itemView.setText("0");
		itemView.getStyleClass().add("itemview");
		itemView.setEffect(ds);
		minusItem.getStyleClass().add("minus");
		plusItem.getStyleClass().add("plus");
		plusItem.setEffect(ds);
		minusItem.setEffect(ds);


		amountModifier.getChildren().addAll(minusItem,itemView,plusItem);
		amountModifier.setSpacing(20);
		amountModifier.setAlignment(Pos.CENTER);
		final String p1="(([0-9]+)|(([0-9]+)*[.])|([-]*([0-9]+))|(([-]*([0-9]+))*[.]))+";

		//set Events
		// when plus is clicked
		plusPrice.setOnAction(e->
		{
			if(priceView.getText().matches(p1))
			{
				
				double interval=10;
				priceView.setStyle("-fx-border-color:green;");
				double price=Double.parseDouble(priceView.getText());
				if(price<0.00)
					minusPrice.setDisable(true);
					else 
						minusPrice.setDisable(false);
						price+=interval;
						priceView.setText(price+"");
					
				
			}
			else 
			{
				priceView.setStyle("-fx-border-color:red;");
				priceView.clear();
				priceView.setText("0.00");

			}
		});

		//when minus is clicked
		minusPrice.setOnAction(e->
		{
			if(priceView.getText().matches(p1))
			{
				int interval=10;
				priceView.setStyle("-fx-border-color:green;");
				double price=Double.parseDouble(priceView.getText());
				
					//
					price-=interval;
					priceView.setText(price+"");
				
				

			}
			else 
			{
				priceView.setStyle("-fx-border-color:red;");
				priceView.clear();
				priceView.setText("0.00");

			}
		});

		// when plus is clicked
		minusItem.setDisable(true);minusPrice.setDisable(true);
		plusItem.setOnAction(e->
		{
			if(itemView.getText().matches(p1))
			{
				int interval=1;
				itemView.setStyle("-fx-border-color:green;");
				int item=Integer.parseInt(itemView.getText());
				if(item<0)
					minusItem.setDisable(true);
				else
					minusItem.setDisable(false);
					item+=interval;
					itemView.setText(item+"");
				
				
					

			}
			else 
			{
				itemView.setStyle("-fx-border-color:red;");
				itemView.clear();
				itemView.setText("0");

			}
		});
		itemView.textProperty().addListener((observable, oldValue, newValue) -> {
    	confirmBtn.setDisable(itemView.equals(""));});
		//when minus is clicked
		minusItem.setOnAction(e->
		{
			if(itemView.getText().matches(p1))
			{
				int interval=1;
				itemView.setStyle("-fx-border-color:green;");
				int item=Integer.parseInt(itemView.getText());
				
				
				
					//minusItem.setDisable(false);
				item-=interval;
				itemView.setText(item+"");
				
					
			}
			else 
			{
				itemView.setStyle("-fx-border-color:red;");
				itemView.clear();
				itemView.setText("0");

			}
		});
		items.setOnAction(e->{
			if(!(items.getText().equals(""))||items.getText()!=null)
			{
				items.setStyle("-fx-border-color:green;");
				confirmBtn.setDisable(false);
			}
		});
		//when confirm is clicked
		//VBox library=itemViewer();
		confirmBtn.setDisable(items.getText().equals("")||items.getText()!=null);
		confirmBtn.setOnAction(e->
		{
		 	try
		 	{
		 		if(!(items.getText().equals(""))||items.getText()!=null)
				{
				
		 		added.setText("\u2713 "+items.getText()+" was added to "+listname);
		 		added.setTextFill(Color.GREEN);
		 		
		 		updateBal();

		 		
		 		wrapperPane.getChildren().clear();
		 		//wrapperPane.getChildren().add(library);
		 		if(this.wal.getBalance()>=0)
		 		{itemMan.addNewItem(new Item(items.getText(),Double.parseDouble(priceView.getText()),Integer.parseInt(itemView.getText())));listView.getItems().clear();
		 		initListView(listView);}
		 		else{
		 			added.setText("X Oops Your Wallet is empty!!\n"+items.getText()+" was not to "+listname);
		 			added.setTextFill(Color.RED);
		 		}
				}
				else {
					added.setText("X Ooops I dont like your input.\n"+items.getText()+" was not to "+listname);
		 		
				}
			}
			catch(IOException ee){}	
		});
		main.setPadding(new Insets(20, 50, 50, 50));
		return main;
	}

	//ItemViwer

	public VBox itemViewer()throws IOException
	{
		 final Label status       = new Label();
    	 final Label changeReport = new Label();
    	 final Label header=new Label(listname);
    	 header.getStyleClass().add("listLabel");
		VBox main=new VBox(10);
		 listView = new ListView<>();
		 initListView(listView);
		 listView.setEffect(ds);

		 System.out.println("waiting for event");
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
      @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        changeReport.setText("Selection changed from '" + oldValue + "' to '" + newValue + "'");
      }
    });
		 final Button removeButton = new Button("Remove Selected Item");
		 final Button refreshButton=new Button("see list");
		 refreshButton.setOnAction(e->{
		 		refreshButton.setText("refresh");
		 		listView.getItems().clear();
		 		 initListView(listView);
		 	});
    removeButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        final int selectedIdx = listView.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) 
        {
          String itemToRemove = listView.getSelectionModel().getSelectedItem();
          System.out.println("I want to remove: "+itemToRemove);
 		  final int newSelectedIdx =
          (selectedIdx == listView.getItems().size() - 1)
          ? selectedIdx - 1
          : selectedIdx;
 			
          listView.getItems().remove(selectedIdx);
          System.out.println("item is at pos: "+selectedIdx);
         try{ man.removeItem(selectedIdx);}catch(IOException exe){}
          status.setText("Removed " + itemToRemove);
          status.setTextFill(Color.RED);
          listView.getSelectionModel().select(newSelectedIdx);
        }
      }
    });
    ScrollPane sp=new ScrollPane();
    sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
          sp.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
          sp.getStyleClass().add("scroll");
          sp.setContent(listView);
          sp.setStyle("-fx-background-color:transparent;");
    final HBox controls = new HBox(10);
    controls.setAlignment(Pos.CENTER);
    controls.getChildren().addAll(refreshButton,removeButton);
  
		main.getChildren().setAll(header,sp,controls,status);
		main.setSpacing(15);
		main.setAlignment(Pos.CENTER);
		main.getStyleClass().add("addItemsVBox");
		main.setPadding(new Insets(20, 50, 50, 50));


		System.out.println("Leaving viwer method...");
		return main;
	}
	  private void initListView(ListView<String> lv) 
	  {
	  		
	  		try{
	  			ArrayList<Item>newLists;
	  			newLists=updatedList();
	  			itemsOnList=newLists;
	    	for (Item item :newLists ) 
	    		{
	    			System.out.println("init"+item);
	    			data.add(item.getItemName()+" - "+currency.getText()+""+item.getPrice()+" #"+item.getAmount());
	    		}lv.setItems(data);
  	  	}catch(IOException ex){}
  	  }
	public ArrayList<Item> updatedList()throws IOException
	{
		man=new FileManager(this.listname+"("+this.user+")");
				man.loadShoppingLists();
				itemsOnList=man.getShoppingLists();
			
		System.out.println(itemsOnList.size());
		
		return itemsOnList;
		
	}
	public void updateBal()throws IOException
	{
		itemsOnList.clear();
		man.loadShoppingLists();
		itemsOnList=man.getShoppingLists();
		System.out.println("Bal=" +this.wal.getBalance()+",Save Bal="+this.save.getBalance());
		double amountSaved=this.wal.getBalance()-this.save.getBalance();
		System.out.println("amountsaved="+amountSaved);
		double total=0;System.out.println("total befor loop:"+total);
		ArrayList<String>temp=new ArrayList<String>();
		for(int i=0;i<itemsOnList.size();i++)
		{
			//if(!(temp.contains(itemsOnList.get(i).getItemName())))
			total+=((itemsOnList.get(i).getPrice())*(itemsOnList.get(i).getAmount()));
			//temp.add(itemsOnList.get(i).getItemName());
		}

		System.out.println("total="+total);
		System.out.println(this.wal.getBalance());
		double change=fb-total;
		System.out.println("Change="+change);
		//Wallet wallet=new Wallet(user,change);
		if(change>=0)
		{this.wal.setBalance(change);
				currentWalletLbl.setText(change+"");}
		else
			this.wal.setBalance(0);
		this.wal.changeBalance();
		

		String newWal=wal.getsBalance();
		System.out.println("newWal="+newWal);
		System.out.println(currentWalletLbl.getText());
		//currentWalletLbl.setText(change);

		 if(wal.getBalance()<this.save.getBalance())
		{
			System.out.println("wal<save");
			 currentWalletLbl.setEffect(glow);
			currentWalletLbl.setTextFill(Color.web("#cc0000"));
		}
		 else if(wal.getBalance()>=(this.save.getBalance())&&wal.getBalance()<(this.save.getBalance()*0.2)+this.save.getBalance())
		{
			System.out.println("wal>=save");
			 currentWalletLbl.setEffect(glow);
			currentWalletLbl.setTextFill(Color.web("#ff0000"));
		}
		else if(wal.getBalance()>=(this.save.getBalance()*0.2)&&wal.getBalance()<(this.save.getBalance()*0.5)+this.save.getBalance())
		{
			System.out.println("wal>=save"+30);
			 currentWalletLbl.setEffect(glow);
			currentWalletLbl.setTextFill(Color.web("#ff9933"));
		}
		else if(wal.getBalance()>=(this.save.getBalance()*0.5)&&wal.getBalance()<(this.save.getBalance()*0.8)+this.save.getBalance())
		{
			System.out.println("wal>=save"+0);
			 currentWalletLbl.setEffect(glow);
			currentWalletLbl.setTextFill(Color.web("#ffff66"));
		}
		else if(wal.getBalance()>=(this.save.getBalance()*0.8)&&wal.getBalance()<(this.save.getBalance()*0.9)+this.save.getBalance())
		{
			System.out.println("wal>=save"+30);
			 currentWalletLbl.setEffect(glow);
			currentWalletLbl.setTextFill(Color.web("#88ff4d"));
		}
		else if(wal.getBalance()>=(this.save.getBalance()*0.90)&&wal.getBalance()<(this.save.getBalance()*1))
		{
			System.out.println("wal>=save"+40);
			 currentWalletLbl.setEffect(glow);
			currentWalletLbl.setTextFill(Color.web("#88ff4d"));
		}
		else 
		{
			 currentWalletLbl.setEffect(glow);
			currentWalletLbl.setTextFill(Color.web("#66ff66"));
		}
	}
}