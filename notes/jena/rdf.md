# RDF API

完整的示例[在这里](../../rdf/)

## 1. 概述

在Jena中，由类`Model`来代表一个内存中的RDF数据模型。在Jena中采用工厂类ModelFactory的获取Model类的实例。

Jena定义了一个接口`RDFNode`，用于表示客体或者主体，定义`Resource`类和`Literal`类继承`RDFNode`接口，分别用于表示IRI资源和非IRI资源（即字符串、数字、日期等）。

同时使用类`Property`来表示三元组中的谓词。

`Statement`类则表示RDF的陈述，即为一个三元组。RDF API的使用示例如下：

```java
// 使用工厂模式，创建一个model
Model model = ModelFactory.createDefaultModel();

// 创建资源
Resource r1 = model.createResource("http://example/r1");
Resource r2 = model.createResource("http://exmaple/r2");

// 创建一个属性来描述资源
Property kindOf = model.createProperty("http://example/relation/kindOf", "kindOf");

// 组成一个三元组
r1.addProperty(kindOf, r2);

// 也可以直接声明一个三元组
Statement s1 = model.createStatement(r1, kindOf, r2);
model.add(s1);
```

## 2. 数据遍历与查询

## 3. 模型操作

## 4. 持久化

*同时，在RDF API中，还提供了模型数据的遍历和RDF内存模型持久化的方法。由于在工程中使用ARQ模块和数据库TDB，因此RDF API的这些方法没有多大的实际用处。*