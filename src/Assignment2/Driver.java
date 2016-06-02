package Assignment2;

public class Driver {
    public static void main(String[] args) {
        Position[] poss = new Position[10];
        for (int i = 0; i < poss.length; i++) {
            poss[i] = new Position(poss.length-i);
        }
        try {
            NodeList list = new NodeList();
            list.setExpansionRule((Math.random() > 0.5) ? 'd' : 'c');
            poss[0] = list.addFirst(1);
            poss[1] = list.addFirst(0);
            poss[2] = list.addLast(1);
            list.swap(0, 1);
            list.set(poss[2], 2);
            list.delete(poss[0]);
            list.truncate();
            poss[3] = list.addFirst(3);
            poss[4] = list.addLast(4);
            poss[5] = list.addBefore(poss[4], 5);
            poss[6] = list.addAfter(poss[1], 6);
            System.out.println("first: " + list.first());
            System.out.println("last: " + list.last());
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
