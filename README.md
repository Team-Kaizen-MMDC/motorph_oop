# OOP MotorPH System

Team Kaizen New MotorPH Payroll Sytems Using OOP

## Table of Contents

- [About](#about)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
- Development Process
- Project and Task Tracking
- UI Designs/ Wire-framing
- ARCHITECTURE

## About

MotorPH project written in Java for our Terminal Assessment in OOP.

## Getting Started

### Prerequisites

- Java SDK17
- IDE preferably Netbeans
- PostgresSQL DB
- pgAdmin4

### Development Process

- Since we are coding as a group, we will continue our previous process of utilizing Github.com for our source code repository at the start of the project. I will create one for our group
- If you haven't signed up for an account yet, please do so, so I can add you to the repository. This will help us streamline our tasks and review our code as we progress. If you are not familiar yet with Git, you can view this 20-minute video for beginners: <https://youtu.be/IHaTbJPdB-s?si=AHDFHDtovD1wjAlS>
- Key concepts to understand:  Git pull, add, commit, push, branching, and merging tasks.
- We will use one codebase, meaning we need to decide how we structure our packages, naming conventions (variables, methods) and "style" of writing code.
- The `main` branch in Github repository will always be our latest running version of the application. You should create a feature branch for the new code you are working on (feature/addNewEmprecord )
  - Features are developed in a branch, following the format `feature/[task description]`
  - Defects are fixed in a branch following the following format `defect/[issue description]`
- Defensive Coding: Catch errors where they happen, instead of having the entire application break if an error happens.
- Save, Commit, Push as often as possible to avoid loss of work due to system failures.
- For now, I will be the reviewer and approver of all `Merge request` to `main` branch to avoid code breakage.

### Project and Task Tracking

- We used Asana  before to track our weekly tasks and milestone as well as assigning owners and due dates for each coding/admin tasks.
- We can revisit in using other tools such as Github Projects or Trello, if you have different preferences. Let's decide as a group when we meet.

### UI Designs/ Wire-framing

- We use Figma for these (UML, UX, etc)
- While we do designing at the onset, during coding we focus on making the logic and function working first. Making the UI pretty is the done at a much later part of the project cycle.

### ARCHITECTURE

- **IDE and DB versions**
  - We advise everyone to use NetBeans with JDK 17
  - PostgresDB v16 <https://www.postgresql.org/download/> and PgAdmin4 <https://www.postgresql.org/ftp/pgadmin/pgadmin4/> for DB management
- **DB Schema**
  - We will use **one** DB schema. We need to spend sometime architecting the DB at the very start so we will not have compatibility issues and differences when we run our code locally.
  - Write SQL scripts (.sql) in creating, updating the DB tables, and save them in the Github repo. This should help us drop , create tables, or make modifications to the schema programmatically versus doing it in PGAdmin4 console.
- **Code Libraries / Modularity**
  - Any code repeated twice or added to another java class file should be created as a Class Method or Object for reusability and cleaner code. e.g. Database Connection etc
  - Decide on group approved Maven packages. If you want to use a new Maven library for your code, please inform the group and check compatibility with other OS.
  - Please create UNIT Tests as much as possible on your code.

- **Communications**
  - We ideally meet once a week, more or less the same time as Async Class or if needed on a weekend.
  - If you can't make the meeting for that week, we need regular progress updates in ASANA, and/or in Group Chat  for your work . Your progress can be reviewed on the GitHub branch you are working on or the Asana Task ticket noted you entered.
  - Open communication in Group Chat is highly encourage. If you have a questions, pls do some research first, and if you still get stuck please ping the group for help.