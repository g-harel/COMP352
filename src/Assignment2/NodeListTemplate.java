package Assignment2;

public interface NodeListTemplate {

    Position first() throws EmptyListException;

    Position last() throws EmptyListException;

    Position prev(Position pos) throws ArrayIndexOutOfBoundsException;

    Position next(Position pos) throws ArrayIndexOutOfBoundsException;

    Position set(Position pos, int val) throws ArrayIndexOutOfBoundsException;

    Position addFirst(int val);

    Position addLast(int val);

    Position addBefore(Position pos, int val) throws ArrayIndexOutOfBoundsException;

    Position addAfter(Position pos, int val) throws ArrayIndexOutOfBoundsException;

    void delete(Position pos) throws ArrayIndexOutOfBoundsException;

    void swap(int p1, int p2) throws ArrayIndexOutOfBoundsException;

    void truncate();

    void setExpansionRule(char rule);
}
