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
/**
 * database manager class for patient info
 */
public class PatientMaintananceAppDB 
{
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
