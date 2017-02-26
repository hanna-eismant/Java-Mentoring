package com.epam.cdp.javats.classloading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
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

    private static final String DEFAULT_PATH_TO_JAR = "d:/greeting.jar";

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
                    try {
                        loadClass(input);
                    } catch (ClassNotFoundException | InstantiationException e) {
                        LOGGER.error(e);
                        showHelp();
                    }
                } else {
                    LOGGER.warn("Unknown command");
                    showHelp();
                }
            }

        } catch (IOException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            LOGGER.error(e);
        }
    }

    private static void loadClass(final String input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, MalformedURLException, InstantiationException {
        final String[] splitStrings = input.split(" ", 2);
        final String usedPath;

        if (splitStrings.length >= 2) {
            usedPath = splitStrings[1];
        } else {
            usedPath = DEFAULT_PATH_TO_JAR;
        }

        LOGGER.info("Trying load class from jar: " + usedPath);
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:/" + usedPath)});
        final Greeting instance = (Greeting) classLoader.loadClass("com.epam.cdp.javats.classloading.SomeImplementation").newInstance();

        instance.greeting(name);
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
        LOGGER.info("Enter name <your name> to save your name");
        LOGGER.info("Enter load <path> to load class and invoke greeting. If path is not specified the default will be used " + DEFAULT_PATH_TO_JAR);
        LOGGER.info("Enter h to see this help again");
        LOGGER.info("Enter q to exit the program");
    }
}
