package de.eshop.productserviceAPI;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import de.eshop.productserviceAPI.Category;
import de.eshop.productserviceAPI.Product;
import de.eshop.productserviceAPI.ProductManager;

@Repository
public class ProductManagerImpl implements ProductManager {

	private static final String BASE_URI = "http://localhost:8030";
	private static final String PRODUCT_URI = BASE_URI + "/product";
	private static final String CATEGORY_URI = BASE_URI + "/category";
    private static RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<Product> getProducts() {
		Product[] products = restTemplate.getForObject(PRODUCT_URI + "s", Product[].class);
	    return Arrays.asList(products);
	}
	
	@Override
	public List<Product> getProductsForSearchValues(String searchDescription,
			Double searchMinPrice, Double searchMaxPrice) {	
		if (searchDescription == null || searchDescription.equals("")) {
			return null;
		}
		
		Product[] products = restTemplate.getForObject(PRODUCT_URI + "s/search?value=" + searchDescription + "&min=" + searchMinPrice + "&max=" + searchMaxPrice, Product[].class);
		return Arrays.asList(products);
	}

	@Override
	public Product getProductById(int id) {
		return restTemplate.getForObject(PRODUCT_URI + "/" + id, Product.class);
	}

	@Override
	public Product getProductByName(String name) {
		if (name == null || name.equals("")) {
			return null;
		}
		return restTemplate.getForObject(PRODUCT_URI + "?name=" + name, Product.class);
	}
	
	@Override
	public void addProduct(Product product) {
		
		Category category = this.getCategory(product.getCategory());
		
		if(category != null){
			Product newProduct;
			if(product.getDetails() == null){
				newProduct = new Product(product.getName(), product.getPrice(), product.getCategory());	
			} else{
				newProduct = new Product(product.getName(), product.getPrice(), product.getCategory(), product.getDetails());
			}
			
			restTemplate.postForLocation(PRODUCT_URI, newProduct);
		}
	}
	

	@Override
	public void deleteProductById(int id) {
		restTemplate.delete(PRODUCT_URI + "/" + id);
	}

	@Override
	public boolean deleteProductsByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Category> getCategories() {
		Category[] categories = restTemplate.getForObject(BASE_URI + "/categories", Category[].class);
	    return Arrays.asList(categories);
	}

	@Override
	public Category getCategory(int id) {
		return restTemplate.getForObject(CATEGORY_URI + "/" + id, Category.class);
	}

	@Override
	public Category getCategoryByName(String name) {
		if (name == null || name.equals("")) {
			return null;
		}
		return restTemplate.getForObject(CATEGORY_URI + "?name=" + name, Category.class);
	}

	@Override
	public void addCategory(String name) {
		Category newCategory = new Category(name);
		restTemplate.postForLocation(CATEGORY_URI, newCategory);
	}

	@Override
	public void delCategoryById(int id) {
	
		restTemplate.delete(CATEGORY_URI + "/" + id);
	}

}
