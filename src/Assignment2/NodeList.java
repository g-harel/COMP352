package Assignment2;

public class NodeList implements NodeListTemplate {

    private Position[] data;
    private char rule;
	// variable to track the index of the last position
    private int last_pos = -1;

	// constructor
    public NodeList() {
		// create the underlying array with a size of 2
        data = new Position[2];
        rule = 'd';
        System.out.println("New NodeList\n>" + this.toString() + "\n");
    }

	// toString method to display contents
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i <= last_pos; i++) {
            output += data[i].toString();
        }
        output += "\nlength: " + data.length + " items: " + (last_pos+1);
        return output;
    }

	// checks if the array can fit another value
    private boolean fits() {
        if ((last_pos+2.0)/data.length < 0.8) {
            return true;
        } else {
            return false;
        }
    }

	// creates an array of a bigger size according to the rule variable
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

	// returns the Position of the first array index
    public Position first() throws EmptyListException {
        if (last_pos > 0) {
            return data[0];
        } else {
            throw new EmptyListException();
        }
    }

	// returns the Position of the last array index
    public Position last() throws EmptyListException {
        if (last_pos >= 0) {
            return data[last_pos];
        } else {
            throw new EmptyListException();
        }
    }

	// looks for the specified Position and returns the one before it
    public Position prev(Position pos) throws ArrayIndexOutOfBoundsException {
        for (int i = 1; i < last_pos; i++) {
            if (data[i] == pos) {
                return data[i-1];
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

	// looks for the specified Position and returns the one before it
    public Position next(Position pos) throws ArrayIndexOutOfBoundsException {
        for (int i = 0; i <= last_pos-1; i++) {
            if (data[i] == pos) {
                return data[i+1];
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

	// finds the Position and changes its element value
    public Position set(Position pos, int val) throws ArrayIndexOutOfBoundsException {
        for (int i = 1; i <= last_pos; i++) {
            if (data[i] == pos) {
                Position temp = data[i];
                data[i] = new Position(val);
                System.out.println("Set " + pos + " to " + val + "\n>" + this.toString() + "\n");
                return temp;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

	// adds to the front of the array by creating a new array and remapping all the contents
    public Position addFirst(int val) {
		// creating temp array to transfer values
        Position[] temp;
        if (!fits()) {
            temp = expand();
        } else {
            temp = new Position[data.length];
        }
        temp[0] = new Position(val);
		// filling in all values after index 0
        for (int i = 0; i <= last_pos; i++) {
            temp[i+1] = data[i];
        }
        data = temp;
        last_pos++;
        System.out.println("AddFirst: " + val + "\n>" + this.toString() + "\n");
        return temp[0];
    }
	// adds to the back of the array by creating a new array and remapping all the contents
    public Position addLast(int val) {
		// creating a new array and copying the original if an extra value doesn't fit
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
	// adds a new Position at the index after the specified Position
    public Position addBefore(Position pos, int val) throws ArrayIndexOutOfBoundsException {
		// creating new array to transfer values
        Position[] temp;
        boolean flag = false;
        if (!fits()) {
            temp = expand();
        } else {
            temp = new Position[data.length];
        }
		// filling all values
        for (int i = 0, j = 0; i <= last_pos; i++, j++) {
			// adding the new Position
            if (data[i] == pos) {
                temp[j] = new Position(val);
                j++;
                flag = true;
            }
            temp[j] = data[i];
        }
        if (!flag) {
            throw new ArrayIndexOutOfBoundsException();
        }
        data = temp;
        last_pos++;
        System.out.println("addBefore: " + pos.element() + " val: " + val + "\n>" + this.toString() + "\n");
        return pos;
    }

	// adds a new Position at the index before the specified Position
    public Position addAfter(Position pos, int val) throws ArrayIndexOutOfBoundsException {
		// creating new array to transfer values
        Position[] temp;
        boolean flag = false;
        if (!fits()) {
            temp = expand();
        } else {
            temp = new Position[data.length];
        }
		// filling in all values
        for (int i = 0, j = 0; i <= last_pos; i++, j++) {
            temp[j] = data[i];
			// adding the new Position
            if (data[i] == pos) {
                j++;
                temp[j] = new Position(val);
                flag = true;
            }
        }
        if (!flag) {
            throw new ArrayIndexOutOfBoundsException();
        }
        data = temp;
        last_pos++;
        System.out.println("addAfter: " + pos.element() + " val: " + val + "\n>" + this.toString() + "\n");
        return pos;
    }

	// removes the specified Position
    public void delete(Position pos) throws ArrayIndexOutOfBoundsException{
        Position[] temp = new Position[data.length];
        boolean flag = false;
		// filling in all values to the new array except the one with the Position that is beind deleted
        for (int i = 0, j = 0; i <= last_pos; i++, j++) {
            if (data[i] == pos) {
                i++;
                flag = true;
            }
            temp[j] = data[i];
        }
        if (!flag) {
            throw new ArrayIndexOutOfBoundsException();
        }
        data = temp;
        last_pos--;
        System.out.println("delete: " + pos.element() + "\n>" + this.toString() + "\n");
    }

	// swaps the values stored in two Positions in the array
    public void swap(int p1, int p2) throws ArrayIndexOutOfBoundsException{
        int temp = data[p1].element();
        data[p1].set(data[p2].element());
        data[p2].set(temp);
        System.out.println("Swap: " + p1 + " and " + p2 + "\n>" + this.toString() + "\n");
    }

	// truncates the array to the minimum possible size
    public void truncate() {
        Position[] temp = new Position[last_pos+1];
		// filling in the new values
        for (int i = 0; i <= last_pos; i++) {
            temp[i] = data[i];
        }
        data = temp;
        System.out.println("Truncate:\n>" + this.toString() + "\n");
    }

	// sets the expansion rule to the one specified
    public void setExpansionRule(char rule) {
        if (rule == 'd' || rule == 'c') {
            System.out.println(rule);
            this.rule = rule;
        }
    }
}
