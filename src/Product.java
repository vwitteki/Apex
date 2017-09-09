import java.text.*;
import java.util.Date;

public class Product {
	/* Date Format */
	public final static DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
	
	/* Private instance vars */
	private int productNumber;
	private String productName;
	private String productCategory;
	private Date expirationDate;

	/* Constructor */
	public Product(int number, String name, String category, String expDateString) {
		this.productNumber = number;
		this.productName = name;
		this.productCategory = category;
		
		/* 
		 * For the purposes of this exercise, I'm going to assume valid input data
		 * and not worry about what happens if expirationDate is null downstream
		 */
		try {
			this.expirationDate = dateFormatter.parse(expDateString);
		} catch (Exception e) {
			this.expirationDate = null;
		}
	}
	
	/* Instance var get methods */
	public int getNumber() {
		return this.productNumber;
	}
	
	public String getName() {
		return this.productName;
	}
	
	public String getCategory() {
		return this.productCategory;
	}
	
	public Date getExpDate() {
		return this.expirationDate;
	}
}
