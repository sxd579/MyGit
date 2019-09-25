package com.HIM.client;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class MyFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        String name = f.getName();
        return  name.toLowerCase().endsWith(".jpg")|| name.toLowerCase().endsWith(".png")||name.toLowerCase().endsWith(".gif")|| name.toLowerCase().endsWith(".jpeg");
    }

    @Override
    public String getDescription() {
        return "*.jpg;*.png;*.gif;*.jpeg";
    }
}
