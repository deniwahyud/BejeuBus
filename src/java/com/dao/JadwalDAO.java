/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import com.model.pojo.Jadwal;  
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author arfan
 */
public class JadwalDAO {
    
    private Jadwal event;  
    private Jadwal newevent;  
    private List < Jadwal > DaoAllJadwal;  
    private List < Jadwal > DaoSearchJadwalList;  
    //Session session;  
    public List < Jadwal > AllJadwal()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllJadwal = session.createCriteria(Jadwal.class).list();  
            int count = DaoAllJadwal.size();  
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
        return DaoAllJadwal;  
    }  
    public Integer getJadwalID()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idJadwal) from Jadwal U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer JadwalID = 1;  
        if (results.get(0) != null)  
        {  
            JadwalID = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return JadwalID;  
    }  
    public List < Jadwal > SearchByJadwal(String Jadwal)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Jadwal > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Jadwal U where U.namaJadwal =:namaJadwal"); //User is the entity not the table  
            qu.setParameter("Jadwal", Jadwal);  
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
    public void add(Jadwal newjadwal)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newjadwal.getIdJadwal());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newjadwal);  
            session.flush();  
            System.out.println("NewJadwal saved, id: " +  
                newjadwal.getIdJadwal());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Jadwal jadwal)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String events = jadwal.getSeriBis();  
            session.beginTransaction();  
            session.delete(jadwal);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Jadwal jadwal)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(jadwal);  
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
