/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_1;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        
         Alert alert = new Alert(Alert.AlertType.WARNING, 
                            "Are you sure you want to cancel? All unsaved data will be lost?", 
                            ButtonType.NO, ButtonType.YES);

            Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES){
                    Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    Scene main = new Scene(parent);
                    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    window.setScene(main);
                    window.show();
                    
                }
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
                
                allParts.add(new Outsourced(
                        addPartName.getText(),
                        Double.parseDouble(addPartPrice.getText()),
                        Integer.parseInt(addPartInv.getText()),
                        Integer.parseInt(addPartMin.getText()),
                        Integer.parseInt(addPartMax.getText()),
                        addPartCompanyName.getText()));

                }
            else if (addPartInHouse.isSelected()) {
                
                allParts.add(new InHouse(
                    addPartName.getText(),
                    Double.parseDouble(addPartPrice.getText()),
                    Integer.parseInt(addPartInv.getText()),
                    Integer.parseInt(addPartMin.getText()),
                    Integer.parseInt(addPartMax.getText()),
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
