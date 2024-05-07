package io.github.jxch.market.index.api;

public interface MarketIndexApi<T,R> {

    R index(T t);

}
