# OOPExam-2017-01
Exam of Objected-Oriented Programming in Politecnico di Torino, Italy





Product Procurement

Develop an application to manage a warehouse.
All the classe must be in the package warehouse.
All JDK documentation is available on a local server.



R1 - Product Definition

The system main interface is represented by class Warehouse.
The method newProduct() that accepts as arguments code and description of products, creates and object of class Product and records it in the warehouse. Class Productprovides the method getter methods getCode() and getDescription().

To define the quantity of products in the warehouse the method setQuantity() can be used, it accepts as argument an integer; in addition the method decreaseQuantity() decrements the quantity of a product by one. The method getQuantity() allow reading the current quantity.

To retrieve the list of products we can use the method products() that returns a collection of Prodotto objects. A specific product can be retrieve by means of the method findProduct() that accepts as argument the code and returns the relative Product object.



R2 - Suppliers Definition

Products are provided by suppliers. To define a supplier we can use the method newSupplier() of class Warehouse that accepts as arguments the code and name of the supplier; such method returns a Supplier object. Class Supplier offers the getter methods getCode() e getName().

To retrieve a given supplier it is possible to use the the methods findSupplier() of class Warehouse that accepts the code of the supplier.

To define which products are provided by a given supplier we can use the method newSupply() of class Supplier that accepts as argument a Product object.

It is possible to know what products are provided by a given supplier through the method supplies() of the Supplier class tha returns a list of Product objects sorted alphabetically by description. Conversely, it is possible to know which suppliers provide a given product using the method suppliers() of class Product that returns a collection of Supplier objects sorted by description.



R3 - Placing orders

When required, an employee can place an order. Orders a places by means of the method issueOrder() of the class Warehouse, that accepts as arguments the product, the supplier, and the required quantity, it returns an object of class Order.

If the supplier is not among those providing that type of product an InvalidSupplier exception is thrown.

The warehouse generates an unique alphanumeric code with the format "ORDn" where n is a progressive number starting from 1. Class Order provides the getter method getCode().

To retrieve a specific order we can use the method findOrder() of class Warehouse that accepts the code and returns the relative Order object.

Every order can be converted into a string using the method toString() that returns a string with the following format:

Order <order code> for <quantity> of <product code> : <product description> from <supplier name>

For instance: "Order ORD2 for 100 of BNN : Banana from Del Monte"



R4 - Pending orders

When orders are first placed they are in the non-delivered state. To check the delivery state of an order we can use the method delivered() that returns a boolean value.

When products are received, we can invoke the method setDelivered() that sets the delivered status to true and increments the quantity of the products available in the warehouse by the order quantity. If the order was already delivered a MultipleDelivery exception is thrown.

Class Warehouse provides the method pendingOrders() that returns a list of orders not yet delivered, sorted by product code.

Also class Product provides a method pendingOrders() that returns the list of yet to be delivered orders for that product, sorted by decreasing quantity.



R5 - Statistics

A few statistics are required concernign the working of the warehouse, in particular:

the method ordersByProduct() returns a map grouping all the orders (both pending and delivered) by product code.
the method orderNBySupplier() returns the count of the delivered orders for each supplier (sorted in alphabetic order)
the method countDeliveredByProduct() returns a list of string each containing the product and the number of delivered orders separated by " - " (es. "BNN - 10") sorted by decreasing number of orders.
