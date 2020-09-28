package bankaccounting;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class TxtIO {
    FileReader reader;
    private static FileWriter writer;
    String content = null;
    private static String fileName;
    File file;
    
    /* http://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java 
      -----------------------------------------------------------------------------------
       grimy and Victor Polevoy created readFile method code */
    public String readFile(String filename) throws IOException {
        file = new File(filename);
        
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } finally {
            if(reader != null){
                reader.close();
            }
        }
        return content;
    }
    
    public final static void write(String input) {
        try {
            writer = new FileWriter(fileName, true);
            PrintWriter printer = new PrintWriter(writer);
            printer.printf(input + "\n");
            printer.close();
        } catch (Exception ex) {
            System.out.println("ERROR: No file to be wrote to.");
        }
    }
    
    public BigDecimal Balance() {
        try {
            return BigDecimal.valueOf(Double.parseDouble(readFile(""))); //TODO: Get file.
        } catch (IOException e) {
            System.err.print("ERROR: No file found.");
            return BigDecimal.valueOf(0);
        }
    }
    
    public String findNewestFile() {
        File dir = new File(""); //TODO: Get file.
        File[] files = dir.listFiles();
        
        int newDate = -1;
        
        for (File file1 : files) {
            try {
                int date = Integer.parseInt(file1.getName().substring(0, 8));
                if (date > newDate) {
                    newDate = date;
                }
                newDate = new Integer(file1.getName().substring(0, 8));
            } catch (Exception e) {
                System.out.println("ERROR: Incorrect File Syntax");
            }
        }
        
        fileName = ""; //TODO: Get file.
        
        if (newDate == -1) {
            return "ERROR: No file found."; 
        }
        
        try {
            return readFile(fileName);
        } catch (IOException e) {
            System.err.print("ERROR: No file found.");
            return "";
        }
    }
}
