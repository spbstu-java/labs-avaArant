package Lab1;

class Hero {
    private int x;
    private int y;
    private MoveStrategy moveStrategy;

    // Конструктор, который инициализирует начальные координаты
    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Устанавливаем стратегию перемещения
    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    // Метод перемещения
    public void move(int newX, int newY) {
        if (moveStrategy != null) {
            moveStrategy.move(newX, newY);
            x = newX;
            y = newY;
            System.out.println("Перемещение в координаты(" + x + ", " + y + ").");
        } else {
            System.out.println("Способ перемещения не выбран.");
        }
    }

    // Получение текущих координат
    public void getCoordinates() {
        System.out.println("Текущие координаты: (" + x + ", " + y + ").");
    }
}