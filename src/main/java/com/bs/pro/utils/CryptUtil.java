package com.bs.pro.utils;

import com.bs.pro.constant.Constants;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class CryptUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CryptUtil.class);
    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final String HMAC_MD5 = "HmacMD5";
    private static final String KEY_SAH1 = "SHA-1";

    private CryptUtil() {
    }

    public static String md5Hex(String data) {
        return data == null ? null : DigestUtils.md5Hex(data);
    }

    public static byte[] sha1(byte[] data) {
        if (data == null) {
            return new byte[0];
        } else {
            byte[] ret = null;

            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                ret = md.digest(data);
            } catch (NoSuchAlgorithmException var3) {
                LOGGER.error("{}", var3);
            }

            return ret;
        }
    }

    public static String sha1Hex(byte[] data) {
        return data == null ? null : Hex.encodeHexString(sha1(data));
    }

    public static String sha1Hex(String data) {
        return sha1Hex(StringUtils.getBytesUtf8(data));
    }

    public static String hmacMd5Hex(byte[] data, byte[] key) throws InvalidKeyException {
        if (data != null && key != null) {
            byte[] hash = null;

            try {
                Mac mac = Mac.getInstance("HmacMD5");
                SecretKeySpec secretKey = new SecretKeySpec(key, mac.getAlgorithm());
                mac.init(secretKey);
                hash = mac.doFinal(data);
            } catch (NoSuchAlgorithmException var5) {
                LOGGER.error("{}", var5);
            }

            return Hex.encodeHexString(hash);
        } else {
            return null;
        }
    }

    public static String hmacMd5Hex(String data, String key) throws InvalidKeyException {
        return hmacMd5Hex(StringUtils.getBytesUtf8(data), StringUtils.getBytesUtf8(key));
    }

    public static String hmacSha1Hex(byte[] data, byte[] key) throws InvalidKeyException {
        if (data != null && key != null) {
            byte[] hash = null;

            try {
                Mac mac = Mac.getInstance("HmacSHA1");
                SecretKeySpec secretKey = new SecretKeySpec(key, mac.getAlgorithm());
                mac.init(secretKey);
                hash = mac.doFinal(data);
            } catch (NoSuchAlgorithmException var5) {
                LOGGER.error("{}", var5);
            }

            return Hex.encodeHexString(hash);
        } else {
            return null;
        }
    }

    public static String hmacSha1Hex(String data, String key) throws Exception {
        return hmacSha1Hex(StringUtils.getBytesUtf8(data), StringUtils.getBytesUtf8(key));
    }

    /**
     * 根据密钥对指定的明文plainText进行加密.
     *
     * @param plainText 明文
     * @return 加密后的密文.
     */
    public static final String aesEncrypt(String plainText) {
        Key secretKey = getKey(Constants.AES_KEYSEED);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] p = plainText.getBytes("UTF-8");
            byte[] result = cipher.doFinal(p);
            BASE64Encoder encoder = new BASE64Encoder();
            String encoded = encoder.encode(result);
            return encoded;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Key getKey(String keySeed) {
        if (keySeed == null) {
            keySeed = System.getenv("AES_SYS_KEY");
        }
        if (keySeed == null) {
            keySeed = System.getProperty("AES_SYS_KEY");
        }
        if (keySeed == null || keySeed.trim().length() == 0) {
            keySeed = "abcd1234!@#$";// 默认种子
        }
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed.getBytes());
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据密钥对指定的密文cipherText进行解密.
     *
     * @param cipherText 密文
     * @return 解密后的明文.
     */
    public static final String aesDecrypt(String cipherText) {
        Key secretKey = getKey(Constants.AES_KEYSEED);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] c = decoder.decodeBuffer(cipherText);
            byte[] result = cipher.doFinal(c);
            String plainText = new String(result, "UTF-8");
            return plainText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
