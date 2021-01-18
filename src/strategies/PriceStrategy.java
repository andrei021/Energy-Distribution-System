package strategies;

import common.Comparators;
import players.Distributor;
import players.Producer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PriceStrategy implements Strategy {

    private ArrayList<Producer> priceProducers;

    public PriceStrategy(final List<Producer> producers) {
        this.priceProducers = new ArrayList<>(producers);
        Collections.sort(
                this.priceProducers,
                Comparators.getInstance().getPRICE());
    }

    @Override
    public void chooseProducers(final Distributor distributor) {
        for (Producer currentProducer : this.priceProducers) {
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
