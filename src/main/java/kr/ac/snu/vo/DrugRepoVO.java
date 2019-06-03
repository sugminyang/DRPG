package kr.ac.snu.vo;

public class DrugRepoVO {
	String gene;
	String disease;
	String interaction_types;
	String drug_name;
	
	public String getGene() {
		return gene;
	}
	public void setGene(String gene) {
		this.gene = gene;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}

	
	public String getInteraction_types() {
		return interaction_types;
	}
	public void setInteraction_types(String interaction_types) {
		this.interaction_types = interaction_types;
	}
	public String getDrug_name() {
		return drug_name;
	}
	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
	}
	@Override
	public String toString() {
		return "DrugRepoVO [gene=" + gene + ", disease=" + disease + ", interaction_types=" + interaction_types
				+ ", drug_name=" + drug_name + "]";
	}
	
	
}
