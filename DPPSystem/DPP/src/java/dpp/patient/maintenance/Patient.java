/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpp.patient.maintenance;

/**
 *  patient class used to map table patient
 */
public class Patient extends Person
{
    private int patient_id;
    private String createdDate;
    private MedicalRecord medRecord;

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
    
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
    public MedicalRecord getMedRecord() {
        return medRecord;
    }

    public void setMedRecord(MedicalRecord medRecord) {
        this.medRecord = medRecord;
    }
}
