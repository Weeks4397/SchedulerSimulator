package Reports.GUI;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 * This Class, when given a TextArea, redirects the System.out and System.err to the TextArea
 */
public class ConsoleRedirect {

    /**
     * BUFFER_SIZE: A finial variable that represents the amount of memory the output stream occupies
     */
    private static int BUFFER_SIZE = 8192;

    /**
     * MAX_TEXT_LENGTH: The maximum amount of characters allowed per line
     */
    private static int MAX_TEXT_LENGTH = Integer.MAX_VALUE;

    /**
     * pos: The position of the of the buffered array. The buffered Array represents the data that needs to be
     * directed to the TextArea
     */
    private int pos = 0;

    /**
     * running: Status of the the ConsoleRedirect. When true the console will be directed to the TextArea
     */
    private boolean running = false;

    /**
     * SaveErr: The PrintStream of the original System.Err. (The default console)
     * SaveOut: The PrintStream of the original System.Out. (The default console)
     */
    private PrintStream saveErr, saveOut;

    /**
     * textArea: The TextArea that the console will be redirected to
     */
    private final TextArea textArea;

    /**
     * flushed: A string buffered that represents the flushed data from the OutputStream
     */
    private final StringBuffer flushed = new StringBuffer();

    /**
     * A worker thread that will be used to redirect the console. This thread calls appendFlush to update the UI thread
     * once the work is complete
     */
    private final Thread flushThread = new Thread(() -> {
        while (running) {
            try {
                Thread.sleep(200);
                appendFlushed();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    });

    /**
     * Custom OutputStream variable that is used to direct incoming bytes to the TextArea
     */
    private final OutputStream output = new OutputStream() {
        private final byte[] buffer = new byte[BUFFER_SIZE];

        /**
         * Overridden write method used to decide when the flush the OutputStream
         * @param i the byte value of the byte to write
         * @throws IOException
         */
        @Override
        public void write(int i) throws IOException {
            if (pos == BUFFER_SIZE) {
                flush();
            }
            buffer[pos++] = (byte)i;
        }

        /**
         * Overridden write method used to decide when the flush the OutputStream
         * @param b an array of bytes that will be written the the OutputStream
         * @param offset the starting index for the array
         * @param len the endign index of the array
         * @throws IOException
         */
        public void write(byte[] b, int offset, int len) throws IOException {
            if (pos + len < BUFFER_SIZE) {
                System.arraycopy(b, offset, buffer, pos, len);
                pos += len;
            } else {
                flush();
                if (len < BUFFER_SIZE) {
                    System.arraycopy(b, offset, buffer, 0, len);
                } else {
                    ConsoleRedirect.this.flush(b, offset, len);
                }
            }
        }

        /**
         * Overridden flush method that is used to flush out an OutputStream. This method sets the pos to 0 as well as
         * call the flush function
         * @throws IOException
         */
        @Override
        public void flush() throws IOException {
            ConsoleRedirect.this.flush(buffer, 0, pos);
            pos = 0;
        }

        /**
         * Closes the OutputStream once all work is finished
         * @throws IOException
         */
        @Override
        public void close() throws IOException {
            flush();
        }
    };

    /**
     * The constructor for the class. Initializes the saveErr, textArea, and saveOut global variables
     * @param textArea the TextArea that the console will be redirected to
     */
    public ConsoleRedirect(TextArea textArea) {
        this.textArea = textArea;
        this.saveErr = System.err;
        this.saveOut = System.out;

        // Set the flushThread to be a Daemon thread. A Daemon thread will continue to run after the the program finishes
        flushThread.setDaemon(true);
    }


    /**
     * This function is used the stare the the console Redirect. It creates a new printStream with the Custom
     * OutputStream variable and stared the flushThread
     */
    public void start() {
        PrintStream printStream = new PrintStream(output, true) {
            @Override
            public void close() {
                super.close();
                stop();
            }
        };
        //Redirect the System.Err and System.out to the new PrintStream that was created
        System.setErr(printStream);
        System.setOut(printStream);
        running = true;
        flushThread.start();
    }

    /**
     * Stops the console redirect and restores the System.out and System.Err to the default console
     */
    public void stop() {
        System.setErr(saveErr);
        System.setOut(saveOut);
        running = false;
        try {
            flushThread.join();
        } catch (InterruptedException e) {
        }
    }

    /**
     * Used to synchronize the flushed variable so only one thread can access the StringBuffer at a time
     * @param b an array of Bytes
     * @param off the starting index
     * @param len the end index
     */
    void flush(byte[] b, int off, int len) {
        if (len > 0) {
            synchronized (flushed) {
                flushed.append(new String(b, off, len));
            }
        }
    }

    /**
     * This function will take the OutputStreams flushed data and add it the the TextArea. In order to do this it runs
     * the worker thread on the UI thread so that it can update
     * @throws IOException
     */
    void appendFlushed() throws IOException {
        synchronized (flushed) {
            if (flushed.length() > 0) {
                final String s = flushed.toString();
                flushed.setLength(0);
                Platform.runLater(() -> {
                    textArea.appendText(s);
                    int textLength = textArea.getText().length();
                    if (textLength > MAX_TEXT_LENGTH) {
                        textArea.setText(textArea.getText(textLength - MAX_TEXT_LENGTH / 2, textLength));
                    }
                });
            }
        }
    }
}