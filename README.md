# ABM Simulation Hogwarts

## O projekcie
Zadaniem programu było
```java
getX();
```

## Oczekiwane wyniki
The algorithm 
<br/>Algorithm requirements:
- Divides the products into the minimum possible number of delivery groups.
- The largest group contains as many products as possible

`Example of output:`
```java
Delivery method: Pick-up point
  Product: Fond - Chocolate

Delivery method: Courier
  Product: Cocoa Butter
  Product: Tart - Raisin And Pecan
  Product: Flower - Daisies
```

## Struktura projektu
<br/>`The structure of my project looks like:`


## Funkcjonalność programu
Główna klasa programu to jest `HogwartsSimulation`.


## Testy
Jedna z głównych rzeczy w projekcie to testy i naszym zdaniem było ich utworzenie dla najważniejszych metod.
<br/>Ztestowaliśmy każdą klasę i główne ich metody
<br/>Po to zostały utworzonę klasy w 'src/test/hogwarts'.
```java
EnvironmentTest.java
HogwartsGUITest.java
HogwartsSimulationTest.java
SliderDemoTest.java
WizardTest.java
SimulationDataTest.java
```
Żeby zacząć testy, wciskamy start lub wpisujemy './gradlew test' w terminal

## Zrobiono za pomocą
- Gradle 8.5
- Java 21
- JetBrains IntellijIDEA

## Start symulacji
Najpierw musimy otwórzyć terminal.
<br/>My budujemy nasz projekt wpisująć `./gradlew build`.
<br/>Żeby zacząć działanie symulacji, mamy runSim task w `build.gradle`. Więc, po prostu wpisujemy `./gradlew runSim` w terminalu.
<br/>Możemy przetestować program za pomocą `./gradlew test`.

### Made by Oleksandr Radionenko & Anna Osokina
