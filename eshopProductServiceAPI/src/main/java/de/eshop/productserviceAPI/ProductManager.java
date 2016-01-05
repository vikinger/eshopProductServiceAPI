package de.eshop.productserviceAPI;

import java.util.List;

import de.eshop.productserviceAPI.Product;

public interface ProductManager {

	public List<Product> getProducts();

	public Product getProductById(int id);

	public Product getProductByName(String name);

	public void addProduct(Product product);

	public List<Product> getProductsForSearchValues(String searchValue, Double searchMinPrice, Double searchMaxPrice);
	
	public boolean deleteProductsByCategoryId(int categoryId);
	
    public void deleteProductById(int id);

    public List<Category> getCategories();

    public Category getCategory(int id);

    public Category getCategoryByName(String name);

    public void delCategoryById(int id);

    public void addCategory(String name);
    
	
}
