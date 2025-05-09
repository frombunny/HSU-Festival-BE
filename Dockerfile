# 1단계: Build Stage
FROM gradle:7.6-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle build -x test

# 2단계: Run Stage
FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar hsu-festival.jar
ENTRYPOINT ["java", "-jar", "hsu-festival.jar"]
