del /f test.txt
fsutil file createnew test.txt 2684354560
javac src\com\epam\cdp\javats\memorymanagement\heapspace\Runner.java
java -Xms1G -Xmx1G -cp "src" com.epam.cdp.javats.memorymanagement.heapspace.Runner test.txt
