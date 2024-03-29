# Object Oriented Programming - Energy Distribution System 

January 2020

Author: MIHAI Andrei 324CD

# About
This program simulates an Energy Distribution System composed of 3 major
entities: Consumers, Producers, Distributors. The input was given as JSON files,
hence I have used the Jackson library in order to parse them. The output was 
stored in JSON files as well.

Consumers have to choose the best contract they can afford from distributors 
and pay their expenses at the end of each month. A month is considered a round.

Distributors need to receive an amount of energy, therefore they will pick 
out from the contracts offerred by producers based on a specific strategy.

Distributors and Producers may recalculate their infrastructure costs and 
change the price of their contracts based on the number of current clients. 
Consumers/distributors whose contracts ended are able to pick out from the 
renewed contracts at the end of the month.

The simulation ends when all distributors/consumers run out of money or
after a number given of rounds run their course.

# Usage of packages and classes
## Package fileio
    - Class InputLoader reads the initial state of the players from JSON format
    and saves it into arrays in order to create and return a game instance
    - Writer creates a JSON file containg the result of the game which was
    saved in an instance of type ObjectToWrite in special fields to match the 
    checker's tests

## Package game
    - Contains the entities which helped the players interact with each other
    (Contract, Cost, MonthUpdate, MonthStats)
    - Game class is the class that starts the game and returns the output of
    type ObjectToWrite. It also contains the game database formed by all players
    and updates. It performs every round using proper order of actions.

## Package players
    - Contains the base abstract class of a player which provides the behaviour 
    which all players must implement in their own way.
    - Consumer Distributer and Producer are players, so they extend the base 
    abstract class and they come with their specific fields and methods
    - This package also contains the Singleton PlayerFactory which returns
    an instance of whatever player you ask for anywhere in the program

## Package strategies
    - Contains the base abstract class EnergyStrategy which provides the 
    signature of the method that all the concrete energy strategies must
    implement.
    - It also contains the 3 concrete strategies which help distributors choose
    their producers properly

# Design patterns
## Factory Method used with Singleton
    - I've used these design patterns combined in order to create a specific
    instance of a player wherever and whenever in the program very quickly
    and easy.

## Observer
    - The abstract class Player extends Observable, so all the classes that 
    extend Player are now able to accept observers and notify them. In our case,
    Producers will be the entities observed so there's the only place I've 
    overridden methods from Observable class.
    - Class Distributor implements Observer interface. Each time a distributor
    chooses a producer, it becomes its observer. If the producer happens to 
    change its energy, it will notify all its observers that something has
    changed and they need to choose their producers again.      

## Strategy
    - The base class EnergyStrategy provides the signature of the method
    chooseProducers which is given a distributor. All concrete strategies
    that inherit from this base class must implement this method in a way that's
    proper to the specific strategy.
    - When a distributor has to choose producers, it recieves all types of 
    strategies as parameters. One of the strategies recieved must match the
    distributor's strategy, so it will choose his next producers based on it.