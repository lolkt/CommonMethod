##类加载机制


https://www.cnblogs.com/xiaoxian1369/p/5498817.html

#### 一、Java类加载的过程
类从被加载到JVM中开始，到卸载为止，整个生命周期包括：加载、验证、准备、解析、初始化、使用和卸载七个阶段。
其中类加载过程包括加载、验证、准备、解析和初始化五个阶段。



1、加载
简单的说，类加载阶段就是由类加载器负责根据一个类的全限定名来读取此类的二进制字节流到JVM内部，并存储在运行时内存区的方法区，然后将其转换为一个与目标类型对应的java.lang.Class对象实例（Java虚拟机规范并没有明确要求一定要存储在堆区中，只是hotspot选择将Class对戏那个存储在方法区中），这个Class对象在日后就会作为方法区中该类的各种数据的访问入口。
2、链接
链接阶段要做的是将加载到JVM中的二进制字节流的类数据信息合并到JVM的运行时状态中，经由验证、准备和解析三个阶段。
1）、验证
验证类数据信息是否符合JVM规范，是否是一个有效的字节码文件，验证内容涵盖了类数据信息的格式验证、语义分析、操作验证等。
格式验证：验证是否符合class文件规范
语义验证：检查一个被标记为final的类型是否包含子类；检查一个类中的final方法视频被子类进行重写；确保父类和子类之间没有不兼容的一些方法声明（比如方法签名相同，但方法的返回值不同）
操作验证：在操作数栈中的数据必须进行正确的操作，对常量池中的各种符号引用执行验证（通常在解析阶段执行，检查是否通过富豪引用中描述的全限定名定位到指定类型上，以及类成员信息的访问修饰符是否允许访问等）

```
四个阶段的验证：文件格式的验证、元数据的验证、字节码验证和符号引用验证。
文件格式的验证：验证字节流是否符合Class文件格式的规范，并且能被当前版本的虚拟机处理，该验证的主要目的是保证输入的字节流能正确地解析并存储于方法区之内。经过该阶段的验证后，字节流才会进入内存的方法区中进行存储，后面的三个验证都是基于方法区的存储结构进行的。
元数据验证：对类的元数据信息进行语义校验（其实就是对类中的各数据类型进行语法校验），保证不存在不符合Java语法规范的元数据信息。
字节码验证：该阶段验证的主要工作是进行数据流和控制流分析，对类的方法体进行校验分析，以保证被校验的类的方法在运行时不会做出危害虚拟机安全的行为。
符号引用验证：这是最后一个阶段的验证，它发生在虚拟机将符号引用转化为直接引用的时候（解析阶段中发生该转化，后面会有讲解），主要是对类自身以外的信息（常量池中的各种符号引用）进行匹配性的校验。

————————————————
版权声明：本文为CSDN博主「兰亭风雨」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/ns_code/article/details/17881581
```


2）、准备
为类中的所有静态变量分配内存空间，并为其设置一个初始值（由于还没有产生对象，实例变量不在此操作范围内）被final修饰的静态变量，会直接赋予原值；
类字段的字段属性表中存在ConstantValue属性，则在准备阶段，其值就是ConstantValue的值
3）、解析
将常量池中的符号引用转为直接引用（得到类或者字段、方法在内存中的指针或者偏移量，以便直接调用该方法），这个可以在初始化之后再执行。
可以认为是一些静态绑定的会被解析，动态绑定则只会在运行是进行解析；静态绑定包括一些final方法(不可以重写),static方法(只会属于当前类)，构造器(不会被重写)
3、初始化
将一个类中所有被static关键字标识的代码统一执行一遍，如果执行的是静态变量，那么就会使用用户指定的值覆盖之前在准备阶段设置的初始值；如果执行的是static代码块，那么在初始化阶段，JVM就会执行static代码块中定义的所有操作。
所有类变量初始化语句和静态代码块都会在编译时被前端编译器放在收集器里头，存放到一个特殊的方法中，这个方法就是<clinit>方法，即类/接口初始化方法。该方法的作用就是初始化一个中的变量，使用用户指定的值覆盖之前在准备阶段里设定的初始值。任何invoke之类的字节码都无法调用<clinit>方法，因为该方法只能在类加载的过程中由JVM调用。
如果父类还没有被初始化，那么优先对父类初始化，但在<clinit>方法内部不会显示调用父类的<clinit>方法，由JVM负责保证一个类的<clinit>方法执行之前，它的父类<clinit>方法已经被执行。
JVM必须确保一个类在初始化的过程中，如果是多线程需要同时初始化它，仅仅只能允许其中一个线程对其执行初始化操作，其余线程必须等待，只有在活动线程执行完对类的初始化操作之后，才会通知正在等待的其他线程。












#### 二、加载类的三种方式

https://blog.csdn.net/world_snow/article/details/99950881

1、由 new 关键字创建一个类的实例（静态加载）
在由运行时刻用 new 方法载入
如：Dog dog ＝ new Dog（）；

2、调用 Class.forName() 方法
通过反射加载类型，并创建对象实例
如：Class clazz ＝ Class.forName（“Dog”）；
Object dog ＝clazz.newInstance（）；

3、调用某个 ClassLoader 实例的 loadClass() 方法
通过该 ClassLoader 实例的 loadClass() 方法载入。应用程序可以通过继承 ClassLoader 实现自己的类装载器。
如：Class clazz ＝ classLoader.loadClass（“Dog”）；
Object dog ＝clazz.newInstance（）；

三者的区别：

1和2使用的类加载器是相同的，都是当前类加载器。（即：this.getClass.getClassLoader）。
3由用户指定类加载器。如果需要在当前类路径以外寻找类，则只能采用第3种方式。第3种方式加载的类与当前类分属不同的命名空间。
另外：
1是静态加载，2、3是动态加载

两个异常(exception)：

静态加载的时候如果在运行环境中找不到要初始化的类,抛出的是NoClassDefFoundError,它在JAVA的异常体系中是一个Error

动态态加载的时候如果在运行环境中找不到要初始化的类,抛出的是ClassNotFoundException,它在JAVA的异常体系中是一个checked异常

Class.forName与ClassLoader.loadClass区别

Class的装载包括3个步骤：加载（loading）,连接（link）,初始化（initialize）.
Class.forName(className)实际上是调用Class.forName(className, true, this.getClass().getClassLoader())。第二个参数，是指Class被loading后是不是必须被初始化。
ClassLoader.loadClass(className)实际上调用的是ClassLoader.loadClass(name, false)，第二个参数指Class是否被link。
Class.forName(className)装载的class已经被初始化，而ClassLoader.loadClass(className)装载的class还没有被link。一般情况下，这两个方法效果一样，都能装载Class。但如果程序依赖于Class是否被初始化，就必须用Class.forName(name)了。
例如，在JDBC编程中，常看到这样的用法，Class.forName(“com.mysql.jdbc.Driver”).
如果换成了getClass().getClassLoader().loadClass(“com.mysql.jdbc.Driver”)，就不行。
com.mysql.jdbc.Driver的源代码如下：
// Register ourselves with the DriverManager
static {
try {
java.sql.DriverManager.registerDriver(new Driver());
} catch (SQLException E) {
throw new RuntimeException(“Can’t register driver!”);
}
}
原来，Driver在static块中会注册自己到java.sql.DriverManager。而static块就是在Class的初始化中被执行。
所以这个地方就只能用Class.forName(className)。

对于相同的类，JVM最多会载入一次。但如果同一个class文件被不同的ClassLoader载入，那么载入后的两个类是完全不同的。因为已被加载的类由该类的类加载器实例与该类的全路径名的组合标识。设有 packagename.A Class ，分别被类加载器 CL1 和 CL2 加载，所以系统中有两个不同的 java.lang.Class 实例： <CL1, packagename.A> 和 <CL2, packagename.A>。


#### 三、类加载器
类加载器的任务就是根据一个类的全限定名来读取此类的二进制字节流到JVM中，然后转换为一个与目标类对应的java.lang.Class对象实例。
BootstrapClassLoader、ExtClassLoader和AppClassLoader
defineClass方法将字节码的byte数组转换为一个类的class对象实例，如果希望在类被记载到JVM时就被链接，那么可以调用resolveClass方法。

自定义类加载器需要继承抽象类ClassLoader，实现findClass方法，该方法会在loadClass调用的时候被调用，findClass默认会抛出异常。
findClass方法表示根据类名查找类对象
loadClass方法表示根据类名进行双亲委托模型进行类加载并返回类对象
defineClass方法表示跟根据类的字节码转换为类对象


#### 四、双亲委托模型，约定类加载器的加载机制
当一个类加载器接收到一个类加载的任务时，不会立即展开加载，而是将加载任务委托给它的父类加载器去执行，每一层的类都采用相同的方式，直至委托给最顶层的启动类加载器为止。如果父类加载器无法加载委托给它的类，便将类的加载任务退回给下一级类加载器去执行加载。

双亲委托模型的工作过程是：如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把这个请求委托给父类加载器去完成，每一个层次的类加载器都是如此，因此所有的加载请求最终都应该传送到顶层的启动类加载器中，只有当父类加载器反馈自己无法完成这个加载请求（它的搜索范围中没有找到所需要加载的类）时，子加载器才会尝试自己去加载。
使用双亲委托机制的好处是：能够有效确保一个类的全局唯一性，当程序中出现多个限定名相同的类时，类加载器在执行加载时，始终只会加载其中的某一个类。

使用双亲委托模型来组织类加载器之间的关系，有一个显而易见的好处就是Java类随着它的类加载器一起具备了一种带有优先级的层次关系。例如类java.lang.Object，它存放在rt.jar之中，无论哪一个类加载器要加载这个类，最终都是委托给处于模型最顶端的启动类加载器进行加载，因此Object类在程序的各种加载器环境中都是同一个类。相反，如果没有使用双亲委托模型，由各个类加载器自行去加载的话，如果用户自己编写了一个称为java.lang.Object的类，并放在程序的ClassPath中，那系统中将会出现多个不同的Object类，Java类型体系中最基础的行为也就无法保证，应用程序也将会变得一片混乱。如果自己去编写一个与rt.jar类库中已有类重名的Java类，将会发现可以正常编译，但永远无法被加载运行。


双亲委托模型对于保证Java程序的稳定运作很重要，但它的实现却非常简单，实现双亲委托的代码都集中在java.lang.ClassLoader的loadClass()方法中，逻辑清晰易懂：先检查是否已经被加载过，若没有加载则调用父类加载器的loadClass()方法，若父加载器为空则默认使用启动类加载器作为父加载器。如果父类加载器加载失败，抛出ClassNotFoundException异常后，再调用自己的findClass方法进行加载。


