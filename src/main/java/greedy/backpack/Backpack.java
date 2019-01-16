package greedy.backpack;

import java.util.*;
import java.util.stream.Collectors;

class Thing {
    public Double price;
    public Integer volume;
    public Double pricePerOne;
    Thing(Double price, Integer volume) {
        this.price = price;
        this.volume = volume;
        if (this.volume != 0) {
            this.pricePerOne = price / Double.valueOf(volume);
        } else {
            this.pricePerOne = 0D;
        }
    }

    public String toString() {
        return "Price: " + this.pricePerOne + "; " + "volume " + this.volume;
    }
}

class Backpack {
    private static List<Thing> sortByPrice(List<Thing> things) {
        List<Thing> sorted = things.stream()
                .filter(thing -> thing.volume > 0 && thing.pricePerOne > 0)
                .sorted(Comparator.comparingDouble(line -> line.pricePerOne))
                .collect(Collectors.toList());
        Collections.reverse(sorted);
        return sorted;
    }

    private static Double pick(List<Thing> things, Integer maxWeight) {
        Double tmpWeight = 0D;
        Double tmpPrice = 0D;
        Iterator<Thing> iterator = things.iterator();
        while (iterator.hasNext() && tmpWeight <= maxWeight) {
            Thing thing = iterator.next();
            if (thing.volume <= 0) {
                continue;
            }
            if (thing.volume > maxWeight - tmpWeight) {
                // Часть веса, который можно взять
                Double partWeight = maxWeight - tmpWeight;
                Double partPrice = partWeight * thing.pricePerOne;
                tmpPrice += partPrice;
                tmpWeight += partWeight;
                break;
            } else {
                tmpPrice += thing.price;
                tmpWeight += Double.valueOf(thing.volume);
            }
        }
        return tmpPrice;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer numThings = scanner.nextInt();
        Integer capacity = scanner.nextInt();
        List<Thing> things = new ArrayList<>();
        for (Integer i = 0; i < numThings; i++) {
            things.add(new Thing(scanner.nextDouble(), scanner.nextInt()));
        }
        List<Thing> sorted = sortByPrice(things);
        Double availableMaxWeight = Math.round(pick(sorted, capacity) * 1000.0) / 1000.0;
        System.out.println(availableMaxWeight);
    }
}
