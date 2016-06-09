package Assignment3;

class bst {

    Node root;

    private class Node {

    	  /*These attributes of the Node class will not be sufficient for part 2 of the programming question: AVL .*/
    	  /*You are free to add extra attributes as you see fit, but do not remove attributes given as it will mess with help code.*/

        String keyword;
        Record record;
        int size;
        Node l;
        Node r;
        Node parent;

        private Node(String k, Node parent) {
            // TODO Instantialize a new Node with keyword k.
            this.keyword = k;
            this.record = null;
            this.size = 0;
            this.l = null;
            this.r = null;
            this.parent = parent;
        }

        private void update(Record r) {
        	  /*TODO Adds the Record r to the linked list of records. You do not have to check if the record is already in the list.*/
        	  /*HINT: Add the Record r to the front of your linked list.*/
            r.next = this.record;
            this.record = r;
            size++;
        }

        public String toString() {
            return (this.keyword + " (" + ((this.l != null)?this.l.toString():" XX ") + ", " + ((this.r != null)?this.r.toString():" XX ") + ")");
        }
    }

    public bst() {
        this.root = null;
    }

    public void insert(String keyword, FileData fd) {
        //System.out.println(keyword + " " + fd);
        Record recordToAdd = new Record(fd.id, fd.author, fd.title, null);
        /*TODO Write a recursive insertion that adds recordToAdd to the list of records for the node associated*/
        /*with keyword. If there is no node, this code should add the node.*/
        insertNode(keyword, root, recordToAdd);
    }

    public boolean contains(String keyword) {
    	/*TODO Write a recursive function which returns true if a particular keyword exists in the bst*/
        if (findNode(keyword, root) != null) {
            return true;
        } else {
            return false;
        }
    }

    public Record get_records(String keyword) {
        /* TODO Returns the first record for a particular keyword. This record will link to other records*/
        /*If the keyword is not in the bst, it should return null.*/
        return findNode(keyword, root).record;
    }

    public void delete(String keyword) {
    	  /*TODO Write a recursive function which removes the Node with keyword from the binary search tree.*/
        /*You may not use lazy deletion and if the keyword is not in the bst, the function should do nothing.*/
        Node doomed_node = findNode(keyword, root);
        if (doomed_node != null) {
            // finding the smallest Node in the right subtree of the Node to be replaced and removing its parent's reference
            Node ambitious_node = doomed_node.r;
            Node lonely_node = new Node(null, null);
            if (ambitious_node != null) {
                while (ambitious_node.l != null) {
                    lonely_node = ambitious_node;
                    ambitious_node = ambitious_node.l;
                }
            }
            lonely_node.l = null;
            // changing the reference of the parent of the deleted node to be the smallest Node
            Node papa_node = doomed_node.parent;
            if (papa_node != null && papa_node.l != null) {
                if (papa_node.l == doomed_node) {
                    papa_node.l = ambitious_node;
                } else {
                    papa_node.r = ambitious_node;
                }
            }
            // setting the references of the smallest Node to be that of the Node to be deleted
            System.out.println("\nnew process");
            System.out.println("ambitious > " + ambitious_node);
            System.out.println("doomed > " + doomed_node);
            System.out.println("papa > " + papa_node);
            System.out.println("lonely > " + lonely_node);
            ambitious_node.l = doomed_node.l;
            ambitious_node.r = doomed_node.r;
            System.out.println(root);
        }
    }

    public void print() {
        print(root);
    }

    private void print(Node t) {
        if (t != null) {
            print(t.l);
            System.out.println(t.keyword);
            Record r = t.record;
            while (r != null) {
                System.out.printf("\t%s\n", r.title);
                r = r.next;
            }
            print(t.r);
        }
    }

    private Node findNode(String keyword, Node node) {
        if (node == null) {
            return null;
        } else if (node.keyword.equals(keyword)) {
            return node;
        } else {
            if (node.keyword.compareToIgnoreCase(keyword) > 0) {
                return findNode(keyword, node.r);
            } else {
                return findNode(keyword, node.l);
            }
        }
    }

    private void insertNode(String keyword, Node node, Record record) {
        if (this.root == null) {
            this.root = new Node(keyword, null);
        } else if (!node.keyword.equals(keyword)) {
            if (node.keyword.compareToIgnoreCase(keyword) > 0) {
                if (node.r == null) {
                    node.r = new Node(keyword, node.r);
                    node.r.record = record;
                } else {
                    insertNode(keyword, node.r, record);
                }
            } else {
                if (node.l == null) {
                    node.l = new Node(keyword, node.l);
                    node.l.record = record;
                } else {
                    insertNode(keyword, node.l, record);
                }
            }
        } else {
            node.update(record);
        }
    }
}
