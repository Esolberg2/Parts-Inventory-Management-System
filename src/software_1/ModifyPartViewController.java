/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_1;

import java.io.IOException;
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
public class ModifyPartViewController implements Initializable {
    
    public static int partIndex;
        

    public ModifyPartViewController() {

    }
    
    @FXML private TextField modifyPartId;
    @FXML private TextField modifyPartName;
    @FXML private TextField modifyPartInv;
    @FXML private TextField modifyPartPrice;
    @FXML private TextField modifyPartMax;
    @FXML private TextField modifyPartMin;
    @FXML private TextField modifyPartCompanyName;
    @FXML private TextField modifyPartMachineId;
    @FXML private Label modifyPartCompanyNameLabel;
    @FXML private Label modifyPartMachineIdLabel;
    @FXML private RadioButton inHouse;
    @FXML private RadioButton outsourced;
    
    @FXML
    private void inHouseAction(ActionEvent event) {
        modifyPartCompanyName.setVisible(false);
        modifyPartCompanyNameLabel.setVisible(false);
        modifyPartMachineId.setVisible(true);
        modifyPartMachineIdLabel.setVisible(true);
        
    }
    
    @FXML
    private void outsourcedAction(ActionEvent event) {
        modifyPartCompanyName.setVisible(true);
        modifyPartCompanyNameLabel.setVisible(true);
        modifyPartMachineId.setVisible(false); 
        modifyPartMachineIdLabel.setVisible(false);
    }
    
    
    @FXML
    private void cancel(ActionEvent event) throws IOException {        
        Alert alert = new Alert(Alert.AlertType.WARNING, 
                            "Are you sure you want to cancel? Any unsaved changes will be lost?", 
                            ButtonType.NO, ButtonType.YES);

            Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES){
                    Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    Scene addPartScene = new Scene(parent);
                    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    window.setScene(addPartScene);
                    window.show();
                }
    }
    
    @FXML
    private void save(ActionEvent event) throws IOException {
        if (inHouse.isSelected()) {
            InHouse part = new InHouse(allParts.get(partIndex).getId());
            
            part.setMachineId(Integer.parseInt(modifyPartMachineId.getText()));
            part.setName(modifyPartName.getText());
            part.setPrice(Double.parseDouble(modifyPartPrice.getText()));
            part.setStock(Integer.parseInt(modifyPartInv.getText()));
            part.setMin(Integer.parseInt(modifyPartMin.getText()));
            part.setMax(Integer.parseInt(modifyPartMax.getText()));
            
            
            allParts.set(partIndex, part);
        }
        else if (outsourced.isSelected()) {
            Outsourced part = new Outsourced(allParts.get(partIndex).getId());
            part.setCompanyName(modifyPartCompanyName.getText());
            part.setName(modifyPartName.getText());
            part.setPrice(Double.parseDouble(modifyPartPrice.getText()));
            part.setMax(Integer.parseInt(modifyPartMax.getText()));
            part.setMin(Integer.parseInt(modifyPartMin.getText()));
            part.setStock(Integer.parseInt(modifyPartInv.getText()));
            allParts.set(partIndex, part);
        }
        
        Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene addPartScene = new Scene(parent);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(addPartScene);
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        modifyPartId.setText(Integer.toString(allParts.get(partIndex).getId()));
        modifyPartName.setText(allParts.get(partIndex).getName());
        modifyPartInv.setText(Integer.toString(allParts.get(partIndex).getStock()));
        modifyPartPrice.setText(Double.toString(allParts.get(partIndex).getPrice()));
        modifyPartMax.setText(Integer.toString(allParts.get(partIndex).getMax()));
        modifyPartMin.setText(Integer.toString(allParts.get(partIndex).getMin()));
        
        ToggleGroup group = new ToggleGroup();
            inHouse.setToggleGroup(group);
            outsourced.setToggleGroup(group);
            
        String classType = allParts.get(partIndex).getClass().getSimpleName().trim();
        
        if (classType.equals("InHouse")) {
            InHouse inHousePart = (InHouse) allParts.get(partIndex);
            System.out.println("InHouse 123");
            this.inHouse.setSelected(true);
            modifyPartMachineIdLabel.setVisible(true);
            modifyPartMachineId.setVisible(true);
            modifyPartMachineId.setText(Integer.toString(inHousePart.getMachineId()));
              
        }
        else if (classType.equals("Outsourced"))  {
//            this.outsourcedAction(event);
            Outsourced outsourcedPart = (Outsourced) allParts.get(partIndex);
            System.out.println("Outsourced 123");
            outsourced.setSelected(true);
            modifyPartCompanyNameLabel.setVisible(true);
            modifyPartCompanyName.setVisible(true);
            modifyPartCompanyName.setText(outsourcedPart.getCompanyName());
        }
        System.out.println("******");
        
    }
    
}
