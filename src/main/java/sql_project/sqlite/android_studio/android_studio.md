# SQLite in Android

## Overview
- **SQLite Version:** The same SQLite database engine as the standalone version.
- **Purpose:** Default relational database management system (RDBMS) for Android applications.

## Integration with Android SDK
- **Android SDK:** Provides a set of APIs and libraries for seamless integration with SQLite databases.
- **Storage:** Databases are stored in the internal storage of the application by default.

## Database Management
- **SQLiteOpenHelper:**
  - API for managing database creation and version management.
  - Helps with opening, creating, and managing a database.

- **SQLiteDatabase:**
  - Represents the database and provides methods for performing various operations (e.g., insert, update, delete, query).
  - Obtained from SQLiteOpenHelper.

## Storage and Isolation
- **Private Databases:**
  - Each application has its own private SQLite database.
  - Isolated from other applications for security.

- **Internal Storage:**
  - Databases are stored internally within the application's storage space.

## ContentProvider
- **ContentProvider:**
  - Mechanism for sharing data between applications.
  - Allows exposing SQLite data to other applications with proper permissions.

## Android-specific APIs
- **SQLiteOpenHelper Class:**
  - Manages database creation and version management.
  - Provides methods to get a readable or writable database.

- **SQLiteDatabase Class:**
  - Represents the database and provides methods for executing SQL statements.

- **Cursor Class:**
  - Represents the result set of a query.
  - Used to iterate over the rows of the result set.

## Example Code
```java
// Creating a SQLiteOpenHelper
public class MyDBHelper extends SQLiteOpenHelper {
    // ... constructor and other methods ...

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables and initial schema
        db.execSQL("CREATE TABLE employees (id INTEGER PRIMARY KEY, name TEXT, salary REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades
    }
}

// Using SQLiteDatabase
public class MyDatabaseHandler {
    private SQLiteDatabase database;

    // ... constructor and other methods ...

    public void open() {
        // Open the database for writing
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        // Close the database
        database.close();
    }

    public long insertEmployee(String name, double salary) {
        // Insert a new employee record
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("salary", salary);
        return database.insert("employees", null, values);
    }

    // ... other database operations ...
}
