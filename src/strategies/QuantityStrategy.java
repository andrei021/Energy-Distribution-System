package strategies;

import common.Comparators;
import players.Distributor;
import players.Producer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class QuantityStrategy implements Strategy {

    private ArrayList<Producer> quanityProducers;

    public QuantityStrategy(final List<Producer> producers) {
        this.quanityProducers = new ArrayList<>(producers);
        Collections.sort(
                this.quanityProducers,
                Comparators.getInstance().getQUANTITY());
    }

    @Override
    public void chooseProducers(final Distributor distributor) {
        for (Producer currentProducer : this.quanityProducers) {
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
