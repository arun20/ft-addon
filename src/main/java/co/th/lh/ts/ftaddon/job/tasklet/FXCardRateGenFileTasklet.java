package co.th.lh.ts.ftaddon.job.tasklet;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.th.lh.ts.ftaddon.config.AppProperties;
import co.th.lh.ts.ftaddon.domain.VFXCardRate;
import co.th.lh.ts.ftaddon.repository.FXCardRateRepository;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component("fxCardRateGenFileTasklet")
public class FXCardRateGenFileTasklet implements Tasklet {
	
    @Autowired
	private FXCardRateRepository repository;
    
    @Autowired
	private AppProperties appProperties;

	@Override
	public RepeatStatus execute(StepContribution step, ChunkContext context) throws Exception {
		List<VFXCardRate> items = repository.findAllByOrderByCurrencySeqAsc();
	    generatePdfFile(items);
		return RepeatStatus.FINISHED;
	}
    
	
	private void generatePdfFile(List<VFXCardRate> items) {
		final String fileName = appProperties.getFxCardRateFilePath().replace("{yyyyMMdd}", DateFormatUtils.format(new Date(), "yyyyMMdd"));
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(items);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("reportDate", DateFormatUtils.format(new Date(), "dd/MM/yyyy HH:mm"));
		int round = repository.getCountStepJob("fxCardRateGenFileStep", DateFormatUtils.format(new Date(), "dd/MM/yyyy"));
		params.put("round", round + 1);
		params.put("decreaseUSD1", appProperties.getFxCardRateUSDDecrease1());
		params.put("decreaseUSD2", appProperties.getFxCardRateUSDDecrease2());
		try {
			String templateFileName = appProperties.getFxCardRateTemplatePath();
			String exportFileName = JasperFillManager.fillReportToFile(templateFileName, params, beanColDataSource);
			JasperExportManager.exportReportToPdfFile(exportFileName, fileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
