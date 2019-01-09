package com.fendo.entity;
/**
 * @author 
 * @return
 * @author
 */
/**
 *@Description
 *@author 
 *@createDate
 */
import java.io.BufferedReader;
 import java.io.InputStreamReader;

 import org.apache.commons.lang3.text.StrBuilder;


 public class DeleteProgram {
    public static void run() {
         Runtime runtime = Runtime.getRuntime();        try {             BufferedReader br = new BufferedReader(new InputStreamReader(runtime.exec("ipconfig").getInputStream()));
             //StringBuffer b = new StringBuffer();
            String line=null;
            StringBuffer b=new StringBuffer();
            while ((line=br.readLine())!=null) {
                b.append(line+"\n");
            }
             System.out.println(b.toString());
         } catch (Exception e) {
             e.printStackTrace();
         }
 
     }
 
     public static void main(String[] args) {
         DeleteProgram delp = new DeleteProgram();
        delp.run();     }

}
