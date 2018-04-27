package chapter4;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StreamAPITest {
    private static List<Dish>
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

    List<String> words = Arrays.asList("Hello", "Worlds");

    @Test
    public void testFilterUniqueElements() {
        StreamAPI.filterUniqueElements();
        assertThat(Arrays.asList(2, 4), is(StreamAPI.filterUniqueElements()));
    }

    @Test
    public void TestTruncateStream() {
        StreamAPI.truncateStream(menu, 1).forEach(System.out::print);
    }

    @Test
    public void testSkipElementsInStream() {
        StreamAPI.skipElementsInStream(menu, 4).forEach(System.out::print);
    }

    @Test
    public void flatteningStream() {
        StreamAPI.flatteningStream(words).forEach(System.out::print);

    }

    @Test
    public void getLowCaroriesUsingJava7() {
    }

    @Test
    public void getLowCaloriesUsingJava8() {
    }

    @Test
    public void getLowCaloriesUsingParallelStreamInJava8() {
    }

    @Test
    public void getUsingGroupByInJava8() {
    }

    @Test
    public void filterOnDishType() {
    }
}