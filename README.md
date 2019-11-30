# Leave Manager - Web Application

[![CircleCI](https://circleci.com/gh/jiben22/leave-manager/tree/master.svg?style=svg)](https://circleci.com/gh/jiben22/leave-manager/tree/master)

[![codecov](https://codecov.io/gh/jiben22/leave-manager/branch/master/graph/badge.svg)](https://codecov.io/gh/jiben22/leave-manager/branch/master)

## Get started

`git clone https://github.com/jiben22/leave-manager.git`

### Database

1. Install MySQL

2. Turn on the server with these values (or update `src/main/resources/application.yml` file)
> Host name: **127.0.0.1** \
> Port: **3306** \
> Username: **root** \
> Password: **password**

3. Create database
> Database: **LeaveManager**

4. Launch the application, it will do the rest

5. You can use `src/test/resources/data.sql` file to feed the database

### Create WAR

Run `mvn package`

WAR original: *src/target/leave_manager-1.0.0.war.original*

WAR with Spring Boot Loader: *src/target/leave_manager-1.0.0.war*

## Users

### HRD

> tony.stark@marvel.com | Ironman12* \
> thor@marvel.com | Thor56789$

## HR

> tony.stark@marvel.com | Ironman12* \
> thor@marvel.com | Thor56789$ \
> captain@marvel.com | Captain12* \
> hawkeye@marvel.com | Hawkeye89* \
> blackwidow@marvel.com | Blackwidow1*

## Team leader

> tony.stark@marvel.com | Ironman12* \
> thor@marvel.com | Thor56789$ \
> captain@marvel.com | Captain12* \
> blackpanther@marvel.com | Blackpanther1*

## Employee

> antman@marvel.com | Antman123* \
> hulk@marvel.com | Hulk56789* \
> captainmarvel@marvel.com | Captainmarvel1* \
> spiderman@marvel.com | Spiderman1*