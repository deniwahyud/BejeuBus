/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import com.model.pojo.Akun;  
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
public class AkunDAO {
    
    private Akun event;  
    private Akun newevent;  
    private List < Akun > DaoAllAkun;  
    private List < Akun > DaoSearchAkunList;  
    //Session session;  
    public List < Akun > AllAkun()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllAkun = session.createCriteria(Akun.class).list();  
            int count = DaoAllAkun.size();  
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
        return DaoAllAkun;  
    }  
    public Integer getAkunID()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idAkun) from Akun U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer AkunID = 1;  
        if (results.get(0) != null)  
        {  
            AkunID = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return AkunID;  
    }  
    public List < Akun > SearchByAkun(String Akun)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Akun > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Akun U where U.namaAkun =:namaAkun"); //User is the entity not the table  
            qu.setParameter("Akun", Akun);  
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
    public void add(Akun newakun)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newakun.getIdAkun());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newakun);  
            session.flush();  
            System.out.println("NewAkun saved, id: " +  
                newakun.getIdAkun());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Akun akun)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String events = akun.getNama();  
            session.beginTransaction();  
            session.delete(akun);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Akun akun)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(akun);  
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
    
    public boolean validateLogin(Akun akun){
        Transaction transObj = null;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        List<Akun> listakun = new ArrayList<Akun>();
        try{
            transObj = sessionObj.beginTransaction();
            Query query = sessionObj.createQuery("from Akun where  username= :username and password = :password");
            query.setParameter("username", akun.getUsername());
            query.setParameter("password", akun.getPassword());
            listakun = query.list();
            
            transObj.commit();
            System.out.println("Sukses");
        }catch(Exception ex){
            ex.printStackTrace();
            transObj.rollback();
        }finally{
            sessionObj.flush();
            sessionObj.close();
        }
        if(listakun.size()>0){
            return true;
        }else{
            return false;
        }
    }
    

    
}
