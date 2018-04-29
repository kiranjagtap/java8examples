package chapter4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StreamAPI {
    private static List<Dish> menu;

    static {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    public static void main(String... arg) {

        /* Before Java 7 */
        System.out.println("Stream using group by Java 7");
        getLowCaroriesUsingJava7(menu).forEach(System.out::println);

        System.out.println("---");
        /* In Java 8 */
        System.out.println("Stream using group by Java 8");
        getLowCaloriesUsingJava8(menu).forEach(System.out::println);

        /* using parllel Stream */
        getLowCaloriesUsingParallelStreamInJava8(menu).forEach(System.out::println);

        /* Stream using group by */
        Map<Dish.Type, List<Dish>> dishesByType = getUsingGroupByInJava8(menu);
        System.out.println("Stream using group by Java 8 - " + dishesByType);

        /* How would you use streams to filter the first two meat dishes? */
        filterOnDishType(menu).forEach(System.out::println);

        //Traversable only once
       /* List<String> title = Arrays.asList("a1","a2","a3");
        Stream<String> stream= title.stream();
        stream.forEach(System.out::print);
        stream.forEach(System.out::print);*/

        /* Filtering unique elements */
        System.out.println("Filtering unique Elelemts");
        filterUniqueElements();

        System.out.println("Truncating Stream");
        truncateStream(menu, 1).forEach(System.out::print);

        /* skip fisrt n elements */
        System.out.println("Skipping elements in Stream");
        skipElementsInStream(menu, 2).forEach(System.out::print);

        System.out.println("flattening in Stream");
        flatteningStream(Arrays.asList("Hello", "Worlds"));

        /* flattening Stream */
        System.out.println("apply function to each element of Stream using map");
        applyFunctionToEachElementOfStreamUsingMap(menu, 2).forEach(System.out::print);

        /* falttening example
         * Given list(1,2,3,4,5) produce output (1,4,9,16,25)
         */

        findSquaresOfEachNumberInList(Arrays.asList(1, 2, 3, 4, 5));

        findPairOfEachNumberWithList();


    }

    private static List<Integer> findSquaresOfEachNumberInList(List<Integer> integers) {
        return integers.stream()
                .map(n -> n * n)
                .collect(toList());
    }

    private static void findPairOfEachNumberWithList() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap((Integer i) -> numbers2.stream()
                                .map((Integer j) -> new int[]{i, j})
                        )
                        .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                        .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));

    }



    public static List<String> applyFunctionToEachElementOfStreamUsingMap(List<Dish> menu, int i) {
        return menu.stream()
                .map(Dish::getName)
                .collect(toList());
    }

    public static List<String> flatteningStream(List<String> words) {
        return words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Dish> skipElementsInStream(List<Dish> menu, int n) {
        return menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(n)
                .collect(toList());
    }

    public static List<Dish> truncateStream(List<Dish> menu, int n) {
        return menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(2)
                .collect(toList());
    }

    public static List<Integer> filterUniqueElements() {
        List<Integer> list = Arrays.asList(1, 2, 3, 2, 2, 1, 4);
        return list.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(toList());
    }

    public static List<String> getLowCaroriesUsingJava7(List<Dish> menu) {
        List<Dish> lowCaloriesDishes = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaloriesDishes.add(dish);
            }
        }

        Collections.sort(lowCaloriesDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        List<String> lowCaloriesDishNames = new ArrayList<>();
        for (Dish dish : lowCaloriesDishes) {
            lowCaloriesDishNames.add(dish.getName());

        }
        return lowCaloriesDishNames;
    }

    public static List<String> getLowCaloriesUsingJava8(List<Dish> menu) {
        return menu
                .stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(d -> d.getName())
                .collect(toList());

    }

    public static List<String> getLowCaloriesUsingParallelStreamInJava8(List<Dish> menu) {

        return menu
                .parallelStream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(d -> d.getName())
                .limit(2)
                .collect(toList());
    }

    public static Map<Dish.Type, List<Dish>> getUsingGroupByInJava8(List<Dish> menu) {

        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().
                        collect(
                                groupingBy(Dish::getType)
                        );
        return dishesByType;
    }

    public static List<Dish> filterOnDishType(List<Dish> menu) {
        return menu
                .stream()
                .filter(d -> d.getType() == Dish.Type.MEAT).limit(2)
                .collect(toList());

    }
}
