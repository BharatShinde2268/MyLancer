package com.MyLancer.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message="Name fields is Required !!")
	//@Size(min = 2,max = 100,message = "minimun 2 and maximum 100 charctor are used..")
	private String name;
	
	// email consider as a userName
	//@NotNull(message="Email fields is Required")
	// @Email(regexp = "[A-Za-z0-9-]+@[A-Za-z0-9_-]+$")
	
	private String email;
	
	//@Size(min=2,max=50,message = "Minimun 2 Charctor are allwed and Max charctore are allower 50")
	
	@NotBlank(message="Password fields is Required !!")
	private String password;
	
	@NotBlank(message="About fields is Required !!")
	private String about;
	
	private String role;
	
	@NotBlank(message="Phone fields is Required !!")
	private String phone;
	
	
	private String address;
	
	
	private String imageUrl;
	
	private boolean enabled;
	
	
	
	public User() {
		super();
		
	}
	public User(int id, String name, String email, String password, String about, String role, String phone,
			String address,String imageUrl, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.role = role;
		this.phone = phone;
		this.address = address;
		
		this.imageUrl = imageUrl;
		this.enabled = enabled;
		
		
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
				+ ", role=" + role + ", phone=" + phone + ", address=" + address + "]";
	}
	
	
	
	

}
