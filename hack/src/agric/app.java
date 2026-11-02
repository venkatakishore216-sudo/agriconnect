package agric;

import java.util.ArrayList;
import java.util.Scanner;

public class app {

    // Member variables managing application state and system inputs
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> communityMembers = new ArrayList<>();
    private static final ArrayList<AgriConnect> cropListings = new ArrayList<>();

    public static void main(String[] args) {
        // Populating sample demonstration data
        initializeDemoData();

        System.out.println("=================================================");
        System.out.println("  WELCOME TO AGRICONNECT: SMART HEALTH SCANNER   ");
        System.out.println("               PROJECT REVIEW 2026-27            ");
        System.out.println("=================================================");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getValidMenuChoice();

            switch (choice) {
                case 1:
                    runPlantHealthGuide();
                    break;
                case 2:
                    runCommunityModule();
                    break;
                case 3:
                    runAddCropListing();
                    break;
                case 4:
                    runViewCropListings();
                    break;
                case 5:
                    System.out.println("\nExiting safely. Thank you for using AgriConnect!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Seed initial sample details for the live demo walkthrough.
     */
    private static void initializeDemoData() {
        communityMembers.add("Farmer Ramesh");
        communityMembers.add("Farmer Suresh");
        cropListings.add(new AgriConnect("Farmer Ramesh", "Organic Tomatoes", 150, 40.0));
        cropListings.add(new AgriConnect("Farmer Suresh", "Premium Basmati Rice", 500, 75.0));
    }

    /**
     * Displays the AgriConnect text-based main menu.
     */
    private static void displayMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Plant Health Guide");
        System.out.println("2. Farmer Community Hub");
        System.out.println("3. Post a Crop Listing");
        System.out.println("4. View Active Crop Listings (Buyer View)");
        System.out.println("5. Exit Program");
        System.out.print("Please enter your choice (1-5): ");
    }

    /**
     * Handles menu choice input validation.
     */
    private static int getValidMenuChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Please enter a valid number matching the menu options.");
            System.out.print("Please enter your choice (1-5): ");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        return choice;
    }

    /**
     * MODULE 1: Plant Health Guide
     * Conditional rule-based symptom matching logic.
     */
    private static void runPlantHealthGuide() {
        System.out.println("\n--- PLANT HEALTH GUIDE ---");
        System.out.println("Select a visible plant symptom from the options below:");
        System.out.println("1. Yellowing Leaves");
        System.out.println("2. Brown Spots on Leaves");
        System.out.println("3. Wilting / Drooping Stems");
        System.out.print("Enter symptom number (1-3): ");

        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please choose 1, 2, or 3.");
            scanner.next();
        }
        int symptomChoice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        System.out.println("\n[SCANNING RESULT SUGGESTION]");
        System.out.println("-------------------------------------------------");
        // Rule-based logic engine matching symptoms to guidance suggestions
        if (symptomChoice == 1) {
            System.out.println("Possible Cause: Nitrogen Deficiency or Overwatering.");
            System.out.println("Recommendation: Check soil moisture levels. Consider adding a nitrogen-rich organic fertilizer.");
        } else if (symptomChoice == 2) {
            System.out.println("Possible Cause: Fungal Infection (Early Blight / Leaf Spot).");
            System.out.println("Recommendation: Prune infected leaves. Keep foliage dry and ensure proper spacing for airflow.");
        } else if (symptomChoice == 3) {
            System.out.println("Possible Cause: Root Rot or Underwatering / Extreme Heat.");
            System.out.println("Recommendation: Verify if soil is completely dry or waterlogged. Protect young crops from direct midday sun.");
        } else {
            System.out.println("Unknown symptom choice selected.");
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Responsible Guidance Disclaimer: This is a rule-based matching suggestion, NOT a confirmed diagnosis.");
    }

    /**
     * MODULE 2: Community Module
     * Displays community information and handles member registration.
     */
    private static void runCommunityModule() {
        System.out.println("\n--- FARMER COMMUNITY HUB ---");
        System.out.println("Current Active Group: 'Green Field Collective'");
        System.out.println("Registered Members in this Network:");
        
        for (String member : communityMembers) {
            System.out.println(" - " + member);
        }

        System.out.print("\nWould you like to join this community? (yes/no): ");
        String joinChoice = scanner.nextLine().trim().toLowerCase();

        if (joinChoice.equals("yes") || joinChoice.equals("y")) {
            System.out.print("Enter your Name: ");
            String farmerName = scanner.nextLine().trim();
            
            // Simple validation check
            if (farmerName.isEmpty()) {
                System.out.println("Error: Name cannot be empty. Registration aborted.");
            } else {
                communityMembers.add(farmerName);
                System.out.println("Success! " + farmerName + " has successfully connected to the community.");
            }
        } else {
            System.out.println("Returning to the main menu.");
        }
    }

    /**
     * MODULE 3: Crop Listing Creation
     * Allows farmers to add new crop records with strict input verification.
     */
    private static void runAddCropListing() {
        System.out.println("\n--- CREATE NEW CROP LISTING ---");
        
        System.out.print("Enter your Name: ");
        String farmerName = scanner.nextLine().trim();
        
        System.out.print("Enter Crop Name (e.g., Wheat, Potato): ");
        String cropName = scanner.nextLine().trim();

        if (farmerName.isEmpty() || cropName.isEmpty()) {
            System.out.println("Error: Farmer name and crop name are mandatory fields.");
            return;
        }

        // Validate available quantity
        System.out.print("Enter Available Quantity (in kg): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Quantity must be a whole numeric value.");
            System.out.print("Enter Available Quantity (in kg): ");
            scanner.next();
        }
        int quantity = scanner.nextInt();
        
        // Validate price entry
        System.out.print("Enter Expected Price per kg (INR): ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Error: Price must be a decimal or whole number value.");
            System.out.print("Enter Expected Price per kg (INR): ");
            scanner.next();
        }
        double price = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer

        // Double check data validity rules before creating object
        if (quantity <= 0 || price <= 0) {
            System.out.println("Error: Quantity and Price must both be greater than 0.");
        } else {
            cropListings.add(new AgriConnect(farmerName, cropName, quantity, price));
            System.out.println("Success: Your crop listing has been created and shared.");
        }
    }

    /**
     * MODULE 4: Crop Listings Review (Buyer Discovery View)
     * Renders crop offers for potential community buyers.
     */
    private static void runViewCropListings() {
        System.out.println("\n--- ACTIVE CROP LISTINGS (BUYER DISCOVERY) ---");
        System.out.println("Target buyers (Schools, Colleges, Local Markets) can review the following offers:\n");

        if (cropListings.isEmpty()) {
            System.out.println("No active crop listings found at the moment.");
            return;
        }

        System.out.printf("%-15s | %-18s | %-14s | %-12s\n", "FARMER", "CROP", "QTY AVAILABLE", "PRICE PER KG");
        System.out.println("-------------------------------------------------------------------------");
        for (AgriConnect listing : cropListings) {
            System.out.printf("%-15s | %-18s | %-14d | INR %.2f\n", 
                    listing.farmerName, listing.cropName, listing.quantity, listing.price);
        }
        System.out.println("-------------------------------------------------------------------------");
    }
}
