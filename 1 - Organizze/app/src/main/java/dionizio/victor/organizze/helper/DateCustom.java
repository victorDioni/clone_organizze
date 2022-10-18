package dionizio.victor.organizze.helper;

import java.text.SimpleDateFormat;

public class DateCustom {

    public static String dataAtual(){
        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format(data);
        return dataString;
    }

    public static String mesAnoDataEscolhida( String data){
       String retornoData[] = data.split("/");
       String mes = retornoData[1];
       String ano = retornoData[2];

       return mes + ano;
    }
}
