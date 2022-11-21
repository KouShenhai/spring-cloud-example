package com.livk.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * <p>
 * FileUtils
 * </p>
 *
 * @author livk
 * @date 2022/11/4
 */
@UtilityClass
public class FileUtils extends FileCopyUtils {

    public void testDownload(InputStream stream, String name) throws IOException {
        String path = "./" + name;
        File file = new File(path);
        if (!file.exists()) {
            LogUtils.info("开始创建文件");
            int count = 0;
            while (!file.createNewFile()) {
                if (++count == 3) {
                    throw new IOException();
                }
            }
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            FileChannel channel = fileOutputStream.getChannel();
            ReadableByteChannel readableByteChannel = Channels.newChannel(stream);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (readableByteChannel.read(buffer) != -1) {
                buffer.flip();
                channel.write(buffer);
                buffer.clear();
            }
        }
    }
}
