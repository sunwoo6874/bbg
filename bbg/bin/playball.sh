#!/bin/bash

# export JAVA_HOME="/data03/sunwoo/jdk/jdk-21.0.2"
# export PATH=${PATH}:$JAVA_HOME/bin

if [ $# -ne 2 ]; then
    echo "Usage: $0 {wiki|book} coins"
    echo "Endless mode: For endless mode, insert any negative value for coins."
    echo "Example: $0 wiki -1"
    exit 1
fi

if [[ $1 != "wiki" && $1 != "book" ]]; then
    echo "Error: The first argument must be 'wiki' or 'book'."
    exit 1
fi

if ! [[ $2 =~ ^-?[0-9]+$ ]]; then
    echo "Error: The second argument must be a valid number."
    exit 1
fi

if [ $2 -lt 0 ]; then
    echo "Endless mode activated."
else
    echo "Starting game with $2 coins."
fi

java -cp "../lib/*:bbg.jar" com.ojt.bbg.GameStarter $1 $2