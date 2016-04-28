/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpp.patient.maintenance;

import dpp.dbClasses.Person;
import dpp.dbClasses.Patient;
import dpp.login.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import dpp.dbClasses.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import static java.lang.Math.toIntExact;
/**
 * database manager class for patient info
 */
public class PatientMaintananceAppDB 
{
    public boolean searchForPatient(String searchField)
    {
        boolean valid = false;
        
        List<Person> person = new ArrayList();
        
        // create session
        Session session = HibernateUtil.getSessionFactory().openSession();
      
        try 
        {
            Transaction trans = session.beginTransaction();
            
            // search database for username
            String hql = "FROM  P person WHERE P.ssn = :searchField";
            Query query = session.createQuery(hql);
            query.setParameter("ssn", searchField);
            person = query.list();
            if(!person.isEmpty()) valid = true;
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
        
        return valid;
                
    }
    
    public int countRows(User user)
    {
        int count = 0;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
             
        try 
        {
            
            long n = (Long)(session.createQuery("select count(*) from User").uniqueResult());
            Transaction trans = session.beginTransaction();
            trans.commit();
            count = toIntExact(n);
        }
        catch(HibernateException e)
        {
            e.printStackTrace();//Later remove this by appropriate logger statement or throw custom exception
        }
        finally
        {
            session.close(); 
        }
        
        
        return count;
    }
    
    /**
     * saves new patient info to DB
     * @param patient
     * @param user 
     */
    public void createNewPatient(Patient patient, User user)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
       
        Person person = patient.getPerson();
        
        try 
        {
            Transaction trans = session.beginTransaction();
            session.save(person);
            session.save(patient);
            session.save(user);
            session.flush();
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
    }
}
