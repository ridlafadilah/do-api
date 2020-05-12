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

public class StatisticsPanicReportSpecification {
	
	private static final String IS_ACTIVE = "active";

	public static Specification<PanicReportEntity> getStatistics(Map<String, Object> keyword) {
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
								case "year" :
									predicate = builder.and(predicate, builder.equal(root.get(key), value));
									break;
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
