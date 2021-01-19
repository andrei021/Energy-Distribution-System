package common;

import players.Producer;

import java.util.Comparator;

public final class Comparators {
    private static final Comparators COMPARATORS;

    static {
        COMPARATORS = new Comparators();
    }

    public static Comparators getInstance() {
        return COMPARATORS;
    }

    private static final Comparator<Producer> GREEN = (o1, o2) -> {

        if (o1.isGreen() && !o2.isGreen()) {
            return -1;
        } else if (o2.isGreen() && !o1.isGreen()) {
            return  1;
        }

        if (o1.getPriceKW() > o2.getPriceKW()) {
            return 1;
        } else if (o1.getPriceKW() < o2.getPriceKW()) {
            return -1;
        }

        if (o1.getEnergyPerDistributor() > o2.getEnergyPerDistributor()) {
            return -1;
        } else if (o1.getEnergyPerDistributor() < o2.getEnergyPerDistributor()) {
            return 1;
        }

        if (o1.getId() > o2.getId()) {
            return 1;
        } else if (o1.getId() < o2.getId()) {
            return -1;
        }

        return 0;
    };

    private static final Comparator<Producer> PRICE = (o1, o2) -> {

        if (o1.getPriceKW() > o2.getPriceKW()) {
            return 1;
        } else if (o1.getPriceKW() < o2.getPriceKW()) {
            return -1;
        }

        if (o1.getEnergyPerDistributor() > o2.getEnergyPerDistributor()) {
            return -1;
        } else if (o1.getEnergyPerDistributor() < o2.getEnergyPerDistributor()) {
            return 1;
        }

        if (o1.getId() > o2.getId()) {
            return 1;
        } else if (o1.getId() < o2.getId()) {
            return -1;
        }

        return 0;
    };

    private static final Comparator<Producer> QUANTITY = (o1, o2) -> {

        if (o1.getEnergyPerDistributor() > o2.getEnergyPerDistributor()) {
            return -1;
        } else if (o1.getEnergyPerDistributor() < o2.getEnergyPerDistributor()) {
            return 1;
        }

        if (o1.getId() > o2.getId()) {
            return 1;
        } else if (o1.getId() < o2.getId()) {
            return -1;
        }

        return 0;
    };

    public Comparator<Producer> getGREEN() {
        return GREEN;
    }

    public Comparator<Producer> getPRICE() {
        return PRICE;
    }

    public Comparator<Producer> getQUANTITY() {
        return QUANTITY;
    }
}
