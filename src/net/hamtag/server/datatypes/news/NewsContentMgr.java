package net.hamtag.server.datatypes.news;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import net.hamtag.server.api.response.types.content.ContentDTO;
import net.hamtag.server.core.RootMgr;

public class NewsContentMgr extends RootMgr{
	@SuppressWarnings("unchecked")
	public static List<ContentDTO>getContentInfoByNews(News news){
		Criteria criteria=getInstance().createCriteria(NewsContent.class);
		criteria.add(Restrictions.eq("news", news));
		criteria
		.setProjection(Projections.projectionList()
			      .add(Projections.property("id"),"id")
			      .add(Projections.property("type"),"type"))
			    .setResultTransformer(Transformers.aliasToBean(ContentDTO.class));
		return criteria.list();
	}
}
