package strategies;

import players.Distributor;
import players.Producer;

import java.util.ArrayList;
import java.util.List;

public abstract class EnergyStrategy {

    private final String name;
    private final List<Producer> producers;

    public EnergyStrategy(final String name, final List<Producer> producers) {
        this.name = name;
        this.producers = new ArrayList<>(producers);
    }

    /**
     * Every concrete energy strategy that extends this class has to implement
     * its way of choosing producers for the distributor given as param
     */
    public abstract void chooseProducers(Distributor distributor);

    public final String getName() {
        return name;
    }

    public final List<Producer> getProducers() {
        return producers;
    }
}
