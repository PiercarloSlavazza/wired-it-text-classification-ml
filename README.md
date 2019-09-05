# Overview

This project contains a Keras/TensorFlow Jupyter notebook which implements a DNN aimed at classifying the [Wired.it Italian corpus](https://github.com/PiercarloSlavazza/wired-it-scraper).

The project also contains a Java tool which process the original corpus so that it is better suited to be read by the Python code in the notebook.

Please note that some branches are also available which contains alternative implementations of the notebook.

The notebook has been featured in the essay ["What is the best method for Automatic Text Classification?"](hhttps://medium.com/@piercarlo_slavazza/https-medium-com-piercarlo-slavazza-what-is-the-best-method-for-automatic-text-classification-a01d4dfadd): you should read the eassy in order to better understand the goals, the algorithms and the rationales.

## Credits

Basic template for the Keras code has been borrowed from the Coursera course ["Natural Language Processing in TensorFlow"](https://www.coursera.org/learn/natural-language-processing-tensorflow) held by [Laurence Moroney](https://twitter.com/lmoroney).

# Usage

## Python notebook

My advice is to install Tensorflow (possibly with GPU support) and Jupyter via Anaconda:

* `conda create -n tensorflow_gpuenv tensorflow-gpu jupyterlab matplotlib scikit-learn`
* `conda activate tensorflow_gpuenv`
* `jupyter notebook`, and then open the notebook from the browser web app

In case you want to try the `talos` branch, you need a different environment:

* `conda create --name talos_gpu scipy=1.2.0 matplotlib=2.2.3  h5py=2.8.0 tensorflow-gpu jupyter`
    * this holds at the time of writing, due to some issues with dependencies
* `conda activate talos_gpu`
* `pip install talos`
* `jupyter notebook`, and then open the notebook from the browser web app

## Java tool

* clone on your system the project [java-ml-text-utils](https://github.com/PiercarloSlavazza/java-ml-text-utils), and execute
    * `git checkout <version needed by this project pom.xml, e.g. v200>`
    * `mvn clean install`
* then, execute the class `com.ml_text_utils.shell.BuildWiredItFileSystemCorpusShell`

WARNING: before creating a new corpus split, consider using the one located in `corpora` folder.
