GET http://localhost:8080/api/dragons                     //list with all the dragons
POST http://localhost:8080/api/dragons                    // create a new dragon
  {"name": "Breeze",
  "type": "FIRE"}                                         // Possible types "FIRE", "ICE", "FOREST"
GET http://localhost:8080/api/dragons/{id}                //Full of information about the dragon by id
GET http://localhost:8080/api/dragons/stats/average-power // Average power of all dragons
POST http://localhost:8080/api/dragons/train              // The dragon became stronger by 5 units, but hunger increases by 5 units
  {"id": "d612be96-8ba7-4e6a-a691-aa3c7f07f319"}
POST http://localhost:8080/api/dragons/feed               // Рunger decreases by 5 units, and health increases by 5 units
  {"id": "d612be96-8ba7-4e6a-a691-aa3c7f07f319"}

Every minute the hunger of every dragon increases by 1 units or became 100 units. Every 5 minutes the age of every dragon increases by 1 age.
The launch date file Application.java
