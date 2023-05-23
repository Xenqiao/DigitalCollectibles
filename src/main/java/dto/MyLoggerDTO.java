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
    private MyLoggerDTO(){
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
                logger.severe(message);
                break;
            case 900:
                logger.warning(message);
                break;
            case 800:
                logger.info(message);
                break;
            case 700:
                logger.config(message);
                break;
            case 500:
                logger.fine(message);
                break;
            case 400:
                logger.finer(message);
                break;
            case 300:
                logger.finest(message);
                break;
            default:
                logger.log(level, message);
                break;
        }
    }
}
