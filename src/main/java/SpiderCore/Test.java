package SpiderCore;

import dao.HouseHZDao;
import model.HouseHZEntity;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<HouseHZEntity> houseHZEntityList = new ArrayList<HouseHZEntity>();

        houseHZEntityList = HouseHZDao.getOne().getHouseInfo();

        houseHZEntityList.forEach( houseHZEntity -> {
            System.out.println(houseHZEntity.getHouseName() + " " + houseHZEntity.getAddress() + " " + houseHZEntity.getPrice());
        });
    }
}
