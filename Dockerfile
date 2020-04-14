FROM openjdk:8
VOLUME /tmp

ADD target/spring-boot-demo-0.0.1-SNAPSHOT.jar /opt/app.jar

WORKDIR /opt/

ENV SPRING_PROFILES_ACTIVE staging

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 8080

ENTRYPOINT [ "/usr/bin/java", "-Djava.security.egd=file:/dev/urandom", "-jar", "/opt/app.jar" ]
## ["",""] 格式可以实现  docker 优雅关机

