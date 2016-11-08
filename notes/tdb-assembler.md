# TDB Assembler

`Assembler`是Jena中一个普遍应用的机制，用来描述建立的数据对象（模型或者数据集）。

## 1. 构建数据集

我们可以通过一个`assembler`文件来构建一个数据集（文件后缀为.ttl），可以在fuseki的项目中找到，在`run/configuration`中。文件还是以`Turtle`的语法来编写，我们以官网上的示例来讲解每一部分的含义。



```
@prefix tdb:     <http://jena.hpl.hp.com/2008/tdb#> .
@prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#> .
@prefix ja:      <http://jena.hpl.hp.com/2005/11/Assembler#> .

[] ja:loadClass "org.apache.jena.tdb.TDB" .
tdb:DatasetTDB  rdfs:subClassOf  ja:RDFDataset .
tdb:GraphTDB    rdfs:subClassOf  ja:Model      .

<#dataset> rdf:type  tdb:DatasetTDB ;
    tdb:location "DB" ;
    .
```


1. 声明接下来文件中要使用的前缀：`@prefix`
2. 描绘TDB的情况，其中`[] ja:loadClass "org.apache.jena.tdb.TDB"`会加载TDB，并且初始化TDB。
3. 最后是一个TDB数据的描述，首先是一个tbd的数据集，然后还有它的数据集的存储位置。（文件存储）。

## 2. 合并默认图(Union Default Graph)

在`assembler`文件中，我们可以设置默认图的是数据集中命名图的合并，即非原来的默认图，只需要添加`tdb:unionDefaultGraph`。

```
<#dataset> rdf:type         tdb:DatasetTDB ;
    tdb:location "DB" ;
    tdb:unionDefaultGraph true ;
    .
```

## 3. 选择特定的rdf图

一个TDB数据集中一般有多个图，很有可能我们只使用到其他的一个。一般来说，如果我们只使用到TDB数据集的一个图，那么这个图就是`默认图`（会与命名图隔离开来）。

我们也可以指定一个命名图

```
<#graphNamed> rdf:type tdb:GraphTDB ;
    tdb:dataset <#dataset> .
    tdb:graphName <http://example/graph1> ;
    .
```

## 4. 多图合并为数据集

我们可以根据多个图数据来创建一个数据集（注意，此时的查询可能效率会很低）

```
@prefix tdb:     <http://jena.hpl.hp.com/2008/tdb#> .
@prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#> .
@prefix ja:      <http://jena.hpl.hp.com/2005/11/Assembler#> .

[] ja:loadClass "org.apache.jena.tdb.TDB" .
tdb:DatasetTDB  rdfs:subClassOf  ja:RDFDataset .
tdb:GraphTDB    rdfs:subClassOf  ja:Model .

# A dataset of one TDB-backed graph as the default graph and 
# an in-memory graph as a named graph.
<#dataset> rdf:type      ja:RDFDataset ;
     ja:defaultGraph <#graph> ;
     ja:namedGraph
        [ ja:graphName      <http://example.org/name1> ;
          ja:graph          <#graph2> ] ;
     .

<#graph> rdf:type tdb:GraphTDB ;
    tdb:location "DB" ;
    .

<#graph2> rdf:type ja:MemoryModel ;
     ja:content [ja:externalContent <file:Data/books.n3> ] ;
     .
```




