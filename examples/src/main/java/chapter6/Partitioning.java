package chapter6;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static chapter6.Dish.menu;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Partitioning {
    public static void main(String... args) {
        System.out.println("Dishes partitioned by vegetarian: " + partitionByVegeterian());
        System.out.println("Dishes partitioned by vegetarian and collect in List<Dish>: " + partitionByVegeterianCollectInList());
        System.out.println("Dishes partitioned by vegetarian and group by Type: " + partitionByVegeterianGroupByType());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());
    }

    private static Map<Boolean,Dish> mostCaloricPartitionedByVegetarian() {
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get
                            )
                ));
    }

    //we have seen using double groupingBy
    private static Map<Boolean, Map<Dish.Type, List<Dish>>> partitionByVegeterianGroupByType() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    private static List<Dish> partitionByVegeterianCollectInList() {
        return menu.stream().filter(Dish::isVegetarian).collect(toList());
    }

    private static Map<Boolean, List<Dish>> partitionByVegeterian() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }


}
