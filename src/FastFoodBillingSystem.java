import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 1. Make proper menu (now only for demo)
// Understand constructor overloading for Exceptions and which message will be finally printed

// Main class
public class FastFoodBillingSystem {

    static int OrderNum = 1000;
    static int TokenNum = 50;
    static double gst = .05;// GST value usually won't change so static

    // Invalid Order Number Exception
    public static class InvalidOrderNumberException extends Exception {

        public InvalidOrderNumberException() {
            super("Order Number out of given range. Please retry");
        }

    }

    // Invalid Quantity Number Exception
    public static class InvalidQtyNumberException extends Exception {

        public InvalidQtyNumberException() {
            super("Quantity of ordered item must be positive. Please retry.");
        }

    }

    // Invalid Phone Number Exception
    public static class InvalidPhoneNumberException extends Exception {

        public InvalidPhoneNumberException() {
            super("Invalid Phone Number");
        }
    }

    // Invalid Coupon Code Exception
    public static class InvalidCouponException extends Exception {

        public InvalidCouponException() {
            super("Invalid Coupon Code");
        }
    }

    // Getting Customer's Phone Number
    public static String getCustPhoneNo() {

        Scanner sc = new Scanner(System.in);
        String phone = "";

        while (true) {
            try {
                System.out.print("\nCustomer Phone Number: ");
                phone = sc.nextLine();

                if (phone.matches("^[6-9][0-9]{9}$"))
                    break;
                else {
                    throw new InvalidPhoneNumberException();
                }
            } catch (InvalidPhoneNumberException e) {
                System.out.println("Phone number must contain exactly 10 digits (starting with 6-9) and no letters. Please retry.");

            }
        }
        return phone;
    }

    //Getting Customer's Name
    public static String getCustName() {

        Scanner sc = new Scanner(System.in);
        String custName = "";
        while (true) {
            try {
                System.out.print("\nCustomer Name: ");
                custName = sc.nextLine();

                if (custName.matches("^[a-zA-Z ]+$"))
                    break;
                else {
                    throw new InputMismatchException("Name must not contain any digits. Please retry.");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());

            }
        }

        return custName;
    }

    // Shows the Menu Card. (Add/Put method lines are not added here as when this function will get called, those add/put methods will get executed every time, increasing the size of the menu and causing errors.)
    public static void showMenuCard(List<String> menuItem, Map<String, Integer> itemPrice) {
        System.out.println("\n=============== WELCOME TO MOOD FOOD ====================\n");

        System.out.printf("%-10s %-20s %-10s \n", "SL NO.", "Items", "Price (in \u20B9)");
        for (int i = 0; i < menuItem.size(); i++) {
            System.out.printf("%-10d %-20s %-10d \n", (i + 1), menuItem.get(i), itemPrice.get(menuItem.get(i)));
            //System.out.println((i + 1) + "        " + menuItem.get(i) + "       " + itemPrice.get(menuItem.get(i)));
        }

        System.out.println("\n=========================================================");
    }

    // Calculates total after applying Coupon Code Discount
    public static double CouponCodeDiscount(String coupon_code, double total) throws InvalidCouponException {
        HashMap<String, Double> discounts = new HashMap<>();
        discounts.put("MOODFOOD5", .05);
        discounts.put("MOODFOOD10", .1);
        discounts.put("MOODFOOD15", .15);

        Double cc_discount = 0.0;

        if (discounts.containsKey(coupon_code)) {
            cc_discount = discounts.get(coupon_code);
            total = total - (cc_discount * total);
        } else {
            throw new InvalidCouponException();
        }
        return total;
    }

    // Class Customer
    public static class CustDetails {

        String custName;
        String custPhone;

        public CustDetails(String custName, String custPhone) {
            this.custName = custName;
            this.custPhone = custPhone;
        }

        public String getCustName() {
            return custName;
        }

        public void setCustName(String custName) {
            this.custName = custName;
        }

        public String getCustPhone() {
            return custPhone;
        }

        public void setCustPhone(String custPhone) {
            this.custPhone = custPhone;
        }
    }



    // Take Customer's order and print Receipt
    public static void takeCustOrder() {

        Scanner sc = new Scanner(System.in);

        // List of items served by the Food Outlet
        List<String> menuItem = new ArrayList<>();
        // HashMap of Items and their respective Prices
        Map<String, Integer> itemPrice = new LinkedHashMap<>();

        menuItem.add("Pizza");
        menuItem.add("Momo");
        menuItem.add("Burger");
        menuItem.add("Pasta");
        menuItem.add("Noodles");

        itemPrice.put("Pizza", 200);
        itemPrice.put("Momo", 200);
        itemPrice.put("Burger", 300);
        itemPrice.put("Pasta", 250);
        itemPrice.put("Noodles", 250);

        // Show Menu Card
        showMenuCard(menuItem, itemPrice);

        Map<String, Integer> orderDetails = new LinkedHashMap<>();
        int order = 0;
        int qty = 0;
        double total = 0;
        String addItem = "";

        // Get Customer Phone No. and Name for printing in Receipt
        String phone = getCustPhoneNo();
        String custName = getCustName();

        // Creating an Customer Object
        CustDetails customer = new CustDetails(custName, phone);

        // Real life Scenario Replication
        OrderNum = OrderNum + 1;
        TokenNum = TokenNum + 1;

        // Taking Order Details from Customer

        // Which Food Item
        while (true) {
            while (true) {
                try {
                    System.out.print("\nPlease enter your Order (based on SL. NO.): ");
                    order = sc.nextInt();
                    sc.nextLine();

                    if (order < 1 || order > menuItem.size()) {
                        throw new InvalidOrderNumberException();
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter integer as SL. NO.");
                    sc.nextLine();
                } catch (InvalidOrderNumberException e) {
                    System.out.println("Please select from the given range of options. ");
                }
            }

            // Quantity of selected Food Item
            while (true) {
                try {
                    System.out.print("\nEnter Qty for " + menuItem.get((order - 1)) + ": ");
                    qty = sc.nextInt();
                    sc.nextLine();

                    if (qty < 1) {
                        throw new InvalidQtyNumberException();
                    }
                    break;

                } catch (InputMismatchException e) {
                    System.out.println("Please enter integer as SL. NO.");
                    sc.nextLine();
                } catch (InvalidQtyNumberException e) {
                    System.out.println("Quantity of ordered item must be positive. Please retry.");
                }

            }

            // Saving Order Details in HashMap (Items ordered and their total Price)
            orderDetails.put(menuItem.get((order - 1)), orderDetails.getOrDefault(menuItem.get((order - 1)), 0) + qty);

            // Calculating Total Bill
            total = total + qty * itemPrice.get(menuItem.get((order - 1)));

            // Adding another Item to Order
            System.out.print("\nAdd another item (Y/N): ");
            addItem = sc.nextLine();

            if (addItem.equalsIgnoreCase("Y")) {
                continue;
            } else {
                break;
            }

        }

        // Calculate Coupon Code Discount

        String coupon_code = "";
        double total_before_discount = total; // Saving total bill before applying discount
        while (true) {
            try {
                System.out.print("\nDo you have any Coupon Code? (Press N if no): ");
                coupon_code = sc.nextLine().trim().toUpperCase();
                if (coupon_code.equals("N")) {
                    coupon_code = "N/A";
                } else {
                    total = CouponCodeDiscount(coupon_code, total);
                }
                break;
            } catch (InvalidCouponException e) {
                System.out.println("Invalid coupon code. Please retry.");
            }
        }

        // Show Order Date and Time in Receipt
        LocalDateTime current_time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted_current_time = current_time.format(formatter);

        // Print the Receipt
        System.out.println("\n============== MOOD FOOD RECEIPT =================\n");

        System.out.println("Order Date and Time: " + formatted_current_time + "\n");
        System.out.println("Your Order No.: " + OrderNum);
        System.out.println("Your Token No.: " + TokenNum + "\n");

        System.out.print("Customer Name: " + customer.getCustName() + "\n");
        System.out.println("Customer Phone Number: " + customer.getCustPhone() + "\n");

        System.out.printf("%-20s %-10s %-26s\n", "Items Ordered", "Qty", "Price");
        for (
                Map.Entry<String, Integer> e : orderDetails.entrySet()) {
            System.out.printf("%-20s %-10d %-26d\n", e.getKey(), e.getValue(), e.getValue() * itemPrice.get(e.getKey()));
        }

        System.out.println("--------------------------------------------------");

        System.out.print("\nTOTAL BILL: " + total_before_discount);
        System.out.print("\nAFTER " + coupon_code + " DISCOUNT: \u20B9" + total);
        System.out.printf("\nGST (5%%): \u20B9%.2f.", gst * total);
        System.out.printf("\nTOTAL AMOUNT TO BE PAID: \u20B9%.2f.", total + (gst * total));

        System.out.println("\n\n================= THANK YOU =====================");
    }

    // Main Function
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String next_cust = "";

        // We can take order and print receipt of multiple Customers
        while (true) {
            takeCustOrder(); // Take order of one Customer
            System.out.print("\nDo you want to take order for another customer: ");
            next_cust = sc.nextLine();
            if (next_cust.equalsIgnoreCase("Y")) {
                continue;
            } else {
                System.out.println("\nHave a good day!\uD83D\uDE42");
                break;
            }

        }

    }


}
