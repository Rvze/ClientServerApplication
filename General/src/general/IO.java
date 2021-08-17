package general;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public interface IO {
    Scanner scanner = new Scanner(new InputStreamReader(System.in));
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    default String readLine() throws IOException {
        return scanner.nextLine();
    }

    default String readPassword() throws IOException {
        Console console = System.console();
        if (console == null)
            return readLine();
        char[] chars = console.readPassword();
        return new String(chars);
    }

    default String read() throws IOException {
        return reader.readLine();
    }

    default void println(String s){
        System.out.println(s);
    }

    default void print(String s){
        System.out.print(s);
    }

    default void errPrint(String s){
        System.err.println(s);
    }

    default Scanner getReader(){
        return scanner;
    }

}
