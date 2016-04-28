/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpp.dbClasses;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.util.Random;

/**
 *  User class used to map to user table
 *  also contains method the generate username
 *  passwords are generated along with an encrypted password
 *  contains a method for comparing passwords
 */
public class User 
{
    private int id;             // user id
    private String username;    // users username
    private String password;    // users password
    private String role;        // users role
    
    private String tmppassword;
    
    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTmppassword() {
        return tmppassword;
    }

    public void setTmppassword(String tmppassword) {
        this.tmppassword = tmppassword;
    }

    /**
     * return generated username
     * @param firstName is the patient first name
     * @param middle is the patient middle name
     * @param lastName is the patient last name
     * @return String
     * note: user name is concatenated from firstName, middle and lastName 
     */
    public String generateUsername(String firstName, String middle, String lastName)
    {
        String firstInitial = "" + firstName.toLowerCase().charAt(0);
        String middleInitial = middle.toLowerCase();
        
        return firstInitial + middleInitial + lastName.toLowerCase();
    }

    /**
     * returns a generated temp password 
     * @return String
     * note: call encryptPassword to return encrpted password
     */
    public String generatePassword()
    {
        final String code = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghujklmnopqrstuvwxyz";
        Random rnd = new Random();
        int len = 9;
        
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ ) 
            sb.append( code.charAt( rnd.nextInt(code.length()) ) );
        
        tmppassword = sb.toString();
        
        return encryptPassword(tmppassword);
    }
    
    /**
     * return the generated encrypted password
     * @param password
     * @return String
     */
    private String encryptPassword(String password)
    {
        return Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
    }
    
    /**
     * return true if passwords are equal
     * @param password
     * @return boolean
     */
    public boolean comparePassword(String password)
    {
        return password.equals(encryptPassword(this.password));
    }
}
