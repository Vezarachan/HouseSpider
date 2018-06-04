package model;

/**
 * @author Plankton
 * @date 2018/06/04
 * @version 1.0.0
 * 房产的详细信息——房产姓名、价格、经度和纬度
 */
public class HouseDetailEntity {

    public HouseDetailEntity() { }

    public HouseDetailEntity(String houseName, int price, double longtitude, double latitude) {
        this.houseName = houseName;
        this.price = price;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    private String houseName;

    private int price;

    private double longtitude;

    private double latitude;

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "[ " + houseName + "," + price + "," + longtitude + "," + latitude + " ]";
    }
}
