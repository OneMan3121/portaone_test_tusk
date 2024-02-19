import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String filePath;
        try {
            filePath = args[0];
        } catch (ArrayIndexOutOfBoundsException e ) {
            throw new ArrayIndexOutOfBoundsException("Enter file path in args");
        }


        List<Integer> numbers = Files.lines(Path.of(filePath))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // Max Increasing
        List<Integer> currentIncreasingSequence = new ArrayList<>();
        List<Integer> longestIncreasingSequence = new ArrayList<>();
        // Min Decreasing
        List<Integer> currentDecreasingSequence = new ArrayList<>();
        List<Integer> longestDecreasingSequence = new ArrayList<>();


        for (Integer number : numbers) {
            if (currentIncreasingSequence.isEmpty() || number > currentIncreasingSequence.get(currentIncreasingSequence.size() - 1)) {
                currentIncreasingSequence.add(number);
            } else {
                currentIncreasingSequence = new ArrayList<>();
                currentIncreasingSequence.add(number);
            }
            if (currentDecreasingSequence.isEmpty() || number < currentDecreasingSequence.get(currentDecreasingSequence.size() - 1)) {
                currentDecreasingSequence.add(number);
            } else {
                currentDecreasingSequence = new ArrayList<>();
                currentDecreasingSequence.add(number);
            }

            if( currentDecreasingSequence.size() > longestDecreasingSequence.size()) {
                longestDecreasingSequence = new ArrayList<>(currentDecreasingSequence);
            }
            if (currentIncreasingSequence.size() > longestIncreasingSequence.size()) {
                longestIncreasingSequence = new ArrayList<>(currentIncreasingSequence);
            }
        }

        BigInteger sum = numbers.stream().map(BigInteger::valueOf).reduce(BigInteger.ZERO, BigInteger::add);
        BigInteger avarageNumber = sum.divide(BigInteger.valueOf(numbers.size()));


        numbers.sort(Integer::compareTo);

//        numbers.stream().toList().forEach(System.out::println);

        int minNumber = numbers.get(0);
        int maxNumber = numbers.get(numbers.size()-1);







        double median = getMedianFromListInt(numbers);






        System.out.println("Maximal Number: " + maxNumber);
        System.out.println("Mininal Number: " + minNumber);
        System.out.println("Median: " + median);
        System.out.println("Avarage Numbers: " + avarageNumber );
        System.out.print("Longest іncreasing sequence of numbers: ");
        longestIncreasingSequence.forEach(element -> System.out.print(element + "  "));
        System.out.print("\nLongest decreasing sequence of numbers: ");
        longestDecreasingSequence.forEach(element -> System.out.print(element + "  "));

        long end = System.currentTimeMillis();
        System.out.println("\nSpend time: " + (end - start) / 1000);



    }



    /**
     * @param list expects a list of implemented Number.
     * @return  returns the type Double.
     */
    public static Double getMedianFromListInt(List<? extends Number> list) {
        int size = list.size();
        if (size % 2 == 0) {

            return list.get(size / 2 - 1).doubleValue() + list.get(size / 2).doubleValue()  / 2.0;
        } else {

            return list.get(size / 2).doubleValue();
        }

    }











}