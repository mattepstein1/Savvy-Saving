package com.example.bohra.savvysavingappfinal;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * This class contains the functionality to handle commonly used IO functions and read some specific data
 */

public class IO {

    public int checkFileExists(String filename)
    {
        File file = new File(Environment.getExternalStorageDirectory(),filename);

        if (file.exists()){
            return 1;
        }

        return 0;
    }

    public String readPinFile(String filename)
    {
        File file = new File(Environment.getExternalStorageDirectory(),filename);
        StringBuilder text = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line=br.readLine();

            text.append(line);


            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();


    }

    public String readFile(String filename)
    {
        File file = new File(Environment.getExternalStorageDirectory(),filename);
        StringBuilder text = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line="";
            while((line = br.readLine())!=null){
                text.append(line);
                text.append("\n");
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public void writeFile(String filename, String content)
    {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),filename);
        try{
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void updateFile(String filename,String content)
    {
        String str = readFile(filename);
        writeFile(filename,str+content);
    }


}
