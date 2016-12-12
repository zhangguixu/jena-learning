package inference;


import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.rulesys.RDFSRuleReasonerFactory;

/**
 * 获取reasoner，配置reasoner的方法示例
 */

public class ReasonerExample {

    public void getReasonerByReasonerRegistry(Model rdfsExample, String NS) {
        // 通过ReasonerRegistry对象的getRDFSReasoner来获取一个reasoner
        Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
        // 通过数据和reasoner，来获取
        InfModel inf = ModelFactory.createInfModel(reasoner, rdfsExample);
        // 之后，进行推理查询
        Resource a = inf.getResource(NS+"a");
        Property q = inf.getProperty(NS+"q");
        System.out.println("Statement: " + a.getProperty(q));
    }

    public void getReasonerByReasonerFactory(Model rdfsExample, String NS) {
        // 通过ReasonerFactory来获取reasoner
        Reasoner reasoner = RDFSRuleReasonerFactory.theInstance().create(null);
        // 通过数据和reasoner，获取InfModel
        InfModel inf = ModelFactory.createInfModel(reasoner, rdfsExample);

        // 进行推理查询
        Resource a = inf.getResource(NS+"a");
        Property q = inf.getProperty(NS+"q");
        System.out.println("Statement: " + a.getProperty(q));
    }

    public static void main(String []args) {
        RDFSData data = new RDFSData();
        ReasonerExample example = new ReasonerExample();
        System.out.println("通过ResonerRegistry的方法来获取reasoner");
        example.getReasonerByReasonerRegistry(data.getModel(), data.NS);
        System.out.println("通过ResonerFactory的方法来获取reasoner");
        example.getReasonerByReasonerFactory(data.getModel(), data.NS);
    }
}
