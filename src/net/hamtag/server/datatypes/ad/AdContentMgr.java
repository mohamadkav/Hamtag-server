package net.hamtag.server.datatypes.ad;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import net.hamtag.server.api.response.types.content.ContentDTO;
import net.hamtag.server.core.RootMgr;

public class AdContentMgr extends RootMgr{
	@SuppressWarnings("unchecked")
	public static List<ContentDTO>getContentInfoByAd(Ad ad){
		Criteria criteria=getInstance().createCriteria(AdContent.class);
		criteria.add(Restrictions.eq("ad", ad));
		criteria
		.setProjection(Projections.projectionList()
			      .add(Projections.property("id"),"id")
			      .add(Projections.property("type"),"type"))
			    .setResultTransformer(Transformers.aliasToBean(ContentDTO.class));
		return criteria.list();
	}
}
