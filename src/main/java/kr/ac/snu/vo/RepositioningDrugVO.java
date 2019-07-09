package kr.ac.snu.vo;

public class RepositioningDrugVO implements Cloneable{
	String diseaseName;
	String targetGene;
	String drugName;
	String phaseNum;
	String interactionType;
	String chemblID;
	String sources;
	
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getTargetGene() {
		return targetGene;
	}
	public void setTargetGene(String targetGene) {
		this.targetGene = targetGene;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getPhaseNum() {
		return phaseNum;
	}
	public void setPhaseNum(String phaseNum) {
		this.phaseNum = phaseNum;
	}
	public String getInteractionType() {
		return interactionType;
	}
	public void setInteractionType(String interactionType) {
		this.interactionType = interactionType;
	}
	public String getChemblID() {
		return chemblID;
	}
	public void setChemblID(String chemblID) {
		this.chemblID = chemblID;
	}
	public String getSources() {
		return sources;
	}
	public void setSources(String sources) {
		this.sources = sources;
	}
	
	@Override
	public String toString() {
		return "RepositioningDrugVO [diseaseName=" + diseaseName + ", targetGene=" + targetGene + ", drugName="
				+ drugName + ", phaseNum=" + phaseNum + ", interactionType=" + interactionType + ", chemblID="
				+ chemblID + ", sources=" + sources + "]";
	}
	

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
