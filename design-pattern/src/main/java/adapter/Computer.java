package adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author brandon
 * Created on 2019-06-04.
 * desc: 电脑类,读取存储卡
 */
public class Computer implements ReadCard {

    private final static Logger LOG = LoggerFactory.getLogger(Computer.class);

    private ReadCard readCard;

    public Computer(ReadCard readCard) {
        this.readCard = readCard;
    }

    public void setReadCard(ReadCard readCard) {
        this.readCard = readCard;
    }

    @Override
    public String usb() {
        return readCard.usb();
    }


}
