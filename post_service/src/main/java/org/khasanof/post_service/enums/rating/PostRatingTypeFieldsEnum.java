package org.khasanof.post_service.enums.rating;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostRatingTypeFieldsEnum {
    LIKES_COUNT("likeCount"),
    VIEWS_COUNT("viewsCount"),
    SAVES_COUNT("savesCount"),
    RATING_TYPE("ratingType");
    private final String value;
}
