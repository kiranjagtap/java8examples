package chapter1and2;

import com.sun.org.apache.xpath.internal.functions.FuncContains;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class PredicateDemo {

    public static void main(String... arg) {

        /*List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));*/

        Apple a1 = new Apple(12,"Green");
        Apple a2 = new Apple(11,"Red");

        BiFunction<Apple, Apple, Integer> c3 = (Apple aa1, Apple aa2) -> a1.getWeight().compareTo(a2.getWeight());

        System.out.println("Weight Comparision : "+c3.apply(a1,a2));

        List<Integer> result = map(Arrays.asList("Lambda In Action", "Hello World "),(String s)->s.length());
        System.out.println(result);
    }

    public static <T, U, R > List<R> compareUsingPredicate(List<T> list, BiFunction<T, U, R> f){
        List<R> result = new ArrayList<>();
        for(T t:list) {

                //result.add(f.apply(t,u));

        }
        return result;
    }
    public static <T, R> List<R> map(List<T> list, Function<T, R> f){
        List<R> result = new ArrayList<>();
        for(T s: list){
            result.add(f.apply(s));
        }
        return result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight) {
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
