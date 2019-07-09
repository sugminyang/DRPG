package kr.ac.snu.vo;

public class DiseaseGeneVO {
	String disease;
	String gene;
	
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getGene() {
		return gene;
	}
	public void setGene(String gene) {
		this.gene = gene;
	}
	@Override
	public String toString() {
		return "DiseaseGeneVO [disease=" + disease + ", gene=" + gene + "]";
	}
	
	
}
