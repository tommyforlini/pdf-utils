# pdf-utils

A concatination utility library for PDFs

## Build

Type 

    $ ./gradlew clean build
    
to build the project. The final SpringBoot **executable** binary will be located `build/libs/<artifactname>.jar`

## Running on local

> NOTE: The utility library deduces the users ```Downloads``` folder to search for incoming files and produce resulting file.

Type 

    $ ./gradlew clean bootRun -Pargs="page1.pdf page2.pdf"

This will generate a ```result.pdf``` which is a concatination of incoming argument files.

### Running on local - Shortcuts

Type

    $ ./concat3.sh

This will assume 3 pdfs named ```page1.pdf page2.pdf page3.pdf``` will need to be concatinated into a ```result.pdf```.

Type

    $ ./concat4.sh

This will assume 4 pdfs named ```page1.pdf page2.pdf page3.pdf page4.pdf``` will need to be concatinated into a ```result.pdf```.