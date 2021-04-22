import java.io.*;
import java.util.Scanner;

public class MainSystem {

    static String fileName;
    static Library lib = new Library();
    static Scanner in = new Scanner(System.in);
    static Boolean running = true;

     public static void main(String[] args){
         while(running){
             System.out.println("""
                     Enter 0 for load a library.\s
                     Enter 1 for save and quit\s
                     Enter 2 for list all book in library\s
                     Enter 3 for add book to library""");

             int answer = in.nextInt();
             switch (answer) {
                 case 0 -> {
                     System.out.println("Enter the file name to load ");
                     FileInputStream fis;
                     ObjectInputStream in;
                     File file = new File(fileName);
                     if (file.exists()) {
                         try {
                             fis = new FileInputStream(fileName + ".ser");
                             in = new ObjectInputStream(fis);
                             lib = (Library) in.readObject();
                             fis.close();
                             in.close();

                         } catch (IOException | ClassNotFoundException e) {
                             e.printStackTrace();
                         }
                     } else {
                         System.out.println("\nThe file does not exist!");
                     }
                 }
                 case 1 -> saveAndQuit();
                 case 2 -> System.out.println(lib.toString());
                 case 3 -> addBook();
             }
         }
         System.exit(0);
     }

    private static void addBook() {
        int isbn;
        String title, author;
        double price;

        System.out.println("\nEnter title: ");
        title = in.next();

        System.out.println("\nEnter Author: ");
        author = in.next();

        System.out.println("\nEnter ISBN: ");
        isbn = in.nextInt();

        System.out.println("\nEnter Price: ");
        price = in.nextDouble();

        Book b = new Book(isbn, title, author, price);
        lib.addBook(b);
    }
    private static void saveAndQuit() {
         System.out.println("Enter file name: ");
         fileName = in.next() + ".ser";
         running = false;
         FileOutputStream fos;
         ObjectOutputStream out;
        try {
            fos = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(lib);
            fos.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

