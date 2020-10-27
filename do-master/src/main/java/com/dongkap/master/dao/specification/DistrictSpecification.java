/**
 * 
 */
package com.dongkap.master.dao.specification;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.dongkap.master.entity.DistrictEntity;

public class DistrictSpecification {
	
	private static final String IS_ACTIVE = "active";

	public static Specification<DistrictEntity> getSelect(final Map<String, Object> keyword) {
		return new Specification<DistrictEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -637621292944403277L;

			@Override
			public Predicate toPredicate(Root<DistrictEntity> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
				Predicate predicate = builder.conjunction();
				if (!keyword.isEmpty()) {
					for(Map.Entry<String, Object> filter : keyword.entrySet()) {
						String key = filter.getKey();
						Object value = filter.getValue();
						if (value != null) {
							switch (key) {
								case "_label" :
								case "districtName" :
									// builder.upper for PostgreSQL
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("districtName")), String.format("%%%s%%", value.toString().toUpperCase())));
									break;
								case "districtCode" :
									predicate.getExpressions().add(builder.equal(root.get(key), value));
									break;
								case "city" :
									predicate = builder.and(predicate, builder.equal(root.join(key).<String>get("cityCode"), value));
									break;
							}	
						}
					}
				}
				predicate = builder.and(predicate, builder.equal(root.get(IS_ACTIVE), true));
				return predicate;
			}
		};
	}

}
