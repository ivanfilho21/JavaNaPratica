package helper;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateHelper {
    public enum Formato {
        INPUT_DATE("yyyy-MM-dd"),
        OUTPUT_DATE("dd/MM/yyyy");

        private String formatoTexto;

        private Formato(String formatoTexto) {
            this.formatoTexto = formatoTexto;
        }

        public String getFormatoTexto() {
            return formatoTexto;
        }
    }

    public static Date parse(String dataFormatada, Formato formatoEntrada) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatoEntrada.getFormatoTexto());

        try {
            return sdf.parse(dataFormatada);
        } catch (Exception e) {
            return null;
        }
    }

    public static String format(Date date, Formato formatoSaida, String textoPadrao) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatoSaida.getFormatoTexto());

        try {
            return sdf.format(date);
        } catch (Exception e) {
            return textoPadrao;
        }
    }

}