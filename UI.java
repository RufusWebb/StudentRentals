public class UI {
    public static void main(String[] args) {
        System.out.println("Welcome to the Student Rentals Application!");
        
        // Create a student
        StudentFactory studentFactory = new StudentFactory();
        Student student = studentFactory.CreateUser("john_doe", "helloworld");
        
        // Create a homeowner  
        HomeownerFactory homeownerFactory = new HomeownerFactory();
        Homeowner homeowner = homeownerFactory.CreateUser("jane_smith", "pass456");
        
        // Test it worked
        System.out.println("Created student: " + student.getUsername() + " (ID: " + student.getUserID() + ")");
        System.out.println("Created homeowner: " + homeowner.getUsername() + " (ID: " + homeowner.getUserID() + ")");
    }
}