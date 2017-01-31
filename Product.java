package warehouse;

import java.util.ArrayList;
// import java.util.Collection;
import java.util.List;
// import java.util.Map;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class Product {
	private String code;
	private String description;
	private int quantity;
	List<Supplier> listSuppliersOfProduct = new ArrayList<>();
	// private Collection<Product> listProduct = new ArrayList<>();
	// private Collection<Integer> listQuantity;
	List<Order> listOrdersOfProduct = new ArrayList<>();

	public Product(String code, String description) {
		this.code = code;
		this.description = description;
		// listQuantity =new LinkedList<>();
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void decreaseQuantity() {
		this.quantity = (this.quantity -1); 
	}

	public void increaseQuantity(int value) {
		this.quantity = this.quantity + value; 
	}

	public int getQuantity() {
		return quantity;
	}

	public void addSuppliers(Supplier s) {
		listSuppliersOfProduct.add(s);
	}
	
	public List<Supplier> suppliers() {
		return listSuppliersOfProduct;
	}

	public void addOrders(Order o) {
		listOrdersOfProduct.add(o);
	}
	
	public List<Order> pendingOrders() {
		return listOrdersOfProduct.stream().filter(e->e.delivered() == false)
			.sorted(comparing(Order::getQuantity, reverseOrder()))
			.collect(toList());
	}
	
	public int getNumOfDeliveredOrders(){
		List<Order> l = listOrdersOfProduct.stream().filter(e->e.delivered() == true)
				.collect(toList());
		return l.size(); 
	}
	
	public String toString() {
		return this.getCode() + " - " + this.getNumOfDeliveredOrders(); 
	}
}