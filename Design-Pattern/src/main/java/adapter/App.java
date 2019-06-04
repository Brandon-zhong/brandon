package adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author brandon
 * Created on 2019-06-04.
 * desc: 适配器模式,栗子: 有各类型内存卡 SD, Computer想要从内存卡中读取数据,但是computer只支持USB读取数据
 * 用适配器模式, 抽象出usb读取数据接口, 建立SD卡适配器 ,SD卡适配器实现USB接口并且持有SD卡对象,
 * 适配器从USB中读取SD卡的数据, Computer 实现USB读取数据接口并持有USB接口引用(即Computer不管具体对象,
 * 只管用usb中读数据,如果有的话),Computer从自身的usb方法里读取USB引用对象的数据;
 */
public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        Computer computer = new Computer(new ReadSDCardAdapter());
        String data = computer.usb();
        LOG.info("the data is --> {}", data);

    }

}
