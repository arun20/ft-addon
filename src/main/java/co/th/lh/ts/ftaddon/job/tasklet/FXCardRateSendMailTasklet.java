package co.th.lh.ts.ftaddon.job.tasklet;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import co.th.lh.ts.ftaddon.config.AppProperties;
import co.th.lh.ts.ftaddon.service.SendMailService;

@Component("fxCardRateSendMailTasklet")
public class FXCardRateSendMailTasklet implements Tasklet {

	@Autowired
	private SendMailService sendMailService;

	@Autowired
	private AppProperties appProperties;

	@Override
	public RepeatStatus execute(StepContribution step, ChunkContext context) throws Exception {

		String pathToAttachment = appProperties.getFxCardRateFilePath().replace("{yyyyMMdd}",
				DateFormatUtils.format(new Date(), "yyyyMMdd"));
		String subject = "[Treasury] FX Card Rate";
		String text = "";
		String from = appProperties.getFxCardRateMailFrom();
		String[] to = appProperties.getFxCardRateMailTo();
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		if (file.exists()) {
			text = "Dear User Treasury" + "\n \t Finacle System generate Report as attachement file.";
			sendMailService.sendMessageWithAttachment(from, to, subject, text, new String[] { pathToAttachment });

		} else {
			text = "FX Card Rate not found.";
			sendMailService.sendSimpleMessage(from, to, subject, text);
		}

		return RepeatStatus.FINISHED;
	}

}
