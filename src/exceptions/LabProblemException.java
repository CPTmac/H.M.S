package exceptions;

/** Problems with labs (duplicate result, mismatch, ...) */
public class LabProblemException extends Exception {

    public LabProblemException(String msg) {
        super(msg);
    }
}
