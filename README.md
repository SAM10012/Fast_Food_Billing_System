# 🍔 Mood Food – Fast Food Billing System (Java + Collections)

**Mood Food** is a console-based Java application that simulates a fast food restaurant's order and billing process. This project emphasizes real-world application of **Java Collections** (`List`, `Map`, `HashMap`, `LinkedHashMap`) to dynamically manage menus, orders, and receipts.

---

## 🚀 Features

- ✅ Customer input validation (name & phone number)
- 📋 Menu display with dynamic pricing
- 🛒 Multi-item ordering system using `Map`
- 💰 Coupon discount system (`HashMap` with code-to-discount mapping)
- 🧾 Formatted receipt generation with totals and GST
- 🔢 Auto-incrementing Order and Token numbers
- ⏱️ Real-time timestamp on bills
- ❌ Custom exceptions for invalid input (phone, order number, quantity, coupon)

---

## 💡 Java Collections Used

| Collection             | Purpose                                      |
|------------------------|----------------------------------------------|
| `List<String>`         | Stores menu items                           |
| `Map<String, Integer>` | Holds item-price mapping                    |
| `Map<String, Integer>` | Tracks ordered items with their quantities  |
| `HashMap<String, Double>` | Stores available coupon codes and discounts |
| `LinkedHashMap`        | Preserves insertion order for receipts      |

---

## 🧠 Learning Objectives

This project is designed to help you:

- Practice **Java Collections API** in a real-world scenario
- Understand how to dynamically store and retrieve data
- Use **custom exception handling** to validate user input
- Structure clean and modular code with helper functions
- Format output using `System.out.printf()` for aligned console UI

---

Technologies Used

1. Java 8+
2. Java Collections Framework
3. Console-based UI
4. Regular Expressions (for phone/name validation)
5. Custom exception classes
6. LocalDateTime and DateTimeFormatter

---

📌 Future Improvements
Integrate with JDBC + MySQL to store customer and order data.

---

📄 License
This project is open-source and free to use for learning and portfolio building. 

---

🙋‍♂️ Author
Made with ❤️ by Samadrita Paul

---
