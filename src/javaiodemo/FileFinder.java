
package javaiodemo;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.swing.JTextArea;

/**
 *
 * @author HASSAN
 */
public class FileFinder  extends SimpleFileVisitor<Path>{

    
    private final HashMap<Long, Path >visitedFilesList;
    private final PathMatcher matcher;
    private  long totalFiles; 
    private String[] option;
    private final  JTextArea  textArea;

    public FileFinder (JTextArea  textArea,HashMap<Long, Path >listMap, String...op){
    visitedFilesList = listMap;
    option= op;
     this.textArea =  textArea;   
    matcher  =  FileSystems.getDefault().getPathMatcher("glob:"+option[0]);

    
    }//end constructor

    
    
    
private void find(Path file){
    //sb.append(key).append(key <= 9 ? ":   " : ":  ").append(value).append("\n");
   
Path  fileName  =  file.getFileName();
  if(option[1].equals("Find Specified Files Only")){     
    if(fileName !=  null && matcher.matches(fileName)){  
    //visitedFilesList.put(++totalFiles, file);
    textArea.append(++totalFiles+(totalFiles <= 9 ? ":   " : ":  ")+file+"\n");
  }
  }else if(option[1].equals("Find Folders Only")){
      if(Files.isDirectory(file))  
         // visitedFilesList.put(++totalFiles, file);
      textArea.append(++totalFiles+(totalFiles <= 9 ? ":   " : ":  ")+file+"\n");
  }else if(option[1].equals("Find Files And Folders")) {
           // visitedFilesList.put(++totalFiles, file); 
            textArea.append(++totalFiles+(totalFiles <= 9 ? ":   " : ":  ")+file+"\n");
  }else if(option[1].equals("Find All Files")) {
       if(!Files.isDirectory(file))
    //visitedFilesList.put(++totalFiles, file);
       textArea.append(++totalFiles+(totalFiles <= 9 ? ":   " : ":  ")+file+"\n");
  }
  
  
}//end method 



    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE; 
    }

    public long getTotalFiles() {
        return totalFiles;
    }

    public PathMatcher getMatcher() {
        return matcher;
    }
    
    

    public HashMap<Long, Path> getVisitedFilesList() {
        return visitedFilesList;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        
        find(dir);
      return FileVisitResult.CONTINUE; 
     
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
   
        find(file);
       
      
        return FileVisitResult.CONTINUE; 
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        
        //exc.printStackTrace();
        return FileVisitResult.CONTINUE; 
    }
    
  
    
    
    
    
    
    
    
    
    
    
    
}//end class 
