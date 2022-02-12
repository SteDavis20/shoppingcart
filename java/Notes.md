# Design Decisions

## ArrayList
Since the receipt needs to print the items in the order that they were scanned I decided to use an ArrayList instead of a HashMap in order to preserve order. I had considered using a LinkedHashMap but ultimately chose not to, because, for example, scanning 2 apples, 1 banana and then 4 apples, would lead to:

    apple  6
    banana 1

Which would indicate that 6 apples were scanned, followed by 1 banana. This is not the correct order in which the items were scanned. So although a HashMap can have faster lookup time, an ArrayList is more suitable since it can preserve order and store more than 1 entry for the same item. The example above is illustrated below:

    apple  2
    banana 1
    apple  4

## Custom Classes
I decided to create 2 custom classes: Receipt and Entry. 

### Entry
Every instance of Entry represents an entry, or a line in the receipt. 
For example: 

    apple - 2 - €4.00

This allows functionality to save the item name, quantity, and total price. This would make future functionality such as sorting the receipt in order of item name or price, easier to implement.

In addition, testing can now be done on the Entry instances that should be added to the shopping cart, so if the receipt format changes, the unit tests will not need to be updated. 

### Receipt
I separated the shopping cart and receipt into their own classes. The rationale being a receipt instance can have a total attribute, and can keep record of the branch name. Perhaps in the future the supermarket til system might want to add a survey to the receipt or to print the branch name, location and phone number on the receipt. Separating the receipt and shopping cart would make this more achievable and reduce future restructuring of code.

## Test Suite
I have updated the test suite to test capabilities for printing different formatted receipts as illustrated below.

### Item First Format
    apple - 2 - €4.00

### Price First Format
    €4.00 - 2 - apple