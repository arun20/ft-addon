package co.th.lh.ts.ftaddon.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
	@Value("${lh.logo.file.path}")
	private String lhLogoFilePath;
	
	@Value("${fx.cardrate.file.path}")
	private String fxCardRateFilePath;
	
	@Value("${fx.cardrate.template.path}")
	private String fxCardRateTemplatePath;
	
	@Value("${fx.cardrate.mail.from}")
	private String fxCardRateMailFrom;
	
	@Value("${fx.cardrate.mail.to}")
	private String[] fxCardRateMailTo;
	
	@Value("${fx.card.rate.USD.1.decrease}")
	private BigDecimal fxCardRateUSDDecrease1;
	
	@Value("${fx.card.rate.USD.2.decrease}")
	private BigDecimal fxCardRateUSDDecrease2;

	
	public String getLhLogoFilePath() {
		return lhLogoFilePath;
	}

	public String getFxCardRateFilePath() {
		return fxCardRateFilePath;
	}

	public String getFxCardRateTemplatePath() {
		return fxCardRateTemplatePath;
	}

	public String getFxCardRateMailFrom() {
		return fxCardRateMailFrom;
	}

	public String[] getFxCardRateMailTo() {
		return fxCardRateMailTo;
	}

	public BigDecimal getFxCardRateUSDDecrease1() {
		return fxCardRateUSDDecrease1;
	}

	public BigDecimal getFxCardRateUSDDecrease2() {
		return fxCardRateUSDDecrease2;
	}

}
