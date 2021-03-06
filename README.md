# Waterloo-coop-2021-inspirational-quote-service
This back end demo-project demonstrates the basics of the spring-boot-framework. The api fetches a live quote from an external API and then stores it in a postgreSQL database. 
Clients can fetch quotes by date and post a like or dislike to the quote.

## Classes and Descriptions

Quote: A java object that contains required information to display on a web server  

Controller: Mvc controller to handle mapping to the "/api/v1/quotes" route. Can either get a quote by date ("/api/v1/quotes/date") or can post a like/dislike to a quote ("/api/v1/quotes/uuid").

QuoteService: Implements a method to push the quote returned from remote or local fetch to the postgres database at midnight, and 2 methods that allow for fetching from the quote database by date and id respectively. 

FetchQuote: An interface used for classes implementing a fetch Quote method.

LocalFetch: A service implementing FetchQuote to read from a local JSON file and return a quote object. Runs when RemoteFetch fails or if there is no quote in the database for a particular date

RemoteFetch: A service implementing FetchQuote that fetches a JSONObject from "theysaidso" API at every midnight and is held for 24 hours and automatically flushed after. The JSONObject is read and turned into a quote object 

QuoteRepository: An interface that extends the JPA repository and implements a built in Spring method which returns a certain quote depending on the provided date.

Reaction:

ReactionRepository:

ReactionService:

## How to Run
1. After pulling, create a docker container with a postgres image with a database that is exposed to port 5432
2. Run mvn spring-boot:run or run the project from IDE 
3. The endpoint for getting quotes by date is: /api/v1/quotes/date
4. The endpoint for posting a like or dislike is: /api/v1/quotes/uuid 
