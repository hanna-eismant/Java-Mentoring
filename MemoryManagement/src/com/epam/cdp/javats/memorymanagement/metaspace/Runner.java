package com.epam.cdp.javats.memorymanagement.metaspace;

import javassist.CannotCompileException;

public class Runner {

    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    public static void main(String[] args) throws CannotCompileException {
        for (int i = 0;; i++) {
            Class c = cp.makeClass("ecom.epam.cdp.javats.memorymanagement.metaspace.generated" + i).toClass();
        }
    }
}
