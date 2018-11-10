---- Scraper ----

Assumption: an item is required have all 4 attributes found in the source material. If some attributes are not found or invalid, error will be printed out but scraping will carry on.

The project is implemented Spring Boot in IntelliJ 2018. The project has Maven nature and was compiled with Java 10 - project language level was however set at Java 8.

To run the project, one can open it in IntelliJ, create a run configuration with target URL provided as program argument and run it.

Alternatively, the project can be run with Maven spring-boot:run command. A wrapper bash shell called run.sh could be used to run the program with preset URL.
