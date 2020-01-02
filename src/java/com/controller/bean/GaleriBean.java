/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.GaleriDAO;  
import com.model.pojo.Galeri;  
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
  
public class GaleriBean implements Serializable  
{  
    private List < Galeri > usersList;  
    private List < Galeri > searchList;  
    private List < Galeri > searchByRecordNoList;  
    GaleriDAO userDao = new GaleriDAO();  
    Galeri user = new Galeri();  
    Galeri newuser = new Galeri();  
    public List < Galeri > getUsers()  
    {  
        usersList = userDao.AllGaleri();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addGaleri()  
    {  
       
        Integer GaleriID = 0;  
        GaleriID = userDao.getGaleriID();  
        newuser.setIdGambar(GaleriID);  
        String Id = Integer.toString(newuser.getIdGambar());    
        userDao.add(newuser);  
        System.out.println("Galeri successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Galeri();  
    }  
    public void changeUser(Galeri galeri)  
    {  
        this.user = galeri;  
    }  
    public void UpdateUser(Galeri galeri)  
    {  
        String namaGaleri = galeri.getCaption();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Galeri", namaGaleri);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Galeri();  
    }  
    public void deleteUser(Galeri user)  
    {  
        String Galeri = user.getCaption();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyGaleri()  
    {  
        searchByRecordNoList = userDao.SearchByGaleri(user.getCaption());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    
    public void Login()
    {
        
    }
    
    public Galeri getUser()  
    {  
        return user;  
    }  
    public void setUser(Galeri user)  
    {  
        this.user = user;  
    }  
    public Galeri getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Galeri newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Galeri > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Galeri > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Galeri > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Galeri > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Galeri > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Galeri > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent galeri)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Galeri) galeri.getObject()).getCaption());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Galeri editeduser = (Galeri) galeri.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent galeri)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Galeri) galeri.getObject());  
    }  
} 
