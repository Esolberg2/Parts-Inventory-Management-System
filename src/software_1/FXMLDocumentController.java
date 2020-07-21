/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_1;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    
    private FilteredList<Part> filteredParts = new FilteredList<>(allParts, s -> true);
    private FilteredList<Product> filteredProducts = new FilteredList<>(allProducts, s -> true);

    
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
    
    @FXML private TextField mainProductSearch;
    @FXML private TextField mainPartSearch;
    
    @FXML
    private void searchParts(ActionEvent event) throws IOException {
        String substring = mainPartSearch.getText();
        filteredParts.setPredicate(
            new Predicate<Part>(){
                public boolean test(Part t){
                    Boolean flag = Integer.toString(t.getId()).contains(substring) || t.getName().contains(substring);
                    return flag;
                }
            }
         );
    }
    
    @FXML void exit(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML void deletePart(ActionEvent event) {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            Alert alert = new Alert(AlertType.WARNING, 
                            "Are you sure you would like to delete this part?", 
                            ButtonType.NO, ButtonType.YES);

            Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES){
                    allParts.remove(selectedPart);
                }
        }
    }
    
    @FXML void deleteProduct(ActionEvent event) {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            Alert alert = new Alert(AlertType.WARNING, 
                        "Are you sure you would like to delete this product?", 
                        ButtonType.NO, ButtonType.YES);
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES){
                allProducts.remove(selectedProduct);
            }
        }
    }
    
    @FXML
    private void searchProducts(ActionEvent event) throws IOException {
        String substring = mainProductSearch.getText();
        filteredProducts.setPredicate(
            new Predicate<Product>(){
                public boolean test(Product t){
                    Boolean flag = Integer.toString(t.getId()).contains(substring) || t.getName().contains(substring);
                    return flag;
                }
            }
         );
    }
    
    
    @FXML
    private void navToScene(ActionEvent event) throws IOException {
        Part selected = partTableView.getSelectionModel().getSelectedItem();
        int partIndex = allParts.indexOf(selected);
        
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();


      
        String id = ((Button)event.getSource()).getId();
        Parent parent;
        switch(id) {
            case "addPartBtn":
                System.out.println("Nav to add part");
                parent = FXMLLoader.load(getClass().getResource("AddPartView.fxml"));
              break;
            case "modifyPartBtn":
                ModifyPartViewController.partIndex = partIndex;
                parent = FXMLLoader.load(getClass().getResource("ModifyPartView.fxml"));

              break;
            case "addProductBtn":
                parent = FXMLLoader.load(getClass().getResource("AddProductView.fxml"));
             break;
            case "modifyProductBtn":
                ModifyProductViewController.product = selectedProduct;
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
        
        partId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partStock.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
          

        partTableView.setItems(filteredParts);
        
        
        
        productId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        productTableView.setItems(filteredProducts);
    }
    
}
