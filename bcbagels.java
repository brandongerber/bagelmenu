/*
 /*
 Brandon Gerber
 	10/26/2023
 	COP1250C
 	BCBAGELS.JAVA
 */
package bcbagels;

import java.util.Scanner;

public class bcbagels {
    public static void main(String[] args) {
        String[] menuItems = {"Bagel", "Butter", "Cream Cheese", "Eggs", "Bacon"};
        double[] itemPrices = {1.00, 0.50, 1.00, 2.75, 2.25};

        // intialize all variables 
        int[] itemQuantities = new int[menuItems.length];
        double[] itemSubtotals = new double[menuItems.length];
        double orderSubtotal = 0.0;
        double taxRate = 0.07;
        double taxAmount;
        double totalAmount;

        Scanner scanner = new Scanner(System.in);

        // menu and user inputs
        while (true) {
            System.out.println("Please choose an item:");
            for (int i = 0; i < menuItems.length; i++) {
                String formattedPrice = String.format("$%.2f", itemPrices[i]);
                System.out.println((i + 1) + ") " + menuItems[i] + " " +
                                   (formattedPrice.endsWith(".00") ? formattedPrice.substring(0, formattedPrice.length() - 3) : formattedPrice));
            }
            System.out.println((menuItems.length + 1) + ") Exit");

            System.out.print("Enter your choice (1-6): ");
            int userChoice;
            try {
                userChoice = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid choice. Please select a valid item or exit.");
                scanner.nextLine(); // Clear the input buffer
                continue;
            }

            if (userChoice < 1 || userChoice > menuItems.length + 1) {
                System.out.println("Invalid choice. Please select a valid item or exit.");
                continue;
            }

            if (userChoice == menuItems.length + 1) {
                // option to exit and recieve final score
                break;
            } else {
                // option to choose an item
                int itemIndex = userChoice - 1;
                System.out.print("Enter the quantity: ");
                int quantity = scanner.nextInt();

                itemQuantities[itemIndex] += quantity;
                itemSubtotals[itemIndex] += itemPrices[itemIndex] * quantity;

                System.out.println("Added " + quantity + " " + menuItems[itemIndex] + " to your order.");
            }
        }

        // calculate the subtotal, tax, and total
        for (int i = 0; i < menuItems.length; i++) {
            orderSubtotal += itemSubtotals[i];
        }

        taxAmount = orderSubtotal * taxRate;
        totalAmount = orderSubtotal + taxAmount;

        // display the final bill
        System.out.println("Item Quantity Subtotal");
        System.out.println("-----------------------");
        for (int i = 0; i < menuItems.length; i++) {
            if (itemQuantities[i] > 0) {
                String subtotalFormatted = String.format("$%.2f", itemSubtotals[i]);
                System.out.println(menuItems[i] + " " + itemQuantities[i] + " " +
                                   (subtotalFormatted.endsWith(".00") ? subtotalFormatted.substring(0, subtotalFormatted.length() - 3) : subtotalFormatted));
            }
        }

        String subtotalFormatted = String.format("$%.2f", orderSubtotal);
        String taxFormatted = String.format("$%.2f", taxAmount);
        String totalFormatted = String.format("$%.2f", totalAmount);
        System.out.println("Subtotal: " + (subtotalFormatted.endsWith(".00") ? subtotalFormatted.substring(0, subtotalFormatted.length() - 3) : subtotalFormatted));
        System.out.println("Tax: " + (taxFormatted.endsWith(".00") ? taxFormatted.substring(0, taxFormatted.length() - 3) : taxFormatted));
        System.out.println("Total: " + (totalFormatted.endsWith(".00") ? totalFormatted.substring(0, totalFormatted.length() - 3) : totalFormatted));

       
        scanner.close();
    }
}