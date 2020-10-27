package com.dongkap.master.dao.specification;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.dongkap.master.entity.ProvinceEntity;

public class ProvinceSpecification {
	
	private static final String IS_ACTIVE = "active";

	public static Specification<ProvinceEntity> getSelect(final Map<String, Object> keyword) {
		return new Specification<ProvinceEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -637621292944403277L;

			@Override
			public Predicate toPredicate(Root<ProvinceEntity> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
				Predicate predicate = builder.conjunction();
				if (!keyword.isEmpty()) {
					for(Map.Entry<String, Object> filter : keyword.entrySet()) {
						String key = filter.getKey();
						Object value = filter.getValue();
						if (value != null) {
							switch (key) {
								case "_label" :
								case "provinceName" :
									// builder.upper for PostgreSQL
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("provinceName")), String.format("%%%s%%", value.toString().toUpperCase())));
									break;
								case "provinceCode" :
									predicate.getExpressions().add(builder.equal(root.get(key), value));
									break;
								case "country" :
									predicate = builder.and(predicate, builder.equal(root.join(key).<String>get("countryCode"), value));
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
