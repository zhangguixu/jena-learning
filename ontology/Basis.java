package ontology;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;

import java.util.Iterator;

public class Basis {

    public static  void  main (String[]args) {

        // 创建使用OWL语言的内存模型
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

        // 加载模型
        ontModel.read("data/Creatrue.ttl");

        // 定义一个类作为模型中的Animal类的等价类
        OntClass cls = ontModel.createClass("DongWuClass");
        cls.addComment("the EquivalentClass of Animal..", "EN");

        // 通过完整的URI取得模型中的Animal类
        OntClass oc = ontModel.getOntClass("http://www.semanticweb.org/zhang/ontologies/2016/10/untitled-ontology-6#Animal");
        oc.addEquivalentClass(cls);

        // 迭代显示模型中的类，
        Iterator it = ontModel.listClasses();
        while(it.hasNext()){
            OntClass c = (OntClass)it.next();
            // 如果不是匿名类
            if(!c.isAnon()){
                System.out.print("Class");

                // 获取类的URI，并输出
                // 在输出时，省略命名空间前缀
                System.out.println(c.getModel().getGraph().getPrefixMapping().shortForm(c.getURI()));
            }

            // 处理Animal类
            if(c.getLocalName().equals("Animal")) {
                System.out.println("URI@" + c.getURI());
                System.out.println("Animal's EquivalentClass is" + c.getEquivalentClass());

                // 输出等价类的注释
                System.out.println("[comments:" + c.getEquivalentClass().getComment("EN") + "]");
            }
        }


    }

}
