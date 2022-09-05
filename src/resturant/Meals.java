/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant;

/**
 *
 * @author Hossam Asaad
 */
public class Meals {
    private int id;
    private String name;
    private String type;
    private  float cost;
    public Meals(int id, String name, String type, float cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public float getCost() {
        return cost;
    }
    
}
