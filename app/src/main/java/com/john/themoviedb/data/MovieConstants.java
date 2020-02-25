package com.john.themoviedb.data;


import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.john.themoviedb.data.MovieConstants.SortBy.FAVORITES;
import static com.john.themoviedb.data.MovieConstants.SortBy.MOST_POPULAR;
import static com.john.themoviedb.data.MovieConstants.SortBy.TOP_RATED;

public interface MovieConstants {
    @StringDef({MOST_POPULAR, TOP_RATED, FAVORITES})
    @Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    @interface SortBy {
        String MOST_POPULAR = "popular";
        String TOP_RATED = "top_rated";
        String FAVORITES = "favorites";
    }
}
