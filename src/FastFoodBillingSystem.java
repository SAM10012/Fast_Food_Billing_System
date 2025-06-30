import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 1. Make proper menu (now only for demo)
// Understand constructor overloading for Exceptions and which message will be finally printed



public class FastFoodBillingSystem {

    static int OrderNum = 1000;
    static int TokenNum = 50;

    public static class InvalidPhoneNumberException extends Exception{

        public InvalidPhoneNumberException() {
            super("Invalid Phone Number");
        }
    }

    public static class CustDetails{

        String custName;
        String custPhone;

        public CustDetails(String custName, String custPhone)
        {
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

    public static class InvalidOrderNumberException extends Exception {

        public InvalidOrderNumberException() {
            super("Order Number out of given range. Please retry");
        }

    }

    public static class InvalidQtyNumberException extends Exception {

        public InvalidQtyNumberException() {
            super("Quantity of ordered item must be positive. Please retry.");
        }

    }

    public static void takeCustOrder() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n=============== WELCOME TO MOOD FOOD ====================\n");

        List<String> menuItem = new ArrayList<>();
        menuItem.add("Pizza");
        menuItem.add("Momo");
        menuItem.add("Burger");
        menuItem.add("Pasta");
        menuItem.add("Noodles");

        //System.out.println(menuItem);

        Map<String, Integer> itemPrice = new LinkedHashMap<>();

        itemPrice.put("Pizza", 200);
        itemPrice.put("Momo", 200);
        itemPrice.put("Burger", 300);
        itemPrice.put("Pasta", 250);
        itemPrice.put("Noodles", 250);

        /*for (Map.Entry<String, Integer> e : menuQty.entrySet()) {
            System.out.println(e.getKey() + " -----> " + e.getValue());
        }*/


        System.out.printf("%-10s %-20s %-10s \n", "SL NO.", "Items", "Price (in \u20B9)");
        for (int i = 0; i < menuItem.size(); i++) {
            System.out.printf("%-10d %-20s %-10d \n", (i + 1), menuItem.get(i), itemPrice.get(menuItem.get(i)));
            //System.out.println((i + 1) + "        " + menuItem.get(i) + "       " + itemPrice.get(menuItem.get(i)));
        }

        System.out.println("\n=========================================================");


        Map<String, Integer> orderDetails = new LinkedHashMap<>();
        int order = 0;
        int qty = 0;
        double total = 0;
        String addItem = "";

        String phone = "";
        String custName = "";
        while(true)
        {
            try {
                System.out.print("\nCustomer Phone Number: ");
                phone = sc.nextLine();

                if(phone.matches("^[6-9][0-9]{9}$"))
                break;
                else {
                    throw new InvalidPhoneNumberException();
                }
            }catch(InvalidPhoneNumberException e) {
                System.out.println("Phone number must contain exactly 10 digits and no letters. Please retry.");

        }
        }

        while(true)
        {
            try{
                System.out.print("\nCustomer Name: ");
                custName = sc.nextLine();

                if(custName.matches("^[a-zA-Z ]+$"))
                break;
                else {
                    throw new InputMismatchException("Name must not contain any digits. Please retry.");
                }
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());

            }
        }

        CustDetails customer = new CustDetails(custName,phone);
        OrderNum = OrderNum + 1;
        TokenNum = TokenNum + 1;

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


            orderDetails.put(menuItem.get((order - 1)), orderDetails.getOrDefault(menuItem.get((order - 1)), 0) + qty);

            total = total + qty * itemPrice.get(menuItem.get((order - 1)));


            System.out.print("\nAdd another item (Y/N): ");
            addItem = sc.nextLine();

            if (addItem.equalsIgnoreCase("Y")) {
                continue;
            } else {
                break;
            }

        }

        LocalDateTime current_time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted_current_time = current_time.format(formatter);

        System.out.println("\n============== MOOD FOOD RECEIPT =================\n");

        System.out.println("Order Date and Time: " + formatted_current_time + "\n");
        System.out.println("Your Order No.: " + OrderNum);
        System.out.println("Your Token No.: " + TokenNum + "\n");

        System.out.print("Customer Name: " + customer.getCustName() + "\n");
        System.out.println("Customer Phone Number: " + customer.getCustPhone() + "\n");

        System.out.printf("%-20s %-10s %-26s\n", "Items Ordered", "Qty", "Price");
        for (Map.Entry<String, Integer> e : orderDetails.entrySet()) {
            //System.out.println(e.getKey() + "              X" + e.getValue() + "     " + e.getValue() * itemPrice.get(e.getKey()));
            System.out.printf("%-20s %-10d %-26d\n", e.getKey(), e.getValue(), e.getValue() * itemPrice.get(e.getKey()));
        }

        System.out.println("--------------------------------------------------");
        System.out.printf("\nTOTAL AMOUNT TO BE PAID: \u20B9%.2f.", total);

        System.out.println("\n\n================= THANK YOU =====================");
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String next_cust = "";
        while(true)
        {
            takeCustOrder();
            System.out.print("\nDo you want to take order for another customer: ");
            next_cust = sc.nextLine();

            if(next_cust.equalsIgnoreCase("Y"))
            {
                continue;
            }
            else {
                System.out.println("\nHave a good day!\uD83D\uDE42");
                break;
            }

        }

    }


}
