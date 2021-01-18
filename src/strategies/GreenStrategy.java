package strategies;

import common.Comparators;
import players.Distributor;
import players.Producer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreenStrategy implements Strategy {

    private ArrayList<Producer> greenProducers;

    public GreenStrategy(final List<Producer> producers) {
        this.greenProducers = new ArrayList<>(producers);
        Collections.sort(
                this.greenProducers,
                Comparators.getInstance().getGREEN());
    }

    @Override
    public void chooseProducers(final Distributor distributor) {
        for (Producer currentProducer : this.greenProducers) {
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
