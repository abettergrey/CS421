package dpp.login;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static 
    {
        try 
        {           
            // loads and intitilizes the object Configuration class with the mappings and configuration properties
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            
            // applies the configuration properties
	    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	    
            // carries the configuration settings that are to be applied
            sessionFactory = configuration.buildSessionFactory(builder.build());      
        } 
        catch (Throwable ex) 
        {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() 
    {
        return sessionFactory;
    }
}
