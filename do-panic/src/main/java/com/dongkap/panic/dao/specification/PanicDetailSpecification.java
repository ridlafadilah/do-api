/**
 * 
 */
package com.dongkap.panic.dao.specification;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.dongkap.panic.entity.PanicDetailEntity;

public class PanicDetailSpecification {

	public static Specification<PanicDetailEntity> getDatatable(Map<String, Object> keyword) {
		return new Specification<PanicDetailEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -637621292944403277L;

			@Override
			public Predicate toPredicate(Root<PanicDetailEntity> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
				Predicate predicate = builder.conjunction();
				if (!keyword.isEmpty()) {
					for(Map.Entry<String, Object> filter : keyword.entrySet()) {
						String key = filter.getKey();
						Object value = filter.getValue();
						if (value != null) {
							switch (key) {
								case "panicCode" :
									predicate = builder.and(predicate, builder.equal(root.join("panicReport").<String>get(key), value.toString()));
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
