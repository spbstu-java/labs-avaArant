package Lab3_translator;

class FileReadException extends Exception {
    public FileReadException(String filePath) {
        super("Error reading file: " + filePath);
    }
}