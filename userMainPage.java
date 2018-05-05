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

public class userMainPage extends Main
{
   
  private DropShadow ds;
  private Scene prev,scene;
  private Messages msg;
  private KeyCode key;
  private String username="";
  private boolean logIn;
  private AddNewTab listMan;
  private Search searchEngine;
  private Stage window;
  private String status;
  private Wallet wal;
  private Savings save;
  private  Main mainMenu;
  protected Label c,tempbal;
  private   VBox hbox4 ;

  

	
    public userMainPage()throws IOException
  {
    c=new Label("");
      ds= new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.web("#000000"));
         msg=new Messages();
       
    
     
   // this.userMainScene=mainUser();
  }
  @Override
    public void start(Stage primaryStage)throws Exception 
    {
     
      
      listMan=new AddNewTab(username);System.out.println("listMan sets the name: "+username);
        searchEngine=new Search();
        window=primaryStage;
        
        scene=mainUser();
        // loadScene=LoadingScene();
          
     
      //  userMainScene=userScene.mainUser();
       //window.setScene(logoScene());
       window.setScene(scene);
        window.setResizable(true);
       window.show();

    }
   public userMainPage(String username,boolean logIn)throws IOException
  {
      ds= new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.web("#000000"));
         msg=new Messages();
        this.username=username;
        this.logIn=logIn;
       
   // this.userMainScene=mainUser();
  }
  public Label getCurrency()
  {
    return c;
  }

    public Scene mainUser()throws IOException
    {
      mainMenu=new Main();
       searchEngine=new Search();
      this.logIn=true;
      Glow glow = new Glow(); 
      glow.setLevel(0.9); 
      System.out.println("->>>"+this.username);
             wal=new Wallet(this.username);
        save=new Savings(this.username);
    	 Group root = new Group();
          //Group root2=new Group();
       Tooltip tp[]=new Tooltip[5];
       for(int i=0;i<tp.length;i++)
        {
          tp[i]=new Tooltip();
          tp[i].getStyleClass().add("tp");
        }
        Label cLabel=new Label();
         cLabel.getStyleClass().add("walletLbl");
          cLabel.setTextFill(Color.rgb(153,153,153));

            Label cLabel1=new Label();
         cLabel1.getStyleClass().add("walletLbl");
          cLabel1.setTextFill(Color.rgb(102,255,102));

            Label cLabel2=new Label();
         cLabel2.getStyleClass().add("walletLbl");
          cLabel2.setTextFill(Color.rgb(102,255,102));

         // cLabel.setEffect(glow);
        final String []currencies=new String[]{"\u20AC","\u00A3","\u00A5","\u20A7","\u0024","\u20B9"};
         final ChoiceBox cb=new ChoiceBox(FXCollections.observableArrayList("Euro","Pound","YEN","Niara","Dolar","Indain Rupies"));
         cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
         {
          public void changed (ObservableValue ov,Number value,Number new_value)
          {
            cLabel.setText(currencies[new_value.intValue()]);
             cLabel1.setText(currencies[new_value.intValue()]);
              cLabel2.setText(currencies[new_value.intValue()]);
              c=cLabel;
          }
          });

        cb.setTooltip(new Tooltip("Select the Currency"));
        cb.setValue("Euro");
        cb.getStyleClass().add("cb");
      
          Scene mainScene = new Scene(root, 700, 630, Color.WHITE);
          mainScene.getStylesheets().add("user.css");
          mainScene.getStylesheets().add("Load.css");
          mainScene.getStylesheets().add("addTab.css");
           mainScene.getStylesheets().add("search_engine.css");
          // System.out.println(wal.getBalance());
          Label walletLbl =new Label(wal.getsBalance());
          Label savingsLbl =new Label(save.getsBalance());
         
          savingsLbl.getStyleClass().add("savingsLbl");
          savingsLbl.setTextFill(Color.rgb(102,255,102));
          savingsLbl.setEffect(glow);

          savingsLbl.setTranslateX(85);
        
          cLabel1.setTranslateX(-110);
          cLabel2.setTranslateX(20);
          walletLbl.getStyleClass().add("walletLbl");
          walletLbl.setTextFill(Color.rgb(153,153,153));
          
        
         
         Button btn=new Button("Wallet");
         btn.getStyleClass().add("wallet");
         btn.setEffect(ds);
         tp[0].setText("This is "+this.username+"'s Wallet\nClick to change Balance");
         btn.setTooltip(tp[0]);
          btn.setOnAction(e->{try{wal.walletDialog(cLabel,walletLbl,tempbal);}catch(IOException iex){}});
         Button btn2=new Button("Savings");
         btn2.getStyleClass().add("savings");
         btn2.setEffect(ds);
          tp[1].setText("Check your Savings");
         btn2.setTooltip(tp[1]);
          btn2.setOnAction(e->{try{if(save.getBalance()<=wal.getBalance()||save.getBalance()>=0)save.SavingsDialog(cLabel,savingsLbl);}catch(IOException iex){}});
          TabPane tabPane = new TabPane();

          

             Button btn3=mainMenu.smallLogoIcon();
               btn3.setScaleX(0.5);
              btn3.setScaleY(0.5);

             Button settings=new Button("\u2699");
               settings.getStyleClass().add("settings");
               tp[4].setText("Settings this way");
         settings.setTooltip(tp[4]);
            
              HBox settingsLogo=new HBox();
              settingsLogo.getChildren().add(settings);
              listMan=new AddNewTab(this.username);System.out.println("listMan sets the name: "+this.username);
 

              Tab tab1 = new Tab();
              tab1.setText("Updates");
              HBox hbox1 = new HBox();
              hbox1.getChildren().add(new Label("Loading Recents..."));
              hbox1.setAlignment(Pos.CENTER);
              tab1.setContent(hbox1);
              tab1.getStyleClass().add("tab1");
              tab1.setId("news");
              tabPane.getTabs().add(tab1);
              
              ImageView rpLogo=new ImageView(new Image("Assets/newUser.png"));
              rpLogo.setScaleX(0.25);
              rpLogo.setScaleY(0.25);
              Button loginBtn=new Button(this.username);
              loginBtn.getStyleClass().add("login");
              tp[3].setText("Click to Logout");
              loginBtn.setTooltip(tp[3]);
              HBox logoStuff=new HBox();
              logoStuff.getChildren().addAll(btn3,loginBtn,settingsLogo);
              logoStuff.setSpacing(15);
              logoStuff.setAlignment(Pos.CENTER);
              logoStuff.setTranslateX(30);
              Tab tab3 = new Tab();
              tab3.setText("ADD New List");
              HBox hbox3 = new HBox();
              hbox3.getChildren().add(new Label("Gathering info..."));
              hbox3.setAlignment(Pos.CENTER);
              tab3.setContent(listMan.mainVBox());
              tab3.getStyleClass().add("tab2");
              tab3.setId("add");
              tabPane.getTabs().add(tab3);


              Tab tab4 = new Tab();
              tab4.setText("Search");
            hbox4 =new VBox();
              hbox4.getChildren().add(new Label("Loading Archive..."));
              hbox4.setAlignment(Pos.CENTER);
              hbox4.getStyleClass().add("Sbackground");
              tab4.setContent(hbox4);
              tab4.getStyleClass().add("tab3");
              tab4.setId("search");
              tabPane.getTabs().add(tab4);


             

            Tab tab2 = new Tab();
            tab2.setText("Favourites");
            HBox hbox2 = new HBox();
            hbox2.getChildren().add(new Label("No Favourites"));
            hbox2.setAlignment(Pos.CENTER);
            tab2.setContent(hbox2);
            tab2.setId("fav");
            tab2.getStyleClass().add("tab4");
            tabPane.getTabs().add(tab2);

             Tab tab5 = new Tab();
             //Text bin=new Text()
              tab5.setText("Recycle \uD83D\uDDD1");
              HBox hbox5 = new HBox();
              hbox5.getChildren().add(new Label("Loading Recents..."));
              hbox5.setAlignment(Pos.CENTER);
              tab5.getStyleClass().add("tab5");
              tab5.setId("bin");
              tab5.setContent(hbox5);

              tabPane.getTabs().add(tab5);
              tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
              tabPane.setId("tabpane");
               System.out.println(tabPane.getSelectionModel().getSelectedItem().getText());
              tabPane.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{ System.out.println(tabPane.getSelectionModel().getSelectedItem().getText());
         hbox4.getChildren().clear();
         hbox4.getChildren().add(searchEngine.searchBox());
        });

            HBox hbox = new HBox();
            hbox.getChildren().addAll(cb,cLabel,walletLbl,btn,btn2,logoStuff);//walletLbl.setTranslateX(100);walletLbl.setTranslateY(-50);walletLbl.setTranslateX(50);walletLbl.setTranslateY(-50);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER);
            BorderPane borderPane=new BorderPane();
             borderPane.prefHeightProperty().bind(mainScene.heightProperty());
            borderPane.prefWidthProperty().bind(mainScene.widthProperty());
          hbox.setStyle("-fx-background-color: black");
        StackPane stk=new StackPane();
        stk.getChildren().addAll(tabPane,savingsLbl,cLabel1,cLabel2);
            borderPane.setCenter(stk);
            //borderPane.getStyleClass().add("walletVBox");
            //savingsLbl.prefHeightProperty().bind(hbox.heightProperty());
            stk.setAlignment(savingsLbl,Pos.BOTTOM_CENTER);
             //stk.setAlignment(currentWalletLbl,Pos.BOTTOM_CENTER);
              stk.setAlignment(cLabel1,Pos.BOTTOM_CENTER);
               stk.setAlignment(cLabel2,Pos.BOTTOM_CENTER);
            borderPane.setBottom(hbox);
        
            loginBtn.setOnAction(e->{
               Alert alert = new Alert(AlertType.WARNING);
              alert.setTitle(username+"Log out");
              alert.setHeaderText("are You 100% sure this is what you want?");
              alert.setContentText("You will be returned to the Guest Account.");

              ButtonType buttonTypeOne = new ButtonType("Yup!");
              ButtonType buttonTypeCancel = new ButtonType("Wait", ButtonData.CANCEL_CLOSE);

              alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeCancel);
             
              Optional<ButtonType> result = alert.showAndWait();
              if (result.get() == buttonTypeOne)
                try{super.start(window);}catch(Exception ee){}
              else {
                  // ... user chose CANCEL or closed the dialog
              }
            });
            btn2.setDisable(false);
            //signUp.setOnAction(e->window.setScene(LoadingScene(scene3)));
           root.getChildren().addAll(borderPane);
            return mainScene;
    }
    /*///////////////////////////////////////////////////
    					LOADING SCENE
    ///////////////////////////////////////////////////*/
  
    /*///////////////////////////////////////////////////
							LOGO SCENE
    ///////////////////////////////////////////////////*/
	
    /*///////////////////////////////////////////////////
    					LOADING SCENE
    ///////////////////////////////////////////////////*/

     public Scene profileLoadingScene(Scene sceneToLoad)
    {
    	    Group root = new Group();
          Scene mainScene = new Scene(root, 700, 630, Color.WHITE);
          mainScene.getStylesheets().add("Load.css");
        
          this.msg=new Messages(this.username);
              	//final Float[] values = new Float[] {-1.0f, 0f, 0.6f, 1.0f};
      		final Label label = new Label(this.msg.getMessage("plMs"));
      		label.getStyleClass().add("loadingText");
      		label.setEffect(this.ds);
      		final Label label2 = new Label(msg.getMessage("plT"));
      		label2.getStyleClass().add("loadingTitle");
      		label2.setEffect(this.ds);
      		final Separator sp=new Separator();

          sp.setMaxWidth(1000);
          sp.getStyleClass().add("line");
          sp.setEffect(this.ds);
          HBox hb = new HBox();
          hb.setSpacing(15);
          hb.setAlignment(Pos.CENTER);
          hb.getChildren().addAll(mainMenu.smallLogoIcon(),label2);
          hb.getStyleClass().add("header");
	
      		final ProgressIndicator pins = new ProgressIndicator();
      		pins.getStyleClass().add("loader");
      		pins.setEffect(this.ds); //[values.length];
          pins.setProgress(-1.0f);
         
          final VBox vb = new VBox();
          vb.setSpacing(8);
          vb.getChildren().addAll(hb,pins,label);
          vb.setAlignment(Pos.CENTER);
          
          VBox hvb = new VBox();
          hvb.setSpacing(15);
          hvb.getChildren().addAll(hb);
          hvb.setAlignment(Pos.CENTER);
          hvb.getChildren().add(1,sp);
          
          BorderPane borderPane=new BorderPane();
          borderPane.getStyleClass().add("bg");
          borderPane.prefHeightProperty().bind(mainScene.heightProperty());
          borderPane.prefWidthProperty().bind(mainScene.widthProperty());
          borderPane.setTop(hvb);
          borderPane.setCenter(vb);
       
    
           double randRange=Math.random()*30;
           KeyFrame kf=new KeyFrame(Duration.seconds(randRange), ae -> window.setScene(sceneToLoad));
  	 			 Timeline timer=new Timeline(kf);
  	 		   System.out.println(kf.getTime());
  	 			 timer.play();
           root.getChildren().addAll(borderPane);
           return mainScene;
    }
    /*////////////////////////////////////////////////////
					LOGIN
    //////////////////////////////////////////////////*/
 
 	
 	 
}