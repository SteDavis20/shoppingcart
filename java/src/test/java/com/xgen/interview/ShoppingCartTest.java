package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    private final String ITEM_FIRST_BRANCH_NAME = "Dublin";
    private final String PRICE_FIRST_BRANCH_NAME = "AnywhereElse";
	private final int ONE_HUNDRED = 100;

	@Test
	public void canAddAnItemToCart() {
		ShoppingCart sc = new ShoppingCart(new Pricer(), ITEM_FIRST_BRANCH_NAME);

		String item = "apple";
		int quantity = 1;
		
		sc.addItem(item, quantity);
				
		assertEquals(sc.cartContents.size(), 1);
		assertEquals(sc.cartContents.get(0).item, item);
		assertEquals(sc.cartContents.get(0).quantity, quantity);
	}
	
	@Test
	public void canAddMoreThanOneItem() {
		ShoppingCart sc = new ShoppingCart(new Pricer(), ITEM_FIRST_BRANCH_NAME);

		sc.addItem("apple", 2);

		assertEquals(sc.cartContents.size(), 1);
		assertEquals(sc.cartContents.get(0).item, "apple");
		assertEquals(sc.cartContents.get(0).quantity, 2);
	}
	
	@Test
	public void canAddDifferentItems() {
		ShoppingCart sc = new ShoppingCart(new Pricer(), ITEM_FIRST_BRANCH_NAME);

		sc.addItem("apple", 2);
		sc.addItem("banana", 1);

		assertEquals(sc.cartContents.size(), 2);
		assertEquals(sc.cartContents.get(0).item, "apple");
		assertEquals(sc.cartContents.get(0).quantity, 2);
		
		assertEquals(sc.cartContents.get(1).item, "banana");
		assertEquals(sc.cartContents.get(1).quantity, 1);
	}
	
	@Test
	public void doesntExplodeOnMysteryItem() {
		ShoppingCart sc = new ShoppingCart(new Pricer(), ITEM_FIRST_BRANCH_NAME);

		sc.addItem("crisps", 2);

		assertEquals(sc.cartContents.size(), 1);
		assertEquals(sc.cartContents.get(0).item, "crisps");
		assertEquals(sc.cartContents.get(0).quantity, 2);
	}
	
	@Test
	public void canAddSameItemTwiceAndPreserveOrder() {
		ShoppingCart sc = new ShoppingCart(new Pricer(), ITEM_FIRST_BRANCH_NAME);

		sc.addItem("apple", 2);
		sc.addItem("banana", 1);
		sc.addItem("apple", 4);

		assertEquals(sc.cartContents.size(), 3);
		assertEquals(sc.cartContents.get(0).item, "apple");
		assertEquals(sc.cartContents.get(0).quantity, 2);
		
		assertEquals(sc.cartContents.get(1).item, "banana");
		assertEquals(sc.cartContents.get(1).quantity, 1);
		
		assertEquals(sc.cartContents.get(2).item, "apple");
		assertEquals(sc.cartContents.get(2).quantity, 4);
	}
	
	@Test
	public void cannotAddNegativeOrZeroQuantity() {
		ShoppingCart sc = new ShoppingCart(new Pricer(), ITEM_FIRST_BRANCH_NAME);

		sc.addItem("apple", -3);
		assertEquals(sc.cartContents.size(), 0);
		
		sc.addItem("apple", 0);
		assertEquals(sc.cartContents.size(), 0);
	}

    @Test
	public void correctTotal() {
		ShoppingCart sc = new ShoppingCart(new Pricer(), ITEM_FIRST_BRANCH_NAME);

		String item1 = "apple";
		int quantity1 = 1;
        String item2 = "crisps";
		int quantity2 = 1;
        String item3 = "apple";
		int quantity3 = 5;
		String item4 = "banana";
		int quantity4 = 3;

		sc.addItem(item1, quantity1);
		sc.addItem(item2, quantity2);
        sc.addItem(item3, quantity3);
        sc.addItem(item4, quantity4);

		int totalAsInt = (sc.pricer.getPrice(item1)*quantity1);
        totalAsInt += (sc.pricer.getPrice(item2)*quantity2);
        totalAsInt += (sc.pricer.getPrice(item3)*quantity3);
        totalAsInt += (sc.pricer.getPrice(item4)*quantity4);
		Float totalAsFloat = Float.valueOf(totalAsInt/ONE_HUNDRED);
		sc.printReceipt();
		
		assertEquals(sc.receipt.total, totalAsFloat);
	}
	
	@Test
	public void canPrintItemFirstReceipt() {
		ShoppingCart sc = new ShoppingCart(new Pricer(), ITEM_FIRST_BRANCH_NAME);

		sc.addItem("apple", 2);
		sc.addItem("banana", 1);
		sc.addItem("apple", 5);
		
		final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));
		
		sc.printReceipt();

		assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%napple - 5 - €5.00%n%nTotal: €9.00"), myOut.toString());
    }

    @Test
	public void canPrintPriceFirstReceipt() {
		ShoppingCart sc = new ShoppingCart(new Pricer(), PRICE_FIRST_BRANCH_NAME);

		sc.addItem("apple", 2);
		sc.addItem("banana", 1);
		sc.addItem("apple", 5);
		
		final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));
		
		sc.printReceipt();

		assertEquals(String.format("€2.00 - 2 - apple%n€2.00 - 1 - banana%n€5.00 - 5 - apple%n%nTotal: €9.00"), myOut.toString());
    }
    
    @Test
	public void canSaveItemFirstReceipt() {
		ShoppingCart sc = new ShoppingCart(new Pricer(), ITEM_FIRST_BRANCH_NAME);

		sc.addItem("apple", 2);
		sc.addItem("banana", 1);
		sc.addItem("apple", 5);
		
		sc.printReceipt();

		assertEquals("apple - 2 - €2.00\nbanana - 1 - €2.00\napple - 5 - €5.00\n\nTotal: €9.00", sc.receipt.fullReceipt);
    }
	
    @Test
	public void canSavePriceFirstReceipt() {
		ShoppingCart sc = new ShoppingCart(new Pricer(), PRICE_FIRST_BRANCH_NAME);

		sc.addItem("apple", 2);
		sc.addItem("banana", 1);
		sc.addItem("apple", 5);
		
		sc.printReceipt();

		assertEquals("€2.00 - 2 - apple\n€2.00 - 1 - banana\n€5.00 - 5 - apple\n\nTotal: €9.00", sc.receipt.fullReceipt);
    }
}
