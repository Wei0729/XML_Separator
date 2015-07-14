
import java.io.*;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wei0000
 */


public class ReadXML {
    
   
    public static String userDir = System.getProperty("user.dir");
    private static String encoding = "UTF-16le";
    
    public static void search(String infolder, String outfolder){
        
        String cFileName;
        String cPath;
        String parentPath;
        String newFileName; 
        String lang = infolder.substring(infolder.lastIndexOf("\\")+1);
        String folderPath;
        //System.out.println(lang);
        boolean found = false;
        try{
            File folder = new File(infolder);
            File destDir, srcFile;
            File[] listOfFiles = folder.listFiles();
            File[] listOfFiles1;
            File[] listOfFiles2;
            File[] listOfFiles3;
            
            //System.out.println(listOfFiles.length);
            for(int i = 0; i < listOfFiles.length; i++){
                //System.out.println(listOfFiles[i].getName());
                listOfFiles1 = listOfFiles[i].listFiles();
                for(int j = 0; j < listOfFiles1.length; j++) {
                     //System.out.println(listOfFiles1[j].getName());
                  listOfFiles2 = listOfFiles1[j].listFiles();
                  for(int k = 0; k < listOfFiles2.length; k++){
                     //System.out.println(listOfFiles2[k].getName());
                   listOfFiles3 = listOfFiles2[k].listFiles();
                  for (int f = 0; f < listOfFiles3.length; f++ ){    
                      
                  if(listOfFiles3[f].isFile()){
                    cFileName = listOfFiles3[f].getName();
                    //System.out.println(cFileName);
                    cPath = listOfFiles3[f].getPath();
                       parentPath = listOfFiles3[f].getParent();
                       srcFile = new File(cPath);
                    if(cFileName.toLowerCase().endsWith(".xml")){
                       
                       //System.out.println(cPath);
                       found = readFileByLine(cPath);
                       folderPath = parentPath.substring(parentPath.indexOf(lang));
                       
                       if(found){
                            //newFileName = parentPath.replace(lang,"output\\gptool");
                           folderPath = folderPath.replace(lang, "\\gptool");
                           //System.out.println(folderPath);
                           newFileName = outfolder + folderPath;
                            //System.out.println(newFileName);
                            destDir = new File(newFileName);
                        }
                       else{
                           folderPath = folderPath.replace(lang, "\\regular");
                           //newFileName = parentPath.replace(lang, "output\\regular");
                           newFileName = outfolder + folderPath;
                           destDir = new File(newFileName);
                           
                        }
                           //System.out.println(newFileName);
                       
                        
                       }
                    else{
                         folderPath = parentPath.substring(parentPath.indexOf(lang));
                         folderPath = folderPath.replace(lang, "\\regular");
                           //newFileName = parentPath.replace(lang, "output\\regular");
                         newFileName = outfolder + folderPath;
                         destDir = new File(newFileName);
                        
                    }
                    try{
                           FileUtils.copyFileToDirectory(srcFile, destDir);
                           System.out.println("File has been successfully separated!!!");
                           log("File has been successfully separated!!!");
                       }
                       catch(Exception e){
                           
                       }
                    
                     }
                   
                   }
                  
                  }
                  
               } 
                 
            }
           log("All XML files have been separated.");  
           System.out.println("All XML files have been separated.");
           JOptionPane.showMessageDialog(null, "All XML files have been separated.!!!");
           
        }catch(Exception e){
            log("Problem occurs: " + e);
        }
     
    }
    
    public static boolean readFileByLine(String filename){
        String originalname = filename;
        boolean found = false;
        try{    
            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, encoding));
            String strLine;
            while((strLine = br.readLine()) != null){
                if(strLine.contains("!DOCTYPE esri_gptool") || strLine.contains("!DOCTYPE map")){
                   found = true; 
                   //System.out.println(filename);
                }
                
            }
            
            
        }catch(Exception e){
            
        }
        return found;
    }
    
     public static synchronized void log(String msg){
          
        DataOutputStream dos = null;
        try{
            dos = new DataOutputStream(new FileOutputStream(userDir + "\\log.txt", true));
            dos.writeBytes(msg + System.getProperty("line.separator"));
            dos.close();
        } catch (FileNotFoundException ex){
         
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        
    }
    
//     public static void main(String[] args){
//         String filename = "D:\\fi";
//         String outputfolder = "D:\\output1";
//         search(filename, outputfolder);
//     }    
}
