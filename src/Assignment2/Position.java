package Assignment2;

public class Position {
    int val;
    public Position(int i) {
        val = i;
    }

    public int element() {
        return val;
    }

    public void set(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "" + val;
    }
}
