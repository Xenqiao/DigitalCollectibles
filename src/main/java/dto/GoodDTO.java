package dto;

/**
 * @author Xenqiao
 * @create 2023/5/23 10:01
 */
public class GoodDTO {
    private String hash;
    private Integer price;
    private String maker;
    private String owner;
    private String title;
    private Boolean isForSale;
    private String description;
    private Integer goodID;

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getForSale() {
        return isForSale;
    }

    public void setForSale(Boolean forSale) {
        isForSale = forSale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGoodID() {
        return goodID;
    }

    public void setGoodID(Integer goodID) {
        this.goodID = goodID;
    }
}
