package com.xgen.interview;

import java.util.*;

public class ShoppingCart implements IShoppingCart {

	ArrayList<Entry> cartContents = new ArrayList<Entry>();
	Pricer pricer;
	Receipt receipt;

	public ShoppingCart(Pricer pricer, String branch) {
		this.pricer = pricer;
		this.receipt = new Receipt(branch);
	}

	public void addItem(String itemType, int number) {
		if(number>0) {
			Float price = Float.valueOf(pricer.getPrice(itemType) * number);
			Entry entry = new Entry(itemType, number, price);
			cartContents.add(entry);
		}
	}

	public void printReceipt() {
		this.receipt.printAndSaveReceipt(this.cartContents);
	}
}