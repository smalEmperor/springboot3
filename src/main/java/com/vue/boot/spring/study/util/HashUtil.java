package com.vue.boot.spring.study.util;

import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public final class HashUtil {

    public static String md5(byte[] bytes) {
        return Hashing.md5().hashBytes(bytes).toString().toUpperCase();
    }

    public static String md5(String source, Charset charset) {
        return Hashing.md5().hashString(source, charset).toString().toUpperCase();
    }

    public static byte[] sha1(byte[] bytes) {
        return Hashing.sha1().hashBytes(bytes).asBytes();
    }

    public static String sha1(String source, Charset charset) {
        return Hashing.sha1().hashString(source, charset).toString();
    }

    public static byte[] sha256(byte[] bytes) {
        return Hashing.sha256().hashBytes(bytes).asBytes();
    }
    public static String sha256(String source, Charset charset) {
        return Hashing.sha256().hashString(source, charset).toString();
    }

    public static byte[] sha384(byte[] bytes) {
        return Hashing.sha384().hashBytes(bytes).asBytes();
    }

    public static String sha384(String source, Charset charset) {
        return Hashing.sha384().hashString(source, charset).toString();
    }

    public static byte[] sha512(byte[] bytes) {
        return Hashing.sha512().hashBytes(bytes).asBytes();
    }

    public static String sha512(String source, Charset charset) {
        return Hashing.sha512().hashString(source, charset).toString();
    }

    public static void main(String[] args) {

    }
}
