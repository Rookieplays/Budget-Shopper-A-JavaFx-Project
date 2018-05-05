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
/**
 * @author ericjbruno
 */
class Search {
    ObservableList<String> entries = FXCollections.observableArrayList();    
    private ListView list = new ListView();
    private ArrayList<ArrayList<String>>shoppingListsInfo=new ArrayList<ArrayList<String>>();
    private Label lbl;
    private Button edit,delete,sort,filter;
     private DropShadow ds;
        public VBox searchBox()
        {
             ds= new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.web("#000000"));
        	TextField txt =searchField();
        	txt.setPromptText("Search");
        	txt.textProperty().addListener(
            new ChangeListener() {
                public void changed(ObservableValue observable, 
                                    Object oldVal, Object newVal) {
                    handleSearchByKey2((String)oldVal, (String)newVal);
                }
            });
         
        // Set up the ListView
        list.setMaxWidth(600);
        try{getLists();}catch(IOException ee){}
        // Populate the list's entries
        for ( int i = 1; i < shoppingListsInfo.get(0).size(); i++ ) {
            entries.add(shoppingListsInfo.get(0).get(i));
        }
       list.getItems().clear();
        list.setItems( entries );
         StackPane stk=new StackPane();
         stk.getChildren().addAll(txt);
         stk.setAlignment(txt,Pos.TOP_CENTER);
         stk.setTranslateY(-70);
        VBox root = new VBox();
        root.setPadding(new Insets(10,10,10,10));
        root.setSpacing(2);
        root.getChildren().addAll(stk,list);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("background");
      return root;
    }
     public TextField searchField()
     {
     	TextField tf=new TextField();
     	tf.getStyleClass().add("searchBar");
        tf.setEffect(ds);

     	return tf;
     }
    public void handleSearchByKey(String oldVal, String newVal) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
            // Restore the lists original set of entries 
            // and start from the beginning
            list.setItems( entries );
        }
         
        // Change to upper case so that case is not an issue
        newVal = newVal.toUpperCase();
 
        // Filter out the entries that don't contain the entered text
        ObservableList<String> subentries = FXCollections.observableArrayList();
        for ( Object entry: list.getItems() ) {
            String entryText = (String)entry;
            if ( entryText.toUpperCase().contains(newVal) ) {
                subentries.add(entryText);
            }
        }
        list.setItems(subentries);
    }
 	public void getLists()throws IOException
 	{
 		shoppingListsInfo.clear();
 		FileManager man=new FileManager();
 		man.loadList_Owners();
 		System.out.println(man.getList_Owners());
 		Order ordered=new Order(man.getList_Owners());
 		shoppingListsInfo=man.getList_Owners();//ordered.ascendingOrder("fn");
        System.out.println(shoppingListsInfo);
 	}
    public void handleSearchByKey2(String oldVal, String newVal) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
            // Restore the lists original set of entries 
            // and start from the beginning
            list.setItems( entries );
        }
         
        // Break out all of the parts of the search text 
        // by splitting on white space
        String[] parts = newVal.toUpperCase().split(" ");
 
        // Filter out the entries that don't contain the entered text
        ObservableList<String> subentries = FXCollections.observableArrayList();
        for ( Object entry: list.getItems() ) {
            boolean match = true;
            String entryText = (String)entry;
            for ( String part: parts ) {
                // The entry needs to contain all portions of the
                // search string *but* in any order
                if ( ! entryText.toUpperCase().contains(part) ) {
                    match = false;
                    break;
                }
            }
 
            if ( match ) {
                subentries.add(entryText);
            }
        }
        list.setItems(subentries);
    }
}
