public class User {
    private final String name;
    private final String mail;
    private final String phoneNo;

    private final String password;

    private final int weight;

    public User(String name, String mail, String phoneNo, String password, int weight) {
        this.name = name;
        this.mail = mail;
        this.phoneNo = phoneNo;
        this.password = password;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
