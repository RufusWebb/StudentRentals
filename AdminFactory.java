public class AdminFactory implements UserFactory<Admin> {
    @Override
    public Admin CreateUser(String Username, String Password, String ContactNumber) {
        return new Admin(Username, Password, "Admin", ContactNumber);
    }
}
