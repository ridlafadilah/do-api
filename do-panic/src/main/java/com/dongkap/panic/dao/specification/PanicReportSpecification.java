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

import com.dongkap.panic.entity.PanicReportEntity;

public class PanicReportSpecification {
	
	private static final String IS_ACTIVE = "active";

	public static Specification<PanicReportEntity> getDatatable(Map<String, Object> keyword) {
		return new Specification<PanicReportEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -637621292944403277L;

			@Override
			public Predicate toPredicate(Root<PanicReportEntity> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
				Predicate predicate = builder.conjunction();
				if (!keyword.isEmpty()) {
					for(Map.Entry<String, Object> filter : keyword.entrySet()) {
						String key = filter.getKey();
						Object value = filter.getValue();
						if (value != null) {
							switch (key) {
								case "fullname":
								case "phoneNumber":
								case "latestFormattedAddress":
								case "latestProvince":
								case "latestCity":
								case "latestDistrict":
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get(key)), String.format("%%%s%%", value.toString().toUpperCase())));
									break;
								case "_all" :
									predicate = builder.disjunction();
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("fullname")), String.format("%%%s%%", value.toString().toUpperCase())));
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("phoneNumber")), String.format("%%%s%%", value.toString().toUpperCase())));
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("latestFormattedAddress")), String.format("%%%s%%", value.toString().toUpperCase())));
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("latestProvince")), String.format("%%%s%%", value.toString().toUpperCase())));
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("latestCity")), String.format("%%%s%%", value.toString().toUpperCase())));
									predicate.getExpressions().add(builder.like(builder.upper(root.<String>get("latestDistrict")), String.format("%%%s%%", value.toString().toUpperCase())));
								default :
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
