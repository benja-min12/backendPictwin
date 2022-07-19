package cl.ucn.disc.dsm.pictwin.backend;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * @author Benjamin Millas
 * class Utils
 */
@Slf4j
public class Utils {

    /**
     * function print debug
     * @param objectName objectName
     * @param o object
     */
    public static void printObject(@NonNull String objectName, Object o){
        if(o != null){
            log.debug("{}: {}", objectName, ToStringBuilder.reflectionToString(o, RecursiveToStringStyle.
                    MULTI_LINE_STYLE));
        }else {
            log.debug("{}: null", objectName);
        }
    }
}
