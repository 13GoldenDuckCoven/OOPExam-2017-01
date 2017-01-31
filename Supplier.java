package warehouse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class Supplier {
	private String code;
	private String name;
	private Warehouse warehouse; 
	List<Product> listProductsOfSupplier = new ArrayList<>();

	public Supplier(String code, String name, Warehouse warehouse) {
		this.code = code;
		this.name = name;
		this.warehouse = warehouse; 
	}

	public String getCodice() {
		return code;
	}

	public String getNome() {
		return name;
	}

	public void newSupply(Product product) {
		listProductsOfSupplier.add(product);
		// add supplier to the list of suppliers in product class 
		Product p = warehouse.findProduct(product.getCode()); 
		p.addSuppliers(this);
	}

	public List<Product> supplies() {
		return listProductsOfSupplier.stream().sorted(comparing(p -> p.getDescription()))
				.collect(toList());
	}
}