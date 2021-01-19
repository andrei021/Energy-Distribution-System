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

    public abstract void chooseProducers(Distributor distributor);

    public String getName() {
        return name;
    }

    public List<Producer> getProducers() {
        return producers;
    }
}
