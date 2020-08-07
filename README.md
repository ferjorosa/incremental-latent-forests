# incremental-latent-forests
[![Build Status](https://travis-ci.com/ferjorosa/incremental-latent-forests.png?branch=master)](https://travis-ci.com/ferjorosa/incremental-latent-forests) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This is the code repository of the paper **Incremental learning of latent forests** (IEEE Access). It includes the Java implementation of the *incremental learner* (IL) and *constrained incremental learner* (CIL) algorithms described in the paper. It also includes data and code used to run the experiments described in the paper.

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

![alt text](https://i.imgur.com/dzCfC4l.png "Intellij with imported project")

* The **src** directory contains the project's Java source code. It is organized in five main packages.
	* **eu.admist** contains the AMIDST toolbox source code, which has been extended to develop the IL and CIL algorithms.
	* **experiments** contains the source code of all the experiments described in the paper. They can be individually executed or in group.
	* **methods** contains the interface of the algorithms used in the experiments, such as Bin-A, GS, GEAST, etc.
	* **voltric** contains the source code of an open-source library that we are currenty developing for Bayesian networks.
	* **org.latlab.core** contains the open-source library code that powers the GEAST algorithm.

* The **python-project** directory contains a series of Python notebooks that were necessary to the preparation of the "Spanish living conditions" experiment.

* The **zhang_algorithms** directory contains the executables of BI and EAST algorithms. The contain the originally provided <code>.jar</code> files. However, in order to make it 
more user-friendly and easier to run the experiments in our paper, we developed one python script for each algorithm that automatically executes all the experiments in the comparative
study without needing to manually go one by one.

* The **data** directory contains datasets required for executing the experiments described in the paper. Now, given that the EAST executable developed by Chen et al. requires an specific data format to work, we have prepared another folder called **data_east** with the same datasets in the specific format.

* The **results** directory contains the results of the comparative and spanish living conditions experiments. In the case of the comparative study it contains a series of JSON files (one for each method and experiment) with the time and score results of each fold, and the average value. They have the following format:

![alt text](https://i.imgur.com/f2iQFLV.png "Result from Bin-A of the Hiv-test experiment")


### How to run the comparative study

Each experiment in the discrete and continuous comparative studies contains a main public method that can be executed. The process is as simple as right-clicking on the corresponding 
experiment file and, on the pop-up, clicking on "run".

![alt text](https://i.imgur.com/mzlZ9xS.png "How to run the Hiv-test experiment")

As an example, when running the Hiv-test experiment in the discrete comparative study, it should return something like this:

![alt text](https://i.imgur.com/OVSDqEi.png "Hiv-test execution example")

Notice that each experiment has its own script, which allows us to run them individually. In addition, we have prepared two scripts called *RunExperiments* in the discrete and continuous packages that can be executed to run all experiments at once. However, notice that there are experiments that may take days to finish, such as News_100 or Coil_42.

### How to run the Spanish living conditions experiment
The process of running the Spanish living conditions experiment is equivalent to any other experiment, simply running its main method. The main difference of this experiment is that we have prepared another script called *Learn_Spanish_living_conditions* that generates the <code>.xdsl</code> files of the results from the IL, CIL and LCM methods.

![alt text](https://i.imgur.com/48ZKz1O.png "CIL result with alpha value of 10")

The <code>.xdsl</code> extension is one of the supported formats by the Bayesian network tool <a href="https://www.bayesfusion.com/genie/">Genie</a>, which is free for academic purposes. You can download the academia version from <a href="https://download.bayesfusion.com/files.html?category=Academia">here</a> (requires registration). With Genie, it is possible to analyze the model and run inference. As an example, here you can see the result from the CIL algorithm with an alpha value of 10:

![alt text](https://i.imgur.com/NMFkxL3.png "CIL result with alpha value of 10 in Genie")
