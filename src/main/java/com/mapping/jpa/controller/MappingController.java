package com.mapping.jpa.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.jpa.dao.BuyerDao;
import com.mapping.jpa.dao.SellerDao;
import com.mapping.jpa.model.Buyer;
import com.mapping.jpa.model.ExternalAccount;
import com.mapping.jpa.model.Seller;

@RestController
public class MappingController {
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private BuyerDao buyerDao;
	
	//Creating buyer example
	@RequestMapping(value = "/newBuyer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object newBuyer() {
		Buyer buyer = new Buyer();
		buyer.setBirthdate(LocalDateTime.now());
		buyer.setEmail("buyer@email.com");
		buyer.setName("Buyer Name");
		ExternalAccount external = new ExternalAccount();
		external.setId(123L);
		buyer.setExternalAccount(external);
		buyerDao.save(buyer);
		return buyer;
	}
	
	//Creating seller example
	@RequestMapping(value = "/newSeller", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object newSeller() {
		Seller seller = new Seller();
		seller.setBirthdate(LocalDateTime.now());
		seller.setEmail("seller@email.com");
		seller.setName("Seller Name");
		ExternalAccount external = new ExternalAccount();
		external.setId(123L);
		seller.setExternalAccount(external);
		sellerDao.save(seller);
		return seller;
	}

}
