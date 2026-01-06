public class StudentFactory implements UserFactory<Student> {
    @Override
    public Student CreateUser(String Username, String Password) {
        return new Student(Username, Password, "Student", "N/A");
    }
}
