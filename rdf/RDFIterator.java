package rdf;

import org.apache.jena.rdf.model.*;

public class RDFIterator {

    public static void main(String []args){


        Model model = new RDFBasis().getModel();

        model.write(System.out);

        // 迭代器进行遍历
        StmtIterator iter = model.listStatements();

        while(iter.hasNext()){
            // 获取一个陈述，即三元组
            Statement stmt = iter.nextStatement();
            // 分别获取，主体、属性和客体
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            // 客体可以是一个资源，也可以是一个文本,
            // 因此返回一个RDFNode，它是Resource和Literal的超类
            RDFNode object = stmt.getObject();

            System.out.print(subject.toString());
            System.out.print(predicate.toString());
            if(object instanceof Resource){
                System.out.print(object.toString());
            } else {
                System.out.print("\"" + object.toString() + "\"");
            }
            System.out.println(".");
        }

    }
}
