package decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author brandon
 * create on 2019-06-12
 * desc: 长剑
 */
public class LongSword implements Sword {

    private static final Logger LOG = LoggerFactory.getLogger(LongSword.class);

    @Override
    public void attack() {
        LOG.info("this is long sword");
    }

    @Override
    public int getAttachPower() {
        return 6;
    }

}
