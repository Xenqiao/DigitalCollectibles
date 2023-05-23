package util.contractRealize;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;


/**
 * @author Xenqiao
 * @create 2023/4/1 22:55
 */
public abstract class FiscoInitializer {

    // 666,tmd是绝对路径
    //private String configFile = "src\\main\\resources\\config.toml";
    private String configFile = "C:\\Users\\谢金桥\\Desktop\\luanLai\\src\\main\\resources\\config.toml";
    private BcosSDK sdk = BcosSDK.build(configFile);
    private Client client = sdk.getClient(1);
    private CryptoKeyPair keyPair = client.getCryptoSuite().createKeyPair();


    /**
     * 单例模式的双重检查
     **/
    private static volatile FiscoInitializer getBcosSDK;

    private FiscoInitializer() {
        int i = 0;
    }

    public static FiscoInitializer theGetBcosSDK() {
        if (getBcosSDK == null) {
            synchronized (FiscoInitializer.class) {
                if (getBcosSDK == null) {
                    getBcosSDK = new FiscoInitializer() {
                    };
                }
            }
        }
        return getBcosSDK;
    }


    public Client getClient() {
        if (client != null) {
            return this.client;
        }
        return null;
    }

    public void stopBcosSDK() {
        if (sdk != null) {
            sdk.stopAll();
        }
    }

    public CryptoKeyPair getCryptoKeyPair() {
        if (keyPair != null) {
            return this.keyPair;
        }
        return null;
    }

}
