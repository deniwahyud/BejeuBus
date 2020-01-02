/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.SaranDAO;  
import com.model.pojo.Saran;  
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
  
public class SaranBean implements Serializable  
{  
    private List < Saran > usersList;  
    private List < Saran > searchList;  
    private List < Saran > searchByRecordNoList;  
    SaranDAO userDao = new SaranDAO();  
    Saran user = new Saran();  
    Saran newuser = new Saran();  
    public List < Saran > getUsers()  
    {  
        usersList = userDao.AllSaran();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addSaran()  
    {  
       
        Integer SaranID = 0;  
        SaranID = userDao.getSaranID();  
        newuser.setIdSaran(SaranID);  
        String Id = Integer.toString(newuser.getIdSaran());    
        userDao.add(newuser);  
        System.out.println("Saran successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Saran();  
    }  
    public void changeUser(Saran saran)  
    {  
        this.user = saran;  
    }  
    public void UpdateUser(Saran saran)  
    {  
        String namaSaran = saran.getNama();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saran", namaSaran);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Saran();  
    }  
    public void deleteUser(Saran user)  
    {  
        String Saran = user.getNama();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbySaran()  
    {  
        searchByRecordNoList = userDao.SearchBySaran(user.getNama());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    
    public void Login()
    {
        
    }
    
    public Saran getUser()  
    {  
        return user;  
    }  
    public void setUser(Saran user)  
    {  
        this.user = user;  
    }  
    public Saran getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Saran newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Saran > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Saran > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Saran > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Saran > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Saran > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Saran > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent saran)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Saran) saran.getObject()).getNama());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Saran editeduser = (Saran) saran.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent saran)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Saran) saran.getObject());  
    }  
} 
