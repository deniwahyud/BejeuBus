/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.BisDAO;  
import com.model.pojo.Bis;  
import java.io.Serializable;  
import org.primefaces.event.RowEditEvent;  
import javax.faces.context.FacesContext;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext; 

/**
 *
 * @author FACULTY
 */
@ManagedBean
@ViewScoped
  
public class BisBean implements Serializable  
{  
    private List < Bis > usersList;  
    private List < Bis > searchList;  
    private List < Bis > searchByRecordNoList;  
    BisDAO userDao = new BisDAO();  
    Bis user = new Bis();  
    Bis newuser = new Bis();  
    public List < Bis > getUsers()  
    {  
        usersList = userDao.AllBis();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addBis()  
    {  
       
        Integer BisID = 0;  
        BisID = userDao.getBisID();  
        newuser.setIdBis(BisID);  
        String Id = Integer.toString(newuser.getIdBis());    
        userDao.add(newuser);  
        System.out.println("Bis successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Bis();  
    }  
    public void changeUser(Bis bis)  
    {  
        this.user = bis;  
    }  
    public void UpdateUser(Bis bis)  
    {  
        String namaBis = bis.getNamaBis();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bis", namaBis);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Bis();  
    }  
    public void deleteUser(Bis user)  
    {  
        String Bis = user.getNamaBis();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyBis()  
    {  
        searchByRecordNoList = userDao.SearchByBis(user.getNamaBis());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    
    public void Login()
    {
        
    }
    
    public Bis getUser()  
    {  
        return user;  
    }  
    public void setUser(Bis user)  
    {  
        this.user = user;  
    }  
    public Bis getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Bis newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Bis > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Bis > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Bis > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Bis > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Bis > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Bis > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent bis)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Bis) bis.getObject()).getNamaBis());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Bis editeduser = (Bis) bis.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent bis)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Bis) bis.getObject());  
    }  
} 
