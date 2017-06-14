javac -cp "lib\javassist.jar" src\com\epam\cdp\javats\memorymanagement\metaspace\Runner.java
java -Xms1G -Xmx1G -XX:MaxMetaspaceSize=1m -cp "lib\javassist.jar;src" com.epam.cdp.javats.memorymanagement.metaspace.Runner
