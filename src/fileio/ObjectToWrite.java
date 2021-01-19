package fileio;

import players.Producer;

import java.util.List;

public final class ObjectToWrite {

    private List<ConsumerToWrite> consumers;
    private List<DistributorToWrite> distributors;
    private List<ProducerToWrite> producers;

    public ObjectToWrite(final List<ConsumerToWrite> consumers,
                         final List<DistributorToWrite> distributors,
                         final List<ProducerToWrite> producers) {
        this.consumers = consumers;
        this.distributors = distributors;
        this.producers = producers;
    }

    public List<ConsumerToWrite> getConsumers() {
        return consumers;
    }

    public List<DistributorToWrite> getDistributors() {
        return distributors;
    }

    public List<ProducerToWrite> getProducers() {
        return producers;
    }
}
