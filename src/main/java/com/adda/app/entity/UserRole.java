package com.adda.app.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class UserRole {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		private Long userRoleId;
	    @ManyToOne(fetch = FetchType.EAGER)
		private User user;
	    @ManyToOne
	    private Role role;
}
