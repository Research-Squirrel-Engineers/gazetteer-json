package org.linkedgeodesy.gazetteerjson.log;

/**
 * EXCEPTION for warnings if RDF model parsing is wrong
 */
public class JenaModelException extends Exception {

    /**
     * EXCEPTION for warnings if Jena Model parsing is wrong
     *
     * @param message
     */
    public JenaModelException(String message) {
        super(message);
    }

    /**
     * EXCEPTION for warnings if Jena Model parsing is wrong
     */
    public JenaModelException() {
        super();
    }

}
