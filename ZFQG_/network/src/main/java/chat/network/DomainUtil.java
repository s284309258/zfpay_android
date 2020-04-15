package chat.network;
import chat.config.URLConfig;


public class DomainUtil {
    public static String configType="dev";//1、dev 2、test
    public static String getDomain(String domainType){
        StringBuilder builder=new StringBuilder();
        builder.append(URLConfig.conditionFlag == 0?"http://":"https://")
                .append(URLConfig.getMainUrl())
                .append("/");
        return builder.toString();
    }
}
