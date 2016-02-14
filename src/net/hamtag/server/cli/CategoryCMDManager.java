package net.hamtag.server.cli;

import java.util.Scanner;

import org.hibernate.Transaction;

import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;

public class CategoryCMDManager {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		CategoryMgr.getCategoryByName("SPORTS");
		while (true) {
			System.out.println("\nChoice 1 to add or change Category, Choice 3 to remove the Fucked up Category"
					+ " and 4 to list all the Categories: ");
			int choice = Integer.parseInt(input.nextLine());
			if (choice == 1) {
				System.out.println("Name: ");
				String name=input.nextLine();
				Category category=CategoryMgr.getCategoryByName(name);
				if(category==null){
					category = new Category();
					category.setId(null);
				}
				category.setName(name);
				System.out.println("In persian: ");
				category.setTranslation(input.nextLine());
				CategoryMgr.add(category);
			}
			else if(choice==3){
				System.out.println("Category Name to DELETE: ");
				Category category = CategoryMgr.getCategoryByName(input.nextLine());
				Transaction tx = CategoryMgr.getInstance().beginTransaction();
				Object o=CategoryMgr.getInstance().load(Category.class, category.getId());
				CategoryMgr.getInstance().delete(o);
				tx.commit();
			}
			else if(choice==4){
				for(Category c:CategoryMgr.list()){
					System.out.println(c.getName()+"\n  "+c.getTranslation());
				}
			}
			System.out.println("Success");
		}
	}
}
