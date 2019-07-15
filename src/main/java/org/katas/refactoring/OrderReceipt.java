package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private static final double sales_tax = 0.1;
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }


    void printNameAndAddress(StringBuilder output, Order order) {
        output.append(order.getCustomerName())
                .append(order.getCustomerAddress());
    }

    void printLineItems(StringBuilder output, Order order){
        // prints lineItems
        double totalSalesTax = 0d;
        double totalAmount = 0d;

        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription()).append('\t').append(lineItem.getPrice()).append('\t')
                    .append(lineItem.getQuantity()).append('\t').append(lineItem.totalAmount()).append('\n');

            // calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * sales_tax;
            totalSalesTax += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalAmount += lineItem.totalAmount() + salesTax;
        }

        // prints the state tax
        output.append("Sales Tax").append('\t').append(totalSalesTax);

        // print total amount
        output.append("Total Amount").append('\t').append(totalAmount);
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder("======Printing Orders======\n");
        printNameAndAddress(output, order);
        printLineItems(output,order);
        return output.toString();
    }
}