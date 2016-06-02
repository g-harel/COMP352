package Assignment2;

public class NodeList {

    private Position[] data;
    private int last_pos = -1;
    private char rule;

    public NodeList() {
        data = new Position[2];
        rule = 'd';
        System.out.println("New NodeList\n>" + this.toString() + "\n");
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i <= last_pos; i++) {
            output += data[i].toString();
        }
        output += "\nlength: " + data.length + " items: " + (last_pos+1);
        return output;
    }

    private boolean fits() {
        if ((last_pos+2.0)/data.length < 0.8) {
            return true;
        } else {
            return false;
        }
    }

    private Position[] expand() {
        int length;
        if (rule =='d') {
            length = data.length*2;
        } else {
            length = data.length+10;
        }
        Position[] temp = new Position[length];
        return temp;
    }

    public Position first() throws EmptyListException {
        if (last_pos > 0) {
            return data[0];
        } else {
            throw new EmptyListException();
        }
    }

    public Position last() throws EmptyListException {
        if (last_pos >= 0) {
            return data[last_pos];
        } else {
            throw new EmptyListException();
        }
    }

    public Position prev(Position pos) throws ArrayIndexOutOfBoundsException {
        for (int i = 1; i < last_pos; i++) {
            if (data[i] == pos) {
                return data[i-1];
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public Position next(Position pos) throws ArrayIndexOutOfBoundsException {
        for (int i = 0; i <= last_pos-1; i++) {
            if (data[i] == pos) {
                return data[i+1];
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int set(Position pos, int val) throws ArrayIndexOutOfBoundsException {
        for (int i = 1; i <= last_pos; i++) {
            if (data[i] == pos) {
                int temp = data[i].element();
                data[i] = new Position(val);
                System.out.println("Set " + pos + " to " + val + "\n>" + this.toString() + "\n");
                return temp;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public Position addFirst(int val) {
        Position[] temp;
        if (!fits()) {
            temp = expand();
        } else {
            temp = new Position[data.length];
        }
        temp[0] = new Position(val);
        for (int i = 0; i <= last_pos; i++) {
            temp[i+1] = data[i];
        }
        data = temp;
        last_pos++;
        System.out.println("AddFirst: " + val + "\n>" + this.toString() + "\n");
        return temp[0];
    }

    public Position addLast(int val) {
        if (!fits()) {
            Position[] temp = expand();
            for (int i = 0; i <= last_pos; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
        last_pos++;
        data[last_pos] = new Position(val);
        System.out.println("AddLast: " + val + "\n>" + this.toString() + "\n");
        return data[last_pos];
    }

    public Position addBefore(Position pos, int val) throws ArrayIndexOutOfBoundsException {
        Position[] temp;
        int index = -1;
        if (!fits()) {
            temp = expand();
        } else {
            temp = new Position[data.length];
        }
        for (int i = 0; i <= last_pos; i++) {
            if (data[i] == pos) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0, j = 0; i <= last_pos; i++, j++) {
            if (j == index) {
                temp[j] = new Position(val);
                j++;
            }
            temp[j] = data[i];
        }
        data = temp;
        last_pos++;
        System.out.println("addBefore: " + pos.element() + " val: " + val + "\n>" + this.toString() + "\n");
        return pos;
    }

    public Position addAfter(Position pos, int val) throws ArrayIndexOutOfBoundsException {
        Position[] temp;
        int index = -1;
        if (!fits()) {
            temp = expand();
        } else {
            temp = new Position[data.length];
        }
        for (int i = 0; i <= last_pos; i++) {
            if (data[i] == pos) {
                index = i+1;
                break;
            }
        }
        if (index == -1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0, j = 0; i <= last_pos; i++, j++) {
            if (j == index) {
                temp[j] = new Position(val);
                j++;
            }
            temp[j] = data[i];
        }
        data = temp;
        last_pos++;
        System.out.println("addAfter: " + pos.element() + " val: " + val + "\n>" + this.toString() + "\n");
        return pos;
    }

    public void delete(Position pos) throws ArrayIndexOutOfBoundsException{
        Position[] temp;
        int index = -1;
        temp = new Position[data.length];
        for (int i = 0; i <= last_pos; i++) {
            if (data[i] == pos) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0, j = 0; i <= last_pos; i++, j++) {
            if (j == index) {
                i++;
            }
            temp[j] = data[i];
        }
        data = temp;
        last_pos--;
        System.out.println("delete: " + pos.element() + "\n>" + this.toString() + "\n");
    }

    public void swap(int p1, int p2) throws ArrayIndexOutOfBoundsException{
        int temp = data[p1].element();
        data[p1].set(data[p2].element());
        data[p2].set(temp);
        System.out.println("Swap: " + p1 + " and " + p2 + "\n>" + this.toString() + "\n");
    }

    public void truncate() {
        Position[] temp = new Position[last_pos+1];
        for (int i = 0; i <= last_pos; i++) {
            temp[i] = data[i];
        }
        data = temp;
        System.out.println("Truncate:\n>" + this.toString() + "\n");
    }

    public void setExpansionRule(char rule) {
        if (rule == 'd' || rule == 'c') {
            this.rule = rule;
        }
    }
}
