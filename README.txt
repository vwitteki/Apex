Can be run from the src directory with:
>javac Product.java Inventory.java InventoryTest.java
>java InventoryTest

The mock data in ProductData1.txt, ProductData2.txt, ProductData3.txt was chosen to test the Comparators defined in src/Inventory.java
The Comparators are used for both the sorting and the deleting API.

The mock data covers the following edge cases:
- Products with the same name
- Products with silimar names
- A wide range of expiration dates
- Many products with the same expiration date
- A category with only a single product
- Data that starts in an un-sorted state for each data point (except product number since that is generated)
