
# Reindexer

This program is used to send a whole document storage to indexingService API to index. During the reindexing, the Reindexer mimics the behaviour of a indexer, so that the indexing service shouldn't notice any difference.

Reindexer uses DocumentStorage to read the given metadata file to obtain document IDs and indexer metadata (such as content type, indexer ID, last change time, and URL), then to read the document in the binary form from the data file using the index, and finally sends the metadata along with the document via HTTP and multipart/form-data to indexing service.

## Installation

To build the program, use the **build.sh** script, which uses maven. Compiled .jar is by default put into the folder _./target/_ as _reindexer-1.0-SNAPSHOT.jar_.

The only dependency you need to worry about is _DocumentStorage.jar_ which should be placed in the _./lib/_ folder.

## How to run

First you need to obtain the document storage data, index, and meta files. You don't need the SQLite database for reindexing.

Make sure that indexingService is running and listening on a known port. By default, the reindexer expects indexingService to listen at _localhost:8080/index_.

### With the run.sh script

```$ ./run.sh```

_Note: The run.sh script expects the document storage files to be present in the current directory._

### Manually with java

```$ java -jar target/reindexer-1.0-SNAPSHOT.jar documentStorage.data documentStorage.idx documentStorage.meta```

You can optionally add the indexingService URL:

```$ java -jar target/reindexer-1.0-SNAPSHOT.jar documentStorage.data documentStorage.idx documentStorage.meta localhost:8080/index```

