import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EcoPointsRecyclingTracker {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Household> households = new HashMap<>(); // Task 2




    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Eco-Points Recycling Tracker ===\n"+
            "1. Register Household");
            System.out.println("2. Log Recycling Event");
            System.out.println("3. Display Households");
            System.out.println("4. Display Household Recycling Events");
            System.out.println("5. Generate Reports");
            System.out.println("6. Save and Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    registerHousehold();
                    break;
                case "2":
                    logRecycleEvent();
                    break;
                case "3":
                    displayHouseholds();
                    break;
                case "4":
                    displayHouseholdRecyclingEvents();
                    break;
                case "5":
                    displayHouseholdRecyclingEventsReports();
                    break;
                case "6":
                    saveHouseholdsToFile();
                    running = false;
                    System.out.println("Data saved. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-6.");
            }
            // You will handle the choice entered here using switch(case)
        }

    }
    public static  void registerHousehold() {
        System.out.println("Enter Household ID: ");
        String id= scanner.nextLine().trim();
        if (households.containsKey(id)) {
            System.out.println("Household ID is already in use");
            return;
        }
        System.out.println("Enter Household Name: ");
        String name= scanner.nextLine().trim();
        System.out.println("Enter Household Address: ");
        String address= scanner.nextLine().trim();
        Household household = new Household(id, name, address);
        households.put(id, household);
        System.out.println("Household has been created "+household.getJoinDate());


    }
    private static void logRecycleEvent(){
        System.out.println("Enter Household ID: ");
        String id= scanner.nextLine().trim();
        Household household = households.get(id);
        if (household != null) {
            System.out.println("No household has been created");
            return;
        }
        System.out.println("Enter Household Material Need be Recycled");
        String material = scanner.nextLine().trim();
        double weight = 0.0;
        while(true){
            try{
                System.out.println("Enter Weight of Recycling Event: ");
                weight = scanner.nextDouble();
                if (weight <= 0) throw new IllegalArgumentException();
                break;
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }
        RecyclingEvent  recEvent=new RecyclingEvent(material,weight);
        household.addEvent(recEvent);
        System.out.println("Recycling event logged! Points earned: " + recEvent.getEcoPoints());
    }
    private static void displayHouseholds() {
        // Check if the households map is empty
        if (households.isEmpty()) {
            System.out.println("No households registered.");
            return; // Exit early if there's nothing to show
        }
        // If there are households, print a header first
        System.out.println("\nRegistered Households:");
        for (Household household : households.values()) {
            System.out.println("ID: " +household.getId() +
                    ", Name: " + household.getName() +
                    ", Address: " + household.getAddress() +
                    ", Joined: " + household.getJoinDate());
        }
    }
    private static void displayHouseholdRecyclingEvents() {
        System.out.println("Enter Household ID: ");
        String id= scanner.nextLine().trim();
        Household household = households.get(id);
        if (household != null) {
            System.out.println("House events are empty");

        }

        System.out.println("\nRecycling Events for " + household.getName() + ":");
        if (household.getEvents().isEmpty()) {
            System.out.println("No households registered.");
        }
            else{
                for(RecyclingEvent events : household.getEvents()){
                    System.out.println(events);
                }

        }
        System.out.println("Total Weight: " + household.getTotalWeight() + " kg");
        // Show the total eco points earned by this household
        System.out.println("Total Points: " + household.getTotalPoints() + " pts");
    }
    private static void displayHouseholdRecyclingEventsReports() {
        // displayHouseholdRecyclingEventsReports
      if(households.isEmpty()){
          System.out.println("No households registered.");
          return;
      }
      Household top=null;
      for(Household households : households.values()){
          if(top==null||households.getTotalPoints()>top.getTotalPoints()){
              top=households;
          }
      }
        System.out.println("\nHousehold with Highest Points:");
        System.out.println("ID: " + top.getId() +
                ", Name: " + top.getName() +
                ", Points: " + top.getTotalPoints());
   double totalWeight = 0.0;
   for(Household hd:households.values()){
       totalWeight += hd.getTotalWeight();
   }
   System.out.println("Total Weight: " + totalWeight + " kg");
    }
    private static void saveHouseholdsToFile(){
        try {
            FileOutputStream fileOutputStream=new FileOutputStream("Households.txt");
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(households);
           fileOutputStream.close();
           objectOutputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadHouseholdsFromFile() throws FileNotFoundException {

        try {
            FileInputStream fileInputStream = new FileInputStream("Households.txt");
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            households=(Map<String, Household>)objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
