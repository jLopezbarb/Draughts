package es.urjccode.mastercloudapps.adcs.dobles.runWith;

public class SUT {

    private DOC doc;

	public SUT(DOC doc) {
        this.doc = doc;
	}

	public int exerciseSUT(boolean value) {
		return this.doc.exerciseDOC(value);
	}
    
}