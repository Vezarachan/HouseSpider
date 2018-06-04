package SpiderCore;

import dao.HouseHZDao;
import model.HouseHZEntity;
import org.apache.log4j.BasicConfigurator;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author Plankton
 * @date 2018/06/04
 * @version 1.0.0
 * 继承了PageProcessor的HouseProessor类，用于选择页面的特定元素
 */
public class HouseProcessor implements PageProcessor {

    //房产信息列表REGEX表达式
    private static final String URL_LIST = "(https://hz\\.fang\\.anjuke\\.com/loupan/all/p\\d+/)";
    //房产的具体信息页面REGEX表达式
    private static final String URL_HOUSEINFO = "https://hz\\.fang\\.anjuke\\.com/loupan/\\w+\\.html";

    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public Site getSite() {
        return site;
    }

    /**
     *
     * @param page
     * 选择页面需要爬取的元素
     */
    @Override
    public void process(Page page) {

        //HouseHZEntity房产信息实体实例
        HouseHZEntity houseHZEntity = new HouseHZEntity();
        if (page.getUrl().regex(URL_LIST).match()) {
            //添加所有的房产信息页面
            page.addTargetRequests(page.getHtml().xpath("//html/body/div[@id=\"container\"]/div[@class=\"list-contents\"]/div[@class=\"list-results\"]/div[@class=\"key-list\"]").links().regex(URL_HOUSEINFO).all());
            //添加所有的房产信息列表页面
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
        } else {
            //添加房产信息HouseName，Address，Price
            houseHZEntity.setHouseName(page.getHtml().xpath("//div[@id=\"header\"]/div[@class=\"lp-info\"]/div[@class=\"lp-info-head\"]/div[@class=\"lp-tit\"]/h1/text()").get());
//            houseHZEntity.setAddress(page.getHtml().xpath("//div[@id=\"container\"]/div[@class=\"main-detail\"]/div[@class=\"basic-details\"]/div[@class=\"basic-parms-wrap\"]/dl[@class=\"basic-parms\"]/dd[4]/span/text()").get());
            houseHZEntity.setAddress(page.getHtml().xpath("//html/body/div[2]/div[1]/div[2]/div[1]/dl/dd[4]/span/text()").get());
            houseHZEntity.setPrice(page.getHtml().xpath("//div[@id=\"container\"]/div[@class=\"main-detail\"]/div[@class=\"basic-details\"]/div[@class=\"basic-parms-wrap\"]/dl[@class=\"basic-parms\"]/dd[@class=\"around-price\"]/span/text()").get().trim());
            //将获取到的房产信息写入数据库
            HouseHZDao.getOne().addHouseInfo(houseHZEntity);
        }
    }

    public static void main(String[] args) {

        //配置log4j
        BasicConfigurator.configure();

        //Spider类入口
        Spider.create(new HouseProcessor())
                //初始抓取页面
                .addUrl("https://hz.fang.anjuke.com/loupan/all/p1/")
                //设置线程数
                .thread(4)
                //启动爬虫
                .run();
    }
}
