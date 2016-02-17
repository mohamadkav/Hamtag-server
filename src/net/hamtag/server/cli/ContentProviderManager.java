package net.hamtag.server.cli;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;
import net.hamtag.server.datatypes.contentprovider.ContentProvider;
import net.hamtag.server.datatypes.contentprovider.ContentProviderMgr;

public class ContentProviderManager {
	public static void main(String[] args) {
		CategoryMgr.getCategoryByName("SPORTS");
		while (true) {
			System.out.println("Add or list? a/l :");
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			String choice = input.nextLine();
			if (choice.equalsIgnoreCase("a")) {
				ContentProvider cp = new ContentProvider();
				System.out.println("Enter the name of your Content Provider: ");
				cp.setName(input.nextLine());
				System.out.println("Enter Categories: (type finish to finish)");
				Set<Category>categories=new HashSet<>();
				while(true){
					String catinput=input.nextLine();
					if(catinput.equalsIgnoreCase("finish"))
						break;
					Category category=CategoryMgr.getCategoryByName(catinput);
					if(category==null)
						System.out.println("INVALID Category");
					else
						categories.add(category);
				}
				cp.setCategories(categories);
				ContentProviderMgr.add(cp);
				System.out.println("Provider ID: " + cp.getId());
			} else if (choice.equalsIgnoreCase("l")) {
				for (ContentProvider cp : ContentProviderMgr.list()){
					System.out.println("Provider ID: " + cp.getId() + "  Provider Name: " + cp.getName());
					for(Category cat:cp.getCategories())
						System.out.println("   Category: "+cat.getName());
				}
			}
		}
	}
}
