package br.com.projeto.authjwt.utils;

public interface ModelMapper<T, V> {

    V toModel(T entity);

    T toEntity(V model);
}
