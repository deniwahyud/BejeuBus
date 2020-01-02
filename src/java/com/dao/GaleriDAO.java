/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import com.model.pojo.Galeri;  
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 *
 * @author arfan
 */
public class GaleriDAO {
    
    private Galeri event;  
    private Galeri newevent;  
    private List < Galeri > DaoAllGaleri;  
    private List < Galeri > DaoSearchGaleriList;  
    //Session session;  
    public List < Galeri > AllGaleri()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllGaleri = session.createCriteria(Galeri.class).list();  
            int count = DaoAllGaleri.size();  
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
        return DaoAllGaleri;  
    }  
    public Integer getGaleriID()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idGaleri) from Galeri U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer GaleriID = 1;  
        if (results.get(0) != null)  
        {  
            GaleriID = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return GaleriID;  
    }  
    public List < Galeri > SearchByGaleri(String Galeri)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Galeri > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Galeri U where U.namaGaleri =:namaGaleri"); //User is the entity not the table  
            qu.setParameter("Galeri", Galeri);  
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
    
    private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private static Transaction tx;

	public void addNewService(Galeri galeri) {
		try {
			Session session = sf.openSession();
			tx = session.beginTransaction();
			session.save(galeri);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
                }
	}
    public void add(Galeri newgaleri)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newgaleri.getIdGambar());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newgaleri);  
            session.flush();  
            System.out.println("NewGaleri saved, id: " +  
                newgaleri.getIdGambar());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Galeri galeri)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String events = galeri.getCaption();  
            session.beginTransaction();  
            session.delete(galeri);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Galeri galeri)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(galeri);  
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
