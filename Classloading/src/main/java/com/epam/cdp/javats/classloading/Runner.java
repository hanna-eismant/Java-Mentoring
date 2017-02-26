package com.epam.cdp.javats.classloading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.log4j.Logger;

import com.google.common.base.Strings;

public class Runner {

    private static final Logger LOGGER = Logger.getLogger(Runner.class);

    private static final String MENU_QUIT = "q";
    private static final String MENU_HELP = "h";
    private static final String MENU_SET_NAME = "name";
    private static final String MENU_LOAD_CLASS = "load";

    private static final String PATH_TO_JAR = "d:/greeting.jar";

    private static String name = "Jon Doe";

    public static void main(String[] args) {

        LOGGER.info("Enter h for help or q for exit");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {

                String input = br.readLine();

                if (MENU_QUIT.equals(input)) {
                    quit();
                } else if (MENU_HELP.equals(input)) {
                    showHelp();
                } else if (!Strings.isNullOrEmpty(input) && input.startsWith(MENU_SET_NAME)) {
                    saveName(input);
                } else if (!Strings.isNullOrEmpty(input) && input.startsWith(MENU_LOAD_CLASS)) {
                    loadClass();
                } else {
                    LOGGER.warn("Unknown command");
                    showHelp();
                }
            }

        } catch (IOException | ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            LOGGER.error(e);
        }
    }

    private static void loadClass() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException {
        LOGGER.info("Trying load class from jar: " + PATH_TO_JAR);
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:/" + PATH_TO_JAR)});
        Class cls = classLoader.loadClass("com.epam.cdp.javats.classloading.SomeImplementation");
        Method method = cls.getMethod("greeting", String.class);
        method.invoke(null, (Object) name);
    }

    private static void saveName(final String input) {
        final String[] splitStrings = input.split(" ", 2);
        name = splitStrings[1];

        LOGGER.info("Your name is " + name);
    }

    private static void quit() {
        LOGGER.info("Quit");
        System.exit(0);
    }

    private static void showHelp() {
        LOGGER.info("Enter name  <your name> to save your name");
        LOGGER.info("Enter load to load class and invoke greeting");
        LOGGER.info("Enter h to see this help again");
        LOGGER.info("Enter q to exit the program");
    }
}
