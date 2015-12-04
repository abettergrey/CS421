package dpp.login;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
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
    public List<User> authenticate(String username, String password)
    {      
        List<User> tmpUser = new ArrayList();
        
        String tmpPassword = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
       
        Session session = HibernateUtil.getSessionFactory().openSession();
                   
        try 
        {
            Transaction trans = session.beginTransaction();
         
            String strquery = "select * from user u where username = ? AND password = ?";
            SQLQuery query = session.createSQLQuery(strquery);
            query.addEntity(User.class);
            query.setString(0, username);
            query.setString(1, tmpPassword);
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
