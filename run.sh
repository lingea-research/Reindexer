#!/bin/bash

# -----------------------------------------------------------------------------
#
# Reindexer run script
#
# Script for simplified running of the reindexer.
#
# -----------------------------------------------------------------------------

java -jar target/reindexer-1.0-SNAPSHOT.jar documentStorage.data documentStorage.idx documentStorage.meta localhost:8080/index