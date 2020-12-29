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
public class Transcation {
    
    private Long id;
    private Long userAccuntId;
    private Long storeAccountId;
    private Integer type;
    
    public Transcation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserAccuntId() {
        return userAccuntId;
    }

    public void setUserAccuntId(Long userAccuntId) {
        this.userAccuntId = userAccuntId;
    }

    public Long getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Long storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transcation{" + "id=" + id + ", userAccuntId=" + userAccuntId + ", storeAccountId=" + storeAccountId + ", type=" + type + '}';
    }
}
