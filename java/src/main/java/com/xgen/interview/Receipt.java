package com.xgen.interview;

import java.util.*;

public class Receipt {

	ArrayList<Entry> entries;
	Float total;
	String totalAsString;
	String branch;
	int receiptType;
    String fullReceipt;
	
	final int ITEM_FIRST_BRANCH = 0;
	final int PRICE_FIRST_BRANCH = 1;
	
	public Receipt(String branch) {
		this.entries = new ArrayList<Entry>();
		this.total = Float.valueOf(0);
		this.branch = branch;
		if(this.branch.equalsIgnoreCase("Dublin")) {
			this.receiptType = this.ITEM_FIRST_BRANCH;
		}
		else {
			this.receiptType = this.PRICE_FIRST_BRANCH;
		}
        this.fullReceipt="";
	}
	
	public void printAndSaveReceipt(ArrayList<Entry> cartContents) {
		this.entries = cartContents;
        StringBuilder builder = new StringBuilder();
		if(this.receiptType == this.ITEM_FIRST_BRANCH) {
			for(Entry e:this.entries) {
				System.out.println(e.item + " - " + e.quantity + " - " + e.priceString);
                builder.append(e.item + " - " + e.quantity + " - " + e.priceString + "\n");
				this.total+=e.price;
			}
		}
		// branches where customers want prices first
		else {
			for(Entry e:this.entries) {
				System.out.println(e.priceString + " - " + e.quantity + " - " + e.item);
                builder.append(e.priceString + " - " + e.quantity + " - " + e.item + "\n");
				this.total+=e.price;
			}
		}

		this.totalAsString = String.format("€%.2f", this.total);
		System.out.format("%nTotal: " + this.totalAsString);

        builder.append("\nTotal: " + this.totalAsString);
        this.fullReceipt = builder.toString();
	}
}