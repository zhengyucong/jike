package jvm;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.Base64;

public class Main extends ClassLoader{
    public static void main(String[] args) {
        try {
            Object obj = new Main().findClass("Hello").newInstance();
            Method method = obj.getClass().getMethod("hello");
            method.invoke(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = new byte[0];
        try {
            byte[] bt = getByteArrFromFile();
//            byte[] bt = getByteArrFromBase64Arr();
            bytes = base64ClassMove(bt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defineClass(name,bytes,0,bytes.length);
    }

    private byte[] getByteArrFromBase64Arr(){
        String base64Str = "NQFFQf///8v/4/X/+f/x9v/w/+/3/+71/+3/7Pj/6/j/6v7/+cOWkZaLwf7//NfWqf7/+7yQm5r+//CzlpGasYqSnZqNq56dk5r+//qXmpOTkP7/9ayQio2cmrmWk5r+//W3mpOTkNGVnome8//4//f4/+nz/+j/5/7/7Leak5OQ09+ck56MjLOQnpuajd74/+bz/+X/5P7/+reak5OQ/v/vlZ6JntCTnpGY0LCdlZqci/7/75WeiZ7Qk56RmNCshoyLmpL+//yQiov+/+qzlZ6JntCWkNCvjZaRi6yLjZqeksT+/+yVnome0JaQ0K+NlpGLrIuNmp6S/v/4j42WkYuTkf7/6tezlZ6JntCTnpGY0KyLjZaRmMTWqf/e//r/+f///////f/+//j/9//+//b////i//7//v////rVSP/+Tv////7/9f////n//v////7//v/0//f//v/2////2v/9//7////2Tf/97fxJ//tO/////v/1////9f/9////+//3//r//v/z/////f/y";
        return decode(base64Str);
    }


    private byte[] getByteArrFromFile() throws IOException {
        File file = new File("/Users/yucong/jikeshijian/week-one/Hello.xlass");
        return Files.readAllBytes(file.toPath());
    }

    /**
     * 作者 zhengyucong
     * 时间 3:58 下午 2021/8/8
     * 说明 每个字节偏移255
     **/
    private byte[] base64ClassMove(byte[] bytes){
        for(int i =0;i<bytes.length;i++){
            bytes[i] = (byte)(255-bytes[i]);
        }
        return bytes;
    }

    private byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }


}
