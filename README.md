# bayesian-latent-forests
[![Build Status](https://travis-ci.org/ferjorosa/bayesian-latent-forests.png?branch=master)](https://travis-ci.org/ferjorosa/bayesian-latent-forests) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This is the code repository of the paper **Bayesian discovery of latent forests** (IEEE Access). It includes the Java implementation of the *incremental learner* (IL) and *constrained incremental learner* (CIL) algorithms described in the paper. It also includes data and code used to run the experiments described in the paper.

Both methods are build on top of the <a href="https://github.com/amidst/toolbox">Amidst toolbox</a>, which has an implementation of the Variational Message Passing algorithm.

## Requirements

* <a href="https://www.oracle.com/java/technologies/javase-jdk8-downloads.html">Java JDK 8 (or higher)</a>
* <a href="https://www.jetbrains.com/idea/">Intellij IDEA</a>
	* <a href="https://www.lagomframework.com/documentation/1.6.x/java/IntellijSbtJava.html">Scala plugin in Intellij IDEA</a>
* <a href="https://www.python.org/downloads/">Python 3.x</a>
  
**Note:** Intellij IDEA is an IDE for Java/Scala, while not strictly necessary to explore the code and execute the experiments, we deem it advisable for people that are not
very familiar with the Java virtual machine and the Scala ecosystem. The project is build using the *Scala Build Tool* (SBT)

## Installation
Once you have installed the Java JDK and Intellij IDEA, the process is identical to any other SBT project.

* Download/clone the project from Github.
* Import it to Intellij (<a href="https://www.lagomframework.com/documentation/1.6.x/java/IntellijSbtJava.html">how to import an SBT project</a>)

## Usage

Once the installation has finished you should see something like this:

![alt text](https://i.imgur.com/2py6OWu.png "Intellij with imported project")

* The **src** directory contains the project's Java source code. It is organized in five main packages.
	* **eu.admist** contains the AMIDST toolbox source code, which has been extended to develop the IL and CIL algorithms.
	* **experiments** contains the source code of all the experiments described in the paper. They can be individually executed or in group.
	* **methods** contains the interface of the algorithms used in the experiments, such as Bin-A, GS, GEAST, etc.
	* **voltric** contains the source code of an open-source library that we are currenty developing for Bayesian networks.
	* **org.latlab.core** contains the open-source library code that powers the GEAST algorithm.

* The **python-project** directory contains a series of Python notebooks that were necessary to the preparation of the "Spanish living conditions" experiment.

* The **zhang_algorithms** directory contains the executables of BI and EAST algorithms. The contain the originally provided .jar files. However, in order to make it 
more user-friendly and easier to run the experiments in our paper, we developed one python script for each algorithm that automatically executes all the experiments in the comparative
study without needing to manually go one by one.

### How to run the comparative study

Each experiment in the discrete and continuous comparative studies contains a main public method that can be executed. The process is as simple as right-clicking on the corresponding 
experiment file and, on the pop-up, clicking on "run".

![alt text](https://i.imgur.com/FevRZEg.png "How to run the Hiv-test experiment")

As an example, when running the Hiv-test experiment in the discrete comparative study, it should return something like this:

![alt text](https://i.imgur.com/PocEwFs.png "Hiv-test execution example")

### How to run the Spanish living conditions experiment

