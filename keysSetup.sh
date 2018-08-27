#!/usr/bin/env bash

function copyEnvVarsToGradleProperties {
    GRADLE_PROPERTIES=$HOME"/.gradle/gradle.properties"
    export GRADLE_PROPERTIES
    echo "Gradle Properties should exist at $GRADLE_PROPERTIES"

    if [ ! -f "$GRADLE_PROPERTIES" ]; then
        echo "Gradle Properties does not exist"

        echo "Creating Gradle Properties file..."
        mkdir $HOME"/.gradle"
        touch $GRADLE_PROPERTIES

        echo "SAFARICOM_CONSUMER_KEY=$SAFARICOM_CONSUMER_KEY" >> $GRADLE_PROPERTIES
        echo "SAFARICOM_CONSUMER_SECRET=$SAFARICOM_CONSUMER_SECRET" >> $GRADLE_PROPERTIES
    fi
}