package model;

/**
 * @author Plankton
 * @date 2018/06/04
 * @version 1.0.0
 * 爬取的房产信息存储使用的类
 */
public class HouseHZEntity {

    public HouseHZEntity() { }

    public HouseHZEntity(String houseName, String address, String price) {
        this.houseName = houseName;
        this.address = address;
        this.price = price;
    }

    private String houseName;

    private String address;

    private String price;

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "[" + houseName + "," + address + "," + price + "]";
    }
}
