/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.JadwalDAO;  
import com.model.pojo.Jadwal;  
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
  
public class JadwalBean implements Serializable  
{  
    private List < Jadwal > usersList;  
    private List < Jadwal > searchList;  
    private List < Jadwal > searchByRecordNoList;  
    JadwalDAO userDao = new JadwalDAO();  
    Jadwal user = new Jadwal();  
    Jadwal newuser = new Jadwal();  
    public List < Jadwal > getUsers()  
    {  
        usersList = userDao.AllJadwal();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addJadwal()  
    {  
       
        Integer JadwalID = 0;  
        JadwalID = userDao.getJadwalID();  
        newuser.setIdJadwal(JadwalID);  
        String Id = Integer.toString(newuser.getIdJadwal());    
        userDao.add(newuser);  
        System.out.println("Jadwal successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Jadwal();  
    }  
    public void changeUser(Jadwal jadwal)  
    {  
        this.user = jadwal;  
    }  
    public void UpdateUser(Jadwal jadwal)  
    {  
        String namaJadwal = jadwal.getSeriBis();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jadwal", namaJadwal);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Jadwal();  
    }  
    public void deleteUser(Jadwal user)  
    {  
        String Jadwal = user.getSeriBis();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyJadwal()  
    {  
        searchByRecordNoList = userDao.SearchByJadwal(user.getSeriBis());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    
    public void Login()
    {
        
    }
    
    public Jadwal getUser()  
    {  
        return user;  
    }  
    public void setUser(Jadwal user)  
    {  
        this.user = user;  
    }  
    public Jadwal getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Jadwal newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Jadwal > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Jadwal > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Jadwal > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Jadwal > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Jadwal > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Jadwal > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent jadwal)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Jadwal) jadwal.getObject()).getSeriBis());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Jadwal editeduser = (Jadwal) jadwal.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent jadwal)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Jadwal) jadwal.getObject());  
    }  
} 
