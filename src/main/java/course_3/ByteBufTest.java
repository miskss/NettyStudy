package course_3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author peter
 * date: 2019-10-22 13:36
 **/
class ByteBufTest {

    public static void main(String[] args) {

        ByteBuf buffer = Unpooled.buffer(9, 10000);
        print("buffer", buffer);

        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print("writeBytes(1,2,3,4)", buffer);

        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写, 写完 int 类型之后，写指针增加4
        buffer.writeInt(12);
        print("writeInt(12)", buffer);

        // write 方法改变写指针, 写完之后写指针等于 capacity 的时候，buffer 不可写
        buffer.writeBytes(new byte[]{5});
        print("writeBytes(5)", buffer);

        // write 方法改变写指针，写的时候发现 buffer 不可写则开始扩容，扩容之后 capacity 随即改变
        buffer.writeBytes(new byte[]{6});
        print("writeBytes(6)", buffer);
        System.out.println("getByte(3) return: " + buffer.getByte(7));
        System.out.println("getShort(3) return: " + buffer.getShort(7));
        System.out.println("getInt(3) return: " + buffer.getInt(7));
        print("getByte()", buffer);


        // set 方法不改变读写指针
        buffer.setByte(buffer.readableBytes() + 1, 0);
        print("setByte()", buffer);

        // read 方法改变读指针
        byte[] dst = new byte[buffer.readableBytes()];
        buffer.readBytes(dst);
        print("readBytes(" + dst.length + ")", buffer);


    }


    public static void print(String action, ByteBuf byteBuf) {

        System.out.println("after ===========" + action + "============");
        System.out.println("capacity():" + byteBuf.capacity());
        System.out.println("maxCapacity():" + byteBuf.maxCapacity());
        System.out.println("readerIndex():" + byteBuf.readerIndex());
        System.out.println("readableBytes():" + byteBuf.readableBytes());
        System.out.println("isReadable():" + byteBuf.isReadable());

        System.out.println("writerIndex():" + byteBuf.writerIndex());
        System.out.println("writableBytes():" + byteBuf.writableBytes());

        System.out.println("isWritable():" + byteBuf.isWritable());
        System.out.println("maxWritableBytes():" + byteBuf.maxWritableBytes());
        System.out.println();

    }
}
