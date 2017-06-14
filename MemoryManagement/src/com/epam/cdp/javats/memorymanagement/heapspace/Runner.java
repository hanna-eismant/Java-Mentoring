package com.epam.cdp.javats.memorymanagement.heapspace;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Runner {

    public static void main(String[] args) throws IOException {
        File file = new File("dummy.txt");
        System.out.println(file.getAbsolutePath());
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
    }

}
