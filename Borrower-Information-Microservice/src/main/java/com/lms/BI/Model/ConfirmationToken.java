package com.lms.BI.Model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String token;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private LocalDateTime expiresAt;
	
	private LocalDateTime confirmedAt;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BorrowerDetailsId", referencedColumnName = "id")
	private BorrowerDetails borrowerDetails;

	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt,
			BorrowerDetails borrowerDetails) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.borrowerDetails = borrowerDetails;
	}
}

