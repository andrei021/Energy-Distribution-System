package game;

import players.Consumer;

import java.util.ArrayList;
import java.util.List;

public final class MonthUpdate {

    private List<Consumer> newConsumers;
    private List<Cost> newDistributorCosts;
    private List<Cost> newProducerEnergyCosts;


    public MonthUpdate() {
        this.newConsumers = new ArrayList<>();
        this.newDistributorCosts = new ArrayList<>();
        this.newProducerEnergyCosts = new ArrayList<>();
    }

    /**
     * Adds a cost the this month's list of costs that will be
     * used later to update a producer's energy per distributor
     * based on id
     *
     * The updated energy per distributor is saved in infrastructureCost
     */
    public void addNewProducerEnergyCost(final Cost cost) {
        this.newProducerEnergyCosts.add(cost);
    }

    /**
     * Adds a new consumer to this month's list of new consumers
     */
    public void addNewConsumer(final Consumer consumer) {
        this.newConsumers.add(consumer);
    }

    /**
     * Adds a cost the this month's list of costs that will be
     * used later to update a distributor's costs based on id
     */
    public void addNewDistributorCost(final Cost cost) {
        this.newDistributorCosts.add(cost);
    }

    public List<Consumer> getNewConsumers() {
        return newConsumers;
    }

    public List<Cost> getNewDistributorCosts() {
        return newDistributorCosts;
    }

    public List<Cost> getNewProducerEnergyCosts() {
        return newProducerEnergyCosts;
    }
}
