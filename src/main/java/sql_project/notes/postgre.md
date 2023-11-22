
USE TERMINAL

`psql -U postgres 
` top open as default superuser

`\q`-closeconnection 

``` SQL

--SQL Comment
/*
SQL
Multi
Line
 Comment
*/
```

`CREATE USER username WITH PASSWORD 'password';` - creates a user


`CREATE DATABASE your_database;` - creates a db

`GRANT ALL PRIVILEGES ON DATABASE your_database TO username;`
- When you grant privileges on a database, it means the user will have full access to all objects within that database, such as tables, views, functions, and schemas. However, it's important to note that this doesn't grant privileges on future databases created within the PostgreSQL instance.

or 

`GRANT ALL PRIVILEGES ON SCHEMA public TO username;
- This statement grants all privileges on a specific schema (public) within a database to a user (username). A schema is a way to organize database objects. In PostgreSQL, the public schema is the default schema where tables and other objects are created unless specified otherwise. Granting privileges on a schema allows the user to perform various actions on the objects within that schema, such as creating tables, executing functions, etc.
` 


**Grant Table permision to user **

```roomsql
 GRANT SELECT ON TABLE example_table TO username;
```

### JDBC

Statement is an interface that represents a SQL statement that can be executed against a database. It's a fundamental part of the JDBC API and is used for executing SQL queries and updates. The Statement interface provides three main types of statements:

- Statement: The basic SQL statement that doesn't take any input parameters. It's used for executing simple SQL queries.

- PreparedStatement: A precompiled SQL statement that may contain input parameters. It is more efficient when you need to execute the same SQL statement multiple times with different parameter values.

 - CallableStatement: Used to execute stored procedures in the database. It can also be used to execute parameterized SQL queries.

Some Code



``` JAVA
// Create a statement
Statement statement = connection.createStatement();
    // Execute a query

ResultSet resultSet = statement.executeQuery("SELECT * FROM yourtable");`

    // Process the result set
    while (resultSet.next()) {
        // Retrieve data by column name
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        // Do something with the data (print it in this example)
        System.out.println("ID: " + id + ", Name: " + name);
    }
```



### Miscellaneous

`psql` - starts terminal in postgre
or
`psql -h localhost -p 5432 -d postgres  `

or
`psql -h localhost -U username -p 5432

or
`psql -h localhost -U admin -d postgres
`

or 
    `psql -h localhost -U postgres
    `

`\c psql
` - navigate
\c your_database

USE ';' WHERE NECESSARY EG IN QUERRIES 


`

Check all users:
```roomsql
SELECT rolname FROM pg_roles 

postgres=> SELECT rolname FROM pg_roles


```

Check all DBs:


```roomsql

\l #- list all
OR
SELECT datname FROM pg_database

psql -h localhost -U your_username -d your_database -c "SELECT datname FROM pg_database;"

```


create DB  

```roomsql
psql -h localhost -U your_username;  # --connect to db 

CREATE DATABASE your_new_database;




```