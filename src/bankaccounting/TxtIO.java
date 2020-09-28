package bankaccounting;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TxtIO {
    FileReader reader = null;
    String content = null;
    File file;
    
    /* http://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java 
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
            if(reader !=null){
                reader.close();
            }
        }
        return content;
    }
    
    public String findNewestFile() throws IOException {
        String newestFile;
        File dir = new File("");
        File[] files = dir.listFiles();
        
        int newDate = -1;
        
        for (File file1 : files) {
            int date = Integer.parseInt(file1.getName().substring(0, 8));
            if (date > newDate) {
                newDate = date;
            }
            newDate = new Integer(file1.getName().substring(0, 8));
        }
        
        newestFile = ""; //TODO: Edit this.
        
        if (newDate == -1) {
            return "ERROR"; 
        }
        
        return readFile(newestFile);
    }
}
