package fileio;

import java.util.List;

public final class ObjectToWrite {

    private List<ConsumerToWrite> consumers;
    private List<DistributorToWrite> distributors;

    public ObjectToWrite(final List<ConsumerToWrite> consumers,
                         final List<DistributorToWrite> distributors) {
        this.consumers = consumers;
        this.distributors = distributors;
    }

    public List<ConsumerToWrite> getConsumers() {
        return consumers;
    }

    public List<DistributorToWrite> getDistributors() {
        return distributors;
    }
}
