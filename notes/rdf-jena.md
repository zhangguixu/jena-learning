# RDF API

完整的示例[在这里](../rdf/)

## 1. 数据模型表示

在Jena中，由类`Model`来创建一个内存中的RDF数据模型，一个基本的RDF数据模型通过以下几个类表示：

1. Resource(资源)：RDF描述的各种事物都被称作资源（URI），用来表示RDF三元组中的主体。
2. Proprety(属性)：属性是资源的一个特定的方面，特征，或者是用来描述资源间的关系，用来表示RDF三元组的谓词。
3. RDFNode(RDF节点)，是Resource和Literal的超类，用来表示客体。
3. Statement(声明)：一个Statement可以直接声明一个三元组，包含主体、谓词和客体三个独立的部分。

```java
// 使用工厂模式，创建一个model
Model model = Model.createDefaultModel();

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