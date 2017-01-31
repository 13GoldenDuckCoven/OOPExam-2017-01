package warehouse;

import java.util.Collection;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Warehouse {
	private Map<String, Product> mapProducts;
	private Map<String, Supplier> mapSuppliers;
	int orderNumber = 0; 
	private Map<String, Order> mapOrders;

	public Warehouse() {
		mapProducts = new HashMap<>();
		mapSuppliers = new HashMap<>();
		mapOrders = new HashMap<>(); 
	}

	public Product newProduct(String code, String description) {
		Product p = new Product(code, description);
		mapProducts.put(code, p);
		return p;
	}

	public Collection<Product> products() {
		return mapProducts.values().stream().collect(toList());
	}

	public Product findProduct(String code) {
		Product p = mapProducts.get(code);
		return p;
	}

	public Supplier newSupplier(String code, String name) {
		Supplier s = new Supplier(code, name, this);
		mapSuppliers.put(code, s);
		return s;
	}

	public Supplier findSupplier(String code) {
		Supplier s = mapSuppliers.get(code);
		return s;
	}

	public Order issueOrder(Product prod, int quantity, Supplier supp)
			throws InvalidSupplier {
		orderNumber = orderNumber + 1; 
		String codeOfOrder = "ORD" + orderNumber; 
		Order o = new Order(prod, quantity, supp, codeOfOrder, this); 
		List<Supplier> validSuppliers = prod.suppliers();
		int flag = 0; 
		for (Supplier s : validSuppliers) {
			if (s.getCodice() == supp.getCodice()) {
				flag = 1; 
				break; 
			}
		}
		if (flag == 0){
			throw new InvalidSupplier(); 
		}
		mapOrders.put(codeOfOrder, o); 
		return o;
	}

	public Order findOrder(String code) {
		return mapOrders.get(code);
	}

	public List<Order> pendingOrders() {
		return mapOrders.values().stream().filter(e->e.delivered() == false)
			.sorted(comparing(p -> p.getProduct().getCode()))
			.collect(toList()); 
	}

	public Map<String, List<Order>> ordersByProduct() {
		return mapOrders.values().stream().collect(groupingBy(o->o.getProduct().getCode()));
	}

	public Map<String, Long> orderNBySupplier() {
		return (Map)mapOrders.values().stream()
				.collect(groupingBy(o->o.getSupplier().getNome(), TreeMap::new, counting()));
	}

	public List<String> countDeliveredByProduct() {
		return mapProducts.values().stream()
				.sorted(comparing(e -> e.getNumOfDeliveredOrders(), reverseOrder()))
				.map(Product::toString)
				.collect(toList());
	}
}