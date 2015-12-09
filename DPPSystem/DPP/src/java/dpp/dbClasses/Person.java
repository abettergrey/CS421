package dpp.dbClasses;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * person class used to map table person
 */
public class Person 
{
    private int id;
    private String firstName;
    private String lastName;
    private String middle;
    private String ssn;
    private String email;
    private String address;
    private String apt;
    private String city;
    private String state;
    private String zip;
    private String birthDate;
    private String phone;
    private String mobile;

    public Person()
    {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public Person getPerson()
    {
        return this;
    }
    
    public String getFullName()
    {
        return firstName + " " + middle + " " + lastName;
    }
    
    public boolean isEmailValid()
    {
        boolean result = true;
        try 
        {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) 
        {
            result = false;
        }
        return result;
    }
    
    public boolean compareEmail(String email)
    {
        return email.equals(this.email);
    }
            
}
