class Employee {
    private String ID;
    private String password;

    Employee() {
    }

    void setID(String ID) {
        this.ID = ID;
    }

    void setPassword(String password) {
        this.password = password;
    }

    boolean compareID(String id) {
        return this.ID.equals(id);
    }

    boolean comparePassword(String password) {
        return this.password.equals(password);
    }
}
