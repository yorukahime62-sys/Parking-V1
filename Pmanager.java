import java.util.ArrayList;
import java.util.List;

public class Pmanager {
    // collections to store users, vehicles, and parking slots
    static List<User> users = new ArrayList<>();
    static List<Vehicle> vehicles = new ArrayList<>();
    static List<ParkingSlot> parkingSlots = new ArrayList<>();

    public void initializeParkingSlots() {
        if (!parkingSlots.isEmpty()) return;
        // Initialize parking slots
        parkingSlots.add(new ParkingSlot("S1", "Section 1", false, null));
        parkingSlots.add(new ParkingSlot("S2", "Section 1", false, null));
        parkingSlots.add(new ParkingSlot("S3", "Section 2", false, null));
        parkingSlots.add(new ParkingSlot("S4", "Section 2", false, null));
    }

    public void updateParkingSlotStatus(String slotID, boolean isOccupied, String vehiclePlateNumber) {
        slotID = slotID == null ? "" : slotID.trim();
        for (ParkingSlot slot : parkingSlots) {
            if (slot.slotID.equalsIgnoreCase(slotID)) {
                if (isOccupied) {
                    Vehicle vehicle = findVehicleByPlateNumber(vehiclePlateNumber);
                    if vehicle == null) {
                        System.out.println("Error: Register the vehicle before asigning a slot");
                        return;
                    }
                    if (slot.isOccupied) {
                        System.out.println("Error: Slot is already occupied. Please choose another slot.\n");
                        return;
                    }
                    for (ParkingSlot existing : parkingSlots) {
                        if (existing.isOccupied && existing.plateNumber != null && InputValidator.plateKey(existing.vehiclePlateNumber).equals(InputValidator.plateKey(vehiclePlateNumber))) {
                    }
                }
                
                slot.CurrentVehiclePlateNumber = vehiclePlateNumber;
                System.out.println("Parking slot status updated successfully!\n");
                return;
            }
        }
        System.out.println("Slot ID not found.\n");
    }

    public void findOwnerByPlateNumber(String plateNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.plateNumber.equalsIgnoreCase(plateNumber)) {
                String ownerID = vehicle.ownerID;
                User owner = findUserByID(ownerID);
                if (owner != null) {
                    System.out.println("--- Owner Found ---");
                    System.out.println("Owner Name: " + owner.name);
                    System.out.println("Contact: " + owner.contact);
                    System.out.println("User Type: " + owner.userType);
                    System.out.println("---------------------");
                    return;
                }
            }
        }
        System.out.println("Owner not found for plate number: " + plateNumber + "\n");
    }

    public void findVehicleByOwnerID(String searchID) {
        boolean found = false;
        for (Vehicle vehicle : vehicles) {
            User owner = findUserByID(vehicle.ownerID);
            
            if (vehicle.ownerID.equalsIgnoreCase(searchID) || 
                (owner != null && matchesSubID(owner, searchID))) {
                System.out.println("--- Vehicle Found ---");
                System.out.println("Vehicle Plate Number: " + vehicle.plateNumber);
                System.out.println("Vehicle Type: " + vehicle.vehicleType);
                System.out.println("Brand: " + vehicle.brand);
                System.out.println("Model: " + vehicle.model);
                System.out.println("Color: " + vehicle.color);
                System.out.println("---------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicle found for owner ID: " + searchID + "\n");
        }
    }

    // Helper method to check subclass IDs (Student, Faculty, Visitor)
    private boolean matchesSubID(User user, String searchID) {
        if (user instanceof User.Student) {
            return ((User.Student) user).studentID.equalsIgnoreCase(searchID);
        } else if (user instanceof User.Faculty) {
            return ((User.Faculty) user).teacherID.equalsIgnoreCase(searchID);
        } else if (user instanceof User.Visitor) {
            return ((User.Visitor) user).VisitorID.equalsIgnoreCase(searchID);
        }
        return false;
    }

    // Find user using base useID or specific sub-IDs
    public User findUserByID(String searchID) {
        for (User user : users) {
            if (user.useID.equalsIgnoreCase(searchID) || matchesSubID(user, searchID)) {
                return user;
            }
        }
        return null; 
    }

    public void displayParkingSlotStatus() {
        System.out.println("\n--- PARKING SLOTS ---");
        for (ParkingSlot slot : parkingSlots) {
            System.out.println("Slot ID: " + slot.slotID);
            System.out.println("Location: " + slot.location);
            System.out.println("Occupied: " + slot.isOccupied);
            System.out.println("Current Vehicle Plate Number: " + (slot.CurrentVehiclePlateNumber != null ? slot.CurrentVehiclePlateNumber : "None"));
            System.out.println("---------------------------");
        }
    }

    public void addVehicle(String plateNumber, String vehicleType, String brand, String model, String color, String ownerID) {
        // Verify user exists before adding vehicle
        User owner = findUserByID(ownerID);
        if (owner == null) {
            System.out.println("Error: Owner ID '" + ownerID + "' does not exist. Register the user first.\n");
            return;
        }
        Vehicle vehicle = new Vehicle(plateNumber, vehicleType, brand, model, color, ownerID);
        vehicles.add(vehicle);
        System.out.println("Vehicle registered successfully!\n");
    }
}