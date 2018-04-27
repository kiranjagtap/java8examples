package chapter1and2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {

    public static void main(String... arg) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        //Before lambda
        List<Apple> oldWayGreenAppleWeight80 = filterHeavyApples(inventory);

        //using predicate
        List<Apple> weightGretterThan80 = filter(inventory, new AppleWeightPredicate());
        System.out.print(weightGretterThan80);

        //using lambda
        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenAppleWeight80 = filterApple(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.print(greenAppleWeight80);

        List<Apple> appleWeight80 = filterApple(inventory, (Apple a) -> a.getWeight()==80);
        System.out.print(appleWeight80);

        //[Apple{color='green', weight=155}, Apple{color='red', weight=120}]
        List<Apple> appleWeightGretterThank80 = filterApple(inventory, (Apple a) -> a.getWeight()>80);
        System.out.print(appleWeightGretterThank80);


        //Method reference

        //Static method reference
        List<Apple> greenApples = filterApple(inventory, FilteringApples::isGreenApple);
        System.out.print(greenApples);

        List<Apple> heavyApples = filterApple(inventory, FilteringApples::isHeavy);
        System.out.print(heavyApples);

        //Method reference using instance
        FilteringApples filterApple = new FilteringApples();
        List<Apple> heavyApplesUsingInstance = filterApple(inventory, filterApple::isAppleHeavy);
        System.out.print(heavyApples);

        //Method reference using constructor
        AppleFactory1 apple1 = i->new Apple(i);
        AppleFactory1 apple2 = Apple::new;

        //using 2 argument constructor
        AppleFactory2 apple3 = Apple::new;


    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavy(Apple apple) {
        return apple.getWeight()>80;
    }

    public boolean isAppleHeavy(Apple apple) {
        return apple.getWeight()>80;
    }
    //Old way
    public static List<Apple> filterHeavyApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApple(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> resu1t = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                resu1t.add(apple);
            }
        }
        return resu1t;
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> resu1t = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                resu1t.add(apple);
            }
        }
        return resu1t;
    }

    interface AppleFactory1{
        public Apple create(int weight);
    }

    interface ApplePredicate{
        public boolean test(Apple a);
    }
    static class AppleWeightPredicate implements ApplePredicate{

        @Override
        public boolean test(Apple a) {
            return a.getWeight()>80;
        }
    }

    interface AppleFactory2{
        public Apple create(int weight, String color);
    }
    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple( int weight) {
            this.weight = weight;

        }

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

}
