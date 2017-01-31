package warehouse;

public class Order {
	// Attributes 
	private int quantity; 
	private Product product; 
	private Supplier supplier; 
	private String code; 
	private boolean deliveryFlag = false; 
	private Warehouse warehouse; 
	
	// Constructor 
	public Order(Product prod, int quantity, Supplier supp, String code, Warehouse warehouse) {
		product = prod; 
		this.quantity = quantity; 
		supplier = supp; 
		this.code = code; 
		this.warehouse = warehouse; 
		// add this order to product 
		Product p = warehouse.findProduct(product.getCode()); 
		p.addOrders(this);
	}

	public String getCode(){
		return code;
	}
	
	public boolean delivered(){
		return deliveryFlag;
	}

	public void setDelivered() throws MultipleDelivery {
		if (deliveryFlag == true) {
			throw new MultipleDelivery(); 
		}
		Product p = warehouse.findProduct(product.getCode()); 
		p.increaseQuantity(quantity);
		deliveryFlag = true; 
	}
	
	public String toString(){
		return "Order" + code + "for" + 
				quantity + "of" + product.getCode() + " :" +
				product.getDescription() + "from" + supplier.getNome(); 
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public Supplier getSupplier() {
		return supplier;
	}
}