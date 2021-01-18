package players;

import game.Cost;

public final class PlayerFactory {
    private static final PlayerFactory PLAYER_FACTORY;

    static {
        PLAYER_FACTORY = new PlayerFactory();
    }

    private PlayerFactory() {
    }

    public static PlayerFactory getInstance() {
        return PLAYER_FACTORY;
    }

    /**
     * @return a Distributor instance
     */
    public Distributor createDistributor(final int id, final int budget,
                                         final int contractLength, final Cost cost,
                                         final int energyNeededKW, final String producerStrategy) {
        return new Distributor(id, budget, contractLength, cost, energyNeededKW, producerStrategy);
    }

    public Producer createProducer(final int id, final String energyType, final int maxDistributors,
                                   final double priceKW, final int energyPerDistributors) {
        return new Producer(id, energyType, maxDistributors, priceKW, energyPerDistributors);
    }

    /**
     * @return a Consumer instance
     */
    public Consumer createConsumer(final int id, final int budget,
                                   final int monthlyIncome) {
        return new Consumer(id, budget, monthlyIncome);
    }
}
