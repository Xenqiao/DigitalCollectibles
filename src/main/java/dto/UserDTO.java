package dto;

import java.util.List;

/**
 * @author Xenqiao
 * @create 2023/4/27 21:31
 */
public class UserDTO {
    private String userName;
    private String name;
    private String pwd;
    private String hash;
    private Integer balance;

    /** 单例模式的双重检查 **/
    private static volatile UserDTO userDTO;
    public UserDTO(){
    }
    public static UserDTO getUserDTO(){
        if (userDTO == null){
            synchronized (UserDTO.class){
                if (userDTO == null){
                    userDTO = new UserDTO();
                }
            }
        }
        return userDTO;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", hash='" + hash + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
