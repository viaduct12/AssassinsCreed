import java.io.IOException;

public class AssassinDriver {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Mortal Kombat!!!");
        AssassinManager game = new AssassinManager("src/test/resources/players");

        System.out.println("Welcome to Mortal Kombat!!!");
        AssassinManager game1 = new AssassinManager("src/test/resources/morePlayers");
    }
}
