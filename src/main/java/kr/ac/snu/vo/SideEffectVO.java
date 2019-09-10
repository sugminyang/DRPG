package kr.ac.snu.vo;

public class SideEffectVO {
	String sideEffect;
	String frequency;
	String description;
	public String getSideEffect() {
		return sideEffect;
	}
	public void setSideEffect(String sideEffect) {
		this.sideEffect = sideEffect;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "SideEffectVO [sideEffect=" + sideEffect + ", frequency=" + frequency + ", description=" + description
				+ "]";
	}
	public SideEffectVO(String sideEffect, String frequency, String description) {
		super();
		this.sideEffect = sideEffect;
		this.frequency = frequency;
		this.description = description;
	}
	
	public SideEffectVO() {
		super();
	}
	
	
	
}
