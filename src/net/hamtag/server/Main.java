package net.hamtag.server;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;

import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.news.NewsMgr;

public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		News ne=NewsMgr.getInstance().get(News.class, 1);
		ne.setText("Khabar resid ke shiit!");
		NewsMgr.getInstance().update(ne);
		Criteria c=CategoryMgr.getInstance().createCriteria(Category.class);
		List<Category>all=c.list();
		for(Category ca:all){
			Set<News>n=ca.getNews();
			for(News news:n){
				System.err.println(news.getText());
			}
		}
	}
}
