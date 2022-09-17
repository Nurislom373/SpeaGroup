package org.khasanof.auth_service.predicate.auth_user;

import org.khasanof.auth_service.criteria.BetweenCriteria;
import org.khasanof.auth_service.criteria.auth_user.AuthUserSearchCriteria;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class AuthUserPredicateExecutor {

    public static class SearchPredicate {

        private final AuthUserSearchCriteria criteria;

        public SearchPredicate(AuthUserSearchCriteria criteria) {
            this.criteria = criteria;
        }

        public Query searchQuery() {
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
            return new Query().addCriteria(Criteria.where(criteria.getKey()).lte(criteria.getFrom()).gte(criteria.getTo()));
        }
    }
}
