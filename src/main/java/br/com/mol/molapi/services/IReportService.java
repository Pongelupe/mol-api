package br.com.mol.molapi.services;

import br.com.mol.molapi.payloads.ReportPayload;
import net.sf.jasperreports.engine.JRException;

public interface IReportService {

	byte[] generateReport(ReportPayload payload) throws JRException;

}
