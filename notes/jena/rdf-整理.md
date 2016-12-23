# RDF API

## 1. 类

在Jena中，我们用`Model`来表示一组RDF三元组数据。

我们知道RDF数据可以被解释为图（RDF Graph），依照这个角度，在Jean中定义了`RDFNode`类来表示RDF数据中的节点（即主体和客体）。

每个节点存储的值不同，因此设计了`RDFNode`的两个子类，来表示不同的值：

* Resource，表示值为IRI资源的节点
* Literal，表示值非IRI资源的节点，包括：

    * 字符串
    * 日期
    * 数值
    * XML Schema定义的数据类型

此外，RDFNode本身表示一个空节点（Blank Node）。

节点之间属性则是用类`Property`来表示。我们通过

在Jena中，我们可以用`Statement`类来表示一个三元组。
