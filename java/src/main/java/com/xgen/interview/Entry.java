package com.xgen.interview;

public class Entry {

	String item;
	int quantity;
	Float price;
	String priceString;
	
	public Entry(String item, int quantity, float price) {
		this.item = item;
		this.quantity = quantity;
		this.price=Float.valueOf((price/100));
		this.priceString = String.format("€%.2f", this.price);
	}
}
