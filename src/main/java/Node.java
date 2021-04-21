import java.time.LocalTime;
import java.util.Objects;

public class Node {
    private LocalTime time;
    private int anInt;
    private float aFloat;

    public Node() {
    }

    public Node(LocalTime time, int anInt, float aFloat) {
        this.time = time;
        this.anInt = anInt;
        this.aFloat = aFloat;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public float getaFloat() {
        return aFloat;
    }

    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    @Override
    public String toString() {
        return "Node{" +
                "time=" + time +
                ", anInt=" + anInt +
                ", aFloat=" + aFloat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return anInt == node.anInt && Float.compare(node.aFloat, aFloat) == 0 && Objects.equals(time, node.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, anInt, aFloat);
    }
}
