import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        User user1= new User("ion","sdfsfda","094354", "parolaion", 90);
        User user2= new User("maria","sdfsfda","094354", "parolamaria", 85);
        List<User> clienti = new ArrayList<>();
        clienti.add(user1);
        clienti.add(user2);
        Flight zbor= new Flight(1,"2", "12", "23","00",clienti,"bucuresti","paris");

        System.out.println(zbor);
    }
}