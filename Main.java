import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

interface Move {
    int move(int start);
}

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Hero myHero = new Hero();
        String command = "";

        System.out.println("Выберите один из вариантов:");
        System.out.println("1 - Показать текущее положение");
        System.out.println("2 - Ползти");
        System.out.println("3 - Идти");
        System.out.println("4 - Бежать");
        System.out.println("5 - Ехать");
        System.out.println("q - Выход");

        while (!command.equals("q")) {
            if (myHero.getPosition() >= 50) {System.out.println("В лесу"); break;}
            command = in.next();

            switch (command) {
                case "1" -> { if (myHero.getPosition() == 0) {System.out.println("Дома");}
                else { System.out.println("В дороге"); }
                     }
                case "2" -> {
                    myHero.setMethodOfMovement("2");
                    myHero.move();
                                  }
                case "3" -> {
                    myHero.setMethodOfMovement("3");
                    myHero.move();
                }
                case "4" -> {
                    myHero.setMethodOfMovement("4");
                    myHero.move();
                }
                case "5" -> {
                    myHero.setMethodOfMovement("5");
                    myHero.move();
                }
                default -> {
                        System.out.println("Неверно выбранный символ. Повторите ввод");
                        continue;
                }

            }
            System.out.println(" ");
            System.out.println("Выберите один из вариантов:");
            System.out.println("1 - Показать текущее положение");
            System.out.println("2 - Ползти");
            System.out.println("3 - Идти");
            System.out.println("4 - Бежать");
            System.out.println("5 - Ехать");
            System.out.println("q - Выход");
        }
        in.close();
    }
}

class Hero {
    private static final Map<String, Move> movementMethods = new HashMap<>();
    private Move methodOfMovement;
    private int position;

    static {
        movementMethods.put("2", new crawl());
        movementMethods.put("3", new walk());
        movementMethods.put("4", new run());
        movementMethods.put("5", new drive());
    }

    public void move() {
        position = methodOfMovement.move(position);
    }

    public int getPosition() {
        return position;
    }

    public void setMethodOfMovement(final String methodOfMovement) {
        this.methodOfMovement = movementMethods.getOrDefault(methodOfMovement, this.methodOfMovement);
    }

}
