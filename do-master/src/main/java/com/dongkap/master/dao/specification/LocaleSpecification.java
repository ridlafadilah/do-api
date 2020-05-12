package com.dongkap.master.dao.specification;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.dongkap.master.entity.LocaleEntity;

public class LocaleSpecification {
	
	private static final String IS_ACTIVE = "active";

	public static Specification<LocaleEntity> getSelect(Map<String, Object> keyword) {
		return new Specification<LocaleEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -637621292944403277L;

			@Override
			public Predicate toPredicate(Root<LocaleEntity> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
				Predicate predicate = builder.conjunction();
				if (!keyword.isEmpty()) {
					for(Map.Entry<String, Object> filter : keyword.entrySet()) {
						String key = filter.getKey();
						Object value = filter.getValue();
						if (value != null) {
							switch (key) {
								case "_label" :
								case "identifier" :
									// builder.upper for PostgreSQL
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("identifier")), String.format("%%%s%%", value.toString().toUpperCase())));
									break;
								case "localeCode" :
								case "icon" :
									predicate.getExpressions().add(builder.equal(root.get(key), value));
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

	public static Specification<LocaleEntity> getDatatable(Map<String, Object> keyword) {
		return new Specification<LocaleEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -637621292944403277L;

			@Override
			public Predicate toPredicate(Root<LocaleEntity> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
				Predicate predicate = builder.conjunction();
				if (!keyword.isEmpty()) {
					for(Map.Entry<String, Object> filter : keyword.entrySet()) {
						String key = filter.getKey();
						Object value = filter.getValue();
						if (value != null) {
							switch (key) {
								case "localeCode" :
								case "identifier" :
									// builder.upper for PostgreSQL
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get(key)), String.format("%%%s%%", value.toString().toUpperCase())));
									break;
								case "_all" :
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("localeCode")), String.format("%%%s%%", value.toString().toUpperCase())));
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("identifier")), String.format("%%%s%%", value.toString().toUpperCase())));
									break;
								default :
									break;
							}	
						}
					}
				}
				return predicate;
			}
		};
	}

}
