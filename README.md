# Lexical-analysis-program
编译原理词法分析程序/java/大学编译原理课程设计


一、实验目的 
实现PL/0的词法分析程序。


二、实验仪器
     电脑，已安装好C、C++编程环境或Java环境。 



三、实验原理  
1.	实验原理：


(1)	编译原理相关知识：
1)	PL/0语言特点:
保留字：if, then, while, do, read, write, call, begin, end, const, var, procedure, odd等；
运算符：+、-、*、/、=、#、<、>、>=、<=、=：等；
标识符：用户定义的变量名、常数名、过程名； 
常数：10、25、100等整数；
分界符： ‘,’、‘.’ 、‘;’ 、‘(’ 、‘)’等；
其他非法字符：nul。

2)	词法分析
	 词法分析主要任务是逐个地扫描构成源程序的字符流，把它们翻译为有意义的单词序列，提供给语法分析器。其中单词序列以二元组的形式表示，如<单词类别，单词属性值>。


(2)	Java相关知识：
1)	Java面向对象思想
客观世界中的一个事物就是一个对象，每个客观事物都有自己的特征和行为。从程序设计的角度来看，事物的特性就是属性，行为就是方法。事物之间的特征和行为可以相互传递，互相作用，有机协调。
本次实验就是利用现实事物---单词符号，词法分析器所输出的单词有采用<单词类别，单词属性值>，可以设置单词符号类的属性为类别和属性值。
2)	ArrayList集合
本次实验的读入文件存储和分析识别单词就用到了ArrayList集合，其特点为集合中允许有重复元素，所有元素以一种线性的方式进行存储，可以通过索引访问集合中的指定元素。
本次实验所用的ArrayList的方法有添加元素add（）。

3)	输入流BufferedReader
		readLine()方法：可以非常方便地一次读取一行内容返回字符串。

3)	String字符串的方法
		trim()：以去除一段字符串前后的空格，只保留中间的部分。

4)	Character的方法
		isUpperCase（）：判断给定的字符是否是大写字母。
						isLowerCase （）：判断给定的字符是否是小写字母。
						isDigit（）：判断给定的字符是否是数字。

5)	Foreach语句：用于遍历ArrayList集合中的元素。

2.	实验思路：
	   步骤如下：
(1)	定义单词序列类
		里面有单词类别、单词属性值属性，和获取设置属性的方法。

(2)	读取文件，返回字符串集合。
1)	定义一个输入流BufferedReader读取指定文件的每一行内容；
2)	定义一个ArrayList字符串集合，用于存放每一行内容；
3)	直至读到文件末端，关闭流，返回字符串集合。

(3)	传入做读取的字符串集合，分析识别单词，返回单词序列集合。
1)	单词分析识别方法用到的一些方法
a)	关键字判断方法 
		定义一个String数组用于存放关键字，传入需要判断的字符串，遍历String数组，依次判断是否与需要判断的字符串相同。如果相同返回关键字，没找到返回null。
b)	分隔符判断方法
定义一个String数组用于存放分隔符类别名字，再定义一个char数组用于存放分隔符的属性值也就是形式。然后传入需要判断的字符，遍历存放分隔符形式的String数组，依次判断是否与需要判断的字符串相同。如果相同返回对应的分隔符类别名字，没有找到返回null。
c)	操作符判断
		定义一个String数组用于存放操作符类别名字，再定义一个String数组用于存放操作符的属性值也就是形式。然后传入需要判断的字符串，遍历存放操作符形式的String数组，依次判断是否与需要判断的字符串相同。如果相同返回对应的操作符类别名字，没有找到返回null。

2)	单词分析识别方法
		定义一个单词序列类型的ArrayList集合，定义一个String类型的中间遍历的word。
		传入读取文件方法返回的每一行字符串集合，遍历其中的每个字符串，再遍历每个字符串的字符。
		如果是字母，将字符连接到word，然后循环判断下一个字符，直至不是字母或长度超出字符串长度。然后判断该word是不是关键字，若是关键字向单词序列集合加入（“”，关键字类别），若不是向单词序列集合加入（ident，字符串），并将word清空。
		如果是数字，将字符连接到word，然后循环判断下一个字符，直至不是数字或长度超出字符串长度。然后直接向单词序列集合加入（number，字符串），并将word清空。
		如果是分隔符，直接向单词序列集合加入（分隔符类别，字符串），并将word清空。如果是空字符，判断下一个字符。
		如果即不是字母也不是数字，单个字符不是分隔符和空字符，将字符连接到word，然后循环判断下一个字符，直至是其它类型或长度超出字符串长度。然后判断是否是运算符，若是向单词序列集合加入（运算符类型，字符串），不是向单词序列集合加入（nul，字符串），并将word清空。
。

(4)	传入单词序列集合，写入文件。
1)	定义一个输出流BufferedWriter用于对指定文件写入内容；
2)	遍历单词序列集合，依次判断单词序列二元式的种类是否为空，若为空则证明是关键字直接写入“（关键字）”，若不为空则证明是其他符号，直接写入“（种类，属性值）”。写入一组换个行。
3)	单词序列集合遍历完，关闭流。

