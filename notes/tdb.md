# TDB

## 1. 简介

TDB是Jena框架的一个组成部分，用于存储和检索RDF数据的数据库，它可以用于在`单机`高效地存储RDF。

TDB中提供`事务(transaction)`的机制，可以保证数据安全地进行操作，避免意外地中断造成的数据丢失等问题。

在Fuseki中，内置了TDB进行数据存储和SPARQL的查询。

TDB感觉是一个内存数据库？

## 2. TDB的设计

### 2.1 概述

TDB将数据集存储在单个目录下的文件系统中，一个数据集包含

1. node table(节点表)，并非传统SQL中的表
2. Triple/Quad indexes(三元组/四元组索引)
3. prefixes table(存储命名空间前缀的表)

### 2.2 The Node Table

`Node Table`实际上是一个字典(dictionary)，提供了`node(节点)`和`nodeid(节点id)`之间的双向映射。

在进行查询的过程中，经常会使用到`nodeid` -> `node`的映射，因此在Node Table的实现上，用来一个缓存来存储这样的结果。？？

NodeId是一个8字节的数字，从Node到Nodeid的映射是基于Node的hash值(128字节的MD5哈希值)

node table的存储是这样的结构

1. 一个顺序的文件映射`nodeId`到`node`
2. 一个B+ tree从映射node到nodeid

### 2.3 Triple and Quad Indexes

三元组用于`default graph`(默认的rdf图)，四元组则是在三元组的基础上多一个一个图的IRI，用于`named graph`(命名的图)，可以用于多图数据检索。

由于在SQL中，并没有`triple table`可以直接存储三元组数据，因此在TDB中，所谓的`triple table`其实是三张表的索引保存在一起。而TDB提供了三张表的管理，暴露在外面的接口可以直接操作三元组。

### 2.4 Prefixes Table

`Prefixes Table`使用了一个`node table`和一个索引(Graph->Prefix->URI)，通常非常地小，且不参与到查询的过程中。

一般来说，只在序列化三元组数据，将其保存为`RDF/XML`或`Turtle`格式的文件中会使用到这个表。

### 2.5 Inline Values

暂时看不懂

### 2.6 Query Processing

TDB提供了一些底层次的图模式的优化，

## 3. TDB Java API

### 2.1 连接数据集

`TDBFactroy`类中包含了静态的工厂方法，可以用于连接数据集，在使用之后可以关闭连接之后，所有的更新操作会被保存到硬盘中。

数据集可以以两种方式提供

1. 目录路径
2. assembler文件

#### 2.1.1 使用目录路径

使用这种方式，当提供的目录为空时，TDB 会建立文件的索引和node table(节点表格)。在存在数据的情况下，TDB会直接连接到已存在数据。

```java
// 创建一个TDB支撑的数据集
String directory = "MyDatabases/Dataset1";
Dataset dataset = TDBFactory.createDataset(directory);

```


#### 2.1.2 使用assembler文件








