package rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

public class RDFBasis {

    private String personURI = "http://somewhere/JohnSmith";
    private String fullName = "John Smith";
    private String givenName = "John";
    private String familyName = "Smith";
    private Model model;

    public Model getModel(){
        return this.model;
    }

    public RDFBasis(){
        this.model = ModelFactory.createDefaultModel();

        Resource johnSmith = model.createResource(personURI);
        johnSmith.addProperty(VCARD.FN, fullName)
                .addProperty(VCARD.N, model.createResource()
                        .addProperty(VCARD.Given, givenName)
                        .addProperty(VCARD.Family, familyName));

    }

    public static void main(String[] args) {

        Model model = new RDFBasis().getModel();
        model.write(System.out);
    }
}
