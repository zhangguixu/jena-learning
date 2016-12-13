package inference;

import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.vocabulary.ReasonerVocabulary;

public class GenericRuleReasonerExample {

    public static void main(String[]args) {
        // 注册一个命名空间
        String demoURI = "http://jena.hpl.hp.com/demo#";
        PrintUtil.registerPrefix("demo", demoURI);

        /**
         * 创建一个配置项，用于加载我们的规则，分为几步
         *
         * 1. 获取一个model，作用是创建Resource
         * 2. 通过model获取Resource类的示例，这个就是我们的配置项
         * 3. 配置项通过addProperty添加属性，设置rule的模式为hybrid
         * 4. 同样的方法，配置项，添加我们的规矩集
         */
        Model m = ModelFactory.createDefaultModel();
        Resource conf = m.createResource();
        conf.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
        conf.addProperty(ReasonerVocabulary.PROPruleSet, "data/demo.rules");

        // 生成一个GenericRuleReasoner的实例，并且传入配置项
        Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(conf);

        // 加载测试数据
        Model data = FileManager.get().loadModel("data/demoData.rdf");

        // 根据reasoner和data，生成infModel
        InfModel inf = ModelFactory.createInfModel(reasoner, data);

        /**
         *在规则集中，我们定义了两条规则：
         *
         * 1. (?A demo:p ?B), (?B demo:p ?C) -> (?A > demo:p ?C)
         * 2. (?Y demo:p ?X) -> (?X demo:p ?Y)
         *
         * 在数据集中，我们有数据
         * a demo:p b
         * c demo:p a
         * b demo:p d
         *
         * 那么根据规则2，我们就可以推理出
         * b demo:p a
         * a demo:p c
         * d demo:p b
         *
         * 根据规则1，我们可以推理出
         * (b demo:p a)(a demo:p c) -> (b demo:p c)
         * .....
         */
        // 我们来查询所有a demo:p 的情况，列出所有的三元组
        Property p = data.getProperty(demoURI, "p");
        Resource a = data.getResource(demoURI + "a");

        StmtIterator i = inf.listStatements(a, p, (RDFNode) null);
        while(i.hasNext()) {
            System.out.println(" - " + PrintUtil.print(i.nextStatement()));
        }

    }

}
