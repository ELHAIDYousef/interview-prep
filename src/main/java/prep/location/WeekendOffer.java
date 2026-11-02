package prep.location;

/**
 *
 * @author Yousef ELHAID
 */
class WeekendOffer extends Offer {

    private static final double WEEKEND_PRICE_RATIO = 0.8;          // ← plus de nombre magique

    WeekendOffer(String category) {
        super(category);
    }

    @Override
    double dailyPrice() {
        return baseDailyRate() * WEEKEND_PRICE_RATIO;
    }

    @Override
    WeekendOffer renew() {
        return new WeekendOffer(getCategory());
    }

    static String label() {
        return "Week-end";
    }
}