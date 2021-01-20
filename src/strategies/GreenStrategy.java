package strategies;

import common.Comparators;
import players.Distributor;
import players.Producer;

import java.util.Collections;
import java.util.List;

public final class GreenStrategy extends EnergyStrategy {

    public GreenStrategy(final String name, final List<Producer> producers) {
        super(name, producers);
    }

    @Override
    public void chooseProducers(final Distributor distributor) {

        Collections.sort(
                super.getProducers(),
                Comparators.getInstance().getGREEN());

        for (Producer currentProducer : super.getProducers()) {
            if (distributor.hasEnoughEnergy()) {
                distributor.setHasToChooseProducers(false);
                return;
            }

            if (currentProducer.hasVacantPlace()) {
                distributor.addProducer(currentProducer);
            }
        }
    }
}
