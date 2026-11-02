package prep.location;

/**
 *
 * @author Yousef ELHAID
 */
class StandardOffer extends Offer {

    StandardOffer(String category) {
        super(category);
    }

    @Override
    double dailyPrice() {
        return baseDailyRate();
    }

    @Override
    StandardOffer renew() {
        return new StandardOffer(getCategory());
    }
}
