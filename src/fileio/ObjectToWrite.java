package fileio;

import java.util.List;

public final class ObjectToWrite {

    private List<ConsumerToWrite> consumers;
    private List<DistributorToWrite> distributors;
    private List<ProducerToWrite> energyProducers;

    public ObjectToWrite(final List<ConsumerToWrite> consumers,
                         final List<DistributorToWrite> distributors,
                         final List<ProducerToWrite> energyProducers) {
        this.consumers = consumers;
        this.distributors = distributors;
        this.energyProducers = energyProducers;
    }

    public List<ConsumerToWrite> getConsumers() {
        return consumers;
    }

    public List<DistributorToWrite> getDistributors() {
        return distributors;
    }

    public List<ProducerToWrite> getEnergyProducers() {
        return energyProducers;
    }
}
