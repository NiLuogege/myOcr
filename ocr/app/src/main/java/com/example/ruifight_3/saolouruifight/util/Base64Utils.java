package com.example.ruifight_3.saolouruifight.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.text.TextUtils.isEmpty;

/**
 * Created by RuiFight-3 on 2017/12/21.
 */

public final class Base64Utils {


    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) throws Exception{
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


    /**
     * Base64 解码
     *
     * @param input 要解码的字符串
     * @return Base64 解码后的字符串
     */
    public static byte[] base64Decode(final String input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * Base64 解码
     *
     * @param input 要解码的字符串
     * @return Base64 解码后的字符串
     */
    public static byte[] base64Decode1(final byte[] input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }


    /**
     * Base64 编码
     *
     * @param input 要编码的字符串
     * @return Base64 编码后的字符串
     */
//    public static byte[] base64Encode(final String input) {
//        return base64Encode(input.getBytes());
//    }

    /**
     * Base64 编码
     *
     * @param input 要编码的字节数组
     * @return Base64 编码后的字符串
     */
    public static String base64Encode2String(final byte[] input) {
        return Base64.encodeToString(input, Base64.NO_WRAP);
    }

    /**
     * 把Bitmap转Byte
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64s(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.NO_WRAP);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 判断字符串结尾是不是 jpg   用endsWith
     * 判断开头字符用startsWith
     *
     * @param imgUrl
     * @return
     */
    public static boolean isHttpUrl(String imgUrl) {
        if (!isEmpty(imgUrl) && (imgUrl.endsWith("jpg"))) {
            return true;
        }
        return false;
    }
}
