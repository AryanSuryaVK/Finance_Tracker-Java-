# 💰 Finance Tracker (Java Swing)

## 📌 About
This is a simple Finance Tracker desktop application built using Java Swing.  
I made this project to learn GUI programming and basic file handling in Java.

It helps to keep track of income and expenses in a simple way.

---

## ✨ Features
- Add income and expenses
- Select category (Food, Travel, Bills, etc.)
- View all transactions in a table
- Shows:
  - Total Income  
  - Total Expense  
  - Balance  
- Saves data to a file (`data.txt`)
- Loads data automatically on startup

---

## 🛠️ Technologies Used
- Java  
- Swing (JFrame, JTable, JPanel, etc.)  
- File Handling (BufferedReader, BufferedWriter)  

---

## ▶️ How to Run

### 1. Compile the files
javac Main.java Transaction.java

### 2. Run the program
java Main

---

## ⚙️ How It Works
- User enters amount, category, and type (Income/Expense)
- On clicking Add Transaction:
  - Data is stored in memory
  - Displayed in the table
  - Saved to a file
- App calculates totals automatically
- When app starts, it loads previous data from file

---

## 📚 What I Learned
- Basics of Java Swing GUI
- Working with JTable
- Event handling (buttons)
- File read/write in Java
- Simple project structure

---

## 🚀 Future Improvements
- Add delete/edit transaction
- Improve UI (dark mode, better design)
- Add charts (pie/bar)
- Use database (SQLite/MySQL)

---

## ⚠️ Note
This is a beginner-level project, so the UI and features are basic.
