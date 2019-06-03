package kr.ac.snu.vo;

public class ResultVO {
	String pmid;
	String disease;
	String gene;
	String snp;
	
	public String getPmid() {
		return pmid;
	}
	public void setPmid(String pmid) {
		this.pmid = pmid;
	}
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
	public String getSnp() {
		return snp;
	}
	public void setSnp(String snp) {
		this.snp = snp;
	}
	
	@Override
	public String toString() {
		return "ResultVO [pmid=" + pmid + ", disease=" + disease + ", gene=" + gene + ", snp=" + snp + "]";
	}
	
	
}
