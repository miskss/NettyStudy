## course_4 的改造版

`ByteToMessageDecoder`  将二进制转换成ByteBuf,重写`decode()` 方法来构建java对象

`SimpleChannelInboundHandler` 自动将对象转 对应的对象

`MessageToByteEncoder` 自动将对象转化成二进制数据