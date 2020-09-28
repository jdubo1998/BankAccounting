package bankaccounting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class GetFile {
    public static void copyFile(String location) {
        File dir = new File(""); //TODO: Get file.
        File downloads;
        File[] files = dir.listFiles();
        
        for (File file1 : files) {
            if (file1.getName().contains(".csv")) {
                try {
                    downloads = new File("" + file1.getName()); //TODO: Get file.
                    copyFile(downloads, 
                            new File("" + file1.getName())); //TODO: Get file.
                    
                    downloads.delete();
                } catch (IOException e) {
                    System.err.println("Error: No file found");
                }
            }
        }
    }
    
    /*http://stackoverflow.com/questions/106770/standard-concise-way-to-copy-a-file-in-java
    */
    public static void copyFile(File sourceFile, File destFile) throws IOException {
    if(!destFile.exists()) {
        destFile.createNewFile();
    }

    FileChannel source = null;
    FileChannel destination = null;

    try {
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        destination.transferFrom(source, 0, source.size());
    }
    finally {
        if(source != null) {
            source.close();
        }
        if(destination != null) {
            destination.close();
        }
    }
}
}
