# Bill Divider App

A desktop Java + Swing application that automates group expense splitting with customizable tips, per-person management, and clear settlement summaries. Built with an emphasis on modular OOP design, input validation, and usability.

## Features

- 🧮 **Fast, accurate splits** — Enter items and amounts, and the app automatically divides costs among selected people.
- 👥 **Per-person tracking** — Add, edit, and manage participants easily.
- 💰 **Customizable tipping** — Add a tip by percentage or dollar amount; apply it per bill or per person.
- ⚙️ **Configurable options** — Choose whether to round totals, apply shared costs, or calculate evenly.
- ✅ **Input safety** — Built-in validation prevents invalid data (e.g., negative or blank amounts).
- 📊 **Settlement summary** — Get a clean breakdown showing who owes what and how much each person spent.
- 📤 **Export results** — Save or share the final summary as a CSV or text file (optional enhancement).

## 🚀 Getting Started

### 1) Clone the repository
```bash
git clone https://github.com/Alton8/Bill-Divider-App.git
cd Bill-Divider-App
```

### 2) Compile and run
```bash
javac -d bin src/**/*.java
java -cp bin GUI
# Or open the project in IntelliJ/Eclipse and run the class with `public static void main(...)`.
```

## 🧩 Usage Guide

1. **Add participants** (e.g., Alice, Bob, Charlie) using the “Add Person” button.
2. **Add expenses** with amount, payer, and recipients.

## 💫 Set tip

Choose a tip:
- By **percentage** (e.g., 15%)
- By **fixed amount** (e.g., $10)

## 📋 Review summary

See each person’s:
- **Total spent**
- **Amount owed**

## 📤 Export results

On the final summary screen, click **Export** to save a `.csv` or `.txt` summary of everyone’s totals (optional enhancement).
