package com.mapping.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Buyer extends User {
	
	@OneToOne(optional=true, cascade= {CascadeType.MERGE})
	@JoinColumn(nullable=true, unique=true)
	private ExternalAccount externalAccount;

	public ExternalAccount getExternalAccount() {
		return externalAccount;
	}

	public void setExternalAccount(ExternalAccount externalAccount) {
		this.externalAccount = externalAccount;
	}

}
