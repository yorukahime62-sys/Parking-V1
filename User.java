import java.util.Scanner;

public class User {
    String useID;
    String name;
    String contact;
    String userType;

    public User(String useID, String name, String contact, String userType) {
        this.useID = useID;
        this.name = name;
        this.contact = contact;
        this.userType = userType;
    }


    public class Student extends User {
        String studentID;
        String course;

        public Student(String useID, String name, String contact, String userType, String studentID, String course) {
            super(useID, name, contact, userType);
            this.studentID = studentID;
            this.course = course;
        }
    }

    public class Faculty extends User {
        String teacherID;
        String subject;

        public Faculty(String useID, String name, String contact, String userType, String teacherID, String subject) {
            super(useID, name, contact, userType);
            this.teacherID = teacherID;
            this.subject = subject;
        }
    }

    public class Visitor extends User {
        String VisitorID;

        public Visitor(String useID, String name, String contact, String userType, String VisitorID) {
            super(useID, name, contact, userType);
            this.VisitorID = VisitorID;

        }
    }

    public void registerUser(String useID, String name, String contact, String userType) {
        if (userType.equalsIgnoreCase("Student")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Student ID: ");
            String studentID = scanner.nextLine();
            System.out.print("Enter Course: ");
            String course = scanner.nextLine();
            Student student = new Student(useID, name, contact, userType, studentID, course);
            Pmanager.users.add(student);


        } else if (userType.equalsIgnoreCase("Faculty")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Teacher ID: ");
            String teacherID = scanner.nextLine();
            System.out.print("Enter Subject: ");
            String subject = scanner.nextLine();
            Faculty faculty = new Faculty(useID, name, contact, userType, teacherID, subject);
            Pmanager.users.add(faculty);


        } else if (userType.equalsIgnoreCase("Visitor")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Visitor ID: ");
            String visitorID = scanner.nextLine();
            Visitor visitor = new Visitor(useID, name, contact, userType, visitorID);
            Pmanager.users.add(visitor);


        } else {
            System.out.println("Invalid user type. User not registered.");
        }


    }



}
