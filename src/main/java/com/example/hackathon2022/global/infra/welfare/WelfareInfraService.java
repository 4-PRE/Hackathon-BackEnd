package com.example.hackathon2022.global.infra.welfare;

import com.example.hackathon2022.domain.job.entity.Job;
import com.example.hackathon2022.domain.job.entity.JobDetail;
import com.example.hackathon2022.domain.job.repository.JobDetailRepository;
import com.example.hackathon2022.domain.job.repository.JobRepository;
import com.example.hackathon2022.domain.job.type.Region;
import com.example.hackathon2022.domain.welfare.entity.Welfare;
import com.example.hackathon2022.domain.welfare.repository.WelfareRepository;
import com.example.hackathon2022.global.infra.job.JobApiProperties;
import com.example.hackathon2022.global.infra.job.JobDetailFeign;
import com.example.hackathon2022.global.infra.job.JobDetailResponse;
import com.example.hackathon2022.global.infra.job.JobListFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.transaction.Transactional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Component
public class WelfareInfraService {
    private final WelfareApiProperties welfareApiProperties;
    private final WelfareListFeign welfareListFeign;
    private final WelfareRepository welfareRepository;

    private String getValueOfNodeList(NodeList list, String keyName) {
        for(int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if(node.getNodeName().equals(keyName)) return node.getTextContent();
        }
        return null;
    }

    private List<Welfare> parseOf(Integer page) {
        List<Welfare> welfareList = new ArrayList<>();

        String response = welfareListFeign.execute(welfareApiProperties.getKey(), "L", 1, 500, "003", "006", "2ug8Dm9qNBfD32JLZGPN64f3EoTlkpD8kSOHWfXpyrY");
        log.info(response);

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(response)));
            doc.getDocumentElement().normalize();

            NodeList rawList = doc.getElementsByTagName("servList");
            for(int i = 0; i < rawList.getLength(); i++) {
                NodeList item = rawList.item(i).getChildNodes();

                Welfare welfare = Welfare.builder()
                        .keywords(getValueOfNodeList(item, "trgterIndvdlArray"))
                        .detailLink(getValueOfNodeList(item, "servDtlLink"))
                        .summary(getValueOfNodeList(item, "servDgst"))
                        .department(getValueOfNodeList(item, "jurMnofNm"))
                        .division(getValueOfNodeList(item, "jurOrgNm"))
                        .build();
                welfareList.add(welfare);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return welfareList;
    }

    @Transactional
    public void updateAll() {
        List<Welfare> welfareList = new ArrayList<>();
        for(int i = 1; i < 6; i++)
            welfareList.addAll(parseOf(i));

        welfareRepository.saveAll(welfareList);
    }
}
