package utilidades;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ComboBoxItem {
    private String value;
    private String label;

    public ComboBoxItem(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }


    
    @Override
    public String toString() {
        return label;
    }
}