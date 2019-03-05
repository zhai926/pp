package com.pp.base.utils;

import com.pp.base.constant.WebConst;
import jodd.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: AES
 *
 * @Author: weiwankun
 * @Date:  2018/6/6
 */
@Slf4j
public class AESUtility {
    
    /** AES Key */
    private static final String ENCODE = "UTF-8";
    private static final String FORMAT = "AES";
    private static final String FORMATION = "AES/CBC/PKCS5Padding";
    
    
    // 解密
    public static String decrypt(String pdata) throws Exception {
        try {
            
            String key = WebConst.ENCRYPT_REQUEST_AES_KEY;
            String iv = WebConst.ENCRYPT_REQUEST_AES_IV;
            
            byte[] data = Hex.decodeHex(pdata.toCharArray());
            
            Cipher cipher = Cipher.getInstance(FORMATION);
            
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(ENCODE), FORMAT);
            
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(iv.getBytes(ENCODE)));
            
            return new String(cipher.doFinal(data),ENCODE);
            
        } catch (NoSuchAlgorithmException na) {
            log.error(na.getMessage(),na);
        } catch (NoSuchPaddingException np) {
            log.error(np.getMessage(),np);
        } catch(Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }
    
    
    // 加密
    public static String encrypt(String pdata) throws Exception {
        try {
            
            String key = WebConst.ENCRYPT_REQUEST_AES_KEY;
            String iv = WebConst.ENCRYPT_REQUEST_AES_IV;
            
            Cipher cipher = Cipher.getInstance(FORMATION);
            
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(ENCODE), FORMAT);
            
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(iv.getBytes(ENCODE)));
            
            byte[] encrypted = cipher.doFinal(pdata.getBytes(ENCODE));
            
            String hexStr = Hex.encodeHexString(encrypted);
            
            return Base64.encodeToString(hexStr);
            
        } catch (NoSuchAlgorithmException na) {
            log.error(na.getMessage(),na);
        } catch (NoSuchPaddingException np) {
            log.error(np.getMessage(),np);
        } catch(Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        
        
        //MmEzMzZkNjIxNzE0NjFiMDJlM2NiOTc3ZWMyNWZlYmNiNzdmNTQ0NDVlZmQ3Y2Q5N2UwMGQwMTFhNjIxNzQ5Yw==
        
        String toParseString = "{openId: \"000000\", packageOutCode:\"R00001\", mobile:  \"13100000000\", memberId: \"M000000001\"}";
        
        try {
            
            String parsedStr = AESUtility.encrypt(toParseString);
            log.info("parsedStr=" + parsedStr);
            
            String base64 = Base64.decodeToString(parsedStr);
            log.info("base64=" + base64);
            
            String recoverStr = AESUtility.decrypt(base64);
            log.info("recoverStr=" + recoverStr);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
        
    }

}
