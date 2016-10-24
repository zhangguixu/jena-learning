# TDB

## 1. 简介

TDB是一种存储模式，构建在传统的关系型数据库上，用于存储RDF数据，具有速度快、操作简单，支持几十亿条记录，且支持几百个并行查询。

## 2. 数据集组成

在一个TDB的数据集中，包括三个部分

1. the Node Table
2. Triple and Quad Indexes
3. The prefixes table

需要注意的是这里的`table`和`index`它们并不对等于SQL中的表格、索引概念。三元组的存储对应到关系型数据中，实际上是三张表，每张表都有自己的主键，TDB实现了对三张表的管理。

### 2.1 The Node Table




