package models;

import java.time.LocalDate; // Import LocalDate class

public class UserModel {
	 private int userId;
    private String fullName;
    private String email;
    private String address;
    private LocalDate dob;
    private String userName;
    private String gender;
    private String phoneNumber;
    private String password;
    private String userRole;
     
    
    public UserModel(String fullName,String email, LocalDate dob,String address, String userName, String gender,String phoneNumber, String password, String userRole) {
        this.fullName = fullName;
        this.email= email;
        this.address = address;
        this.dob = dob;
        this.userName = userName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userRole = userRole;
    }
    

    public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public UserModel() {
		// TODO Auto-generated constructor stub
	}


	// Getter to read firstName
    public String getUserName() {
        return this.userName;
    }
    
    // Setter to set firstName
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    // Getter to read firstName
    public String getFullName() {
        return this.fullName;
    }
    
    
    // Setter to set firstName
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

 
    
    // Getter to read dob
    public LocalDate getDob() {
        return this.dob;
    }
            
    // Setter to set dob
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
   
    
    // Getter to read email
    public String getEmail() {
        return this.email;
    }
                        
    // Setter to set email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter to read email
    public String getGender() {
        return this.gender;
    }
                        
    // Setter to set email
    public void setGender(String gender) {
        this.gender = gender;
    }

    
    // Getter to read email
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
                        
    // Setter to set email
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    } 
    
    // Getter to readaddress
    public String getAddress() {
        return this.address;
    }
                        
    // Setter to set email
    public void setAddress(String address) {
        this.address = address;
    } 
    
    // Getter to read Password
    public String getPassword() {
        return this.password;
    }
                                
    // Setter to set password
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
 // Getter to read Password
    public String getUserRole() {
        return this.userRole;
    }
                                
    // Setter to set password
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
