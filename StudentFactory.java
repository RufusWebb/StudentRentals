public class StudentFactory implements UserFactory<Student> {
    @Override
    public Student CreateUser(String Username, String Password, String ContactNumber){ 
        return new Student(Username, Password, "Student", ContactNumber);
    }
}
