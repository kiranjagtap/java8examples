package chapter6;

import static chapter6.Dish.menu;
import static java.util.stream.Collectors.reducing;

public class Reducing {
    public static void main(String... args) {
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Calculate Total Calories With Method Reference: " + calculateTotalCaloriesWithMethodReference());
        System.out.println("Calculate Total Calories Without Collectors: " + calculateTotalCaloriesWithoutCollectors());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
    }

    private static int calculateTotalCalories() {
        return menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
    }

    private static Integer calculateTotalCaloriesWithMethodReference() {
        return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }


    private static int calculateTotalCaloriesWithoutCollectors() {
        return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    private static int calculateTotalCaloriesUsingSum() {
        return menu.stream().mapToInt(Dish::getCalories).sum();
    }

}
