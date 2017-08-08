package com.mapping.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Seller extends User {
	
	@OneToOne(optional=false, cascade= {CascadeType.MERGE})
	@MapsId
	@JoinColumn(nullable=false)
	private ExternalAccount externalAccount;

	public ExternalAccount getExternalAccount() {
		return externalAccount;
	}

	public void setExternalAccount(ExternalAccount externalAccount) {
		this.externalAccount = externalAccount;
	}
	
}
