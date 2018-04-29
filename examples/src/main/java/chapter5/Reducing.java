package chapter5;

import chapter4.Dish;
import static chapter4.Dish.menu;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reducing {
    public static void main(String... args){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        int sum = numbers.stream().reduce(0,(a, b)->(a+b));
        System.out.println(sum);

        Optional<Integer> sum2 = numbers.stream().reduce((a, b)->(a+b));
        System.out.println(sum2);

        int max1 = numbers.stream().reduce(0,Integer::max);
        System.out.println(max1);

        Optional<Integer> max2 = numbers.stream().reduce(Integer::max);
        System.out.println(max2);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);


    }
}
