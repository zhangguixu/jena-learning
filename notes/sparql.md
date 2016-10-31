# SPARQL

[中文学习文档](http://www.chinaw3c.org/REC-sparql11-overview-20130321-cn.html)
[搭建测试环境](http://jena.apache.org/tutorials/sparql_query1.html)

## 1. 概述

SPARQL 1.1 是一组W3C推荐标准，提供了对Web上或RDF存储（RDF store）中的RDF图内容（graph）进行查询和处理的语言和协议。它包含以下几个部分：

1. 查询语言：一个面向RDF的查询语言
2. 查询结果格式处理：SPARQL查询结果提供了多种格式，例如json，csv，xml等等。
3. 联邦查询：此规范定义了一个SPARQL 1.1查询语言扩展，用来在分布的多个SPARQL端点(endpints)上执行SPARQL查询。
4. Entailment Regimes(蕴含)：此规范定义了RDF Schema，OWL或RIF等蕴含规则下的SPARQL查询语义。
5. update（更新）：一个面向RDF graph的更新语言。
6. RDF协议：该协议定义了一种方法，执行任意的SPARQL查询，并将结果更新到一个SPARQL服务。
7. 服务描述：该规范定义了一个标准方法，用来发现SPARQL服务，并提供了描述SPARQL服务所需要的词汇表。
8. 图存储HTTP协议：相对于完整的SPARQL协议，该文档定义了一个最小子集，基于常用的HTTP协议的操作，管理RDF graph的内容。
9. 测试用例：这是一组测试，帮助理解规范，并检测一个系统是否符合SPARQL 1.1规范。

## 2. 搭建sparql测试环境

1. 下载解压包，[下载地址](http://jena.apache.org/)
2. 解压，添加环境变量：JENA_HOME : D:\apache-jena-3.1.0
3. 可以把sparql命令添加到系统变量PATH ： %JENA_HOME%\bat
4. 运行sparql --version检测是否安装成功

安装过程中的问题

出现了Unsupported major.minor version 51.0 
因为使用了过低版本的jdk1.6而出现的问题，换成jdk1.8后问题解决；

## 3. SPARQL 1.1 查询语言

