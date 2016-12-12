package inference;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.vocabulary.RDFS;

public class RDFSData {
    private Model rdfsExample;
    public static final String NS = "urn:x-hp-jena:eg/";

    public RDFSData() {
        /**
         * 生成一个数据集，有p和q两个属性，
         *
         * 其中p是q的子属性（RDFS定义）
         *
         * 此外添加一个三元组数据： a p foo
         */
        this.rdfsExample = ModelFactory.createDefaultModel();
        Property p = rdfsExample.createProperty(NS, "p");
        Property q = rdfsExample.createProperty(NS, "q");
        rdfsExample.add(p, RDFS.subPropertyOf, q);
        rdfsExample.createResource(NS+"a").addProperty(p, "foo");
    }

    public Model getModel() {
        return this.rdfsExample;
    }

}
