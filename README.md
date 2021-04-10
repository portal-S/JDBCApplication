# JDBCApplication

Technologies:
Connection to DB: JDBC
Migration DB: Liquibase
Testing: Junit + Mockito
Build: Maven

For migration DB need import dependencies from pom.xml and execute maven command 'mvn liquibase:update', by entering the data from the database in file bd.properties

Form of the executed command:
<Data type> <command> <arg>;<arg>;<arg1>,<arg2>,<arg3>;
Data types: Skill, Developer, Team
Commands: readAll, read, create, update, delete

Example: 
developer readAll
developer read 1
developer create FirstName;LastName;1,2,3 (Skills id)
developer update 1 FirstName;LastName;1,2,3 (Skills id)
developer delete 1
