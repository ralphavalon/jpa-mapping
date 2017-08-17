package com.mapping.jpa.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapping.jpa.model.Buyer;

@Service
public class BuyerService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Buyer save(Buyer buyer) {
		return entityManager.merge(buyer);
	}

}
