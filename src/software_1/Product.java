/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_1;


//import Software_1.*;
import java.lang.*;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author eric
 */
public class Product {
    
    private ObservableList<Part> associatedParts; // when "add" is pressed, the part from the part list should be added here.
    private int id;
    private static int nextId = 0;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(String name, double price, int stock, int min, int max, ObservableList parts) {
        this.id = nextId;
        this.nextId++;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = parts;
        
        System.out.println("product created");
    }


    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public void addAssociatedParts(Part part) {
        this.associatedParts.add(part);
    }
    
    public void setAssociatedParts(ObservableList parts) {
        this.associatedParts = parts;
    }

    public int getId() {
        return id;
    }
    
    public static int getNextId() {
        return nextId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
 
}
