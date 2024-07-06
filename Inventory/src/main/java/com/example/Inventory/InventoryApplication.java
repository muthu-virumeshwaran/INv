package com.example.Inventory;

import com.example.Inventory.models.Product;
import com.example.Inventory.models.Users;
import com.example.Inventory.services.ProductService;
import com.example.Inventory.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@SpringBootApplication
@EntityScan("com.example.Inventory.models")
public class InventoryApplication implements CommandLineRunner {
	@Autowired
	private UsersService usersService;

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Add Product");
			System.out.println("4. View Products");
			System.out.println("5. Exit");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					register(scanner);
					break;
				case 2:
					login(scanner);
					break;
				case 3:
					addProduct(scanner);
					break;
				case 4:
					viewProducts();
					break;
				case 5:
					System.exit(0);
					break;
				default:
					System.out.println("Invalid choice. Try again.");
			}
		}
	}

	private void register(Scanner scanner) {
		System.out.println("Enter username:");
		String username = scanner.nextLine();

		System.out.println("Enter password:");
		String password = scanner.nextLine();

		System.out.println("Enter email:");
		String email = scanner.nextLine();

		Users user = new Users();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);

		usersService.register(user);
		System.out.println("User registered successfully.");
	}

	private void login(Scanner scanner) {
		System.out.println("Enter username:");
		String username = scanner.nextLine();

		System.out.println("Enter password:");
		String password = scanner.nextLine();

		Users user = new Users();
		user.setUsername(username);
		user.setPassword(password);

		System.out.println("User logged in successfully.");
	}

	private void addProduct(Scanner scanner) {
		System.out.println("Enter product name:");
		String name = scanner.nextLine();

		System.out.println("Enter product description:");
		String description = scanner.nextLine();

		System.out.println("Enter product price:");
		Double price = scanner.nextDouble();

		System.out.println("Enter product quantity:");
		int quantity = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter product category:");
		String category = scanner.nextLine();

		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setQuantity(quantity);
		product.setCategory(category);

		productService.addProduct(product);
		System.out.println("Product added successfully.");
	}

	private void viewProducts() {
		productService.getAllProducts().forEach(product -> {
			System.out.println(product.getId() + " " + product.getName() + " " + product.getDescription() + " " + product.getPrice() + " " + product.getQuantity() + " " + product.getCategory());
		});
	}
}
