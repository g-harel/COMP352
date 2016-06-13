package Assignment3;

class bst {

    Node root;

    private class Node {

        String keyword;
        Record record;
        Node l;
        Node r;
        Node parent;

        private Node(String k, Node parent) {
            this.keyword = k;
            this.record = null;
            this.l = null;
            this.r = null;
            this.parent = parent;
        }

        private void update(Record r) {
            r.next = this.record;
            this.record = r;
        }

        private Node unlink() {
            Node lonely = this.parent;
            if (lonely.r == this) {
                lonely.r = null;
            } else {
                lonely.l = null;
            }
            return this;
        }

        public String toString() {
            return (this.keyword + " (" + ((this.l != null)?this.l.toString():" XX ") + ", " + ((this.r != null)?this.r.toString():" XX ") + ")");
        }
    }

    public bst() {
        this.root = null;
    }

    public void insert(String keyword, FileData fd) {
        Record recordToAdd = new Record(fd.id, fd.author, fd.title, null);
        insertNode(keyword, root, recordToAdd);
    }

    public boolean contains(String keyword) {
        if (findNode(keyword, root) != null) {
            return true;
        } else {
            return false;
        }
    }

    public Record get_records(String keyword) {
        return findNode(keyword, root).record;
    }

    public void delete(String keyword) {
        Node target = findNode(keyword, root);
        Node source = target.r;
        if (source == null) {
            if (target.l != null) {
                source = target.l;
            } else {
                Node lonely = target.parent;
                if (lonely.r == source) {
                    lonely.r = null;
                } else {
                    lonely.l = null;
                }
                return;
            }
        } else {
            while (source.l != null) {
                source = source.l;
            }
        }
        target.keyword = source.keyword;
        target.record = source.record;
        source.unlink();
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
                    node.r = new Node(keyword, node);
                    node.r.record = record;
                } else {
                    insertNode(keyword, node.r, record);
                }
            } else {
                if (node.l == null) {
                    node.l = new Node(keyword, node);
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
