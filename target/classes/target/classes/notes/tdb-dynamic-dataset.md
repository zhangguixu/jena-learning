# TDB 动态数据集(Dynamic Datasets)

TDB支持只查询TDB数据集下所有命名图的一个子集，从而提高查询效率。

在SPARQL查询语句中，可以通过关键字`FROM`和`FROM NAMED`来指定查询范围。其中

* FROM：指的是默认图（多图合并还是默认）
* FROM NAMED: 指定的是命名图

*在ARQ模块中，没有对此查询语句的关键字进行区分，会在所有图中进行查询*

在TDB实现了对查询语句中的`FROM`和`FROM NAMED`的区分，有以下三种情况

* 使用一到多个FROM，会使FROM后面的图合并为默认图（图合并）
* 使用一到多个FROM NAMED，没有使用FROM，则会使用一个空的图作为默认图。
* 