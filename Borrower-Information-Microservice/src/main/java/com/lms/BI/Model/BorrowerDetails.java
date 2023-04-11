package com.lms.BI.Model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BorrowerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne(cascade = CascadeType.ALL)
    private PersonalInformation personalInformation;

    @Column(name = "username", unique = true)
    private String uname;

    private String password;

    @Column(name = "Phone Number")
    private String phoneno;

    @Column(name = "Email Id", unique = true)
    private String emailId;

    @OneToOne(cascade = CascadeType.ALL)
    private Address borrowerAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "borrower_id", referencedColumnName = "id")
    private Set<Role> roles;
    
    private boolean enabled = false;

	public BorrowerDetails(PersonalInformation personalInformation, String uname, String password, String phoneno,
			String emailId, Address borrowerAddress, Set<Role> roles, boolean enabled) {
		super();
		this.personalInformation = personalInformation;
		this.uname = uname;
		this.password = password;
		this.phoneno = phoneno;
		this.emailId = emailId;
		this.borrowerAddress = borrowerAddress;
		this.roles = roles;
		this.enabled = enabled;
	}
}
