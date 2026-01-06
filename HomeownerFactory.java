public class HomeownerFactory implements UserFactory<Homeowner> {
    @Override
    public Homeowner CreateUser(String Username, String Password){
        return new Homeowner(Username, Password, "Homeowner", "N/A");
    }
}

