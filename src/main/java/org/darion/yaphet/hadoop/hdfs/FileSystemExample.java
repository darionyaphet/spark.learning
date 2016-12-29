package org.darion.yaphet.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class FileSystemExample {
    public static void main(String[] args) throws IOException {
        FileSystem system = FileSystem.get(new Configuration());
        Path path = new Path("");
        FSDataInputStream inputStream = system.open(path);
        try {
            inputStream.skip(0L);
            inputStream.readChar();
        } finally {
            inputStream.close();
            system.close();
        }
    }
}
