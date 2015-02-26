package com.volcanoind.challenge.businesswars;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.volcanoind.challenge.businesswars.domain.LineItem;
import com.volcanoind.challenge.businesswars.domain.Product;
import com.volcanoind.challenge.businesswars.domain.TxRequest;
import com.volcanoind.challenge.businesswars.exceptions.ProductNotFoundException;

public class InventoryManagerTest {
	private static final Logger log = LoggerFactory.getLogger( InventoryManagerTest.class );

	private InventoryManagerTester mgr;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		mgr = new InventoryManagerTester( "src/test/resources/products.csv" );
		mgr.init();
	}

	@Test
	public void loadedData() throws IOException {
		Collection<Product> products = mgr.getProducts();
		for ( Product pdct : products ) {
			log.info( "{}", pdct.toString() );
		}
		
		assertTrue( products.size() > 0 );
	}
	
	@Test( expected = ProductNotFoundException.class )
	public void noSuchProduct() {		
		mgr.buyItem( new TxRequest( true, new BigDecimal( "11.99" ), Arrays.asList( new LineItem[] { new LineItem( "ABC", null, null, 0, null ) } ) ) );
	}
	
//	@Test
//	public void priceMisMatch() {
//		thrown.expect( PriceMismatchException.class );
//		thrown.expectMessage( "Price mismatch. Mine: 11.99, Yours: 1.23" );
//		
//		mgr.buyItem( new TxRequest( "1234", 1, new BigDecimal( "1.23" ), true ) );
//	}
//	
//	@Test
//	public void buyABattery() {
//		Purchase purchase = mgr.buyItem( new TxRequest( "1234", 1, new BigDecimal( "11.99" ), true ) );
//		assertEquals( 1, purchase.getQtyPurchased() );
//	}
//	
//	@Test
//	public void buyMoreThanAvailibleRequired() {
//		thrown.expect( InsufficientStockException.class );
//		thrown.expectMessage( "Insufficient Stock to fulfill request. Available: 0, Requested: 1" );
//		
//		Purchase purchase = mgr.buyItem( new TxRequest( "1234", 1, new BigDecimal( "11.99" ), true ) );
//		assertEquals( 1, purchase.getQtyPurchased() );
//		purchase = mgr.buyItem( new TxRequest( "1234", 1, new BigDecimal( "11.99" ), true ) );
//	}
//	
//	@Test
//	public void buyMoreThanAvailibleOptional() {
//		Purchase purchase = mgr.buyItem( new TxRequest( "1234", 1, new BigDecimal( "11.99" ), true ) );
//		assertEquals( 1, purchase.getQtyPurchased() );
//		purchase = mgr.buyItem( new TxRequest( "1234", 1, new BigDecimal( "11.99" ), false ) );
//		assertEquals( 0, purchase.getQtyPurchased() );
//	}
//	
//	@Test
//	public void buyMoreThanAvailiblePartial() {
//		Purchase purchase = mgr.buyItem( new TxRequest( "6061184", 3, new BigDecimal( "274.95" ), false ) );
//		assertEquals( 2, purchase.getQtyPurchased() );
//	}

	// Testing helper ---------------------------------------------------------------------------------------
	private class InventoryManagerTester extends InventoryManager {
		InventoryManagerTester(String dataPath) {
			this.dataPath = dataPath;
		}		
	}
}
