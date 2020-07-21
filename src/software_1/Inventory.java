/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_1;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author eric
 */
public class Inventory {
    
    public static ObservableList<Part> allParts = FXCollections.observableArrayList(); 
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList(); 
    
    
    Inventory() {
        allParts.add(new InHouse("screw", .05, 25, 1000, 100, 1));
        allParts.add(new Outsourced("bolt", 1.05, 10, 20, 32, "IBM"));
        allParts.add(new Outsourced("nail", 2.00, 4, 16, 1, "Home Depot"));
            
    }
    
    public void addPart(Part part) {
        allParts.add(part);
    };
    
    public void addProduct(Product product) {
        allProducts.add(product);
    };
    
    
    public List getAllParts() {
        List results = new ArrayList();
        for (Part element: allParts) {
            results.add(element);
        }
        return results;
    };
    
}
