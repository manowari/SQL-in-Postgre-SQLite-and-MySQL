To create a MySQL HR database with data on salaries, investment, and pension funds, you can follow these steps:

1. **Create the Database and Tables**:
   First, you need to create the MySQL database and the necessary tables. Here's an example of SQL code to create a simple schema:

```sql
CREATE DATABASE hr_database;

USE hr_database;

CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    department VARCHAR(50),
    salary DECIMAL(10, 2)
);

CREATE TABLE investments (
    investment_id INT PRIMARY KEY,
    employee_id INT,
    investment_amount DECIMAL(10, 2),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE pension_funds (
    pension_id INT PRIMARY KEY,
    employee_id INT,
    pension_amount DECIMAL(10, 2),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);
```

2. **Insert Data into the Tables**:
   You can insert sample data into the tables you created. Here's an example:

```sql
INSERT INTO employees (employee_id, first_name, last_name, department, salary)
VALUES (1, 'John', 'Doe', 'Finance', 60000.00),
       (2, 'Jane', 'Smith', 'Marketing', 55000.00),
       (3, 'Alice', 'Johnson', 'Human Resources', 50000.00);

INSERT INTO investments (investment_id, employee_id, investment_amount)
VALUES (1, 1, 10000.00),
       (2, 2, 8000.00),
       (3, 3, 12000.00);

INSERT INTO pension_funds (pension_id, employee_id, pension_amount)
VALUES (1, 1, 5000.00),
       (2, 2, 4000.00),
       (3, 3, 6000.00);
```

3. **Export Data to CSV**:
   You can export the data from your MySQL database into CSV files using the `SELECT ... INTO OUTFILE` syntax:

```sql
SELECT * FROM employees INTO OUTFILE '/path/to/employees.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';

SELECT * FROM investments INTO OUTFILE '/path/to/investments.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';

SELECT * FROM pension_funds INTO OUTFILE '/path/to/pension_funds.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';
```

Replace `/path/to/` with the desired location where you want to save the CSV files.

These steps should help you create a MySQL HR database with data on salaries, investments, and pension funds, and then export that data to CSV files for further analysis.
