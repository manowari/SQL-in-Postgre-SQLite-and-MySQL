Getting current port `SHOW GLOBAL VARIABLES LIKE 'PORT'`


Adding a new field (column) to an existing table in MySQL can be done using the `ALTER TABLE` statement. This statement allows you to modify the structure of a table by adding, modifying, or dropping columns. When adding a new field, you might want to specify a default value for existing records to handle missing data gracefully.

Here's the basic syntax for adding a new field to an existing table:

```sql
ALTER TABLE table_name
ADD COLUMN new_column_name data_type [DEFAULT default_value];
```

Let's break down this syntax:

- `ALTER TABLE`: Keyword indicating that you want to modify the structure of a table.
- `table_name`: The name of the table you want to alter.
- `ADD COLUMN`: Specifies that you want to add a new column to the table.
- `new_column_name`: The name of the new column you want to add.
- `data_type`: The data type of the new column.
- `[DEFAULT default_value]`: Optional specification of a default value for the new column. This is useful for existing records that do not have a value for the new column.

Here's an example to illustrate how to add a new field to an existing table:

Let's say we want to add a new field named `email` of type `VARCHAR(255)` to the `students` table:

```sql
ALTER TABLE students
ADD COLUMN email VARCHAR(255);
```

This SQL statement will add a new column named `email` to the `students` table.

To handle missing data for existing records when adding a new column, you can specify a default value for the new column. For example, if you want to set the default email for existing records to an empty string (''):

```sql
ALTER TABLE students
ADD COLUMN email VARCHAR(255) DEFAULT '';
```

With this modification, any existing records in the `students` table will have an empty string as the default value for the `email` column.

Alternatively, if you want to allow NULL values for the new column and not specify a default value:

```sql
ALTER TABLE students
ADD COLUMN email VARCHAR(255) DEFAULT NULL;
```

In this case, existing records will have NULL values for the `email` column unless explicitly updated.
