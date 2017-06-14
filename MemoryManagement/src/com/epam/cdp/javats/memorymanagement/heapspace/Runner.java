package com.epam.cdp.javats.memorymanagement.heapspace;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Runner {

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        System.out.println("Read file " + file.getAbsolutePath());
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[Integer.MAX_VALUE-2];
        fis.read(data);
    }

}
