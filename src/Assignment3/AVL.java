package Assignment3;

class avl {

    Node root;

    private class Node {

        String keyword;
        Record record;
        Node l;
        Node r;
        Node parent;
        int depth;
        int balance;

        private Node(String k, Node parent) {
            this.keyword = k;
            this.record = null;
            this.l = null;
            this.r = null;
            this.parent = parent;
            this.depth = 0;
            this.balance = 0;
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
            return (this.keyword + " " + this.depth + " " + this.balance + " (" + ((this.l != null)?this.l.toString():" XX ") + ", " + ((this.r != null)?this.r.toString():" XX ") + ")");
        }
    }

    public avl() {
        this.root = null;
    }

    public void insert(String keyword, FileData fd) {
        Record recordToAdd = new Record(fd.id, fd.author, fd.title, null);
        Node temp = insertNode(keyword, root, recordToAdd);
        // storing the value of the node that was just added if it has a parent and grandparent which makes it eligible for a re-balancing
        Node balancecheck;
        if (temp != null && temp.parent != null && temp.parent.parent != null) {
            balancecheck = temp.parent;
        } else {
            balancecheck = null;
        }
        // giving a depth attribute to all nodes
        for (int i = 1; temp != null; i++) {
            if (temp.depth < i) {
                temp.depth++;
            }
            int rdepth, ldepth;
            if (temp.r != null) {
                rdepth = temp.r.depth;
            } else {
                rdepth = 0;
            }if (temp.l != null) {
                ldepth = temp.l.depth;
            } else {
                ldepth = 0;
            }
            temp.balance = rdepth - ldepth;

            temp = temp.parent;
        }
        // if the above criteria were met
        if (balancecheck != null) {
            // temporary pointers/Node for the swapping
            Node child, baby;
            Node storage  = new Node("", null);
            // in the case that the right tree is unbalanced
            if (balancecheck.balance > 1) {
                child = balancecheck.r;
                // if the right branch is problematic
                if (child.balance > 0) {
                    baby = child.r;
                    // pushing down all the values stored in the nodes
                    storage.record = balancecheck.record;
                    storage.keyword = balancecheck.keyword;
                    balancecheck.record = child.record;
                    balancecheck.keyword = child.keyword;
                    child.record = baby.record;
                    child.keyword = baby.keyword;
                    baby.record = storage.record;
                    baby.keyword = storage.keyword;
                } else {
                    baby = child.l;
                    // swapping the values of the grandparent and baby nodes
                    storage.record = balancecheck.record;
                    storage.keyword = balancecheck.keyword;
                    balancecheck.record = baby.record;
                    balancecheck.keyword = baby.keyword;
                    baby.record = storage.record;
                    baby.keyword = storage.keyword;
                }
                // moving the baby node to the other branch of the grandparent and adjusting the balance/depth values
                baby.unlink();
                baby.parent = balancecheck;
                balancecheck.l = baby;
                balancecheck.depth--;
                child.depth--;
                balancecheck.balance = 0;
                child.balance = 0;
            } else if (balancecheck.balance < -1) {
                child = balancecheck.l;
                // if the left tree is problematic
                if (child.balance < 0) {
                    baby = child.l;
                    // pushing down all values stored in the nodes
                    storage.record = balancecheck.record;
                    storage.keyword = balancecheck.keyword;
                    balancecheck.record = child.record;
                    balancecheck.keyword = child.keyword;
                    child.record = baby.record;
                    child.keyword = baby.keyword;
                    baby.record = storage.record;
                    baby.keyword = storage.keyword;
                } else {
                    baby = child.r;
                    // swapping the values of grandparent and baby nodes
                    storage.record = balancecheck.record;
                    storage.keyword = balancecheck.keyword;
                    balancecheck.record = baby.record;
                    balancecheck.keyword = baby.keyword;
                    baby.record = storage.record;
                    baby.keyword = storage.keyword;
                }
                // moving the baby node to the other branch of the grandparent and adjusting the balance/depth values
                baby.unlink();
                baby.parent = balancecheck;
                balancecheck.r = baby;
                balancecheck.depth--;
                child.depth--;
                balancecheck.balance = 0;
                child.balance = 0;
            }
        }

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

    private Node insertNode(String keyword, Node node, Record record) {
        if (this.root == null) {
            this.root = new Node(keyword, null);
            return this.root;
        } else if (!node.keyword.equals(keyword)) {
            if (node.keyword.compareToIgnoreCase(keyword) > 0) {
                if (node.r == null) {
                    node.r = new Node(keyword, node);
                    node.r.record = record;
                    return node.r;
                } else {
                    return insertNode(keyword, node.r, record);
                }
            } else {
                if (node.l == null) {
                    node.l = new Node(keyword, node);
                    node.l.record = record;
                    return node.l;
                } else {
                    return insertNode(keyword, node.l, record);
                }
            }
        } else {
            node.update(record);
            return null;
        }
    }
}
