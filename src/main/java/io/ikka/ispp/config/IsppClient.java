package io.ikka.ispp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.transport.http.HttpsUrlConnectionMessageSender;
import ru.axetta.ecafe.processor.web.partner.integra.soap.GetSummaryByGuardMobile;
import ru.axetta.ecafe.processor.web.partner.integra.soap.GetSummaryByGuardMobileResponse;
import ru.axetta.ecafe.processor.web.partner.integra.soap.ObjectFactory;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBElement;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Slf4j
public class IsppClient extends WebServiceGatewaySupport {

    public GetSummaryByGuardMobileResponse getSummaryByGuardMobile(GetSummaryByGuardMobile getSummaryByGuardMobile) {
        log.info("Requesting location for " + getSummaryByGuardMobile);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<GetSummaryByGuardMobile> getSummaryByGuardMobile1 = objectFactory.createGetSummaryByGuardMobile(getSummaryByGuardMobile);

        WebServiceTemplate webServiceTemplate = getWebServiceTemplate();

        HttpsUrlConnectionMessageSender sender = new HttpsUrlConnectionMessageSender();
        sender.setHostnameVerifier((s, sslSession) -> true);
        sender.setTrustManagers(new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }});
        webServiceTemplate.setMessageSender(sender);

        JAXBElement<GetSummaryByGuardMobileResponse> response = (JAXBElement<GetSummaryByGuardMobileResponse>) webServiceTemplate
                .marshalSendAndReceive(getSummaryByGuardMobile1, new SoapActionCallback(""));

        return response.getValue();
    }

}
