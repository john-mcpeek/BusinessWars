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

import com.volcanoind.challenge.businesswars.domain.LineItem;
import com.volcanoind.challenge.businesswars.domain.Product;
import com.volcanoind.challenge.businesswars.domain.Transaction;
import com.volcanoind.challenge.businesswars.domain.TxRequest;

@Controller
public class BusinessWarsController {
	
	@Autowired
	private InventoryManager invMgr;
	
	@RequestMapping( value = "/catalog", method = RequestMethod.GET )
	@ResponseBody
	public Collection<Product> products() {
		return invMgr.getProducts();
	}
	
	@RequestMapping( value = "/inventory", method = RequestMethod.GET )
	@ResponseBody
	public Collection<LineItem> inventory() {
		return invMgr.getStock();
	}
	
	@RequestMapping( value = "/inventory/{sku}", method = RequestMethod.GET )
	@ResponseBody
	public Product inventory(@PathVariable String sku) {
		return invMgr.getStock( sku );
	}
	
	@RequestMapping( value = "/inventory/{sku}", method = RequestMethod.POST )
	@ResponseBody
	public Transaction buy(@PathVariable int sku, @RequestBody TxRequest bid) {
		return invMgr.buyItem( bid );
	}
	
	@RequestMapping( value = "/purchases", method = RequestMethod.GET )
	@ResponseBody
	public List<Transaction> salesHistory() {
		return invMgr.getSalesHistory();
	}
}
