/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import com.model.pojo.Blog;  
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
public class BlogDAO {
    
    private Blog event;  
    private Blog newevent;  
    private List < Blog > DaoAllBlog;  
    private List < Blog > DaoSearchBlogList;  
    //Session session;  
    public List < Blog > AllBlog()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllBlog = session.createCriteria(Blog.class).list();  
            int count = DaoAllBlog.size();  
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
        return DaoAllBlog;  
    }  
    public Integer getBlogID()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idBlog) from Blog U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer BlogID = 1;  
        if (results.get(0) != null)  
        {  
            BlogID = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return BlogID;  
    }  
    public List < Blog > SearchByBlog(String Blog)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Blog > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Blog U where U.namaBlog =:namaBlog"); //User is the entity not the table  
            qu.setParameter("Blog", Blog);  
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

	public void addNewService(Blog blog) {
		try {
			Session session = sf.openSession();
			tx = session.beginTransaction();
			session.save(blog);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
                }
	}
    
    public void add(Blog newblog)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newblog.getIdBlog());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newblog);  
            session.flush();  
            System.out.println("NewBlog saved, id: " +  
                newblog.getIdBlog());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Blog blog)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String events = blog.getJudul();  
            session.beginTransaction();  
            session.delete(blog);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Blog blog)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(blog);  
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
