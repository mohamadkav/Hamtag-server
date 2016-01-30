package net.hamtag.server.cli;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.ad.AdContent;
import net.hamtag.server.datatypes.ad.AdContentMgr;
import net.hamtag.server.datatypes.ad.AdMgr;
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.news.NewsContent;
import net.hamtag.server.datatypes.news.NewsContentMgr;
import net.hamtag.server.datatypes.news.NewsMgr;

public class ResourceAdder {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println("Enter News or Ad? n/a");
			String choice = input.nextLine();
			if (choice.equalsIgnoreCase("a")) {
				System.out.println("Enter Ad ID: ");
				String id = input.nextLine();
				Ad ad = AdMgr.getInstance().get(Ad.class, Long.parseLong(id));
				if (ad == null) {
					System.out.println("invalid id");
					continue;
				}
				AdContent ac=new AdContent();
				ac.setAd(ad);
				System.out.println("Type (EG: TUMBNAIL): ");
				ac.setType(input.nextLine());
				System.out.println("File location: ");
				String path=input.nextLine();
				File file = new File(path);
				byte[] bFile = new byte[(int) file.length()];
				try {
					FileInputStream fileInputStream = new FileInputStream(file);
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ac.setContent(bFile);
				AdContentMgr.add(ac);
			}
			else if(choice.equalsIgnoreCase("n")){
				System.out.println("Enter News ID: ");
				String id = input.nextLine();
				News news = NewsMgr.getInstance().get(News.class, Long.parseLong(id));
				if (news == null) {
					System.out.println("invalid id");
					continue;
				}
				NewsContent nc=new NewsContent();
				nc.setNews(news);
				System.out.println("Type (EG: TUMBNAIL): ");
				nc.setType(input.nextLine());
				System.out.println("File location: ");
				String path=input.nextLine();
				File file = new File(path);
				byte[] bFile = new byte[(int) file.length()];
				try {
					FileInputStream fileInputStream = new FileInputStream(file);
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				nc.setContent(bFile);
				NewsContentMgr.add(nc);
			}
		}
	}
}
