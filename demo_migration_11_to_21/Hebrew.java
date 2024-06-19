/* few i18n changes break this (kind of) test */

import java.util.Locale;

public class Hebrew {

    public static void main(String[] args) {
        Locale hebrewOld = new Locale("iw", "IL", "");
        System.out.println(hebrewOld.getLanguage().toString().equals("iw"));
    }
}

