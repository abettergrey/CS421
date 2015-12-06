package dpp.login;

import dpp.dbClasses.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * database manager class
 */
public class LogAppDB 
{
    /**
     * return the User found in the database that match username and password
     * @param username
     * @param password
     * @return User
     */
    public List<User> authenticate(String username)
    {      
        List<User> tmpUser = new ArrayList();
        
        // create session
        Session session = HibernateUtil.getSessionFactory().openSession();
                   
        try 
        {
            Transaction trans = session.beginTransaction();
            
            // search database for username
            String hql = "FROM User U WHERE U.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            tmpUser = query.list();

            trans.commit();
             
        }
        catch(HibernateException e)
        {
            e.printStackTrace();//Later remove this by appropriate logger statement or throw custom exception
        }
        finally
        {
            session.close(); 
        }
        
        return tmpUser;
    }
}
