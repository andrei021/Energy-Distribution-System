package fileio;

import common.Constants;
import game.Game;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import game.Cost;
import game.MonthUpdate;
import players.Consumer;
import players.Distributor;
import players.PlayerFactory;
import players.Producer;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class InputLoader {

    private static final InputLoader INPUT_LOADER;

    static {
        INPUT_LOADER = new InputLoader();
    }

    private InputLoader() {
    }

    public static InputLoader getInstance() {
        return INPUT_LOADER;
    }

    /**
     * @param inputPath path to JSON input file
     * @return an object of type game.Game which holds the game's initial
     * data - players and each month's updates
     */
    public Game readData(final String inputPath) {
        JSONParser jsonParser = new JSONParser();

        int numOfTurns = 0;
        List<Consumer> consumers = new ArrayList<>();
        List<Distributor> distributors = new ArrayList<>();
        List<Producer> producers = new ArrayList<>();
        List<MonthUpdate> updates = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            numOfTurns = Integer
                    .parseInt(String.valueOf(jsonObject.get(Constants.NUM_OF_TURNS)));
            JSONObject initialData = (JSONObject) jsonObject
                    .get(Constants.INITIAL_DATA);
            JSONArray jsonConsumers = (JSONArray) initialData
                    .get(Constants.CONSUMERS);
            JSONArray jsonDistributors = (JSONArray) initialData
                    .get(Constants.DISTRIBUTORS);
            JSONArray jsonProducers = (JSONArray) initialData
                    .get(Constants.PRODUCERS);
            JSONArray jsonMonthlyUpdates = (JSONArray) jsonObject
                    .get(Constants.MONTHLY_UPDATES);

            if (jsonConsumers != null) {
                for (Object jsonConsumer : jsonConsumers) {
                    int id = Integer
                            .parseInt(String.valueOf(((JSONObject) jsonConsumer)
                                    .get(Constants.ID)));
                    int initialBudget = Integer
                            .parseInt(String.valueOf(((JSONObject) jsonConsumer)
                                    .get(Constants.INITIAL_BUDGET)));
                    int monthlyIncome = Integer
                            .parseInt(String.valueOf(((JSONObject) jsonConsumer)
                                    .get(Constants.MONTHLY_INCOME)));

                    consumers.add(PlayerFactory.getInstance().createConsumer(
                            id,
                            initialBudget,
                            monthlyIncome
                    ));
                }
            }

            if (jsonDistributors != null) {
                for (Object jsonDistributor : jsonDistributors) {
                    int id = Integer
                            .parseInt(String.valueOf(((JSONObject) jsonDistributor)
                                    .get(Constants.ID)));
                    int contractLength = Integer
                            .parseInt(String.valueOf(((JSONObject) jsonDistributor)
                                    .get(Constants.CONTRACT_LENGTH)));
                    int initialBudget = Integer
                            .parseInt(String.valueOf(((JSONObject) jsonDistributor)
                                    .get(Constants.INITIAL_BUDGET)));
                    int initialInfrastructureCost = Integer
                            .parseInt(String.valueOf(((JSONObject) jsonDistributor)
                                    .get(Constants.INITIAL_INFRASTRUCTURE_COST)));
                    int energyNeededKW = Integer
                            .parseInt(String.valueOf(((JSONObject) jsonDistributor)
                                    .get(Constants.ENERGY_NEEDED)));

                    String producerStrategy = (String.valueOf(((JSONObject) jsonDistributor)
                            .get(Constants.PRODUCER_STRATEGY)));

                    distributors.add(PlayerFactory.getInstance().createDistributor(
                            id,
                            initialBudget,
                            contractLength,
                            new Cost(id, initialInfrastructureCost),
                            energyNeededKW,
                            producerStrategy
                    ));
                }
            }

            if (jsonProducers != null) {
                for (Object jsonProducer : jsonProducers) {
                    int id = Integer
                            .parseInt(String.valueOf(((JSONObject) jsonProducer)
                                    .get(Constants.ID)));
                    String energyType = String.valueOf(((JSONObject) jsonProducer)
                                    .get(Constants.ENERGY_TYPE));
                    int maxDistributors = Integer
                            .parseInt(String.valueOf(((JSONObject) jsonProducer)
                                    .get(Constants.MAX_DISTRIBUTORS)));
                    double priceKW = Double
                            .parseDouble(String.valueOf(((JSONObject) jsonProducer)
                                    .get(Constants.PRICE_KW)));
                    int energyPerDistributor = Integer
                            .parseInt(String.valueOf(((JSONObject) jsonProducer)
                                    .get(Constants.ENERGY_PER_DISTRIBUTOR)));

                    producers.add(PlayerFactory.getInstance().createProducer(
                            id,
                            energyType,
                            maxDistributors,
                            priceKW,
                            energyPerDistributor
                    ));
                }
            }

            if (jsonMonthlyUpdates != null) {
                for (Object jsonCurrentMonth : jsonMonthlyUpdates) {
                    MonthUpdate currentMonth = new MonthUpdate();

                    JSONArray jsonCosts = (JSONArray) ((JSONObject) jsonCurrentMonth)
                            .get(Constants.DISTRIBUTOR_CHANGES);
                    JSONArray jsonNewConsumers = (JSONArray) ((JSONObject) jsonCurrentMonth)
                            .get(Constants.NEW_CONSUMERS);
                    JSONArray jsonProducerChanges = (JSONArray) ((JSONObject) jsonCurrentMonth)
                            .get(Constants.PRODUCER_CHANGES);

                    if (jsonNewConsumers != null) {
                        for (Object jsonConsumer : jsonNewConsumers) {
                            int id = Integer
                                    .parseInt(String.valueOf(((JSONObject) jsonConsumer)
                                            .get(Constants.ID)));
                            int initialBudget = Integer
                                    .parseInt(String.valueOf(((JSONObject) jsonConsumer)
                                            .get(Constants.INITIAL_BUDGET)));
                            int monthlyIncome = Integer
                                    .parseInt(String.valueOf(((JSONObject) jsonConsumer)
                                            .get(Constants.MONTHLY_INCOME)));

                            currentMonth.addNewConsumer(
                                    PlayerFactory.getInstance().createConsumer(
                                            id,
                                            initialBudget,
                                            monthlyIncome
                                    ));
                        }
                    }

                    if (jsonCosts != null) {
                        for (Object jsonCost : jsonCosts) {
                            int id = Integer
                                    .parseInt(String.valueOf(((JSONObject) jsonCost)
                                            .get(Constants.ID)));
                            int infrastructureCost = Integer
                                    .parseInt(String.valueOf(((JSONObject) jsonCost)
                                            .get(Constants.INFRASTRUCTURE_COST)));

                            currentMonth.addNewDistributorCost(new Cost(
                                    id,
                                    infrastructureCost));
                        }
                    }

                    if (jsonProducerChanges != null) {
                        for (Object jsonProducer : jsonProducerChanges) {
                            int id = Integer
                                    .parseInt(String.valueOf(((JSONObject) jsonProducer)
                                            .get(Constants.ID)));
                            int energyPerDistributor = Integer
                                    .parseInt(String.valueOf(((JSONObject) jsonProducer)
                                            .get(Constants.ENERGY_PER_DISTRIBUTOR)));

                            currentMonth.addNewProducerEnergyCost(new Cost(
                                    id,
                                    energyPerDistributor));
                        }
                    }

                    updates.add(currentMonth);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return new Game(numOfTurns, consumers, producers, distributors, updates);
    }
}
