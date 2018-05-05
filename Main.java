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
import javafx.scene.media.AudioClip;
import java.util.*;
import javafx.scene.media.AudioClip;
import javafx.collections.*;
import javafx.beans.value.*;
public class Main extends Application 
{
	private Scene scene,scene2,scene3,loadScene,userMainScene;
	protected Stage window;
 	private Login login;
 	private Registration reg;
 	private DropShadow ds;
 	private Scene prev;
 	private Messages msg;
 	private KeyCode key;
 	private String username="";
 	private boolean logIn;
  private AddNewTab listMan;
  private Wallet wal;
  private userMainPage userScene;
  private    HBox hbox4;
  

	public static void main(String[] args) {
		launch(args);
	}
	 @Override
    public void start(Stage primaryStage)throws Exception 
    {
     
      logIn=false;
      if(logIn==false)
      {
        username="Guest";
      }
      //stack=new StackPane();
        wal=new Wallet(this.username);
      listMan=new AddNewTab(username,false);System.out.println("listMan sets the name: "+username);
        ds= new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.web("#000000"));
        window=primaryStage;
        window.setTitle("Budget Shopper");
        scene=Main();
        // loadScene=LoadingScene();
       
        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
       	{
       		@Override
       		public void handle(KeyEvent key)
       		{
       			if(key.getCode().equals(KeyCode.L))
       				window.setScene(LoadingScene(scene2));
       			else if(key.getCode().equals(KeyCode.S))
       				window.setScene(LoadingScene(scene3));
       		}
         });
        scene2.setOnKeyPressed(new EventHandler<KeyEvent>()
       	{
       		@Override
       		public void handle(KeyEvent key)
       		{
       			if(key.getCode().equals(KeyCode.ESCAPE))
       				window.setScene(scene);
       			
       		}
        });
        scene3.setOnKeyPressed(new EventHandler<KeyEvent>()
       	{
       		@Override
       		public void handle(KeyEvent key)
       		{
       			if(key.getCode().equals(KeyCode.ESCAPE))
       				window.setScene(scene);
       			
       		}
        });
      //  userMainScene=userScene.mainUser();
      //window.setScene(logoScene());
      window.setScene(scene);
        window.setResizable(true);
        window.show();

    }
    public Scene Main()throws Exception
    {
    	 Group root = new Group();
       Search searchEngine=new Search();
          //Group root2=new Group();
       Tooltip tp[]=new Tooltip[5];
       for(int i=0;i<tp.length;i++)
        {
          tp[i]=new Tooltip();
          tp[i].getStyleClass().add("tp");
        }
        Label cLabel=new Label();
         cLabel.getStyleClass().add("walletLbl1");
          cLabel.setTextFill(Color.rgb(179,255,255));

         // cLabel.setEffect(glow);
        final String []currencies=new String[]{"\u20AC","\u00A3","\u00A5","\u20A7","\u0024","\u20B9"};
         final ChoiceBox cb=new ChoiceBox(FXCollections.observableArrayList("Euro","Pound","YEN","Niara","Dolar","Indain Rupies"));
         cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
         {
          public void changed (ObservableValue ov,Number value,Number new_value)
          {
            cLabel.setText(currencies[new_value.intValue()]);
            
          }
          });
         cb.setTooltip(new Tooltip("Select the Currency"));
        cb.setValue("Euro");
        cb.getStyleClass().add("cb1");
        cb.setTranslateX(-250);

         Label walletLbl =new Label(wal.getsBalance());
          walletLbl.getStyleClass().add("walletLbl1");
          walletLbl.setTextFill(Color.rgb(179,255,255));
          walletLbl.setTranslateX(-100);
          cLabel.setTranslateX(-150);
         Scene mainScene = new Scene(root, 700, 630, Color.WHITE);
          mainScene.getStylesheets().add("main.css");
            mainScene.getStylesheets().add("Load.css");
            mainScene.getStylesheets().add("user.css");
            mainScene.getStylesheets().add("addTab.css");
        scene2 = LoginScene();
        scene3=RegisterScene();
         Button btn=new Button("Wallet");
         btn.getStyleClass().add("wallet");
         btn.setEffect(ds);
         tp[0].setText("This is a Guest Wallet\nClick to change Balance");
         btn.setTooltip(tp[0]); btn.setOnAction(e->{try{wal.walletDialog(cLabel,walletLbl);}catch(IOException iex){}});
          btn.setOnAction(e->{try{wal.walletDialog(cLabel,walletLbl);}catch(IOException iex){}});
         Button btn2=new Button("Savings");
         btn2.getStyleClass().add("savings");
         btn2.setEffect(ds);
          tp[1].setText("Login To Acess ->");
         btn2.setTooltip(tp[1]);
                      TabPane tabPane = new TabPane();

           mainScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
          @Override
          public void handle(KeyEvent key)
          {
            if(key.getCode().equals(KeyCode.L))
              window.setScene(scene2);
            else if(key.getCode().equals(KeyCode.S))
              window.setScene(scene3);
            else if(key.getCode().equals(KeyCode.TAB))
              window.setScene(logoScene());
            
          }
          });

             Button btn3=smallLogoIcon();
               btn3.setScaleX(0.5);
              btn3.setScaleY(0.5);

             Button settings=new Button("\u2699");
               settings.getStyleClass().add("settings");
               tp[4].setText("Settings this way");
         settings.setTooltip(tp[4]);
            
              HBox settingsLogo=new HBox();
              settingsLogo.getChildren().add(settings);

              Tab tab1 = new Tab();
              tab1.setText("Updates");
              HBox hbox1 = new HBox();
              hbox1.getChildren().add(new Label("No Updates..."));
              hbox1.setAlignment(Pos.CENTER);
              tab1.setContent(hbox1);
              tab1.getStyleClass().add("tab1");
              tab1.setId("news");
              tabPane.getTabs().add(tab1);
              ImageView nU=new ImageView(new Image("Assets/newUserOn.png"));
              Button signUp=new Button("",nU);
              nU.setScaleX(0.075);
              nU.setScaleY(0.075);
              signUp.setTranslateX(-20);
              signUp.getStyleClass().add("signupbtn");
              tp[2].setText("Sign up Here");
         signUp.setTooltip(tp[2]);
              ImageView rpLogo=new ImageView(new Image("Assets/newUser.png"));
              rpLogo.setScaleX(0.25);
              rpLogo.setScaleY(0.25);
              Button loginBtn=new Button("Guest");
              loginBtn.getStyleClass().add("login");
              tp[3].setText("Click to Login");
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
               hbox4 = new HBox();
              hbox4.getChildren().add(new Label("Loading Archive..."));
              hbox4.setAlignment(Pos.CENTER);
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
              StackPane stk=new StackPane();
              tabPane.getTabs().add(tab5);
              tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
              tabPane.setId("tabpane");
                  tabPane.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{ System.out.println(tabPane.getSelectionModel().getSelectedItem().getText());
         hbox4.getChildren().clear();
         hbox4.getChildren().add(searchEngine.searchBox());
        });
                stk.getChildren().addAll(tabPane,walletLbl,cLabel,cb);
            HBox hbox = new HBox();
            hbox.getChildren().addAll(signUp,btn,btn2,logoStuff);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER);
            BorderPane borderPane=new BorderPane();
             borderPane.prefHeightProperty().bind(mainScene.heightProperty());
            borderPane.prefWidthProperty().bind(mainScene.widthProperty());
          hbox.setStyle("-fx-background-color: black");
            borderPane.setCenter(stk);
            stk.setAlignment(cb,Pos.BOTTOM_CENTER);
               stk.setAlignment(cLabel,Pos.BOTTOM_CENTER);
               stk.setAlignment(walletLbl,Pos.BOTTOM_CENTER);
              
            borderPane.setBottom(hbox);
        
            loginBtn.setOnAction(e->window.setScene(LoadingScene(scene2)));
            btn2.setDisable(true);
            signUp.setOnAction(e->window.setScene(LoadingScene(scene3)));
           root.getChildren().addAll(borderPane);
            return mainScene;
    }
    /*///////////////////////////////////////////////////
    					LOADING SCENE
    ///////////////////////////////////////////////////*/
     protected Scene LoadingScene(Scene sceneToLoad)
    {
         Group root =  new Group();
          //Group root2=new Group();
         Scene mainScene = new Scene(root, 700, 630, Color.WHITE);
         mainScene.getStylesheets().add("Load.css");
        
          msg=new Messages();
    			final Label label = new Label(msg.getMessage("lMs"));
    			label.getStyleClass().add("loadingText");
    			label.setEffect(ds);
    			final ProgressIndicator pins = new ProgressIndicator();
    			pins.getStyleClass().add("loader");
    			pins.setEffect(ds); //[values.length];
    			pins.setProgress(-1.0f);
                
            
 
            final VBox vb = new VBox();
            vb.setSpacing(15);
            vb.getChildren().addAll(pins,label);
            vb.setAlignment(Pos.CENTER);
                
            BorderPane borderPane=new BorderPane();
            borderPane.getStyleClass().add("bg");
            borderPane.prefHeightProperty().bind(mainScene.heightProperty());
            borderPane.prefWidthProperty().bind(mainScene.widthProperty());
            borderPane.setCenter(vb);
     
            double randRange=Math.random()*10;
            KeyFrame kf=new KeyFrame(Duration.seconds(randRange), ae -> window.setScene(sceneToLoad));
	 					Timeline timer=new Timeline(kf);
	 				System.out.println(kf.getTime());
	 					timer.play();
            root.getChildren().addAll(borderPane);

            mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() 
            {
              public void handle(KeyEvent ke) {
                
                if (KeyCode.SPACE.equals(ke.getCode())) 
                {
                    window.setScene(sceneToLoad);    
                }
            }
            });


        return mainScene;
    }
     public void setUsername(String username)
    {
      this.username=username;
    }
    public String getUsername()
    {
      System.out.println("->>>>"+username);
      return username;
    }
    /*///////////////////////////////////////////////////
							LOGO SCENE
    ///////////////////////////////////////////////////*/
	public Scene logoScene()
	{
		    Group root = new Group();
          //Group root2=new Group();
        Scene mainScene = new Scene(root, 700, 630, Color.WHITE);
        mainScene.getStylesheets().addAll("Logo.css","Load.css");
        
        Circle c=new Circle(14);
        c.getStyleClass().add("c");
        c.setEffect(new Glow(0.8));
        c.setFill(Color.BLUE);
        c.setTranslateX(210);
        c.setTranslateY(110);
        
        Circle thisPath=new Circle(50);

        Button logoBtn=LogoIcon(false);
         Button logoBtn2=LogoIcon(false);
        logoBtn.getStyleClass().add("logobtn");
        logoBtn.setEffect(ds);
       
        logoBtn.setScaleX(0.5);
        logoBtn.setScaleY(0.5);
        logoBtn.setTranslateX(30);
        logoBtn.setTranslateY(100);

        logoBtn2.getStyleClass().add("logobtn");
        logoBtn2.setEffect(ds);
       
        logoBtn2.setScaleX(0.5);
        logoBtn2.setScaleY(0.5);
        logoBtn2.setTranslateX(30);
        logoBtn2.setTranslateY(100);
             // Animation moving up....
        TranslateTransition transition=new TranslateTransition();
        transition.setDuration(Duration.millis(1800));
        transition.setNode(logoBtn);
        transition.setToY(-100);
        transition.setAutoReverse(true);
         
        TranslateTransition transition2=new TranslateTransition();
        transition2.setDuration(Duration.millis(1800));
        transition2.setNode(logoBtn2);
        transition2.setToY(-100);
        transition2.setAutoReverse(true);
         
        //Animation Grow up...
        ScaleTransition grow=new ScaleTransition(Duration.millis(3000),logoBtn);
        grow.setToX(1.2);
        grow.setToY(1.2);

        ScaleTransition grow2=new ScaleTransition(Duration.millis(3000),logoBtn2);
        grow2.setToX(1.2);
        grow2.setToY(1.2);
         
       
        ParallelTransition transitions=new ParallelTransition(transition,grow,transition2,grow2);
        transition.setCycleCount(3);
        transitions.play();
      
      	root.getStyleClass().add("logoscene");

        ProgressIndicator pins = new ProgressIndicator();
        pins.getStyleClass().add("loader");
        pins.setEffect(ds);//[values.length];
        pins.setProgress(-1.0f);
        
        ProgressIndicator pins2 = new ProgressIndicator();
        pins2.getStyleClass().add("loader");
        pins2.setEffect(ds); //[values.length];
        pins2.setProgress(-1.0f);
        

        BorderPane borderPane2=new BorderPane();
        borderPane2.prefHeightProperty().bind(mainScene.heightProperty());
        borderPane2.prefWidthProperty().bind(mainScene.widthProperty());
        borderPane2.getStyleClass().add("logo2scene");
        borderPane2.setCenter(logoBtn2);

        BorderPane borderPane=new BorderPane();
        borderPane.prefHeightProperty().bind(mainScene.heightProperty());
        borderPane.prefWidthProperty().bind(mainScene.widthProperty());
        borderPane.getStyleClass().add("logoscene");
        borderPane.setCenter(logoBtn);

        BorderPane borderPane3=new BorderPane();
        Label lab=new Label("Loading...");
        lab.getStyleClass().add("loadingText");
        Label lab2=new Label("Starting up..");
        lab2.getStyleClass().add("loadingText");
        HBox l=new HBox(); HBox l2=new HBox();
        l.getChildren().addAll(lab,pins);
        l.setSpacing(10);
        l2.getChildren().addAll(lab2,pins2);
        l2.setSpacing(10);
        l2.setAlignment(Pos.BOTTOM_RIGHT);
        l.setAlignment(Pos.BOTTOM_RIGHT);
        
        final String content = "Budget Shopper";
        final Text text = new Text(10, 20, "");
        borderPane.setBottom(l);
        borderPane2.setBottom(l2);
        double randRange=Math.random()*10;
        
      	transitions.setOnFinished(e->
        {
          FadeTransition fadeout=new FadeTransition(Duration.millis(2500),borderPane2);
          fadeout.setFromValue(1.0);
          fadeout.setToValue(0.0);
          fadeout.play();
              
          RotateTransition st=new RotateTransition(Duration.millis(2500),logoBtn2);
          st.setToAngle(360);
          st.play();
          RotateTransition st2=new RotateTransition(Duration.millis(2500),logoBtn);
          st2.setToAngle(360);
          st2.play();
          st2.setOnFinished(ev->
            {
              FadeTransition fadeoutE=new FadeTransition(Duration.millis(2500),borderPane3);
              fadeoutE.setFromValue(1.0);
              fadeoutE.setToValue(0.0);
              fadeoutE.play();
              fadeoutE.setOnFinished(eve->window.setScene(LoadingScene(scene)));
           });
        });
          
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() 
        {
          public void handle(KeyEvent ke) 
          {
            if (KeyCode.SPACE.equals(ke.getCode())) 
            {
                window.setScene(scene);    
            }
            else if(ke.getCode().equals(KeyCode.R))
            {
               window.setScene(logoScene());
            }
          }
        });

        //borderPane2.getChildren().add(text);
        root.getChildren().addAll(borderPane,borderPane2,borderPane3);
        return mainScene;
	}
    /*///////////////////////////////////////////////////
    					LOADING SCENE
    ///////////////////////////////////////////////////*/
     public Scene profileLoadingScene(Scene sceneToLoad)
    {
    	    Group root = new Group();
          Scene mainScene = new Scene(root, 700, 630, Color.WHITE);
          mainScene.getStylesheets().add("Load.css");
        
          msg=new Messages(username);
              	//final Float[] values = new Float[] {-1.0f, 0f, 0.6f, 1.0f};
      		final Label label = new Label(msg.getMessage("plMs"));
      		label.getStyleClass().add("loadingText");
      		label.setEffect(ds);
      		final Label label2 = new Label(msg.getMessage("plT"));
      		label2.getStyleClass().add("loadingTitle");
      		label2.setEffect(ds);
      		final Separator sp=new Separator();

          sp.setMaxWidth(1000);
          sp.getStyleClass().add("line");
          sp.setEffect(ds);
          HBox hb = new HBox();
          hb.setSpacing(15);
          hb.setAlignment(Pos.CENTER);
          hb.getChildren().addAll(smallLogoIcon(),label2);
          hb.getStyleClass().add("header");
	
      		final ProgressIndicator pins = new ProgressIndicator();
      		pins.getStyleClass().add("loader");
      		pins.setEffect(ds); //[values.length];
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
       
    
           double randRange=Math.random()*10;
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
 	public Scene LoginScene()throws IOException
 	{
 		
        Group root=new Group();
        Scene sc=new Scene(root,700,630,Color.WHITE);
        sc.getStylesheets().add("Login.css");
        sc.getStylesheets().add("https://fonts.googleapis.com/icon?family=Material+Icons");
       
        VBox field=new VBox();
        field.getChildren().addAll(LogoIcon(true),login());
        
        field.setAlignment(Pos.CENTER);
        field.setSpacing(40); 
        
        BorderPane borderPane=new BorderPane();
        borderPane.prefHeightProperty().bind(sc.heightProperty());
        borderPane.prefWidthProperty().bind(sc.widthProperty());
        //borderPane.setAlignment(field,Pos.CENTER);
        
        borderPane.setCenter(field);
        borderPane.getStyleClass().add("bg");

        root.getChildren().addAll(borderPane);

        return sc;

 	}
 	private VBox login()throws IOException
 	{
   		VBox vb=new VBox();
   		TextField usernameField=new TextField();
   		usernameField.getStyleClass().add("loginfield");
   		usernameField.setPromptText("Username..");
   		usernameField.setId("username");
   		usernameField.setEffect(ds);
   		Label message=new Label("");
   		msg=new Messages(usernameField.getText().trim());

   		PasswordField passwordField=new PasswordField();
   		Label confirm=new Label("confirm");
   
   		confirm.setFont(Font.font("Cambria", FontWeight.BOLD,18));
   		confirm.setTextFill(Color.WHITE);
   		Button confirmButton=new Button("",confirm);

   		confirmButton.getStyleClass().addAll("material-icons","confirmBtn");
   		confirmButton.setEffect(ds);
   		confirmButton.setDisable(true);
   		//KeyFrame kf=new KeyFrame(Duration.minutes(1));
   		Tooltip tt=new Tooltip();
   		
 		 	passwordField.setOnAction(new EventHandler<ActionEvent>()
      {
   			@Override
   			public void handle(ActionEvent e) 
   			{
   				 msg=new Messages(usernameField.getText().trim());
   			  try{
   				     login=new Login(usernameField.getText(),passwordField.getText());

   				     if(!(usernameField.getText().equals(""))||usernameField.getText()!=null)
   				     {
   					      confirmButton.getStyleClass().add("confirm2");
   		            tt.setText("Click Me! I missed You");	
         					tt.getStyleClass().add("tooltip");
         					confirmButton.setTooltip(tt);

   					      login.setUsername(usernameField.getText().trim());
   						    login.setPassword(passwordField.getText().trim());
         					try{
          		 					if(!login.existingAccount())
          		 					{
          		 						message.setText(msg.getMessage("eLFU"));
          		 						message.setTextFill(Color.rgb(210,39,30));
          		 						confirmButton.setDisable(true);
          	 							passwordField.clear();
          		 					}
          		 					else
          		 					{
          		 						message.setText(msg.getMessage("lWM"));
          		 						confirmButton.setDisable(false);
          		 						logIn=true;
          		 						username=usernameField.getText().trim();System.out.println("Changing username to ->"+username);
                          setUsername(username);
                           userScene=new userMainPage(username,logIn);
                          listMan=new AddNewTab(username,false);
          	 							passwordField.clear();usernameField.clear();
          	 							message.setText("");//profileLoadingScene(scene)
          	 						//	window.setScene(userScene.mainUser());
                          window.setScene(profileLoadingScene(userScene.mainUser()));
                          //window.close();
                          System.out.println(username);
                          
                          message.setTextFill(Color.rgb(21,117,84));
                        }
                      }
  		 			      catch(IOException ex){System.out.println(ex+"\nIOException");}
                }
                else
  	 					  {
    	 						tt.setText(msg.getMessage("eIM"));
                  login.existingAccount();
    	 						confirmButton.setDisable(true);
                }
   					  }
   				catch(IOException ex){System.out.println(ex+"\nIOException");}
        }
   		});
 		
 				
      confirmButton.setOnAction(e->window.setScene(scene));
 			passwordField.setPromptText("Password..");
 			
   		
   		passwordField.getStyleClass().add("loginfield");
   		passwordField.setId("password");
   		passwordField.setEffect(ds);
   		vb.getChildren().addAll(usernameField,passwordField,message,confirmButton);
   		vb.getStyleClass().add("login");
   		vb.setSpacing(20);
   		vb.setAlignment(Pos.CENTER);

   		return vb;

 	}
 	public Button LogoIcon(boolean toolt)
  {
        Button icon;
        ImageView iv=new ImageView(new Image("Assets/Logo138x141.png"));

        iv.setEffect(ds);
        Tooltip tt=new Tooltip();
        icon=new Button("",iv);
        icon.setEffect(ds);
        icon.getStyleClass().add("logoicon");
        if(toolt==true)
        {
          tt.setText("Tap to go Back <-");
          tt.getStyleClass().add("tooltip2");
          icon.setTooltip(tt);
         // icon.setOnAction(e->window.setScene(scene));
        }
		    return icon;

  }
   public Button smallLogoIcon()
  {
        Button icon;
        //"#0d0d0d"
          
        Tooltip tt=new Tooltip();
        icon=new Button("",new ImageView(new Image("Assets/Logo50x50.png")));
        icon.setEffect(ds);
        icon.getStyleClass().add("smalllogoicon");
        tt.setText("Go to Main menu");
        tt.getStyleClass().add("tooltip2");
        icon.setTooltip(tt);
       // icon.setOnAction(e->window.setScene(scene));
		    return icon;

  }

  /*///////////////////////////////////////////////////
					REGISTER
  //////////////////////////////////////////////////*/
 	public Scene RegisterScene()throws IOException
 	{
      Group root=new Group();
      Scene sc=new Scene(root,700,630,Color.WHITE);
      sc.getStylesheets().add("Register.css");
      sc.getStylesheets().add("https://fonts.googleapis.com/css?family=Sofia");
      VBox field=new VBox();
      field.getChildren().addAll(LogoIcon(true),register());
      field.setAlignment(Pos.CENTER);
      field.setSpacing(40); 
      BorderPane borderPane=new BorderPane();
      borderPane.prefHeightProperty().bind(sc.heightProperty());
      borderPane.prefWidthProperty().bind(sc.widthProperty());
      borderPane.setCenter(field);
      borderPane.getStyleClass().add("bg");
      root.getChildren().addAll(borderPane);
      return sc;

 	}
 	private VBox register()throws IOException
 	{
 		VBox vb=new VBox();
 		TextField usernameField=new TextField();
 		usernameField.getStyleClass().add("registerfield");
 		usernameField.setPromptText("Username..");
 		usernameField.setId("username");
 		usernameField.setEffect(ds);
 		Label message=new Label("");
 		
 		PasswordField passwordField=new PasswordField();
 		PasswordField rep_passwordField=new PasswordField();
 		Label confirm=new Label("confirm");
 		Button pinfobtn2=new Button("");
 		pinfobtn2.getStyleClass().add("infobuttonDudd");
 		Button pinfobtn=new Button("..");
 		pinfobtn.getStyleClass().add("infobutton");
 		pinfobtn.setEffect(ds);
 		Button uinfobtn=new Button("..");
 		uinfobtn.getStyleClass().add("infobutton");
 		uinfobtn.setEffect(ds);
 		confirm.setFont(Font.font("Cambria", FontWeight.BOLD,18));
 		confirm.setTextFill(Color.WHITE);
 		Button confirmButton2=new Button("",confirm);
 		msg=new Messages(usernameField.getText().trim());

 		confirmButton2.getStyleClass().addAll("material-icons","confirmBtn");
 		confirmButton2.setEffect(ds);
 		confirmButton2.setDisable(true);

 		Tooltip tt=new Tooltip();
 		Tooltip pinfo=new Tooltip();
 		Tooltip uinfo=new Tooltip();
 		pinfo.getStyleClass().add("tooltip");
 		pinfo.setText(msg.getMessage("pC"));
 		pinfobtn.setTooltip(pinfo);

 		uinfo.getStyleClass().add("tooltip");
 		uinfo.setText(msg.getMessage("uC"));
 		uinfobtn.setTooltip(uinfo);

 		System.out.println(passwordField.getText());
 		rep_passwordField.setOnAction(new EventHandler<ActionEvent>()
    {
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				msg=new Messages(usernameField.getText().trim());
 				try{reg=new Registration(usernameField.getText(),passwordField.getText());
 				if((!(usernameField.getText().equals(""))||usernameField.getText()!=null)&&(!(passwordField.getText().equals(""))||passwordField.getText()!=null)&&(!(rep_passwordField.getText().equals(""))||rep_passwordField.getText()!=null))
 				if(reg.validUsername()&&(!(passwordField.getText().equals(""))||passwordField.getText()!=null)&&(!(rep_passwordField.getText().equals(""))||rep_passwordField.getText()!=null))
 				{
	 				if(passwordField.getText().trim().equals(rep_passwordField.getText().trim()))
	 				{		System.out.println(passwordField.getText());
		 				if(reg.validPassword()&&(!(usernameField.getText().equals(""))||usernameField.getText()!=null)&&(!(passwordField.getText().equals(""))||passwordField.getText()!=null))
		 				{
		 					confirmButton2.getStyleClass().add("confirm2");
		 					reg.setUsername(usernameField.getText().trim());
		 					reg.setPassword(passwordField.getText().trim());
		 					if(reg.existingAccount())
		 					{
                
		 						message.setText(msg.getMessage("eFU"));
		 						message.setTextFill(Color.rgb(210,39,30));
		 						int confirmDialog;
		 						confirmDialog=JOptionPane.showConfirmDialog(null,msg.getMessage("eFU")+"\nDo You Want to Login?","wait a sec...",JOptionPane.YES_NO_OPTION);
		 						if(confirmDialog==JOptionPane.YES_OPTION)
		 					  window.setScene(LoadingScene(scene));

                         }
		 					else
		 					{

		 						message.setText(msg.getMessage("gM"));
		 						message.setTextFill(Color.rgb(21,117,84));
		 						try{reg.saveDetails();}catch(IOException ex){System.out.println("IOException");};
		 						confirmButton2.setDisable(false);
		 						tt.setText("Click me! And we'll get You started.");	
		 				   	tt.getStyleClass().add("tooltip");
		 				   	confirmButton2.setTooltip(tt);
		 						
		 					}
            }
		 				else
		 					{
		 						message.setText(msg.getMessage("eMBP"));
		 						message.setTextFill(Color.rgb(210,39,30));
		 						passwordField.clear();rep_passwordField.clear();
		 						confirmButton2.setDisable(true);
		 					}
		 			}
		 			else 
		 			{
		 						message.setText(msg.getMessage("eMP"));
		 						message.setTextFill(Color.rgb(210,39,30));
		 						passwordField.clear();rep_passwordField.clear();
		 						confirmButton2.setDisable(true);
		 			}
		 		}
		 		else
		 		{
		 			message.setText(msg.getMessage("eMBU"));
		 						message.setTextFill(Color.rgb(210,39,30));
		 						passwordField.clear();rep_passwordField.clear();usernameField.clear();
		 						confirmButton2.setDisable(true);
		 		}
      }catch(IOException exc){System.out.println(exc);}

 			}
 		});
 		confirmButton2.setOnAction(e->{
      
      logIn=true;
     username=usernameField.getText().trim();
      try{
          
                
                System.out.println("Changing username to ->"+username);
                          setUsername(username); 
               userScene=new userMainPage(username,logIn);

            listMan=new AddNewTab(username,false);
             window.setScene(profileLoadingScene(userScene.mainUser()));
        
          }
          catch(IOException exce){};
         
          passwordField.clear();
          rep_passwordField.clear();
          usernameField.clear();
          message.setText("");

    });
 	
 		passwordField.setPromptText("Password..");
 		passwordField.getStyleClass().add("registerfield");
 		passwordField.setId("password");
 		passwordField.setEffect(ds);

 		rep_passwordField.setPromptText("Confirm Password..");
 		rep_passwordField.getStyleClass().add("registerfield");
 		rep_passwordField.setId("password");
 		rep_passwordField.setEffect(ds);

 		HBox phbox=new HBox();
 		phbox.getChildren().addAll(passwordField,pinfobtn);
 		phbox.setSpacing(15);
 		phbox.setAlignment(Pos.CENTER);
 		HBox uhbox=new HBox();
 		uhbox.getChildren().addAll(usernameField,uinfobtn);
 		uhbox.setSpacing(15);
 		uhbox.setAlignment(Pos.CENTER);
 		HBox phbox2=new HBox();
 		phbox2.getChildren().addAll(rep_passwordField,pinfobtn2);
 		phbox2.setSpacing(15);
 		phbox2.setAlignment(Pos.CENTER);
 		vb.getChildren().addAll(uhbox,phbox,phbox2,message,confirmButton2);
 		vb.getStyleClass().add("register");
 		vb.setSpacing(20);
 		vb.setAlignment(Pos.CENTER);
 		//rep_passwordField.setTranslateX(-12);


 		return vb;

 	}
 	

  public Circle createCircle() 
  {
            Circle circle = new Circle(50);
            DropShadow ds = new DropShadow();
            ds.setOffsetY(3.0);
            ds.setOffsetX(3.0);
            ds.setColor(Color.GRAY);
           //circle.setStroke(Color.BLUE);
            circle.setStrokeWidth(5);
            circle.setStrokeType(StrokeType.INSIDE);
            //   circle.setID("circ");
            circle.setFill(Paint.valueOf("#b3b3b3"));
            circle.relocate(0, 0);
            circle.setEffect(ds);
            
            return circle;
  }
 
}