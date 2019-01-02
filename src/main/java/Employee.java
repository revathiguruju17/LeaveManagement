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

    public boolean compareIDAndPassword(String id, String password) {
        return this.ID.equals(id) && this.password.equals(password);
    }
}
