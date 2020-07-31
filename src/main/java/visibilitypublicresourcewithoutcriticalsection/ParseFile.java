package visibilitypublicresourcewithoutcriticalsection;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.StringJoiner;

/**
 * Class of parsing file
 *
 * @author Денис Висков
 * @version 1.0
 * @since 31.07.2020
 */
public class ParseFile {
    /**
     * File
     */
    private File file;

    /**
     * Method set given file
     *
     * @param file
     */
    public synchronized void setFile(File file) {
        this.file = file;
    }

    /**
     * Method return file
     *
     * @return File
     */
    public synchronized File getFile() {
        return file;
    }

    /**
     * Method returns content from file
     *
     * @return content
     */
    public synchronized String getContent() {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines()
                    .forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * Method returns content from file without Unicode
     *
     * @return content
     */
    public synchronized String getContentWithoutUnicode() {
        StringBuilder result = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int read;
            while ((read = in.read()) != -1) {
                if (read < 0x80) {
                    result.append((char) read);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * Method writing content to file
     *
     * @param content
     */
    public synchronized void saveContent(String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardOpenOption.CREATE_NEW)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
