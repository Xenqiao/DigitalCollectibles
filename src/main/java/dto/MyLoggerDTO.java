package dto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 用于记录日志的单例模式类
 * 在所有必要的地方(如数据库连接、发生异常、数据异常等)进行统一日志框架进行记录日志
 * 支持记录不同日志等级，使用JDK自带的日志类
 * @author Xenqiao
 * @create 2023/5/23 15:49
 */
public class MyLoggerDTO {
    private static volatile MyLoggerDTO myLoggerDTO;
    private Logger logger = Logger.getLogger(MyLoggerDTO.class.getName());
    public MyLoggerDTO(){
    }

    /** 单例模式的双重检查 **/

    public static MyLoggerDTO getMyLoggerDTO(){
        if (myLoggerDTO == null){
            synchronized (MyLoggerDTO.class){
                if (myLoggerDTO == null){
                    myLoggerDTO = new MyLoggerDTO();
                }
            }
        }
        return myLoggerDTO;
    }

    public void log(Level level, String message) {
        logger.log(level, message);

        switch (level.intValue()) {
            case 1000:
                //表示最高级别的日志记录，通常用于记录严重错误，导致应用程序无法继续运行的情况。
                logger.severe(message);
                break;
            case 900:
                //表示警告级别的日志记录，通常用于记录可能导致应用程序出现问题或不正常运行的情况。
                logger.warning(message);
                break;
            case 800:
                //表示信息级别的日志记录，通常用于记录一般性的应用程序运行信息，如启动信息、配置信息等。
                logger.info(message);
                break;
            case 700:
                //表示配置级别的日志记录，通常用于记录应用程序的配置信息。
                logger.config(message);
                break;
            case 500:
                //表示精细级别的日志记录，通常用于记录详细的调试信息。
                logger.fine(message);
                break;
            case 400:
                //表示更精细级别的日志记录，通常用于记录更加详细的调试信息。
                logger.finer(message);
                break;
            case 300:
                //表示最精细级别的日志记录，通常用于记录最详细的调试信息。
                logger.finest(message);
                break;
            default:
                logger.log(level, message);
                break;
        }
    }
}
