# messenger
Spring Framework + Spring Security + Postgres project


It's a simple web application. I did it as a test for one job interview a long time ago.
I'm sure, it has some bugs, as it wasn't properly tested, although, there was no need for it.

It uses Spring Framework, Spring Security, Liquibase, Hibernate, JPA, Jackson, slf4j, JSP. The database is Postgres.

The front-end is written using JSP and native JS (it was one of the conditions of the task). It's kind of a mess, so maybe later I'll create some proper front-end with Angular or Backbone, or maybe smth else.
Also, it doesn't have unit tests (it wasn't required).

I ran it on GlassFish4 server.

Use 'mvn clean install' to build the application.
Database name is 'zagnitko_messenger'.
