package br.com.nunesonline.todolistapi.util;

import br.com.nunesonline.todolistapi.rest.TodoListApiREST;
import java.io.DataInput;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static final String FMT = "dd/MM/yyyy HH:mm:ss";

    public static String formatDtHrMinSec(Date data) {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat(FMT);
        return simpleFormatter.format(data);
    }

    public static Date formatToDtHrMinSec(Date data) {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat(FMT);
        return formatStrToDtHrMinSec(simpleFormatter.format(data));
    }

    public static Date formatStrToDtHrMinSec(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat(FMT);
        try {
            return formatter.parse(dateInString);
        } catch (Exception e) {
            logger.info("Exception parsing date ("+dateInString+") : " + e.getMessage());
            return null;
        }
    }

}
