package com.mapping.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Seller extends User {
	
	@OneToOne(optional=false, cascade= {CascadeType.MERGE})
	@JoinColumn(nullable=false, unique=true)
	private ExternalAccount externalAccount;

	public ExternalAccount getExternalAccount() {
		return externalAccount;
	}

	public void setExternalAccount(ExternalAccount externalAccount) {
		this.externalAccount = externalAccount;
	}
	
}
