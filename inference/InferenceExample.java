package inference;

import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;

/**
 * 获取InfModel的方法
 */

public class InferenceExample {

    public void getInfModelByDefaultReasonerAndData(Model rdfsExample, String NS) {
        // 获取InfModel，这个模型会根据内置的reasoner处理数据
        InfModel inf = ModelFactory.createRDFSModel(rdfsExample);

        // 通过一个例子来说明reasoner的作用
        // 查询a q ?，在数据集中并没有直接的a q ?三元组数据，但是由于
        // 使用了reasoner，p是q的子属性，因此会转化为a p ?，返回结果
        Resource a = inf.getResource(NS+"a");
        Property q = inf.getProperty(NS+"q");
        System.out.println("Statement: " + a.getProperty(q));
    }

    public void getInfModelByReasonerAndData(Model rdfsExample, String NS) {
        // 通过ReasonerRegistry对象的getRDFSReasoner来获取一个reasoner
        Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
        // 通过数据和reasoner，来获取InfModel
        InfModel inf = ModelFactory.createInfModel(reasoner, rdfsExample);
        // 之后，进行同样的查询，效果是一致的
        Resource a = inf.getResource(NS+"a");
        Property q = inf.getProperty(NS+"q");
        System.out.println("Statement: " + a.getProperty(q));
    }


    public static void main(String []args){
        RDFSData data = new RDFSData();
        InferenceExample infExample = new InferenceExample();
        System.out.println("通过数据直接获取InfModel，此时内置默认的reasoner");
        infExample.getInfModelByDefaultReasonerAndData(data.getModel(), data.NS);
        System.out.println("通过reasoner和数据来获取InfModel");
        infExample.getInfModelByReasonerAndData(data.getModel(), data.NS);
    }

}
