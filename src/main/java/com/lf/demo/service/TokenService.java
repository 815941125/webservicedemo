package com.lf.demo.service;

import com.lf.demo.util.AESEncrypt;
import com.lf.demo.util.MD5Encoder;
import com.lf.demo.vo.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * PACKAGE      :  com.jiahui.eappt.service
 * CREATE DATE  :  2018/4/2
 * AUTHOR       :  linfei
 * 文件描述      :  token 服务
 */
public class TokenService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String keyToken = "security-key-token";
    private AESEncrypt aes;
    private boolean useAES = false;

    public TokenService() {
        this.initAES();
    }

    public TokenService(String keyToken) {
        this.keyToken = keyToken;
        this.initAES();
    }

    private void initAES() {
        try {
            this.aes = new AESEncrypt(this.keyToken);
        } catch (NoSuchAlgorithmException var2) {
            this.logger.error("initAES, NoSuchAlgorithmException", var2);
        } catch (NoSuchPaddingException var3) {
            this.logger.error("initAES, NoSuchPaddingException", var3);
        } catch (InvalidKeyException var4) {
            this.logger.error("initAES, InvalidKeyException", var4);
        } catch (UnsupportedEncodingException var5) {
            this.logger.error("initAES, UnsupportedEncodingException", var5);
        }

        if (null == this.aes) {
            throw new RuntimeException("init AES error.");
        }
    }

    public String createToken(String type, Long id, String name) {
        Token token = new Token();
        token.setType(type);
        token.setId(id);
        token.setName(name);
        token.setTs(new Date());
        String code = createToken(token);
        return code;
    }

    public Token parseToken(String code) {
        return this.decodeAES(code);
    }

    public String createToken(Token token) {
        return this.encodeAES(token);
    }

    private String encodeAES(Token token) {
        try {
            String data = this.encode(token);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("token code:{}", data);
            }

            if (this.useAES) {
                data = this.aes.encode(data);
            }
            return data;
        } catch (BadPaddingException var3) {
            this.logger.error("encodeAES", var3);
        } catch (InvalidKeyException var4) {
            this.logger.error("encodeAES", var4);
        } catch (IllegalBlockSizeException var5) {
            this.logger.error("encodeAES:", var5);
        } catch (UnsupportedEncodingException var6) {
            this.logger.error("encodeAES:", var6);
        }

        return null;
    }

    private Token decodeAES(String code) {
        try {
            String data = code;
            if (this.useAES) {
                data = this.aes.decode(code);
            }

            if (this.logger.isDebugEnabled()) {
                this.logger.debug("token code:{}", data);
            }

            Token token = this.decode(data);
            return token;
        } catch (IOException var4) {
            this.logger.warn("decodeAES:{}", code, var4);
        } catch (BadPaddingException var5) {
            this.logger.warn("decodeAES:{}", code, var5);
        } catch (InvalidKeyException var6) {
            this.logger.warn("decodeAES:{}", code, var6);
        } catch (IllegalBlockSizeException var7) {
            this.logger.warn("decodeAES:{}", code, var7);
        }

        return null;
    }

    private String encode(Token token) {
        String sign = this.sign(token);
        String data = String.format("t:%s:%s:%s:%s:%s",
                new Object[]{token.getType(), token.getId(), token.getName(),
                        Long.valueOf(token.getTs().getTime()), sign});
        return data;
    }

    private Token decode(String code) {
        String[] sets = code.split(":");
        if (null != sets && 6 == sets.length && sets[0].equals("t")) {
            Token token = new Token();

            try {
                token.setType(sets[1]);
                token.setId(Long.valueOf(Long.parseLong(sets[2])));
                token.setName(sets[3]);
                token.setTs(new Date(Long.parseLong(sets[4])));
            } catch (Exception var6) {
                this.logger.warn("token data is illegal:{}", code, var6);
                return null;
            }

            String sign = sets[5];
            String sign_check = this.sign(token);
            if (!sign.equals(sign_check)) {
                this.logger.warn("token sign not match, code:{}, sign:{}, sign_check:{}",
                        new Object[]{code, sign, sign_check});
                return null;
            } else {
                return token;
            }
        } else {
            this.logger.warn("token format is illegal:{}", code);
            return null;
        }
    }

    private String sign(Token token) {
        String data = String.format("%s:%s:%s:%s:%s",
                new Object[]{token.getType(), token.getId(), token.getName(),
                        Long.valueOf(token.getTs().getTime()), this.keyToken});
        try {
            String sign = MD5Encoder.md5(data);
            return sign.substring(0, 8);
        } catch (NoSuchAlgorithmException var4) {
            this.logger.error("NO md5 algorithm", var4);
            throw new RuntimeException("NO md5 algorithm");
        }
    }

    public boolean isUseAES() {
        return this.useAES;
    }

    public void setUseAES(boolean useAES) {
        this.useAES = useAES;
    }

}