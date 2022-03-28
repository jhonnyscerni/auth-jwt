package br.com.projeto.authjwt.utils;

public interface ModelMapper<T, V> {

    V toResponse(T entity);

    T toEntity(V model);
}
