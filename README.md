# things-todo
TODO app written to pick up some new skills

## Backend

Steps to run backend locally:
1. Make sure you have docker installed
1. Navigate into the backend folder and run `docker-compose up`
1. Run `./gradlew`

Backend will run on localhost port 4567

Current available Endpoints:
GET `/hello` -> Get a "Hello world" message
GET `/todos` -> Get a list of todos
GET `/todo/:id` -> Get todo with specific id
POST `/todo` -> Create a new todo. Accepts json in request body in format `{"message": "The todo message"}` 
