package chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class PracticeOnStream {
    private static List<Transaction> transactions = null;

    public static void main(String... arg) {

        /* Query 1: Find all transactions from year 2011 and sort them by value (small to high). */

        List<Transaction> tr2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());
        System.out.println("Query 1: Find all transactions from year 2011 and sort them by value (small to high).");
        System.out.println(tr2011);
        System.out.println();

        /* Query 2: What are all the unique cities where the traders work? */

        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println("Query 2: What are all the unique cities where the traders work?");
        System.out.println(cities);
        System.out.println();

        /* Query 3: Find all traders from Cambridge and sort them by name. */

        List<Transaction> tradersFromCambridge1 = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(transaction -> transaction.getTrader().getName()))
                .collect(toList());
        System.out.println("Query 3: Find all traders from Cambridge and sort them by name.");
        System.out.println(tradersFromCambridge1);
        System.out.println();

        List<Trader> tradersFromCambridge2 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());
        System.out.println("Query 3: Find all traders from Cambridge and sort them by name.");
        System.out.println(tradersFromCambridge2);
        System.out.println();

        /*Query 4: Return a string of all traders’ names sorted alphabetically
         * Note this solution isn't efficient using joining() is more efficient which internally use StringBuilder
         */

        String traders = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println("Query 4: Return a string of all traders’ names sorted alphabetically");
        System.out.println(traders);
        System.out.println();

        /* Query 5: Are there any trader based in Milan? */

        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println("Query 5: Are there any trader based in Milan?");
        System.out.println(milanBased);
        System.out.println();

        /*Query 6: Print all transactions values from the traders living in Cambridge*/

        System.out.println("Query 6: Print all transactions values from the traders living in Cambridge");
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        System.out.println();

        /* Query 7: Update all transactions so that the traders from Milan are set to Cambridge. */

        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Milan"))
                .forEach(t -> t.setCity("Cambrodge"));
        System.out.println("Query 7: Update all transactions so that the traders from Milan are set to Cambridge. ");
        System.out.println(transactions);
        System.out.println();

        /* Query 8: What's the highest value in all the transactions? */

        int highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println("Query 8: What's the highest value in all the transactions?");
        System.out.println(highestValue);
    }

    static {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }
}
