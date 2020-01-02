/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import java.util.List;  
import java.util.ArrayList;  
import org.hibernate.Query;  
import org.hibernate.Session;  
import com.util.HibernateUtil;  
import com.model.pojo.Bis;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/**
 *
 * @author FACULTY
 */
public class BisDAO  
{  
    private Bis event;  
    private Bis newevent;  
    private List < Bis > DaoAllBis;  
    private List < Bis > DaoSearchBisList;  
    //Session session;  
    public List < Bis > AllBis()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllBis = session.createCriteria(Bis.class).list();  
            int count = DaoAllBis.size();  
            // FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "List Size", Integer.toString(count));//Debugging Purpose  
            //RequestContext.getCurrentInstance().showMessageInDialog(message1);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
        return DaoAllBis;  
    }  
    public Integer getBisID()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idBis) from Bis U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer BisID = 1;  
        if (results.get(0) != null)  
        {  
            BisID = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return BisID;  
    }  
    public List < Bis > SearchByBis(String Bis)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Bis > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Bis U where U.namaBis =:namaBis"); //User is the entity not the table  
            qu.setParameter("Bis", Bis);  
            daoSearchList = qu.list();  
            int count = daoSearchList.size();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        finally  
        {  
            session.close();  
        }  
        return daoSearchList;  
    }  
    public void add(Bis newbis)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newbis.getIdBis());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newbis);  
            session.flush();  
            System.out.println("NewBis saved, id: " +  
                newbis.getIdBis());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Bis bis)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String events = bis.getNamaBis();  
            session.beginTransaction();  
            session.delete(bis);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Bis bis)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(bis);  
            session.flush();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
} 
