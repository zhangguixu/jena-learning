# Ontology API

在Jena中，本体模型继承自Jena中的Model类。Model允许访问RDF数据集合中的陈述（Statements），OntModel对此进行了扩展，以便支持本体中的各种数据对象：类（classes）、属性（properties）、实例（个体individuals）。

## 1. 本体模型OntModel

本体模型（OntModel）是对Jena RDF模型的扩展（继承自RDF模型），提供了处理本体数据的功能。

使用Jena处理本体首先就是要建立一个本体模型，之后就能够通过本体模型中所定义的方法操作模型，比如导入子模型、获取模型中本体的信息、操作本体属性以及将本体的表示输出到磁盘文件等等。

Jena通过model包中的ModelFactory创建本体模型，ModelFactory是Jena提供用来创建各种模型的类，在类中定义了具体实现模型的成员数据以及创建模型的二十多种方法。一个最简单的创建本体模型的语句如下：


```
OntModel ontModel = ModelFactory.createOntologyModel()
```

该语句不含参数，应用默认设置创建一个本体模型ontModel，也就是说：它使用OWL语言、基于内存，支持RDFS推理。可以通过创建时应用模型类别(OntModelSpec)参数创建不同的模型，以实现不同语言不同类型不同推理层次的本体操作。

例如，下面的语句创建了一个使用DAML语言内存本体模型。直观地讲，内存模型就是只在程序运行时存在的模型，它没有将数据写回磁盘文件或者数据库表。

```
OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.DAML_MEM)
```

我们所使用的本体是从OWL文件获得的，也就是说，是从磁盘读取的。读取的方法是调用Jena OntoModel提供的Read方法。例如

```
ontModel.read('Creature.owl');
```

`read`方法有很多重载，方法如下

```
read( String url );
read( Reader reader, String base );
read( InputStream reader, String base );
read( String url, String lang );
read( Reader reader, String base, String Lang );
read( InputStream reader, String base, String Lang );
```

## 2. 文档管理器(Document Manager)

本体文档管理器（OntDocumentManager）是用来帮助管理本体文档的类，它包含了导入本体文档创建本体模型、帮助缓存下载网络上的本体等功能。

每个本体模型都有一个相关联的文档管理器。在创建本体模型时，可以创建独立的文档管理器并作为参数传递给模型工厂（ModelFactory）。

文档管理器有非常多的配置选项，基本可以满足应用的需求。首先，每个文档管理器的参数都可以通过Java代码来设置（注：OntDocumentManager有五种重载的构造函数）。另外，文档管理器也可以在创建的时候从一个RDF格式的策略文件读取相应设定值。

一个示例，创建一个本体，并且获取它的文档管理器。

```
OntModel m = ModelFactory.createOntologyModel();

OntDocumentManager dm = m.getDocumentManager();
```

## 3. 接口OntClass

这个接口中定义了本体种与概念（也就是类Class）相关的操作。

OntoClass对概念之间的各种关系都有相应的定义方法，典型的有添加子类、添加约束、创建互斥概念、迭代返回某种类型的概念以及相关的逻辑判断等等。

例如通过ontModel中的listClasses()便可以返回模型中的所有概念组成的迭代器(Iterator),然后调用OntClass的各种方法具体进行操作。

## 4. 基本本体类型(OntResource)

所有本体API中用于表示本体的类继承自OntResource，这样就可以在OntResource中放置所有类公用的功能，并可以为一般的方法设置通用的返回值。Java接口OntResource扩展了Jena的RDF资源接口，所以任何可以接受资源或者RDFNode的方法都可以接受OntResource，并且也就可以接受任何其他本体值。

[完整示例](../../ontology/Basis.java)





