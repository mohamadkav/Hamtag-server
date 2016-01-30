package net.hamtag.server;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.news.GetNewsByCategoryForDeviceRequest;
import net.hamtag.server.api.resource.NewsResource;
import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.ad.AdContent;
import net.hamtag.server.datatypes.ad.AdContentMgr;
import net.hamtag.server.datatypes.ad.AdMgr;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;
import net.hamtag.server.datatypes.corporation.Corporation;
import net.hamtag.server.datatypes.corporation.CorporationMgr;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.news.NewsMgr;
public class Main {
	public static void main(String[] args) {
/*		for(Category c:DeviceMgr.getDeviceByPhoneNumber("0912").getCategories())
			System.err.println(c.getName());
		System.err.println(DeviceMgr.getDeviceByPhoneNumber("0912").getCategories());*/
		// TODO: change location parameter for ad to float
		// System.err.println(new GetAdsByCategoryForDeviceRequest("2",
		// "SPORTS","0").getHandler().handle().toString());
		// System.err.println(GenerateTokenUtil.generateNewToken());
/*		 Device d=DeviceMgr.getDeviceByPhoneNumber("0912");
		 Set<News>n=new HashSet<>();
		 n.add(NewsMgr.getInstance().get(News.class, 2));
		 n.add(NewsMgr.getInstance().get(News.class, 3));
		 d.setLikedNews(n);
		 DeviceMgr.update(d);*/
//		 d.addToLikes(NewsMgr.getInstance().get(News.class, 2));
//		 DeviceMgr.update(d);
//		 for(News n:d.getLikedNews()){
//			 System.err.println(n.getText());
//		 }
		// d.setPhoneNumber("0913!!!");
		// DeviceMgr.update(d);
/*		Ad a = AdMgr.getInstance().get(Ad.class, 1);
		AdContent ac = new AdContent();
		ac.setAd(a);
		ac.setType("THUMBNAIL");
		File file = new File("/home/mohammad/Desktop/1.jpg");
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			// convert file into array of bytes
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ac.setContent(bFile);
		AdContentMgr.add(ac);*/
/*		Ad a=new Ad();
		Set<Category>cat=new HashSet<>();
		cat.add(CategoryMgr.getCategoryByName("SPORTS"));
		a.setCategories(cat);
		a.setCorporation(CorporationMgr.getInstance().get(Corporation.class,1L));
		a.setPrice(200);
		a.setPublishTime(new Date());
		AdMgr.add(a);*/
//		System.out.println(new Date().getTime());
/*		News n=new News();
		Set<Category>cat=new HashSet<>();
		cat.add(CategoryMgr.getCategoryByName("SPORTS"));
		n.setCategories(cat);
		n.setPublishTime(new Date());
		n.setText("Khabar Nemirese");
		n.setTitle("A GREAT NEWS!!");
		NewsMgr.add(n);*/
/*		NewsContent nc= new NewsContent();
		nc.setNews(NewsMgr.getInstance().get(News.class, 1));
		nc.setType("TUMBNAIL");id
		File file = new File("/home/mohammad/Desktop/1.jpg");
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			// convert file into array of bytes
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		nc.setContent(bFile);
		NewsContentMgr.add(nc);*/
	}
}
