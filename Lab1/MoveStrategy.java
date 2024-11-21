package Lab1;

interface MoveStrategy {
    void move(int x, int y);
}

// Реализация стратегии: Пешком
class WalkStrategy implements MoveStrategy {
    @Override
    public void move(int x, int y) {
        System.out.println("Герой идет пешком к координатам (" + x + ", " + y + ").");
    }
}

// Реализация стратегии: На лошади
class HorseRideStrategy implements MoveStrategy {
    @Override
    public void move(int x, int y) {
        System.out.println("Герой едет на лошади к координатам (" + x + ", " + y + ").");
    }
}

// Реализация стратегии: Лететь
class FlyStrategy implements MoveStrategy {
    @Override
    public void move(int x, int y) {
        System.out.println("Герой летит к координатам (" + x + ", " + y + ").");
    }
}