package chapter5;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStream {
    public static void main(String... args) throws Exception {

        // Stream.of
        System.out.println("Stream.of");
        Stream<String> stream = Stream.of("Java 8", " Lambdas", " In", " Action");
        stream.map(String::toUpperCase).forEach(System.out::print);

        // Stream.empty
        System.out.println("Stream.empty");
        Stream<String> emptyStream = Stream.empty();

        // Arrays.stream
        System.out.println("Arrays.stream");
        int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays.stream(numbers).sum());

        // Stream.iterate
        System.out.println("Stream.iterate");
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::print);

        // fibonnaci with iterate
        System.out.println("fibonnaci with iterate (a,b)");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));

        // fibonnaci with iterate
        System.out.println("fibonnaci with iterate with limit 10");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);

        //  stream of 1s with Stream.generate
        System.out.println("stream of 1s with Stream.generate");
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        // stream of 1s with Stream.generate
        System.out.println("random stream of doubles with Stream.generate");
        Stream.generate(() -> 1)
                .limit(5)
                .forEach(System.out::print);

        IntStream.generate(new IntSupplier() {
            public int getAsInt() {
                return 2;
            }
        }).limit(5)
                .forEach(System.out::println);

        //IntSupplier example
        IntSupplier fib = new IntSupplier() {
            int current = 0;
            int previous = 1;

            @Override
            public int getAsInt() {
                int nextValue = this.current + this.previous;
                this.previous = this.current;
                this.current = nextValue;
                return this.previous;
            }

            ;
        };
        System.out.println("IntSupplier example");
        IntStream.generate(fib).limit(10).forEach(System.out::print);

        System.out.println("Unique words in data.txt");
        long uniqueWords = Files.lines(Paths.get("lambdasinaction/chap5/data.txt"), Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();

        System.out.println("There are " + uniqueWords + " unique words in data.txt");

    }
}
