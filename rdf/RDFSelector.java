package rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

public class RDFSelector {

    static final String johnSmith = "http://somewhere/JohnSmith/";

    public static void main(String []args){

        Model model = new RDFRead().getModel();

        model.write(System.out);

        // 获取John Smith的资源
        Resource vcard = model.getResource(johnSmith);

        // 获取名字
        Resource name = (Resource) vcard.getRequiredProperty(VCARD.N).getObject();

        // 获取全名，会进行类型的转换
        String fullName = vcard.getRequiredProperty(VCARD.FN).getString();

        // 添加为john smith添加昵称资源
        vcard.addProperty(VCARD.NICKNAME, "Smithy")
                .addProperty(VCARD.NICKNAME, "Adman");




    }

}
