# Reasoner

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
    
![完整示例](../inference/ReasonerExample.java)

## 2. Reasoner参数设置

## 3. 各种Reasoner详解

只关注OWL Reasoner和自定义Reasoner

### 3.1 OWL Reasoner

![完整示例](../inference/OWLReasonerExample.java)

### 3.2 自定义Reasoner
