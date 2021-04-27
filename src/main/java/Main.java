import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static final int CAPACITY = 100;

    public static void main(String[] args) {
        Random random = new Random();
        DateNode[] nodes = new DateNode[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            LocalDate date1 = LocalDate.of(random.nextInt(12) + 2010,
                    random.nextInt(11) + 1, random.nextInt(27) + 1);
//            date1 = LocalDate.of(11,11,11);
            LocalDate date2 = LocalDate.of(random.nextInt(12) + 2010,
                    random.nextInt(11) + 1, random.nextInt(27) + 1);
            nodes[i] = new DateNode(date1, date2);
        }

        sort(nodes);

        Arrays.stream(nodes).forEach(System.out::println);

    }

    private static void sort(DateNode[] nodes) {
        DateNode tmp;
        int counter = 0;
        for (int i = 0; i < nodes.length; i++) {
            for (int j = i; j > 0 && nodes[j - 1].getDate1().compareTo(nodes[j].getDate1()) <= 0; j--) {
                if(nodes[j - 1].getDate1().compareTo(nodes[j].getDate1()) == 0){
                    if(nodes[j - 1].getDate2().compareTo(nodes[j].getDate2()) < 0){
                        tmp = nodes[j - 1];
                        nodes[j - 1] = nodes[j];
                        nodes[j] = tmp;
                        counter++;
                    }
                }else {
                    tmp = nodes[j - 1];
                    nodes[j - 1] = nodes[j];
                    nodes[j] = tmp;
                    counter++;
                }

            }
        }

        System.out.println(counter);

    }
}
