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
        allParts.add(new Part(1, "screw", .05, 25, 1000, 100));
        allParts.add(new Part(1, "bolt", 1.05, 10, 20, 32));
        allParts.add(new Part(1, "nail", 2.00, 4, 16, 1));
            
        
        allProducts.add(new Product(1, "screw assm", 1.00, 25, 30, 225));
        allProducts.add(new Product(1, "bolt assm", 1.12, 10, 20, 600));
        allProducts.add(new Product(1, "screen assm", 1.99, 12, 111, 75));
    }
    
    public void addPart(Part part) {
        allParts.add(part);
    };
    
    
    public List getAllParts() {
        List results = new ArrayList();
        for (Part element: allParts) {
            results.add(element);
        }
        return results;
    };

//    public List getAllParts() {
//        List results = new ArrayList();
//        for (Part2 element: allParts) {
//            results.add(element.name);
//        }
//        return results;
//    };
    
}
