package decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author brandon
 * create on 2019-06-12
 * desc:
 */
public class StormSword implements Sword {

    private Logger LOG = LoggerFactory.getLogger(StormSword.class);

    private Sword sword;

    public StormSword(Sword sword) {
        this.sword = sword;
    }

    @Override
    public void attack() {
        sword.attack();
        LOG.info("this is storm sword");

    }

    @Override
    public int getAttachPower() {
        return sword.getAttachPower() + 3;
    }
}
