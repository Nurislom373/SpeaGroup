package org.khasanof.auth_service.predicate.auth_user;

import org.khasanof.auth_service.criteria.BetweenCriteria;
import org.khasanof.auth_service.criteria.SearchCriteria;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

public class AuthUserPredicateExecutor {

    public static class SearchPredicate {

        private final SearchCriteria criteria;

        public SearchPredicate(SearchCriteria criteria) {
            this.criteria = criteria;
        }

        public Query searchQuery() {
            Assert.isNull(criteria, "criteria must be not null");
            Query query = new Query();
            return switch (criteria.getOperation()) {
                case ":" -> query.addCriteria(Criteria.where(criteria.getKey()).is(criteria.getValue()));
                case ">" -> query.addCriteria(Criteria.where(criteria.getKey()).gt(criteria.getValue()));
                case "<" -> query.addCriteria(Criteria.where(criteria.getKey()).lt(criteria.getValue()));
                case ">=" -> query.addCriteria(Criteria.where(criteria.getKey()).gte(criteria.getValue()));
                case "<=" -> query.addCriteria(Criteria.where(criteria.getKey()).lte(criteria.getValue()));
                default -> new Query();
            };
        }

    }

    public static class BetweenPredicate {

        private final BetweenCriteria criteria;

        public BetweenPredicate(BetweenCriteria criteria) {
            this.criteria = criteria;
        }

        public Query betweenQuery() {
            Assert.isNull(criteria, "criteria must be not null");
            return new Query().addCriteria(Criteria.where(criteria.getKey()).lte(criteria.getFrom()).gte(criteria.getTo()));
        }
    }
}
