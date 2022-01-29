package com.homework.bootcamp.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
    GENERIC_EXCEPTION(1, "Bilinmeyen bir sorun oluştu"),

    MOVIE_DATA_NOT_FOUND(1001,"Film Bulunamadi"),
    ACTOR_DATA_NOT_FOUND(1002,"Oyuncu Bulunamadi"),
    RATE_DATA_NOT_FOUND(1003,"Puanlama Bulunamadi"),
    MEMBER_DATA_NOT_FOUND(1004,"Member Bulunamadi"),
    WATCHLIST_DATA_NOT_FOUND(1005,"Izleme listesi Bulunamadi"),

    COLLECTION_SIZE_EXCEPTION(2001,"Liste Boyutlari uyusmuyor");

    private final Integer code;
    private final String message;
}
