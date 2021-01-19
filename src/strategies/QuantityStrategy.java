package strategies;

import common.Comparators;
import players.Distributor;
import players.Producer;

import java.util.Collections;
import java.util.List;

public final class QuantityStrategy extends EnergyStrategy {

    public QuantityStrategy(final String name, final List<Producer> producers) {
        super(name, producers);
    }

    @Override
    public void chooseProducers(final Distributor distributor) {

        Collections.sort(
                super.getProducers(),
                Comparators.getInstance().getQUANTITY());

        for (Producer currentProducer : super.getProducers()) {
            if (distributor.hasEnoughEnergy()) {
                return;
            }

            if (currentProducer.hasVacantPlace()) {
                distributor.addProducer(currentProducer);
            }
        }
    }
}
