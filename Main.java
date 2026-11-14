import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Pmanager pmanager = new Pmanager();
        pmanager.initializeParkingSlots();

        Scanner scanner = new Scanner(System.in);
        int choice = 0;


        do {
            System.out.println("Parking Management System");
            System.out.println("1. Register Vehicle");
            System.out.println("2. Register User");
            System.out.println("3. Park Vehicle");
            System.out.println("4. Find Owner by Plate Number");
            System.out.println("5. Find Vehicle by Owner ID");
            System.out.println("6. Display Parking Slot Status");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (Pmanager.users.isEmpty()) {
                        System.out.println("No users registered. Please register a user first.");
                        break;
                    }
                    System.out.print("Enter Plate Number: ");
                    String plateNumber = InputValidator.read(scanner, "Enter Plate Number: ", InputValidator::validPlate, "Invalid plate number. Please try again.");
                    System.out.print("Enter Vehicle Type: ");
                    String vehicleType = scanner.nextLine();
                    System.out.print("Enter Brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("Enter Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter Color: ");
                    String color = InputValidator.required(scanner, "Enter Color: ");
                    System.out.print("Enter Owner Student/Faculty/Visitor ID: ");
                    String ownerID = scanner.nextLine();
                    pmanager.addVehicle(plateNumber, vehicleType, brand, model, color, ownerID);
                    break;
                case 2:
                    int nextID = Pmanager.users.size() + 1;
                    while (pmanager.findUserByID("U" + nextID) != null) nextID++;
                    String useID = "U" + nextID;
                    // System.out.print("Enter User ID: ");
                    // String useID = scanner.nextLine();
                    
                    //auto userID generation
                    
                    System.out.print("Enter Name: ");
                    String name = InputValidator.required(scanner, "Enter Name: ");
                    System.out.print("Enter Contact: ");
                    String contact = scanner.nextLine();
                    
                    //loop until valid userType is entered
                    String userType = "";
                    while (true) {
                        System.out.print("Enter User Type (Student/Faculty/Visitor): ");
                        userType = scanner.nextLine();
                        if (userType.equalsIgnoreCase("Student") || userType.equalsIgnoreCase("Faculty") || userType.equalsIgnoreCase("Visitor")) {
                            break;
                        } else {
                            System.out.println("Invalid user type. Please enter Student, Faculty, or Visitor.");
                        }
                    }

                    // System.out.print("Enter User Type (Student/Faculty/Visitor): ");
                    // String userType = scanner.nextLine();
                    User user = new User(useID, name, contact, userType); 
                    Pmanager.users.add(user);
                    break;

                case 3:
                    // show available parking slots
                    pmanager.displayParkingSlotStatus();
                    //enetr slotID if exist
                    while (true) {
                        System.out.print("Enter Slot ID to park the vehicle: ");
                        String slotID = scanner.nextLine();
                        ParkingSlot selectedSlot = null;
                        for (ParkingSlot slot : Pmanager.parkingSlots) {
                            if (slot.slotID.equalsIgnoreCase(slotID)) {
                                selectedSlot = slot;
                                break;
                            }
                        }
                        if (selectedSlot != null) {
                            if (!selectedSlot.isOccupied) {
                                System.out.print("Enter Vehicle Plate Number to park: ");
                                // check if multiple owner registered with same plate number
                                if (Pmanager.vehicles.stream().filter(v -> v.plateNumber.equalsIgnoreCase(scanner.nextLine())).count() > 1) {
                                    System.out.println("Multiple owners found for this plate number. Please provide the correct owner ID.");
                                    System.out.print("Enter Owner Studen/Faculty/Visitor ID: ");
                                    String parkingOwnerID = scanner.nextLine();
                                    // check if ownerID is valid
                                    if (Pmanager.users.stream().anyMatch(u -> u.useID.equalsIgnoreCase(parkingOwnerID))) {
                                        System.out.print("Enter Vehicle Plate Number to park: ");
                                        String vehiclePlateNumber = scanner.nextLine();
                                        pmanager.updateParkingSlotStatus(slotID, true, vehiclePlateNumber);
                                        break; // exit the loop after successful parking
                                    } else {
                                        System.out.println("Invalid Owner ID. Please try again.");
                                    }
                                } else {
                                    System.out.print("Enter Vehicle Plate Number to park: ");
                                    String vehiclePlateNumber = scanner.nextLine();
                                    pmanager.updateParkingSlotStatus(slotID, true, vehiclePlateNumber);
                                    break; // exit the loop after successful parking
                                }

                                // String vehiclePlateNumber = scanner.nextLine();
                                // pmanager.updateParkingSlotStatus(slotID, true, vehiclePlateNumber);
                                break; // exit the loop after successful parking
                            } else {
                                System.out.println("Selected slot is already occupied. Please choose another slot.");
                            }
                        } else {
                            System.out.println("Invalid Slot ID. Please try again.");
                        }
                    }




                    // System.out.print("Enter Slot ID: ");
                    // String slotID = scanner.nextLine();
                    // System.out.print("Is Occupied (true/false): ");
                    // boolean isOccupied = scanner.nextBoolean();
                    // scanner.nextLine(); // Consume newline
                    // System.out.print("Enter Vehicle Plate Number (if occupied): ");
                    // String vehiclePlateNumber = scanner.nextLine();
                    // pmanager.updateParkingSlotStatus(slotID, isOccupied, vehiclePlateNumber);
                    break;
                case 4:
                    System.out.print("Enter Plate Number: ");
                    String searchPlateNumber = scanner.nextLine();
                    pmanager.findOwnerByPlateNumber(searchPlateNumber);
                    break;
                case 5:
                    System.out.print("Enter Owner ID: ");
                    String searchOwnerID = scanner.nextLine();
                    pmanager.findVehicleByOwnerID(searchOwnerID);
                    break;
                case 6:
                    pmanager.displayParkingSlotStatus();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }
}