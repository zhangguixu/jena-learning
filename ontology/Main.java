package ontology;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;

public class Main {

    public static  void  main (String[]args) {

        // 创建使用OWL语言的内存模型
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

        // 加载模型
        ontModel.read("/data/Creatrue.owl");

        // 定义一个类作为模型中的Animal类的等价类
        OntClass cls = ontModel.createClass("DongWuClass");
        cls.addComment("the EquivalentClass of Animal..", "EN");

        // 通过完整的URI取得模型中的Animal类
        OntClass oc = ontModel.getOntClass("http://www.semanticweb.org/zhang/ontologies/2016/10/untitled-ontology-6#Animal");

        oc.addEquivalentClass(cls);



    }

}
