# SPARQL 1.1 Query Language

W3C Recommendation 21 March 2013

[原文地址](https://www.w3.org/TR/sparql11-query/)

## 4. SPARQL基础语法(Syntax)

本章节会介绍RDF术语和三元组模式的一些语法表示（跟turtle的语法有点类似）。

### 4.1 IRI

IRI实际上是比URI更加一般化的概念，可以简单地理解为URI和URL。

关于IRI，知识点有：

1. 使用\<uri\>来表示一个IRI
2. IRI有绝对IRI(irelative IRI)和相对IRI(relative IRI)
3. 前缀名(PrefixedName)可以表示一个IRI的地址前缀（也可以说是一个命名空间），这样可以相同的IRI前缀提取出来，减少一定数据量，也带来书写的方便。
4. BASE表示是当前文件中使用的IRI的基本路径，它的作用跟前缀名类似，在语法上有点不同。

举个例子，以下是一个IRI

```
<http://example.org/book/book1>
```

我们可以通过前缀名和相对IRI来表示跟上面同一个IRI

1. 使用BASE

    ```
    BASE <http://exmaple.org/book/>
    <book1>
    ```

2. 使用前缀名

    ```
    PREFIX book: <http://exmaple.org/book/>
    book:book1
    ```

### 4.2 Literals

Literal用于表示三元组中客体(Object)，表示非IRI的数据，例如字符串(String)，数字(xsd:integer)，日期(xsd:date)等。

前面章节的例子可以看到，我们可以Literal中加入特殊的符号，来对数据进行进一步的约束和说明，在SPARQL中，literal的语法包括以下：

1. 普通字符串

    ```
    "chat"
    ```

2. 字符串中，使用`@+语言标签`来限定语言，例如例子中限定为法语

    ```
    "chat"@fr
    ```

3. 自定义类型，使用`^^`来指定定义类型的命名空间加以说明

    ```
    "xyz"^^<http://example.org/ns/userDatatype>
    "abc"^^appNS:appDataType
    ```

4. 内置支持类型，在`<http://www.w3.org/2001/XMLSchema#> `定义好了，在SPARQL可以直接使用（会自行进行匹配）

    ```
    1        (xsd:integer)
    1.3      (xsd:decimal)
    1.0e6    (xsd:double)
    true     (xsd:boolean)
    ```

### 4.3 查询变量表示

可以使用`?+string`或`$+string`来表示查询的变量，当然变量名有一些限制，具体可以[查看](https://www.w3.org/TR/sparql11-query/#rVARNAME)

### 4.4 空节点的语法表示

空节点的表示有两种

* _:label 或者直接 :label，label仅仅在同一数据集有含义，标识一个空节点
* []，当查询中只出现一次空节点时，就可以使用这种表示形式。

*理解空节点语法的关键在于如何标识空节点，是否能与其他空节点区分开来*

此外，我们可以利用`[ predicate object ]`的语法形式来创建一个三元组模式，其中subject为一个空节点，里面的内容为谓词和客体。举个例子

```
[ 
    foaf:name ?name ；
    foaf:mox <mailto:alice@example.org> .
]
```

就相当于(假设我们创建的空白节点的标签为b18)

```
_:b18  foaf:name  ?name .
_:b18  foaf:mbox  <mailto:alice@example.org> . 
```

### 4.5 三元组模式语法

三元组模式对应着RDF的三元组，只不是在主体、谓词、客体上可能是一个变量而已，它是用于匹配RDF数据的。

*感觉全称可以叫做rdf匹配三元组*

#### 4.5.1 谓词-客体列表(Predicate-Object Lists)

当都是同一个主体时，可以采用简写的方式，只要在每一个三元组模式列表项后面加上`;`，在最后一个三元组模式后面加上`.`来结束就可以。

例如

```
?x foaf:name ?name ;
   foaf:mbox ?mbox .
```

就相当于

```
?x foaf:name ?name .
?x foaf:mbox ?mbox .
```

#### 4.5.2 客体列表(Object Lists)

我们可以通过`,`来表示多个客体。

```
?x foaf:nick "Alice" , "Alice_" .
```

相当于

```
?x foaf:nick "Alice" .
?x foaf:nick "Alice_" .
```

Object List可以和Predicate-Object List结合使用。

```
?x  foaf:name ?name ; foaf:nick "Alice" , "Alice_" .
```

#### 4.5.3 RDF集合

我们可以使用`(e1 e2 ...)`在三元组模式中表示一个RDF集合。当使用集合元素时，会为其生成空节点，将各个元素串联起来。例如

```
(1 ?x 3 4) foaf:name "zhang" .
```

其实是以下代码的语法糖：

```
_:b0 rdf:first 1 ;
     rdf:rest _:b2 .

_:b1  rdf:first  ?x ;
      rdf:rest   _:b2 .

_:b2  rdf:first  3 ;
      rdf:rest   _:b3 .
    
_:b3  rdf:first  4 ;
      rdf:rest   rdf:nil .

_:b0 foaf:name "zhang" .
```

*空节点是不会用于查询的*

RDF集合还支持嵌套，和其他语法混合，例如

```
(1 [ foaf:name "zhang" ] (2)) .
```

相当于

```
_:b0 rdf:first 1 ;
     rdf:rest _:b1 .

_:b1 rdf:first _:b2 ;
     rdf:rest _:b3 .
_:b2 foaf:name "zhang" .

_:b3 rdf:first _:b4 ;
     rdf:rest rdf:nil .
_:b4 rdf:first 2 ;
     rdf:rest rdf:nil .
```

#### 4.5.4 rdf:type

在SPARQL中，rdf:type可以简写成`a`，在三元组模式中作为谓词使用，代表IRI(http://www.w3.org/1999/02/22-rdf-syntax-ns#type)，举个例子

```
?x a :Class1 .
```

相当于

```
?x rdf:type :Class1 .
```




