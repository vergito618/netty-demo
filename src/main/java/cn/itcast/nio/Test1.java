package cn.itcast.nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @program: netty-demo
 * @description:
 * @author: 作者
 * @create: 2022-05-29 23:53
 */
@Slf4j
public class Test1 {


    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        //                     11            24
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);

        source.put("w are you?\nhaha!\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        int oldLimit = source.limit();
        for (int i = 0; i < oldLimit; i++) {
            if (source.get(i) == '\n') {
                System.out.println(i);
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                // 0 ~ limit
                source.limit(i + 1);
                target.put(source); // 从source 读，向 target 写
//                debugAll(target);
                log.info(String.valueOf(target));
                source.limit(oldLimit);
            }
        }
        source.compact();
    }

}
