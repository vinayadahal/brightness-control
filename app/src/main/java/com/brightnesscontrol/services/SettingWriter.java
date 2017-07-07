package com.brightnesscontrol.services;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import static com.brightnesscontrol.activites.MainActivity.ctx_main_activity;

public class SettingWriter {

    StringBuilder text = new StringBuilder();

    public StringBuilder readFile(String filename) {
        File file = new File(ctx_main_activity.getApplicationContext().getFilesDir() + "/" + filename);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
            return text;
        } catch (IOException e) {
            System.out.println("exception ----------------> " + e);
            return null;
        }
    }

    public boolean writeFile(String data, String filename) {
        FileOutputStream outputStream;
        try {
            outputStream = ctx_main_activity.openFileOutput(filename, ctx_main_activity.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
            return true;
        } catch (Exception e) {
            System.out.println("WriteToFile failed >>>>>" + e);
            return false;
        }
    }

    public boolean checkFile(String filename) {
        File file = new File(ctx_main_activity.getApplicationContext().getFilesDir() + "/" + filename);
        return file.exists();
    }

}
