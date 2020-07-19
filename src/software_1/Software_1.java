/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import static javafx.collections.FXCollections.observableArrayList;

/**
 *
 * @author eric
 */
public class Software_1 extends Application {
    
    public void Software_1() {
        System.out.println("software_1 constructor");
        System.out.println("-------------");
    }
                        
//    public static Inventory parts = new Inventory();
        
    public static Inventory partsInventory = new Inventory();
    
    @Override
    public void start(Stage stage) throws Exception {

        System.out.println(partsInventory.getAllParts());
        System.out.println(partsInventory.allParts.size());
        System.out.println("///////////");

               
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root, 1000, 500);
        scene.getStylesheets().add(Software_1.class.getResource("main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);        
        
        System.out.println("Sotware_1.java main run");
        
        System.out.println("--------------");
      
}
}
    
