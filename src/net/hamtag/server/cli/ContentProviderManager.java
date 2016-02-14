package net.hamtag.server.cli;

import java.util.Scanner;

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
				ContentProviderMgr.add(cp);
				System.out.println("Provider ID: " + cp.getId());
			} else if (choice.equalsIgnoreCase("l")) {
				for (ContentProvider cp : ContentProviderMgr.list())
					System.out.println("Provider ID: " + cp.getId() + "  Provider Name: " + cp.getName());
			}
		}
	}
}
