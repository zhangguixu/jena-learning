# 语义技术与Jena框架

## Jena简介

Jena是一个免费的、开源的Java平台上语义网开发框架。最初是由惠普实验室(HPL)的BrianMcBride开发，后来Jena称为Apache开源项目之一。

![jena](./images/jena.png)

结合整体架构图和Jena的官方文档，可以看到Jena框架共分为几大模块：

* RDF API（RDF模型操作相关）
* Ontology API（OWL本体模型操作相关）
* ARQ（SPARQL标准的实现）
* Inference API（本体推理的实现）
* TDB（RDF数据的存储数据库）

此外Jena还提供了非常实用的命令行工具（例如SPARQL查询、批量导入RDF数据等）和一个基于Jena框架构建的Web应用程序Fuseki。

*学习Jena框架，一定要结合对于的语义技术的知识，这样才能更加快速地上手。*

## Jena框架的各个模块

1. RDF API

    * [RDF简介](./semantic/rdf.md)
    * [RDF API使用示例](./jena/rdf.md)

2. Ontology API

    * [owl](./notes/semantic/owl.md)
    * [Protégé](./notes/tools/protege.md)
    * [Ontology API](./notes/jena/ontology.md)
    
3. Inference API

    * [inference](./notes/inference.md)
    * [reasoner](./notes/reasoner.md)

4. [ARQ](./notes/arq.md)
5. [Fuseki](./notes/fuseki.md)
6. [SPARQL](./notes/sparql.md)

    * [w3c sparql query 2013文档翻译](./notes/sparql/)

7. [TDB](./notes/tdb.md)

    * [TDB Assembler](./notes/tdb-assember.md)
    * [TDB Dynamic Datasets](./notes/tdb-dynamic-dataset.md)

## Jena的整体web流程框架

![jena-complete](./images/jena-webflow.jpg)


## 代码示例

建议使用[Intellij IDEA](http://www.jetbrains.com/idea/#chooseYourEdition)，安装社区版即可。

1. 需要下载[jena](http://jena.apache.org/download/index.cgi)，解压之后，在项目中引入jena的包（在解压目录下的lib，全部引入）
2. 需要Java 8，

示例：

1. [RDF API](./rdf)
2. [Ontology API](./ontology)
3. [Inference API](./inference)
4. [ARQ](./arq)
5. [TDB](./tdb)

## 学习资料汇总

1. [Jena官网](http://jena.apache.org/index.html)
2. [sparql](https://www.w3.org/TR/sparql11-query/)
3. [如何在ecplise中使用Jena](http://www.iandickinson.me.uk/articles/jena-eclipse-helloworld/)
4. [Jena资料整理博客](http://www.itdadao.com/tags/jena-0.html)
5. [Jena API文档](http://jena.apache.org/documentation/javadoc/jena/)
6. [OWL](https://www.w3.org/TR/2004/REC-owl-features-20040210/)
7. [owl本体语言学习笔记](http://blog.sina.com.cn/s/blog_6a7447840100utms.html)
8. [Jena的Ontology学习](http://blog.csdn.net/zhang6560329/article/details/27095197)
9. [Protégé中文教程](http://wenku.baidu.com/link?url=5Wb66TcG8jSBImBTIxriCUp6KaYd6sLZ20SS4emUgjyVs14GegIVKmGVlM7CSs56p0eQ6vpHnlph3eGgUsKHmsma8GREPc-iPR9cRDwHmLW)
10. [Protégé构建本体教程](http://wenku.baidu.com/link?url=TcDAyKe0DzP38i6sGG70s8P4lCvaQ5RptO1iuX1n1ljPtIluBloeu4NfJVi1sFQVBeJ0O1d2I4U9_9RDa3n5jtlcB5eSVuFMioQvnEgConG)
11. [rdf数据模型下载](http://semanticweb.org/wiki/Main_Page.html)