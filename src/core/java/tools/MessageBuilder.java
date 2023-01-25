package core.java.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageBuilder {
    
    public static final String AUX = "&var";

    private MessageBuilder(){
    }    

    public static String build(String message, String ...args) {
        return build("", message, args);
    }

    public static String build(String code, String message, String ...args) {
        String buildMessage = message;

        for (int i = 0; i < args.length; i++) {
            int index = args.length > 1 ? i + 1 : i;
            String aux = buildAux(index);
            buildMessage = replaceAll(buildMessage, aux, args[i]);
        }
        
        buildMessage = injectCode(code, buildMessage);

        return buildMessage;
    }

    private static String buildAux(int index) {
        StringBuilder sb = new StringBuilder();
        sb.append(AUX);
        if(index > 0)
            sb.append(index);
        return sb.toString();
    }

    private static String replaceAll(String buildMessage, String aux, String replace) {
        Pattern pat = Pattern.compile(aux);
        Matcher mat = pat.matcher(buildMessage);
        buildMessage = mat.replaceAll(replace);

        return buildMessage;
    }

    private static String injectCode(String code, String message) {
        StringBuilder sb = new StringBuilder();
        
        if(!code.isEmpty()){
            sb.append('[');
            sb.append(code);
            sb.append("] => ");
        }
        sb.append(message);
        sb.append('.');

        return sb.toString();
    }

}