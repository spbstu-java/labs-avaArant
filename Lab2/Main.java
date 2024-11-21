package Lab2;

public class Main {
    public static void main(String[] args) {
        try {
            SubMetods mysub = new SubMetods();
            DescribeCall item = new DescribeCall();
            item.repeatAnn(mysub);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}