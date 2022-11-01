package org.khasanof.auth_service.predicate.auth_info;

import org.khasanof.auth_service.criteria.auth_info.AuthInfoBetweenCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoSearchCriteria;
import org.khasanof.auth_service.enums.auth_info.AuthInfoVisibilityEnum;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class AuthInfoPredicateExecutor {
    public static class SearchPredicate {

        private final AuthInfoSearchCriteria criteria;
        private final List<String> fields = List.of("phoneNumber", "bornYear", "visibility");

        public SearchPredicate(AuthInfoSearchCriteria criteria) {
            this.criteria = criteria;
        }

        public Query searchQuery() {
            if (!presentField(criteria.getKey())) {
                throw new RuntimeException("Field not found");
            }
            Query query = new Query();
            if (criteria.getKey().equals("visibility")) {
                return switch (criteria.getOperation()) {
                    case ":" -> query.addCriteria(Criteria.where(criteria.getKey()).is(AuthInfoVisibilityEnum.valueOf(criteria.getValue())));
                    case ">" -> query.addCriteria(Criteria.where(criteria.getKey()).gt(AuthInfoVisibilityEnum.valueOf(criteria.getValue())));
                    case "<" -> query.addCriteria(Criteria.where(criteria.getKey()).lt(AuthInfoVisibilityEnum.valueOf(criteria.getValue())));
                    case ">=" -> query.addCriteria(Criteria.where(criteria.getKey()).gte(AuthInfoVisibilityEnum.valueOf(criteria.getValue())));
                    case "<=" -> query.addCriteria(Criteria.where(criteria.getKey()).lte(AuthInfoVisibilityEnum.valueOf(criteria.getValue())));
                    default -> query;
                };
            } else {
                return switch (criteria.getOperation()) {
                    case ":" -> query.addCriteria(Criteria.where(criteria.getKey()).is(criteria.getValue()));
                    case ">" -> query.addCriteria(Criteria.where(criteria.getKey()).gt(criteria.getValue()));
                    case "<" -> query.addCriteria(Criteria.where(criteria.getKey()).lt(criteria.getValue()));
                    case ">+" -> query.addCriteria(Criteria.where(criteria.getKey()).gte(criteria.getValue()));
                    case "<+" -> query.addCriteria(Criteria.where(criteria.getKey()).lte(criteria.getValue()));
                    default -> query;
                };
            }
        }

        private boolean presentField(String key) {
            return fields.stream()
                    .anyMatch(any -> any.equals(key));
        }

    }

    public static class BetweenPredicate {

        private final AuthInfoBetweenCriteria criteria;

        public BetweenPredicate(AuthInfoBetweenCriteria criteria) {
            this.criteria = criteria;
        }

        public Query betweenQuery() {
            return new Query().addCriteria(Criteria.where(criteria.getKey()).lte(criteria.getFrom()).gte(criteria.getTo()));
        }
    }
}
