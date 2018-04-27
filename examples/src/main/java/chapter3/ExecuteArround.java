package chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteArround {
    public static void main(String... args) throws IOException {
        String result = processFileLimited();

        String readOneLine = processFile((BufferedReader b)->b.readLine());
        System.out.print(readOneLine);
        String readTwoLine = processFile((BufferedReader b)->b.readLine());
        System.out.print(readTwoLine);
    }
    public static String processFileLimited() throws IOException {
    try(BufferedReader br = new BufferedReader(new FileReader("C:\\Git\\Kiran\\github\\examples\\src\\main\\resources\\lambdasinaction\\chap3\\data.txt"))){
        return br.readLine();

    }

    }
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Git\\Kiran\\github\\examples\\src\\main\\resources\\lambdasinaction\\chap3\\data.txt"))){
            return p.process(br);
        }

    }

    public interface BufferedReaderProcessor{
        public String process(BufferedReader br) throws IOException;
    }
}