package br.com.mol.molapi.services.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import br.com.mol.molapi.payloads.ReportPayload;
import br.com.mol.molapi.services.IReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportService implements IReportService {

	@Override
	public byte[] generateReport(ReportPayload payload) throws JRException {
		InputStream inputStream = this.getClass().getResourceAsStream(payload.getReport().getPath());
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("SUBREPORT_DIR", "reports/");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				new JsonDataSource(new ByteArrayInputStream(payload.getJson().getBytes())));
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
