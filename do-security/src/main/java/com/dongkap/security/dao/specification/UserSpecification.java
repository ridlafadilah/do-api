/**
 * 
 */
package com.dongkap.security.dao.specification;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.dongkap.security.entity.UserEntity;

public class UserSpecification {

	public static Specification<UserEntity> getDatatable(Map<String, Object> keyword) {
		return new Specification<UserEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -637621292944403277L;

			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
				Predicate predicate = builder.conjunction();
				if (!keyword.isEmpty()) {
					for(Map.Entry<String, Object> filter : keyword.entrySet()) {
						String key = filter.getKey();
						Object value = filter.getValue();
						if (value != null) {
							switch (key) {
								case "authority" :
									predicate = builder.and(predicate, builder.equal(root.join("roles").<String>get(key), value.toString()));
									break;
								case "email" :
									predicate.getExpressions().add(builder.equal(root.<String>get(key), value.toString()));
									break;
								case "username" :
									// builder.upper for PostgreSQL
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get(key)), String.format("%%%s%%", value.toString().toUpperCase())));
									break;
								case "name" :
									// builder.upper for PostgreSQL
									predicate.getExpressions().add(builder.like(builder.upper(root.join("contactUser").<String>get(key)), String.format("%%%s%%", value.toString().toUpperCase())));
									break;
								case "phoneNumber" :
									predicate.getExpressions().add(builder.equal(root.join("contactUser").<String>get(key), value.toString()));
									break;
								case "_all" :
									predicate.getExpressions().add(builder.equal(root.<String>get("email"), value.toString()));
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("username")), String.format("%%%s%%", value.toString().toUpperCase())));
									predicate.getExpressions().add(builder.like(builder.upper(root.join("contactUser").<String>get("name")), String.format("%%%s%%", value.toString().toUpperCase())));
									predicate.getExpressions().add(builder.equal(root.join("contactUser").<String>get("phoneNumber"), value.toString()));
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
