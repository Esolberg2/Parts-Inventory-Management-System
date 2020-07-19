/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_1;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import static software_1.Inventory.allParts;

/**
 * FXML Controller class
 *
 * @author eric
 */
public class AddPartViewController implements Initializable {
    
    @FXML
    private void cancel(ActionEvent event) throws IOException {
//        String id = ((Button)event.getSource()).getId();
        Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene main = new Scene(parent);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(main);
        window.show();
    }
    
    private boolean emptyCheck(String text) {
        if (text.trim().isEmpty()) {
            return false;
        }
        return true;
    }
    
    @FXML
    private void inHouseAction(ActionEvent event) {
        addPartCompanyName.setVisible(false);
        addPartCompanyNameLabel.setVisible(false);
        addPartMachineID.setVisible(true);
        addPartMachineIDLabel.setVisible(true);
        
    }
    
    @FXML
    private void outsourcedAction(ActionEvent event) {
        addPartCompanyName.setVisible(true);
        addPartCompanyNameLabel.setVisible(true);
        addPartMachineID.setVisible(false); 
        addPartMachineIDLabel.setVisible(false);
    }
    
    @FXML
    private void save(ActionEvent event) throws IOException {
        System.out.println(addPartName.getText());
        System.out.println();
        
        if (addPartOutsourced.isSelected() || addPartInHouse.isSelected()) {
            if (addPartOutsourced.isSelected()) {
                System.out.println("outsourced");
                System.out.println(addPartName.getText());
                System.out.println(addPartInv.getText());
                System.out.println(addPartPrice.getText());
                System.out.println(addPartMax.getText());
                System.out.println(addPartMin.getText());
                System.out.println(addPartCompanyName.getText());
    //            allParts.add(new Outsourced("imported bolt", 1.05, 10, 20, 32, "IBM"));
                allParts.add(new Outsourced(
                        addPartName.getText(),
                        Integer.parseInt(addPartInv.getText()),
                        Integer.parseInt(addPartPrice.getText()),
                        Integer.parseInt(addPartMax.getText()),
                        Integer.parseInt(addPartMin.getText()),
                        addPartCompanyName.getText()));

                }
            else if (addPartInHouse.isSelected()) {
                System.out.println("inhouse");
    //            allParts.add(new InHouse(1, "hinge", .05, 25, 1000, 100, 1));
                allParts.add(new InHouse(
                    addPartName.getText(),
                    Integer.parseInt(addPartInv.getText()),
                    Integer.parseInt(addPartPrice.getText()),
                    Integer.parseInt(addPartMax.getText()),
                    Integer.parseInt(addPartMin.getText()),
                    Integer.parseInt(addPartMachineID.getText())));
            }
            
            Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene main = new Scene(parent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(main);
            window.show();
        }
        
        else {
            System.out.println("select a source");
        }
        
    }
    
//    @FXML
//    private void save(ActionEvent event) throws IOException {
////        String id = ((Button)event.getSource()).getId();
//        Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        Scene main = new Scene(parent);
//        
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        
//        window.setScene(main);
//        window.show();
//    }
    
    @FXML private TextField addPartId;
    @FXML private TextField addPartName;
    @FXML private TextField addPartInv;
    @FXML private TextField addPartPrice;
    @FXML private TextField addPartMax;
    @FXML private TextField addPartMin;
    @FXML private TextField addPartCompanyName;
    @FXML private TextField addPartMachineID;
    @FXML private Label addPartCompanyNameLabel;
    @FXML private Label addPartMachineIDLabel;
    
    ToggleGroup toggleGroup = new ToggleGroup();
    @FXML private RadioButton addPartInHouse;
    @FXML private RadioButton addPartOutsourced;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
            addPartInHouse.setToggleGroup(group);
            addPartOutsourced.setToggleGroup(group);
        
        addPartId.setText(Integer.toString(Part.getNextId()));
    }    
    
}
