package Lab2;

public class SubMetods {


    public void item0(String name) {
        System.out.println("Start calculation");
    }

    public int item2(int mass, int molWeight) {
        return mass / molWeight;
    }

    @SubAnnotation(value = 3)
    protected void item3(int mass, int molWeight, double count) {
        System.out.println("Amount particle: " + (item2(mass, molWeight) * count));
    }

    @SubAnnotation()
    protected void item4(int mass, int molWeight) {
        System.out.println("Amount substance: " + (item2(mass, molWeight) * 22.4));
    }

    @SubAnnotation(value = 1)
    private void item1(String name) {
        System.out.println("Chemical substance: " + name);
    }

    @SubAnnotation(value = 1)
    private void item5() {
        System.out.println("*According to the last year's data");
    }

    @SubAnnotation(value = 4)
    private void item6(int mass) {
        System.out.println("Density:" + mass * 1000);
    }
}
