import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
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
import java.util.*;
import java.time.*;
class LoginScene extends Main
 {
 	private String userName,password;
 	private double wallet,savings;
 	private LocalDateTime date;
 	private DropShadow ds;
 	private Button confirmButton;
 	private Login login;
 	
 	private Scene prev;

 	public LoginScene()throws Exception
 	{
 		super();
 		userName="username";
 		password="password";
 		ds= new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.web("#000000"));
 		wallet=0.00;
 		savings=0.00;
 		date=date.now();
 		

 		//window.setScene(LoginScene());
       //window.setResizable(true);
      // window.show();
 	}
 	public Scene LoginScene()throws IOException
 	{
 		
        Group root=new Group();
        Scene sc=new Scene(root,310,530,Color.WHITE);
       sc.getStylesheets().add("Login.css");
        sc.getStylesheets().add("https://fonts.googleapis.com/icon?family=Material+Icons");
       
          VBox field=new VBox();
          field.getChildren().addAll(LogoIcon(),login());
        
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
 		
 		PasswordField passwordField=new PasswordField();
 		Label confirm=new Label("confirm");
 
 		confirm.setFont(Font.font("Cambria", FontWeight.BOLD,18));
 		confirm.setTextFill(Color.WHITE);
 		confirmButton=new Button("",confirm);

 		confirmButton.getStyleClass().addAll("material-icons","confirmBtn");
 		confirmButton.setEffect(ds);
 		confirmButton.setDisable(true);

 		Tooltip tt=new Tooltip();
 		
 		login=new Login(usernameField.getText(),passwordField.getText());
 		passwordField.setOnAction(new EventHandler<ActionEvent>(){
 			@Override
 			public void handle(ActionEvent e) 
 			{
 				if(!(usernameField.getText().equals(""))||usernameField.getText()!=null)
 				{
 					confirmButton.getStyleClass().add("confirm2");
 					confirmButton.setDisable(false);
 					
 					tt.setText("Click when you are done!!");	
 					tt.getStyleClass().add("tooltip");
 					confirmButton.setTooltip(tt);

 					login.setUsername(usernameField.getText().trim());
 						login.setPassword(passwordField.getText().trim());
 					//if(!login.existingAccount())
 					//{
 						message.setText("This user does not exist");
 						message.setTextFill(Color.rgb(210,39,30));
 						passwordField.clear();
 					//}
 					//else
 					//{
 						message.setText("Welcome Back! "+usernameField.getText());
 						message.setTextFill(Color.rgb(21,117,84));

 				//	}
//

 				}
 				else
 					{
 						tt.setText("Oops you didn't write anything we can use!!");
 						confirmButton.setDisable(true);
 						//login.existingAccount();

 						
 						//confirmButton.setOnAction(e->{login.existingAccount();login.setUsername(usernameField.getText().trim());login.setPassword(passwordField.getText().trim());});
 						
 						
 						
 					}

 			}
 		});
 		//	confirmButton.setOnAction(e->{login.existingAccount();login.setUsername(usernameField.getText().trim());login.setPassword(passwordField.getText().trim());});
 	
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
 	public Button LogoIcon()
    {
        Button icon;
        //"#0d0d0d"
          
       Tooltip tt=new Tooltip();
                icon=new Button("",new ImageView(new Image("Assets/Logo138x141.png")));
        icon.setEffect(ds);
        icon.getStyleClass().add("logoicon");
        tt.setText("Tap to go Back <-");
        tt.getStyleClass().add("tooltip2");
        icon.setTooltip(tt);
      //  icon.setOnAction(e->super.window.setScene(super.scene));
		 return icon;

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
 	private Stack getProfileimage(String img_url)
 	{
 		return null;
 	}
 	//setup the login 
 	

 }
