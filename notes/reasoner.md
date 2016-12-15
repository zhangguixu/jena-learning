# Reasoner

[官方文档](http://jena.apache.org/documentation/inference/index.html#owl)

## 1. Reasoner实例的获取

两种方式获取Reasoner实例

1. 通过全局对象ReasonerRegistry的方法来获取一个Reasoner实例，例如以下获取一个RDFS类型的Reasoner

    ```
    Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
    ```
    
2. 通过ReasonerFactory的theInstance()方法获取一个Reasoner实例，其中的create()可以对reasoner进行参数设置

    ```
    Reasoner reasoner = RDFSReasonerFactory.theInstance().create(null);
    ```
    
[完整示例](../inference/ReasonerExample.java)

## 2. Reasoner参数设置

## 3. OWL Reasoner


[完整示例](../inference/OWLReasonerExample.java)

## 4. Rule Engine

Jena中包含了Rule Engine(规则引擎)，它是实现RDFS和OWL推理器的基础，当然也可以用于实现自定义的推理器。

1. 这个规则引擎有自己的一套语法规则和结构，在定义规则之后，我们可以通过`GenericRuleReasoner`对象来构建推理器，然后应用到我们的数据集中。

2. 规则引擎支持前向推理和后向推理以及两种混合推理。

### 4.1 规则的语法和结构

### 4.2 Forward Chaining Engine(前向链式引擎)

### 4.3 Backward Chaining Engine(后向链式引擎)

### 4.4 Hybrid rule engine

![jena-inf-figure2.png](../images/jena-inf-figure2.png)


### 4.5 示例

[完整示例](../inference/GenericRuleReasonerExample.java)


