package net.hamtag.server.cli;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;

public class CategoryCMDManager {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
		System.out.println("Choice 1 to add new Category, Choice 2 to add image to Category: ");
		int choice=Integer.parseInt(input.nextLine());
		if(choice==1){
			System.out.println("Name: ");
			Category category=new Category();
			category.setName(input.nextLine());
			CategoryMgr.add(category);
		}
		else{
			System.out.println("Category Name to add Image: ");
			Category category=CategoryMgr.getCategoryByName(input.nextLine());
			System.out.println("File Location: ");
			File file=new File(input.nextLine());
			byte[] bFile = new byte[(int) file.length()];
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				fileInputStream.read(bFile);
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			category.setImage(bFile);
			CategoryMgr.update(category);
		}
	}
}
