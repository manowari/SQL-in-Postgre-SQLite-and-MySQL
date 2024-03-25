To add tables and fields in MySQL Workbench using SQL code, you'll typically use the `CREATE TABLE` statement to create tables and the `ALTER TABLE` statement to add fields to existing tables. Below are examples demonstrating how to do this:

1. **Creating a Table:**
   
   To create a new table, you'll use the `CREATE TABLE` statement followed by the table name and its fields along with their data types.

   ```sql
   CREATE TABLE employees (
       id INT AUTO_INCREMENT PRIMARY KEY,
       first_name VARCHAR(50),
       last_name VARCHAR(50),
       email VARCHAR(100),
       hire_date DATE
   );
   ```

   In this example, we're creating a table named `employees` with fields `id`, `first_name`, `last_name`, `email`, and `hire_date`.

2. **Adding Fields to an Existing Table:**
   
   To add a new field to an existing table, you'll use the `ALTER TABLE` statement followed by the table name and the `ADD COLUMN` clause along with the new field definition.

   ```sql
   ALTER TABLE employees
   ADD COLUMN phone_number VARCHAR(20);
   ```

   This will add a new field named `phone_number` to the `employees` table.

3. **Adding a Primary Key Constraint:**

   If you forgot to add a primary key during table creation, you can add it later using the `ALTER TABLE` statement with the `ADD CONSTRAINT` clause.

   ```sql
   ALTER TABLE employees
   ADD CONSTRAINT pk_employees PRIMARY KEY (id);
   ```

   This adds a primary key constraint named `pk_employees` on the `id` field of the `employees` table.

4. **Adding Indexes:**

   If you need to add an index to a field for faster querying, you can use the `CREATE INDEX` statement.

   ```sql
   CREATE INDEX idx_email ON employees (email);
   ```

   This creates an index named `idx_email` on the `email` field of the `employees` table.

After executing these SQL statements in MySQL Workbench's SQL Editor, you should see the changes reflected in your database schema. Remember to adapt the table and field names, data types, and constraints according to your specific requirements.
