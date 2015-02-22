package com.volcanoind.challenge.businesswars;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InventoryManagerTests {
	private InventoryManager mgr;

	@Before
	public void setUp() throws Exception {
		mgr = new InventoryManagerTester();
	}

	@Test
	public void loadData() {
		mgr = new InventoryManagerTester( "../resources/products.csv" );
	}

	private class InventoryManagerTester extends InventoryManager {
		InventoryManagerTester() {
		}
		
		InventoryManagerTester(String dataPath) {
			this.dataPath = dataPath;
		}
	}
}
