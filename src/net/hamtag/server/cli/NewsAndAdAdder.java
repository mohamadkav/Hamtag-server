package net.hamtag.server.cli;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.ad.AdMgr;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;
import net.hamtag.server.datatypes.contentprovider.ContentProvider;
import net.hamtag.server.datatypes.contentprovider.ContentProviderMgr;
import net.hamtag.server.datatypes.corporation.Corporation;
import net.hamtag.server.datatypes.corporation.CorporationMgr;
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.news.NewsMgr;
import net.hamtag.server.datatypes.user.UserMgr;

public class NewsAndAdAdder {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
		CategoryMgr.getCategoryByName("SPORTS");
		System.out.println("\n\n");
		System.out.println("Enter News or Ad? n/a");
		String choice=input.nextLine();
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
		if(choice.equalsIgnoreCase("a")){
			System.out.println("Corporation will be the Default corporation!");
			Ad ad=new Ad();
			ad.setCategories(categories);
			System.out.println("Address: ");
			ad.setAddress(input.nextLine());
			System.out.println("Comments: ");
			ad.setComments(input.nextLine());
			ad.setCorporation(CorporationMgr.getInstance().get(Corporation.class, 1L));
			System.out.println("Link: ");
			ad.setLink(input.nextLine());
			System.out.println("Location: ");
			ad.setLocation(input.nextLine());
			System.out.println("Phone number: ");
			ad.setPhone(input.nextLine());
			System.out.println("Enter price (must be numeric): ");
			ad.setPrice(Integer.parseInt(input.nextLine()));
			ad.setPublishTime(new Date());
			System.out.println("Enter Username of the adder: mohamad.gholami");
			ad.setUser(UserMgr.getByUsername(input.nextLine()));
			ad.setIsRelatedToNews(false);
			System.out.println("THE ID FOR YOUR AD IS: ");
			AdMgr.add(ad);
			System.out.println(ad.getId());
		}
		else if(choice.equalsIgnoreCase("n")){
			News news=new News();
			news.setCategories(categories);
			news.setPublishTime(new Date());
			System.out.println("Title: ");
			news.setTitle(input.nextLine());
			System.out.println("Text: ");
			news.setText(input.nextLine());
			System.out.println("Content provider ID: ");
			news.setContentProvider(ContentProviderMgr.getInstance().get(ContentProvider.class,Long.parseLong(input.nextLine())));
			System.out.println("Enter Username of the adder: mohamad.gholami");
			news.setUser(UserMgr.getByUsername(input.nextLine()));
			System.out.println("THE ID FOR YOU NEWS IS: ");
			System.out.println(NewsMgr.add(news));
		}
		else
			System.out.println("Choice Invalid");
	}
}
