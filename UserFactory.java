public interface UserFactory<T> {
    T CreateUser(String Username, String Password);
    }
    