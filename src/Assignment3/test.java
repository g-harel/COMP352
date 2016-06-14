package Assignment3;

import java.io.*;


class FileData {

    int id;
    String title;
    String author;
    String keywords[];

    FileData(int id, String title, String author, int keywordCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        keywords = new String[keywordCount];
        for (int i = 0; i < keywords.length; i++) {
            keywords[i] = null;
        }
    }

    boolean addKeyword(String keyword) {
        for (int i = 0; i < keywords.length; i++) {
            if (keywords[i] == null) {
                keywords[i] = keyword;
                return true;
            }
        }
        return false;
    }

}

class test {

    BufferedReader b;
    bst a;

    public FileData readNextRecord() {
        if (b == null) {
            System.out.println("Error: You must open the file first.");
            return null;
        } else {
            FileData readData;
            try {
                String data = b.readLine();
                if (data == null) {
                    return null;
                }
                int readNo = Integer.parseInt(data);
                readData = new FileData(readNo, b.readLine(), b.readLine(), Integer.parseInt(b.readLine()));
                for (int i = 0; i < readData.keywords.length; i++) {
                    readData.addKeyword(b.readLine());
                }
                String space = b.readLine();
                if ((space != null) && (!space.trim().equals(""))) {
                    System.out.println("Error in file format");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error Number Expected! ");
                return null;
            } catch (Exception e) {
                System.out.println("Fatal Error: " + e);
                return null;
            }
            return readData;
        }
    }

    public test(String filename) {
        try {
            this.a = new bst();
            this.b = new BufferedReader(new FileReader(filename));
            FileData fd;
            while ((fd = this.readNextRecord()) != null) {
                for (int i = 0; i < fd.keywords.length; i++) {
                    a.insert(fd.keywords[i], fd);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (b != null) {
                    b.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        test T = new test("src/Assignment3/datafile.txt");
        T.a.get_records("medical").print();
        T.a.delete("medical");
        T.a.delete("learning");
        T.a.delete("concepts");
        T.a.print();
        System.out.println(T.a.root);
    }
}
