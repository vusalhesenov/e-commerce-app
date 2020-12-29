/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author User
 */
public class ProductType {
    
    private Long id;
    private String name;
    private Integer isActive;

    public ProductType() {
    }

    public ProductType(Long id, String name, Integer isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "ProductType{" + "id=" + id + ", name=" + name + ", isActive=" + isActive + '}';
    }
    
}
