import java.time.LocalDate;
import java.util.Objects;

public class DateNode {

    private LocalDate date1;
    private LocalDate date2;

    public DateNode(LocalDate date1, LocalDate date2) {
        this.date1 = date1;
        this.date2 = date2;
    }

    public LocalDate getDate1() {
        return date1;
    }

    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }

    public LocalDate getDate2() {
        return date2;
    }

    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateNode dateNode = (DateNode) o;
        return Objects.equals(date1, dateNode.date1) && Objects.equals(date2, dateNode.date2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date1, date2);
    }

    @Override
    public String toString() {
        return "DateNode{" +
                "date1=" + date1 +
                ", date2=" + date2 +
                '}';
    }
}
