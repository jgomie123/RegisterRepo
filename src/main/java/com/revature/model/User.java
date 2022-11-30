package com.revature.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "users") // schema = "springdata"
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@Column(name = "user_id", nullable = false, unique = true, updatable = false)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ JsonViewProfiles.User.class, JsonViewProfiles.Address.class })
	private int id;

	@Length(min = 1) // hibernate specific
	private String firstName;
	private String lastName;

	@Length(min = 5)
	@NotBlank // this comes From java EE from javax.val...
	@Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*") // using regex to make sure that only alphanumeric characters are allowed,
												// no spaces allowed
	private String username;

	@NotEmpty
	private String password;

	@Email // checks for @ and .com
	private String email;
	
    @ManyToMany
	@JoinTable(name = "users_address",
    joinColumns = @JoinColumn(name= "user_id"),
    inverseJoinColumns = @JoinColumn(name = "address_id"))
    @JsonView(JsonViewProfiles.User.class)
	private Set<Address> addresses;
    
    public User() {
    	
    }

	public User(@Length(min = 1) String firstName, String lastName,
			@Length(min = 5) @NotBlank @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*") String username,
			@NotEmpty String password, @Email String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Set<Address> getAddresses() {
		return addresses;
	}



	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	
	
}
