# Job executor

Simple job executor using SpringBoot.

## Build

```console
$ mvn clean install
```

## Running

```console
$ mvn spring-boot:run -f dt-web-app
```

## Using

### Defining number of workers

```console
$ curl -X POST  http://localhost:8080/rest/jobs/workers/size/2
```

### Using jobs

```console
$ curl -X POST  http://localhost:8080/rest/jobs/WaitFor20Second
$ curl -X POST  http://localhost:8080/rest/jobs/CalculatePi
$ curl -X POST  http://localhost:8080/rest/jobs/FailImmediately
```
