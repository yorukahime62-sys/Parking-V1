import java.util.ArrayList;
import java.util.List;

public class Pmanager2 {
    // collections to store users, vehicles, and parking slots
    static List<User> users = new ArrayList<>();
    static List<Vehicle> vehicles = new ArrayList<>();
    List<ParkingSlot> parkingSlots = new ArrayList<>();

    public void initializeParkingSlots() {
        // Initialize parking slots
        parkingSlots.add(new ParkingSlot("S1", "Section 1", false, null));
        parkingSlots.add(new ParkingSlot("S2", "Section 1", false, null));
        parkingSlots.add(new ParkingSlot("S3", "Section 2", false, null));
        parkingSlots.add(new ParkingSlot("S4", "Section 2", false, null));
    }

    public void updateParkingSlotStatus(String slotID, boolean isOccupied, String vehiclePlateNumber) {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.slotID.equals(slotID)) {
                slot.isOccupied = isOccupied;
                slot.CurrentVehiclePlateNumber = vehiclePlateNumber;
                break;
            }
        }
    }

    public void findOwnerByPlateNumber(String plateNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.plateNumber.equals(plateNumber)) {
                String ownerID = vehicle.ownerID;
                for (User user : users) {
                    if (user.useID.equals(ownerID)) {
                        System.out.println("Owner Name: " + user.name);
                        System.out.println("Contact: " + user.contact);
                        return;
                    }
                }
            }
        }
        System.out.println("Owner not found for plate number: " + plateNumber);
    }


public void findVehicleByOwnerID(String searchID) {
        boolean found = false;
        for (Vehicle vehicle : vehicles) {
            // Check if the vehicle's ownerID matches the search ID or matches the user's specific sub-ID
            User owner = findUserByID(vehicle.ownerID);
            
            if (vehicle.ownerID.equalsIgnoreCase(searchID) || 
                (owner != null && matchesSubID(owner, searchID))) {
                System.out.println("--- Vehicle Found ---");
                System.out.println("Vehicle Plate Number: " + vehicle.plateNumber);
                System.out.println("Vehicle Type: " + vehicle.vehicleType);
                System.out.println("Brand: " + vehicle.brand);
                System.out.println("Model: " + vehicle.model);
                System.out.println("Color: " + vehicle.color);
                System.out.println("Owner ID Match: " + vehicle.ownerID);
                System.out.println("---------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicle found for owner ID: " + searchID);
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



    
    //find user in using their studentID, facultyID, or visitorID
    public User findUserByID(String userID) {
        for (User user : users) {
            if (user.useID.equals(userID)) {
                return user;
            }
        }
        return null; // User not found
    }
    


    public void displayParkingSlotStatus() {
        for (ParkingSlot slot : parkingSlots) {
            System.out.println("Slot ID: " + slot.slotID);
            System.out.println("Location: " + slot.location);
            System.out.println("Occupied: " + slot.isOccupied);
            System.out.println("Current Vehicle Plate Number: " + slot.CurrentVehiclePlateNumber);
            System.out.println("---------------------------");
        }
    }

    public void findAvailableParkingSlot() {
        for (ParkingSlot slot : parkingSlots) {
            if (!slot.isOccupied) {
                System.out.println("Available Slot ID: " + slot.slotID);
                System.out.println("Location: " + slot.location);
                return;
            }
        }
        System.out.println("No available parking slots.");
    }


    public void addVehicle(String plateNumber, String vehicleType, String brand, String model, String color, String ownerID) {
        Vehicle vehicle = new Vehicle(plateNumber, vehicleType, brand, model, color, ownerID);
        vehicles.add(vehicle);
    }


    
}
