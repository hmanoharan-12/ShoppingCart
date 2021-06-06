# Shopping Cart

## Description 

This application visualizes a list of prices for items. Contains a price engine to calculate prices for a given quantity of item. 

## Table of Contents
* [Installation](#installation)
* [Usage](#usage)

## Installation

*Database*

In order to create the database and persist the required information execute the `createdatabase.sql` script provided in the location `\database`. The script contains:
* Query to create the database.
* Query to create necessary tables.
* Query to insert necessary data.

*Server*

The server side of this application is a web service developed using spring boot. In order to run the application follow the steps below:
* Navigate to the `application.properties` file in the location `server/shoppingcartapi/src/main/resources/` and update your database information.
* Open command prompt from the location `server\shoppingcartapi` and execute the command `gradlew build`.
* Once the build is success, navigate to the location `server\shoppingcartapi\build\libs` and open command prompt. 
* Execute the `shoppingcartapi-0.0.1-SNAPSHOT.jar` buy running the command `java -jar shoppingcartapi-0.0.1-SNAPSHOT.jar`.
* The Web Service should now be running on localhost port 8080.

*Client*

The client side of this application is development with Angular version 10 framework. In order to run the client of the application follow the steps below.
* Open command prompt in the location `client\ShoppingCartClient` and run `npm install`.
* Once the dependencies are installed by the node package manager, Execute the command `ng serve`.
* The client should now be running on localhost port 4200.

## Usage


 