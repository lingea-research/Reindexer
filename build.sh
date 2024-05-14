#!/bin/bash

# -----------------------------------------------------------------------------
#
# Reindexer build script
#
# Script for compiling the reindexer using maven. 
# The script build an executable .jar file.
# Run the .jar using 'java -jar reindexer-1.0-SNAPSHOT.jar'
#
# -----------------------------------------------------------------------------

mvn package
