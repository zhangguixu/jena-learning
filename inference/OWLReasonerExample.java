package inference;

import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.vocabulary.RDF;

import java.util.Iterator;

/**
 * owl reasoner的示例
 */

public class OWLReasonerExample {

    // 辅助函数，打印三元组
    public static void printStatements(Model m, Resource s, Property p, Resource o) {
        for(StmtIterator i = m.listStatements(s, p, o); i.hasNext(); ){
            Statement stmt = i.nextStatement();
            System.out.println("-" + PrintUtil.print(stmt));
        }
    }

    public static void main(String []args) {
        // 加载预订好的模式（定义computer和computer的子类以及拥有的属性）
        // 可以将其视为本体库
        Model schema = FileManager.get().loadModel("data/owlDemoSchema.owl");
        // 加载rdf数据
        Model data = FileManager.get().loadModel("data/owlDemoData.rdf");

        // 获取推理器
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();

        // 加入我们预先设置好的模式（本体库）
        reasoner = reasoner.bindSchema(schema);

        // 根据数据和reasoner，获取InfModel
        InfModel inf = ModelFactory.createInfModel(reasoner, data);

        // 进行推理查询

        /**
         * 示例1： SELECT * WHERE { ?nForce ?x ?y }
         *          查找nForce作为主体的所有三元组
         * 由于nForce是一种插件板（mother board），，插件板在schema中定义了
         * 若干种属性都会被打印出来。
         */
        System.out.println("示例1： 属性推断");
        Resource nForce = inf.getResource("urn:x-hp:eg/nForce");
        System.out.println("nForce *:");
        printStatements(inf, nForce, null, null);

        /**
         * 示例2：实例识别（instance recognition）。
         * whileBoxZx是一个Computer的实例，并且显示声明拥有gamebundle，
         * 还拥有nForce，因为nForce又有gamingGraphic组件
         * GamingComputer也是Computer的实例，并且拥有gamebundle和gamingGraphic
         * 因此，可以推断出whileBoxZx是GamingComputer的实例。
         */
        System.out.println("示例2： 实例识别");
        Resource gamingComputer = inf.getResource("urn:x-hp:eg/GamingComputer");
        Resource whiteBox = inf.getResource("urn:x-hp:eg/whiteBoxZX");
        if(inf.contains(whiteBox, RDF.type, gamingComputer)) {
            System.out.println("White box recognized as gaming computer");
        } else {
            System.out.println("Failed to recognize white box correctly");
        }

        /**
         * 示例3：检测逻辑一致性，
         * 在rdf数据中，bigName42既拥有插件板bigNameSpecialMB，有拥有nForce，
         * 违反了作为一个Computer只能拥有一个插件板的逻辑，
         */
        System.out.println("示例2： 逻辑一致性检测");
        ValidityReport validity = inf.validate();
        if(validity.isValid()) {
            System.out.println("ok");
        } else {
            System.out.println("Conflicts");
            for(Iterator i = validity.getReports(); i.hasNext(); ) {
                ValidityReport.Report report = (ValidityReport.Report)i.next();
                System.out.println(" - " + report);
            }
        }
    }
}
