package day01.src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class maxCalories {
    public static void main(String[] args) {
        String filename = "day01/src/data/input.txt";

        try {
            List<String> list = toList(filename);
            findSolution(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static List<String> toList(String filename) throws IOException {
        List<String> result;
        try (Stream<String> calories = Files.lines(Paths.get(filename))){
            result = calories.collect(Collectors.toList());
        }
        return result;
    }

    private static void findSolution(List<String> list) {
        int currentAmount = 0;
        int firstPlace = 0;
        int secondPlace = 0;
        int thirdPlace = 0;
        for (String calorie : list) {
            if (!calorie.isEmpty()) {
                currentAmount += Integer.parseInt(calorie);
            } else {
                if (currentAmount > firstPlace) {
                    int tempSecond = firstPlace;
                    int tempThird = secondPlace;
                    firstPlace = currentAmount;
                    secondPlace = tempSecond;
                    thirdPlace = tempThird;
                } else if (currentAmount > secondPlace && currentAmount < firstPlace) {
                    int tempThird = secondPlace;
                    secondPlace = currentAmount;
                    thirdPlace = tempThird;
                } else if (currentAmount > thirdPlace && currentAmount < secondPlace) {
                    thirdPlace = currentAmount;
                }
                currentAmount = 0;
            }

        }
        System.out.println("Max amount: " + firstPlace);
        System.out.println("First: " + firstPlace + "\nSecond: " + secondPlace + "\nThird: " + thirdPlace);
        System.out.println("Top three total: " + (firstPlace + secondPlace + thirdPlace));
    }
}