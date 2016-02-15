package net.hamtag.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.hamtag.server.datatypes.category.Category;

public class CategoryToStringConverter {
	public static List<String> getCategoryList(Set<Category> categories){
		List<String>toReturn=new ArrayList<>();
		for(Category category:categories)
			toReturn.add(category.getName());
		return toReturn;
	}
}
