# EquationSolver
![Gitlab pipeline status](https://img.shields.io/gitlab/pipeline/CubeArrow/EquationSolver?style=flat-square)

This is a project designed to compute and simplify simple arithmetic equations using a binary tree. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The applications you need to install the software and how to install them

1. Maven
2. Java 14

### Installing

Clone the repository to your local machine
```
git clone https://gitlab.com/CubeArrow/EquationSolver.git
```

Change directory to `EquationSolver`
```
cd EquationSolver 
```

Package the program in an executable JAR

```
mvn package
```

Run the program making sure you use `--enable-preview`
```
java -jar controller/target/controller-1.0-SNAPSHOT-jar-with-dependencies.jar --enable-preview
```


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JUnit](https://junit.org/junit5/) - Testing framework
* [Reflections](https://github.com/ronmamo/reflections) - Java runtime metadata analysis

## Authors

* **CubeArrow** - *Initial work* - [CubeArrow](https://gitlab.com/CubeArrow)

See also the list of [contributors](https://gitlab.com/CubeArrow/EquationSolver/-/graphs/master) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
