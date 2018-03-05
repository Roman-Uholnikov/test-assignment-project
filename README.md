## About
This project is implementation of the next assignment:

```
Your service should work with small documents where each document contains a series of tokens (words). To keep things simple document can
be represented as String.
The usage model of your service:
    - Put documents into the search engine by key
    - Get document by key
    - Search on a string of tokens to return keys of all documents that contain all tokens in the set
For index persistence you can store documents in server's memory.
To keep things simple we can assume that there will be no overwrites of a key with a new document.
You should not use existing tools like Lucene based solution, Sphinx or similar.
Simplest static configuration could be used (no service discovery, replicas, balancing etc.)
```
Full description in [Java_TestTask__2018.pdf](Java_TestTask__2018.pdf)
 
## How to run
To run everything execute

```
start mvnw spring-boot:run -f test-assignment-client/pom.xml
start mvnw spring-boot:run -f test-assignment-server/pom.xml
```
Or simply [run.bat](run.bat) or [run.sh](run.sh)

Open [http://localhost:8080](http://localhost:8080) to visit client.


## How to test
Execute in the root folder of the project:

```
mvnw test
```

## Time spent
Approximate time that was spent on the project: 8-10 hours.