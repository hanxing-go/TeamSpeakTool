package util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class CustomObjectOutputStream extends ObjectOutputStream {
    public CustomObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    protected CustomObjectOutputStream() throws IOException, SecurityException {
    }

    @Override
    public void writeStreamHeader() {

    }
}
