
**Creating  a DB
**

`String query = "CREATE TABLE " + tableName + " (empID SERIAL, name VARCHAR(200), PRIMARY KEY(empID))";
`
The '()' groups columns together, SERIAL is in Postgre in MYSQL we have something like AUTOINCREMENT


VARCHAR - variable-length character string with a maximum length of 200 characters