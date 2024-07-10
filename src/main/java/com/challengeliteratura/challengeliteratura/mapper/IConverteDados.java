package com.challengeliteratura.challengeliteratura.mapper;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> clase);

}