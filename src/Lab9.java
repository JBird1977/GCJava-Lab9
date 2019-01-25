/* Display at least 8 item names and prices
 * Ask user to enter item name
 * If it doesn't exist present error and re-prompt
 * If it exists display item and price and add item and price to user's order
 * 
 * Prompt if they want to add another and repeat as appropriate
 * when done, display list of items with prices in columns
 * 
 * display avg price of items ordered
 * 
 * use hashtable to keep track of menus items - you can code with literals
 * 
 * use parallel array lists (one of strings other of doubles) to store items and their prices
 * write 3 methods to find: 1. avg of items ordered and indexes
 * 2. indexes of highest price items
 * 3. indexes of lowest price items
 * 
 * 
 */

import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;




public class Lab9 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        Map <String, Double> menus = new HashMap<String, Double>();
        ArrayList <String> cartItem = new ArrayList<>();
        ArrayList <Double> cartPrice = new ArrayList<>();
        int valid = 0;
        int another = -1;
        int end = 0;
        String input = "";
     do { // main do while loop
       do { //repeat while the user wants to order more items

            do { //loop to get user input for their order and validate it
            printMenu(menus);
           input = orderItem(scan, input);
           valid = validateItem(input, menus);           
            } while (valid == -1); //repeat while item is invalid
            
           
            addOrder(input, menus, cartItem, cartPrice);
            another = addAnother(scan);         
          } while (another == 1); //loop until the user doesn't want to add another item to their order
           
             printOrder(menus, cartItem, cartPrice);
             printAverage(cartPrice);
         
    } while (end == 1); //end main do while loop
     System.out.println();
     System.out.println("Thanks for playing!");
    
  scan.close();     
} //end main
    public static void printMenu(Map <String, Double> menus) {
        
        //create the menus of items
        menus.put("apple", 0.99);
        menus.put("banana", 0.59);
        menus.put("cauliflower", 1.59);
        menus.put("dragonfruit", 2.19);
        menus.put("Elderberry", 1.79);
        menus.put("figs", 2.09);
        menus.put("grapefruit", 1.99);
        menus.put("honeydew", 3.49);
        
        //print them out
        
        for (Map.Entry<String, Double> entry : menus.entrySet()) {
            System.out.println(entry.getKey() + " $" + entry.getValue().toString());
        } //end for loop
        
} //end printMenu
    
    private static String orderItem(Scanner scan, String input) {
        
        System.out.println("Please enter the name of the item you would like to order: ");
        input = scan.nextLine();
        return input;    
    } //end orderItem
    
    private static int validateItem(String input, Map <String, Double> menus) {
        if (!(menus.containsKey(input))) {
        
            System.out.println("Invalid input. Please try again");
            return -1;
        }
        return 1;
    } //end validateItem
    
    
    //add the item to the user's order
    private static void addOrder(String input, Map <String, Double> menus, ArrayList <String> cartItem, ArrayList <Double> cartPrice) {
     cartItem.add(input);
     cartPrice.add(menus.get(input));
     
     
     System.out.println(input + " added to your order." ); //let the user know they added the item to their order.
    } //end addItem
    
    private static int addAnother(Scanner scan) {
        String input; 
    do {
        System.out.println("Would you like to add another? (y/n)");
        input = scan.nextLine();
        if (!(input.matches("[YNyn]"))) { //user input must match Y/y or N/n
            System.out.println("Invalid input please try again."); //if the user's input is invalid start at the beginning of the do while loop
            continue;
        }
        if (input.equalsIgnoreCase("y")) {
            return 1; //return 1 which says the user does want to add another item. 
        } else return 0; //user doesn't want to add another item so we quit the method and loop
    } while (!(input.matches("[Yy]"))); //repeat so long as user input is yes
    
    return 0; 
    }

    private static void printOrder(Map <String, Double> menus, ArrayList <String> cartItem, ArrayList <Double> cartPrice) {
      System.out.printf("%-15s %s \n", "Item", "Price" );
      System.out.println("==============================");
      
        for (int i = 0; i < cartItem.size(); i++) {
            System.out.printf("%-15s $ %2.2f \n", cartItem.get(i), cartPrice.get(i));
            } //end for loop
    }

    
    private static void printAverage( ArrayList <Double> cartPrice) {
        double total = 0.0;
        double average = 0.0;
        for (int i=0; i <cartPrice.size(); i++) {
            total += cartPrice.get(i); //add up all entries of cartPrice
        }
        average = (total / cartPrice.size()); //divide by the number of indexes in cartPrice to get average
        System.out.printf("%-10s %d %s $%2.2f", "You ordered ", cartPrice.size(), "items and the average price is: ", average);
        
    } //end printAverage
} //end class
