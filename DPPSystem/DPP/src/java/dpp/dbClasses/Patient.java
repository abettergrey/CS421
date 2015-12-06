/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpp.dbClasses;

import dpp.patient.maintenance.MedicalRecord;
import java.util.Calendar;
import java.util.Date;

/**
 *  patient class used to map table patient
 */
public class Patient extends Person
{
    private int patient_id;
    private String createdDate;
    private MedicalRecord medRecord;

    public Patient()
    {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        createdDate = date.toString();
    }
    
    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
    
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String date) {
        this.createdDate = date;
    }
    
    public MedicalRecord getMedRecord() {
        return medRecord;
    }

    public void setMedRecord(MedicalRecord medRecord) {
        this.medRecord = medRecord;
    }
}
