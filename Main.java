package CreditCard;
import CreditCard.CreditCardType;//Import CreditCardType enum
import CreditCard.Util.DateChecker; // Import DateChecker utility class
import CreditCard.Util.DateParser;// Import DateParser utility class
import CreditCard.Util.TypeChecker; // Import TypeChecker utility class

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter Credit Card Number: "); // Prompt user to enter credit card number
        String creditCardNumber = input.nextLine();
        System.out.println("Enter Credit Card Date ");// Prompt user to enter credit card expiration date
        String creditCardDate = input.nextLine();
      CreditCardType creditCardType = TypeChecker.checkType(creditCardNumber);// Determine the credit card type using TypeChecker

        // Check if the card type is invalid (OTHER)
      if (creditCardType == CreditCardType.OTHER) {
          System.out.println("Invalid card");
      }else {System.out.println(creditCardType.toString());// Check and print whether the date is valid using ternary operator
          System.out.println(DateChecker.isValidDate(creditCardDate)==true?"Valid Date":"Invalid Date");
      }


    }

}