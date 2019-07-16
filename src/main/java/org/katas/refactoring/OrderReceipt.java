package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private static final double SALES_TAX = .10;
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    private void printHeader(StringBuilder output) {
        output.append("======Printing Orders======\n");
    }

    private void printCustomerInfo(StringBuilder output) {
        output.append(order.getCustomerName()).append(order.getCustomerAddress());
    }

    private void printTax(StringBuilder output){
        output.append("Sales Tax").append('\t').append(calculateSalesTax());
    }

    private void printTotalAmount(StringBuilder output){
        output.append("Total Amount").append('\t').append(calculateTotalAmount());
    }

    private double calculateSalesTax() {
        return order.getLineItems().stream().map(LineItem::getTotalAmount).mapToDouble(item -> item * SALES_TAX).sum();
    }

    private double calculateTotalAmount() {
        return order.getLineItems().stream().mapToDouble(LineItem::getTotalAmount).sum() + calculateSalesTax();
    }

    private void printLineItems(StringBuilder output){
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription()).append('\t')
                    .append(lineItem.getPrice()).append('\t')
                    .append(lineItem.getQuantity()).append('\t')
                    .append(lineItem.getTotalAmount()).append('\n');
        }
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        printHeader(output);
        printCustomerInfo(output);
        printLineItems(output);
        printTax(output);
        printTotalAmount(output);
        return output.toString();
    }
}