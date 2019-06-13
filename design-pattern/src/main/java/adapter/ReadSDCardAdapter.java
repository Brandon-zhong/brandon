package adapter;

/**
 * @author brandon
 * Created on 2019-06-04.
 * desc:
 */
public class ReadSDCardAdapter implements ReadCard {

    private SDCard sdCard;

    public ReadSDCardAdapter() {
        this.sdCard = new SDCard();
    }

    public void setSdCard(SDCard sdCard) {
        this.sdCard = sdCard;
    }

    @Override
    public String usb() {
        return sdCard.sdCardRead();
    }
}
