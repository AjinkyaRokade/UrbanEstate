package com.app.Entities;

	import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

	@Entity
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@ToString
	@Validated
	public class Buyer extends BaseEntity{
		
		@Column(length = 30, nullable = false)
		private String firstName;
		@Column(length = 30, nullable = false)
		private String lastName;
		@Email(message = "Invalid Email Entered")
		@Column(length = 50, nullable = false, unique = true)
		private String email;
		@Column(length = 50, nullable = false)
		@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "Invalid password entered")
		private String password;
		@Embedded
		private Address address;
		@OneToMany(mappedBy = "buyer", cascade =CascadeType.ALL)
		@JsonProperty(access = Access.WRITE_ONLY)
		private List<Property> properties=new ArrayList<Property>();
		private long mobNo;
		@OneToOne(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
		@JsonProperty(access = Access.WRITE_ONLY)
		private Wishlist wishlist;
		@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<Service_buyer> services;
	}