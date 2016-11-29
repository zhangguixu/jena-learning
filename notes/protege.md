# Protégé

## 1. 概述

Protégé是一个开源智能系统本体编辑和知识获取框架，包括构建框架和本体（ontology）编辑器。

主页：http://protege.stanford.edu/
下载：http://download.csdn.net/download/loveshouwang/8513995

*官网上不去，只能用别人上传的资源*

OWL本体的组成部分：

* Individual(个体)

    指的是在域`domain`中我们感兴趣的对象，类中一个实例。Individuals必须明确声明彼此之间是否相同，不同名字可能指的是相同的Individuals。


* Properties(属性或关联)，有Object Properties和Data properties

    指的是Individuals上的二元关系，连接着两个individual。属性可以存在逆反关系(reverse)，或者被限定成单值(functinal),或者是可传递的(transitive),对称的(symmetric)。

* Classes

    可以看作是包含individuals的集合。Classes通常被组织成supperclass-subclass形式的层次结构。

大致对应在protégé中就是：

* Instances(实例)
* Slote
* Classes


## 2. 创建本体

以创建本体`myPizza`为例子。







