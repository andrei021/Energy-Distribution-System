Object Oriented Programming
Homework - project - phase 1

December 2020

Author: MIHAI Andrei 324CD

## Project structure
These are the project's packages and their classes

Package             Classes

fileio    ----------- InputLoader      

	      ----------- Writer    

	      ----------- ConsumerToWrite       

	      ----------- DistributorToWrite      

  	      ----------- ObjectToWrite


common ----------- Constants


game          ----------- Contract

              ----------- Cost

              ----------- Game

              ----------- MonthUpdate


players       ----------- Player

              ----------- Consumer

              ----------- Distributer

              ----------- PlayerFactory

## Usage of packages and classes
# Package fileio
    - Class InputLoader reads the initial state of the players from JSON format
    and saves it into arrays in order to create and return a game instance
    - Writer creates a JSON file containg the result of the game which was
    saved in an instance of type ObjectToWrite in special fields to match the 
    checker's tests

# Package game
    - Contains some entities which helped the players interact with each other
    (Contract, Cost, MonthUpdate)
    - Game class is the class that starts the game and returns the output of
    type ObjectToWrite. It also contains the game database formed by all players
    and updates. It performs every round using proper order of actions.

# Package players
    - Contains the base abstract class of a player which provides the behaviour 
    which all players must implement in their own way.
    - Consumer and Distributer are players, so they extend the base abstract 
    class and they come with their specific fields and methods
    - This package also contains the Singleton PlayerFactory which returns
    an instance of whatever player you ask for anywhere in the program