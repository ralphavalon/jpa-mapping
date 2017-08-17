package com.mapping.jpa.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapping.jpa.model.Seller;

@Service
public class SellerService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Seller save(Seller seller) {
		return entityManager.merge(seller);
	}

}
