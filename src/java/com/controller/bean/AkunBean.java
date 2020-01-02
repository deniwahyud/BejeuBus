/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.AkunDAO;  
import com.model.pojo.Akun;  
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
  
public class AkunBean implements Serializable  
{  
    private List < Akun > usersList;  
    private List < Akun > searchList;  
    private List < Akun > searchByRecordNoList;  
    AkunDAO userDao = new AkunDAO();  
    Akun user = new Akun();  
    Akun newuser = new Akun();  
    public List < Akun > getUsers()  
    {  
        usersList = userDao.AllAkun();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addAkun()  
    {  
       
        Integer AkunID = 0;  
        AkunID = userDao.getAkunID();  
        newuser.setIdAkun(AkunID);  
        String Id = Integer.toString(newuser.getIdAkun());    
        userDao.add(newuser);  
        System.out.println("Akun successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Akun();  
    }  
    public void changeUser(Akun akun)  
    {  
        this.user = akun;  
    }  
    public void UpdateUser(Akun akun)  
    {  
        String namaAkun = akun.getNama();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Akun", namaAkun);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Akun();  
    }  
    public void deleteUser(Akun user)  
    {  
        String Akun = user.getNama();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyAkun()  
    {  
        searchByRecordNoList = userDao.SearchByAkun(user.getNama());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    
    public void Login()
    {
        
    }
    
    public Akun getUser()  
    {  
        return user;  
    }  
    public void setUser(Akun user)  
    {  
        this.user = user;  
    }  
    public Akun getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Akun newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Akun > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Akun > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Akun > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Akun > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Akun > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Akun > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent akun)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Akun) akun.getObject()).getNama());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Akun editeduser = (Akun) akun.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent akun)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Akun) akun.getObject());  
    }  
} 
