package com.volcanoind.challenge.businesswars;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.volcanoind.challenge.businesswars.domain.Bid;
import com.volcanoind.challenge.businesswars.domain.Purchase;
import com.volcanoind.challenge.businesswars.domain.StockAvailable;

@Controller
public class BusinessWarsController {
	
	@Autowired
	private InventoryManager invMgr;
	
	@RequestMapping( value = "/products", method = RequestMethod.GET )
	@ResponseBody
	public Collection<StockAvailable> products() {
		return invMgr.getProducts();
	}
	
	@RequestMapping( value = "/inventory/{sku}", method = RequestMethod.GET )
	@ResponseBody
	public StockAvailable inventory(@PathVariable String sku) {
		return invMgr.getQuote( sku );
	}
	
	@RequestMapping( value = "/inventory/{sku}", method = RequestMethod.POST )
	@ResponseBody
	public Purchase buy(@PathVariable int sku, @RequestBody Bid bid) {
		Purchase sale = invMgr.buyItem( bid );
		
		return sale;
	}
	
	@RequestMapping( value = "/purchases", method = RequestMethod.GET )
	@ResponseBody
	public List<Purchase> salesHistory() {
		return invMgr.getSalesHistory();
	}
}
