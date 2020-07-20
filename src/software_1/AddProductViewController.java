/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static software_1.Inventory.allParts;
import static software_1.Inventory.allProducts;

/**
 * FXML Controller class
 *
 * @author eric
 */
public class AddProductViewController implements Initializable {
    
    private ObservableList<Part> stagedParts = FXCollections.observableArrayList();
    
    @FXML private TextField addProductId;
    @FXML private TextField addProductName;
    @FXML private TextField addProductInv;
    @FXML private TextField addProductPrice;
    @FXML private TextField addProductMax;
    @FXML private TextField addProductMin;
    
    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partId;
    @FXML private TableColumn<Part, String> partName;
    @FXML private TableColumn<Part, Integer> partStock;
    @FXML private TableColumn<Part, Double> partPrice;
    
    @FXML private TableView<Part> stagedPartTableView;
    @FXML private TableColumn<Part, Integer> stagedPartId;
    @FXML private TableColumn<Part, String> stagedPartName;
    @FXML private TableColumn<Part, Integer> stagedPartStock;
    @FXML private TableColumn<Part, Double> stagedPartPrice;
    
    
    @FXML
    private void cancel(ActionEvent event) throws IOException {
//        String id = ((Button)event.getSource()).getId();
        Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene main = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(main);
        window.show();
    }
    
    
    @FXML
    private void save(ActionEvent event) throws IOException {
//        Part selected = partTableView.getSelectionModel().getSelectedItem();
        
        System.out.println(addProductName.getText());
                System.out.println(addProductName.getText());
                System.out.println(addProductInv.getText());
                System.out.println(addProductPrice.getText());
                System.out.println(addProductMax.getText());
                System.out.println(addProductMin.getText());
    //            allParts.add(new Outsourced("imported bolt", 1.05, 10, 20, 32, "IBM"));
        allProducts.add(new Product(
                addProductName.getText(),
                Integer.parseInt(addProductInv.getText()),
                Integer.parseInt(addProductPrice.getText()),
                Integer.parseInt(addProductMax.getText()),
                Integer.parseInt(addProductMin.getText()),
                stagedParts
        ));
        
        Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene main = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(main);
        window.show();
                
    }
    
    @FXML
    private void add(ActionEvent event) throws IOException {
        Part selected = partTableView.getSelectionModel().getSelectedItem();
        if (selected != null && !stagedParts.contains(selected)) {
            stagedParts.add(selected);
        }
        System.out.println(selected);
        System.out.println(stagedParts);      
    }
    
    @FXML
    private void delete(ActionEvent event) throws IOException {
        Part selected = stagedPartTableView.getSelectionModel().getSelectedItem();
        if (selected != null && stagedParts.contains(selected)) {
            stagedParts.remove(selected);
        }
        System.out.println(selected);
        System.out.println(stagedParts);      
    }
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addProductId.setText(Integer.toString(Product.getNextId()));
        System.out.println("******");

        System.out.println(stagedParts.size());
        
        partId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partStock.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
          
        partTableView.setItems(allParts);
        
        stagedPartId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        stagedPartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        stagedPartStock.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        stagedPartPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
          
        stagedPartTableView.setItems(stagedParts);
    }    
    
}
