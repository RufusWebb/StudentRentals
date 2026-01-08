public class HomeownerFactory implements UserFactory<Homeowner> {
    @Override
    public Homeowner CreateUser(String Username, String Password, String ContactNumber) {
        return new Homeowner(Username, Password, "Homeowner", ContactNumber);
    }
}

