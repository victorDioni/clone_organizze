package dionizio.victor.organizze.helper;

import android.util.Base64;

public class Base64Custom {

    public static String codificarBase64(String texto){
        // replaceAll sendo utilizado para substituir por vazio quando encontrar caracteres invalidos
        return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String deodificarBase64(String textoCodificado){
        // Convertendo para String pq o Base64 nao retorna String
        return new String(Base64.decode(textoCodificado, Base64.DEFAULT));
    }
}
