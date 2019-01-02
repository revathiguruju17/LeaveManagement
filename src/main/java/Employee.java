public class Employee {
    private String ID;
    private String password;

    public Employee() {
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean compareID(String id) {
        return this.ID.equals(id);
    }

    public boolean comparePassword(String password) {
        return this.password.equals(password);
    }
}
