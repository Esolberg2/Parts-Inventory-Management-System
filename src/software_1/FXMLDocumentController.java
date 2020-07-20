/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_1;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static software_1.Inventory.allParts;
import static software_1.Inventory.allProducts;
import static software_1.Software_1.partsInventory;


/**
 *
 * @author eric
 */
public class FXMLDocumentController implements Initializable {
    
    public FXMLDocumentController() {
    }
    
    @FXML
    private Label label;
    
    @FXML
    private Text actionTarget;
    
    @FXML
    private Button addPartBtn;
    
    @FXML
    private Button modifyPartBtn;
    
    @FXML
    private Button addProductBtn;
    
    @FXML
    private Button modifyProductBtn;
   
    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partId;
    @FXML private TableColumn<Part, String> partName;
    @FXML private TableColumn<Part, Integer> partStock;
    @FXML private TableColumn<Part, Double> partPrice;
    
    @FXML private TableView<Product> productTableView;
    @FXML private TableColumn<Product, Integer> productId;
    @FXML private TableColumn<Product, String> productName;
    @FXML private TableColumn<Product, Integer> productStock;
    @FXML private TableColumn<Product, Double> productPrice;
    
    
    @FXML
    private void navToScene(ActionEvent event) throws IOException {
      
        String id = ((Button)event.getSource()).getId();
        Parent parent;
        switch(id) {
            case "addPartBtn":
                System.out.println("Nav to add part");
                parent = FXMLLoader.load(getClass().getResource("AddPartView.fxml"));
              break;
            case "modifyPartBtn":
                parent = FXMLLoader.load(getClass().getResource("ModifyPartView.fxml"));
              break;
            case "addProductBtn":
                parent = FXMLLoader.load(getClass().getResource("AddProductView.fxml"));
             break;
            case "modifyProductBtn":
                parent = FXMLLoader.load(getClass().getResource("ModifyProductView.fxml"));
             break;
            default:
                System.out.println("no scene found by that name");
                parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
          }
        
        Scene addPartScene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("from initializer");
        partId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partStock.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
          

//        tableView.getItems().setAll(parseUserList());
        partTableView.setItems(allParts);
        
        
        
        System.out.println("from initializer");
        productId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        productTableView.setItems(allProducts);
    }
    
    private List<Part> parseUserList(){
        List returnList = partsInventory.getAllParts();
        return returnList;
    }
    
}
