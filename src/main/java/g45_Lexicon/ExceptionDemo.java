package g45_Lexicon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
//If we need to read a file ==> better to use BufferReader ==> good performance

public class ExceptionDemo {
    public static void main(String[] args) {

        //ex1();
        //ex2();
        //ex3();
       /* do {
            try {
               String studentId = ex4();
                System.out.println(studentId);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // print out the exception message that we created corresponding to different scenario.
            }
        } while (true);*/
        //ex5();
        /*try{ex6();} catch (DataNotFoundException e) {System.out.println("Error code: " + e.getErrorCode() + ", Error message: " + e.getParamValue() + " " + e.getMessage());}*/
        //try{ex7();} catch (InsufficientFoundsException e) {System.out.println(e.getMessage());}
        //try{ex8();} catch (IOException e) {System.out.println(e.getMessage());}
        //ex8();

        System.out.println(ConsoleColors.BLUE + "-----------------" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "display menu" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "display menu" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "display menu" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "-----------------" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE);
        System.out.println("display menu");
        System.out.println("display menu");
        System.out.println(ConsoleColors.RESET); // after reset, we can change other color
        System.out.println(ConsoleColors.RED);
        System.out.println("display menu");
        System.out.println("display menu");

    }

    //CHECKED EXCEPTION
    public static void ex1() {
        while (true) {
            try {
                //Protected Code
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter a valid file path: ");
                String filePath = scanner.nextLine();
                // BufferedReader reader = Files.newBufferedReader(Paths.get("dir/skills.txt"));
                BufferedReader reader = Files.newBufferedReader(Paths.get(filePath)); // https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
                // reader.lines(); => return a stream of line
                List<String> skillList = reader.lines().collect(Collectors.toList());
                skillList.forEach(System.out::println);

                //Other solution: lines() reads all lines ==> return a Stream<String>
                Files.lines(Paths.get(filePath)).forEach(System.out::println);

                //readAllLines() return an array of String
                List<String> strings = Files.readAllLines(Paths.get(filePath));
                strings.forEach(System.out::println);
                break;
            } catch (IOException e) {
                //System.out.println(e);
                System.out.println("File path is not valid");
            }
        }
    }

    public static void ex2() {
        //copy() method to copy data from specific directory to another destination directory
        // It take 2 param (dir of source file, dir of destination file)

        try {
            Path sourcePath = Paths.get("source/Darth_Vader.png");
            Path destinationPath = Paths.get("destination/Darth_Vader.png");
            if (Files.exists(sourcePath) && Files.isRegularFile(sourcePath)) {
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING); //remove if file exits in target dir
            } else System.out.println("source file is not valid");
        } catch (IOException e) {
            System.out.println(e);
            //System.out.println(e.getMessage()); // print exception message
            // e.printStackTrace(); // print all data exits in memory tree. all content.
        }
    }

    //UNCHECKED EXCEPTION
    public static void ex3() {
        int[] numbers = {4, 5, 8, 9}; //4
        try {
            System.out.println(numbers[1]);
            System.out.println(numbers[0]);
            //System.out.println(numbers[4]); //it throws ArrayIndexOutOfBoundsException

            String text = null;
            //System.out.println(text.toUpperCase()); //it throws NullPointerException

            LocalDate localDate = LocalDate.parse("2020-1-1"); // YYYY -MM -DD

       /* } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("Text was null");
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }*/
        } catch (ArrayIndexOutOfBoundsException | NullPointerException | DateTimeException e) {
            System.out.println(e);
        }
        System.out.println("Done ");

    }

    //throw is used to throw an exception explicitly (ro rang)
    // it is concept of exception handling in Java
    public static String ex4() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a valid student id (A-1234) : ");
        String studentId = scanner.nextLine();

        if (studentId.length() == 0) throw new IllegalArgumentException("student id was null");
        if (studentId.length() != 6) throw new IllegalArgumentException("student id length was not valid");
        if (!studentId.startsWith("A")) throw new IllegalArgumentException("student id must start with A");
        return studentId;
    }

    public static void ex5() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        double n1 = scanner.nextDouble();
        System.out.println("Enter a number: ");
        double n2 = scanner.nextDouble();

        if (n2 == 0) throw new IllegalArgumentException("second number must not be zero");
        double result = n1 / n2;
        System.out.println(result);

    }

    //CUSTOM EXCEPTION
    //throws keyword is used to declare an exception
    public static void ex6() throws DataNotFoundException {
        List<String> names = Arrays.asList("Mehrdad", "Marcus", "Simon", "Åsa");
        String inputName = "Test";
        Optional<String> optional = names.stream()
                .filter(name -> name.equalsIgnoreCase(inputName))
                .findFirst();
        if (optional.isPresent()) System.out.println(optional.get());//optional.ifPresent(System.out::println);
        else throw new DataNotFoundException("name info not found", inputName);
    }

    public static void ex7() throws InsufficientFoundsException {
        double balance = 100;
        System.out.println("Current balance is: " + balance);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a valid number: ");
        double amount = scanner.nextDouble();
        if (amount > balance) {
            throw new InsufficientFoundsException("balance is insufficient");
        }
        balance -= amount;
        System.out.println(balance);
    }

    //update info in skills.txt
    public static void ex8() {
        BufferedWriter writer = null; //to use writer.close in finally block
        try {
            Path path = Paths.get("dir/skills.txt");
            // StandardOpenOption.APPEND concat to current data
            writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND);
            writer.newLine();
            writer.append("ExceptionHandling In Java");
            writer.close(); // need to close to apply change for writer operation
        } catch (IOException e) {
            System.out.println(e);
        } finally { //finally block always implement in any scenario
            System.out.println("finally block");
            //need to close writer here in case Exception happen, so writer.close in try block will not be implemented.
            //but we need to initialize BufferedWriter writer = null; to use writer.close here in finally block.
            //writer.close(); // will have checked exception here so need to put it in try/ catch
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    //try-with-resources
    public static void ex9() {
        Path path = Paths.get("dir/skills.txt");

        //all classes using AutoCloseable method can put inside try() below. If it is not using AutoCloseable method, do as ex8
        try (
        BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)
        ) {
            writer.newLine();
            writer.append("ExceptionHandling In Javaaaaa");
        } catch (IOException e) {
            System.out.println(e);
        } // here Java will auto close writer so no need to write writer.close in finally block anymore.
    }
}

