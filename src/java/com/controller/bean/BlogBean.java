/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.BlogDAO;  
import com.model.pojo.Blog;  
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
  
public class BlogBean implements Serializable  
{  
    private List < Blog > usersList;  
    private List < Blog > searchList;  
    private List < Blog > searchByRecordNoList;  
    BlogDAO userDao = new BlogDAO();  
    Blog user = new Blog();  
    Blog newuser = new Blog();  
    public List < Blog > getUsers()  
    {  
        usersList = userDao.AllBlog();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addBlog()  
    {  
       
        Integer BlogID = 0;  
        BlogID = userDao.getBlogID();  
        newuser.setIdBlog(BlogID);  
        String Id = Integer.toString(newuser.getIdBlog());    
        userDao.add(newuser);  
        System.out.println("Blog successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Blog();  
    }  
    public void changeUser(Blog blog)  
    {  
        this.user = blog;  
    }  
    public void UpdateUser(Blog blog)  
    {  
        String namaBlog = blog.getJudul();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Blog", namaBlog);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Blog();  
    }  
    public void deleteUser(Blog user)  
    {  
        String Blog = user.getJudul();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyBlog()  
    {  
        searchByRecordNoList = userDao.SearchByBlog(user.getJudul());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    
    public void Login()
    {
        
    }
    
    public Blog getUser()  
    {  
        return user;  
    }  
    public void setUser(Blog user)  
    {  
        this.user = user;  
    }  
    public Blog getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Blog newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Blog > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Blog > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Blog > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Blog > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Blog > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Blog > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent blog)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Blog) blog.getObject()).getJudul());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Blog editeduser = (Blog) blog.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent blog)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Blog) blog.getObject());  
    }  
} 
