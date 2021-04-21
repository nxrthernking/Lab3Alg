import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

public class Runner {

    public static final int HOUR = 24;
    public static final int MIN_SEC = 60;
    public static final int ARRAY_SIZE = 10;

    public static void main(String[] args) {
        Random random = new Random();
        Node[] nodes1 = new Node[ARRAY_SIZE];
        Node[] nodes2 = new Node[ARRAY_SIZE];
        Node[] nodes3 = new Node[ARRAY_SIZE];
        Node[] nodes4 = new Node[ARRAY_SIZE];
        Node node = null;
        LocalTime time;
        for (int i = 0; i < ARRAY_SIZE; i++) {
            time = LocalTime.of(random.nextInt(HOUR), random.nextInt(MIN_SEC), random.nextInt(MIN_SEC));
//            time = LocalTime.of(11,11,11);
            node = new Node(time, random.nextInt(100), random.nextFloat());
            nodes1[i] = node;
            nodes2[i] = node;
            nodes3[i] = node;
            nodes4[i] = node;
        }


        System.out.println("Сортировка по 1 критерию");
        sortByOneCriteria(nodes1);
        print(nodes1);

        System.out.println("Сортировка по 2 критериям");
        sortByTwoCriteria(nodes2);
//        print(nodes2);

        System.out.println("Сортировка по 3 критериям");
        sortByThreeCriteria(nodes3);
//        print(nodes3);


        System.out.println("Сортировка с проверкой на каждом шаге");
        lastSort(nodes4);
//        print(nodes4);

    }

    private static void sortByOneCriteria(Node[] nodes) {
        Node tmp;
        int swapCount = 0;
        int numberOfComparisons = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                if (nodes[i].getTime().isBefore(nodes[j].getTime())) {
                    swap(nodes, i, j);
                    swapCount++;
                }
                numberOfComparisons++;
            }
        }
        long resultTIme = System.currentTimeMillis() - start;
        extracted(swapCount, numberOfComparisons, resultTIme);
    }

    private static void extracted(int swapCount, int numberOfComparisons, long resultTIme) {
        System.out.println("Количество сравнений: " + numberOfComparisons);
        System.out.println("Количество перестановок: " + swapCount);
        System.out.println("Время: " + resultTIme);
    }

    private static void sortByTwoCriteria(Node[] nodes) {

        int swapCount = 0;
        int numberOfComparisons = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                if (nodes[i].getTime().isBefore(nodes[j].getTime())) {
                    swap(nodes, i, j);
                    swapCount++;
                } else if (nodes[i].getTime().compareTo(nodes[j].getTime()) == 0) {
                    numberOfComparisons++;
                    if (nodes[i].getAnInt() > nodes[j].getAnInt()) {
                        swap(nodes, i, j);
                        swapCount++;
                    }
                }
                numberOfComparisons += 2;
            }
        }

        long resultTIme = System.currentTimeMillis() - start;
        extracted(swapCount, numberOfComparisons, resultTIme);
    }

    private static void sortByThreeCriteria(Node[] nodes) {

        int swapCount = 0;
        int numberOfComparisons = 0;
        long start = System.currentTimeMillis();

        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                if (nodes[i].getTime().isBefore(nodes[j].getTime())) {
                    swap(nodes, i, j);
                    swapCount++;
                } else if (nodes[i].getTime().compareTo(nodes[j].getTime()) == 0) {
                    if (nodes[i].getAnInt() > nodes[j].getAnInt()) {
                        swap(nodes, i, j);
                        swapCount++;
                    } else if (nodes[i].getAnInt() == nodes[j].getAnInt()) {
                        numberOfComparisons++;
                        if (nodes[i].getaFloat() > nodes[j].getaFloat()) {
                            swap(nodes, i, j);
                            swapCount++;
                        }
                    }
                    numberOfComparisons += 2;
                }
                numberOfComparisons += 2;
            }
        }

        long resultTIme = System.currentTimeMillis() - start;
        extracted(swapCount, numberOfComparisons, resultTIme);
    }

    private static void swap(Node[] nodes, int i, int j) {
        Node tmp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = tmp;
    }


    private static void print(Node[] nodes) {
        Arrays.stream(nodes).forEach(System.out::println);
    }

    private static void lastSort(Node[] nodes) {
        boolean isSorted = false;

        int swapCount = 0;
        int numberOfComparisons = 0;
        long start = System.currentTimeMillis();

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < nodes.length - 1; i++) {
                if (nodes[i].getTime().isAfter(nodes[i + 1].getTime())) {
                    swap(nodes, i, i + 1);
                    isSorted = false;
                    swapCount++;
                } else if (nodes[i].getTime().compareTo(nodes[i + 1].getTime()) == 0) {
                    if (nodes[i].getAnInt() < nodes[i + 1].getAnInt()) {
                        swap(nodes, i, i + 1);
                        swapCount++;
                        isSorted = false;
                    } else if (nodes[i].getAnInt() == nodes[i + 1].getAnInt()) {
                        numberOfComparisons++;
                        if (nodes[i].getaFloat() < nodes[i + 1].getaFloat()) {
                            swap(nodes, i, i + 1);
                            swapCount++;
                            isSorted = false;
                        }
                    }
                    numberOfComparisons += 2;
                }
                numberOfComparisons += 2;
            }
        }

        long resultTIme = System.currentTimeMillis() - start;
        extracted(swapCount, numberOfComparisons, resultTIme);
    }

    private static int interpolationSearchByTime(Node[] nodes, Node elementToSearch) {
        int startIndex = 0;
        int lastIndex = nodes.length;
        
        while ((startIndex <= lastIndex) && elementToSearch.getAnInt() >= nodes[startIndex].getAnInt()
                && elementToSearch.getAnInt() <= nodes[lastIndex].getAnInt()) {
            int pos = startIndex + ((lastIndex - startIndex) /
                    (nodes[lastIndex].getAnInt() - nodes[startIndex].getAnInt()
                            * elementToSearch.getAnInt() - nodes[startIndex].getAnInt()));
            if (nodes[pos].getAnInt() == elementToSearch.getAnInt()) {
                return pos;
            }

            if (nodes[pos].getAnInt() < elementToSearch.getAnInt()) {
                startIndex = pos + 1;
            } else {
                lastIndex = pos + 1;
            }

        }

        return  -1;
    }

}
