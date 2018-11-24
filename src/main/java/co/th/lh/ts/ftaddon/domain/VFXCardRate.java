package co.th.lh.ts.ftaddon.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "v_fx_card_rate")
public class VFXCardRate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String currencyId;
	@Id
	private int currencySeq;
	private String currencyDesc;
	private BigDecimal bnbRate;
	private BigDecimal bnsRate;
	private BigDecimal stbRate;
	private BigDecimal ttbRate;
	private BigDecimal ttsRate;
	
	public int getCurrencySeq() {
		return currencySeq;
	}
	public void setCurrencySeq(int currencySeq) {
		this.currencySeq = currencySeq;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public String getCurrencyDesc() {
		return currencyDesc;
	}
	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
	}
	public BigDecimal getBnbRate() {
		return bnbRate;
	}
	public void setBnbRate(BigDecimal bnbRate) {
		this.bnbRate = bnbRate;
	}
	public BigDecimal getBnsRate() {
		return bnsRate;
	}
	public void setBnsRate(BigDecimal bnsRate) {
		this.bnsRate = bnsRate;
	}
	public BigDecimal getStbRate() {
		return stbRate;
	}
	public void setStbRate(BigDecimal stbRate) {
		this.stbRate = stbRate;
	}
	public BigDecimal getTtbRate() {
		return ttbRate;
	}
	public void setTtbRate(BigDecimal ttbRate) {
		this.ttbRate = ttbRate;
	}
	public BigDecimal getTtsRate() {
		return ttsRate;
	}
	public void setTtsRate(BigDecimal ttsRate) {
		this.ttsRate = ttsRate;
	}
	
	
}
