package com.mapping.jpa.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.mapping.jpa.dao.BuyerDao;
import com.mapping.jpa.dao.SellerDao;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class BuyerSellerTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private BuyerDao buyerDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Test
	public void shouldNotSaveTwoBuyersWithTheSameExternalAccount() {
		buyerDao.save(getFullBuyer());
		
		Buyer savedBuyer = entityManager.find(Buyer.class, 1L);
		ExternalAccount savedExternalAccount = savedBuyer.getExternalAccount();
		assertThat(savedExternalAccount).isNotNull();
		assertThat(savedExternalAccount.getId()).isEqualTo(123L);
		
		expected.expect(Exception.class);
		buyerDao.save(getFullBuyer());
	}
	
	@Test
	public void shouldNotSaveTwoSellersWithTheSameExternalAccount() {
		sellerDao.save(getFullSeller());
		
		Seller savedSeller = entityManager.find(Seller.class, 1L);
		ExternalAccount savedExternalAccount = savedSeller.getExternalAccount();
		assertThat(savedExternalAccount).isNotNull();
		assertThat(savedExternalAccount.getId()).isEqualTo(123L);
		
		expected.expect(Exception.class);
		sellerDao.save(getFullSeller());
	}
	
	@Test
	public void shouldSaveOneBuyerAndOneSellerWithTheSameExternalAccount() {
		buyerDao.save(getFullBuyer());
		
		Buyer savedBuyer = entityManager.find(Buyer.class, 1L);
		ExternalAccount savedExternalAccount = savedBuyer.getExternalAccount();
		assertThat(savedExternalAccount).isNotNull();
		assertThat(savedExternalAccount.getId()).isEqualTo(123L);
		
		sellerDao.save(getFullSeller());
		
		Seller savedSeller = entityManager.find(Seller.class, 1L);
		savedExternalAccount = savedSeller.getExternalAccount();
		assertThat(savedExternalAccount).isNotNull();
		assertThat(savedExternalAccount.getId()).isEqualTo(123L);
	}
	
	@Test
	public void shouldNotSaveTwoBuyersAndOneSellerWithTheSameExternalAccount() {
		buyerDao.save(getFullBuyer());
		
		Buyer savedBuyer = entityManager.find(Buyer.class, 1L);
		ExternalAccount savedExternalAccount = savedBuyer.getExternalAccount();
		assertThat(savedExternalAccount).isNotNull();
		assertThat(savedExternalAccount.getId()).isEqualTo(123L);
		
		sellerDao.save(getFullSeller());
		
		expected.expect(Exception.class);
		buyerDao.save(getFullBuyer());
	}
	
	@Test
	public void shouldNotSaveOneBuyerAndTwoSellersWithTheSameExternalAccount() {
		buyerDao.save(getFullBuyer());
		
		Buyer savedBuyer = entityManager.find(Buyer.class, 1L);
		ExternalAccount savedExternalAccount = savedBuyer.getExternalAccount();
		assertThat(savedExternalAccount).isNotNull();
		assertThat(savedExternalAccount.getId()).isEqualTo(123L);
		
		sellerDao.save(getFullSeller());
		
		expected.expect(Exception.class);
		sellerDao.save(getFullSeller());
	}

	private Buyer getFullBuyer() {
		Buyer buyer = new Buyer();
		buyer.setBirthdate(LocalDateTime.now());
		buyer.setEmail("buyer@email.com");
		buyer.setName("Buyer Name");
		
		ExternalAccount external = new ExternalAccount();
		external.setId(123L);
		buyer.setExternalAccount(external);
		return buyer;
	}
	
	private Seller getFullSeller() {
		Seller seller = new Seller();
		seller.setBirthdate(LocalDateTime.now());
		seller.setEmail("seller@email.com");
		seller.setName("Seller Name");
		
		ExternalAccount external = new ExternalAccount();
		external.setId(123L);
		seller.setExternalAccount(external);
		return seller;
	}

}
