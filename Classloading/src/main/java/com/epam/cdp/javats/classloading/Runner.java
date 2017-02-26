package com.epam.cdp.javats.classloading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

import com.google.common.base.Strings;

public class Runner {

    private static final Logger LOGGER = Logger.getLogger(Runner.class);

    private static final String MENU_QUIT = "q";
    private static final String MENU_HELP = "h";
    private static final String MENU_SET_NAME = "name";
    private static final String MENU_LOAD_CLASS = "load";

    private static String name = "Jon Doe";

    private static String pathToJar;
    private static URLClassLoader classLoader;

    public static void main(String[] args) {

        try {
            prepareClassLoader();
        } catch (MalformedURLException e) {
            LOGGER.error(e);
            final String message = String.format("Class Loader can't load jar: '%s'. Terminate program.", pathToJar);
            LOGGER.error(message);
            System.exit(-1);
        }

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
                    loadClass(input);
                } else {
                    LOGGER.warn("Unknown command");
                    showHelp();
                }
            }

        } catch (IOException | ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            LOGGER.error(e);
        }
    }

    private static void prepareClassLoader() throws MalformedURLException {
        Path currentRelativePath = Paths.get("");
        String currentAbsolutePath = currentRelativePath.toAbsolutePath().toString();
        pathToJar = currentAbsolutePath + "/build/libs/Classloading-0.1.jar";

        classLoader = URLClassLoader.newInstance(new URL[]{new URL("file:/" + pathToJar)});
    }

    private static void loadClass(final String input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LOGGER.info("Trying load class from jar: " + pathToJar);

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
