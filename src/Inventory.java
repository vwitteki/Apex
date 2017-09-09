import java.io.*;
import java.util.*;

public class Inventory {
	
	/* ArrayList for primary storage of products */ 
	private ArrayList<Product> inventoryList;
	/* Used to generate unique product numbers */ 
	private int nextProductNumber;
	 
	 /* Constructor */ 
	public Inventory() {
		inventoryList = new ArrayList<Product>();
		nextProductNumber = 1;
	}
	
	/* 
	 * Comparator enum,
	 * these are the comparators used for sorting and deleting
	 */
	public static enum ProductCompare implements Comparator<Product>{
		ByNumber{
			public int compare(Product o1, Product o2) {
				return ((Integer)(o1.getNumber())).compareTo((Integer)(o2.getNumber()));
			}
		},
		
		ByName{
			public int compare(Product o1, Product o2) {
				return (o1.getName()).compareTo(o2.getName());
			}
		},
		
		ByCategory{
			public int compare(Product o1, Product o2) {
				return (o1.getCategory()).compareTo(o2.getCategory());
			}
		},
		
		ByExpDate{
			public int compare(Product o1, Product o2) {
				return (o1.getExpDate()).compareTo(o2.getExpDate());
			}
		}
	}
	
	public void DeleteProducts(Comparator<Product> c, String value) {
		
		/* Create a base Product object to which to compare the rest for deletion */
		Product deletionProduct;
		try {
			int numberVal = Integer.parseInt(value);
			deletionProduct = new Product(numberVal,value,value,value);
		}catch(Exception e) {
			deletionProduct = new Product(0,value,value,value);
		}
		
		/*
		 * Iterate over the inventory, delete items that match
		 * the deletionProduct using comparator c
		 */
		Iterator<Product> iter = inventoryList.iterator();
		while (iter.hasNext()) {
			if ((c.compare(iter.next() , deletionProduct)) == 0) {
				iter.remove();
			}
		}
	}
	
	/*
	 * Returns an ArrayList copy of inventoryList,
	 * sorted using comparator c
	 */
	public ArrayList<Product> getSortedList(Comparator<Product> c){
		ArrayList<Product> sortedList = new ArrayList<Product>(this.inventoryList);
		if (c == null) {
			sortedList.sort(ProductCompare.ByNumber);
		}
		else {
			sortedList.sort(c);
		}
		return sortedList;
	}
	
	/*
	 * Wrapper for method getSortedList,
	 * prints the list in a readable format
	 */
	public void printSortedList(Comparator<Product> c){
		ArrayList<Product> sortedList = this.getSortedList(c);
		for (Product product : sortedList) {
			System.out.println("Number: " + product.getNumber());
			System.out.println("Name: " + product.getName());
			System.out.println("Category: " + product.getCategory());
			System.out.println("Expiration: " + Product.dateFormatter.format(product.getExpDate()));
			System.out.println();
		}
	}
	
	/*
	 * Adds Product data from an input file to the inventoryList
	 * Product data should be in the form Name^_Category^_MM/dd/yyyy
	 */
	public void AddDataFromSrc(String dataSrc) throws Exception {
		BufferedReader fileReader = new BufferedReader(new FileReader(new File(dataSrc)));
		String line;
		
		while ((line = fileReader.readLine()) != null) {
			/* 
			 * For the purposes of this exercise, I'm assuming
			 * that "^_" is going to be a suitable delimiter.
			 * 
			 * I'm also assuming error-free input data
			 */
			String [] productInfo = line.split("\\^_");
			AddProduct(new Product(
					this.nextProductNumber,
					productInfo[0],
					productInfo[1],
					productInfo[2]));
		}
		fileReader.close();
	}
	
	/*
	 * Adds a product to the inventoryList and increments the productNumber
	 */
	public void AddProduct(Product product) {
		this.inventoryList.add(product);
		this.nextProductNumber++;
	}
	
}
