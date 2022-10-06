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

                Integer targetFlag = 0;
                String keyword = getValueOfNodeList(item, "trgterIndvdlArray");
                if(keyword != null) {
                    if(keyword.contains("저소득"))
                        targetFlag |= 0b001;
                    else if(keyword.contains("장애인"))
                        targetFlag |= 0b010;
                    else if(keyword.contains("보훈"))
                        targetFlag |= 0b100;
                }

                Integer benefitFlag = 0;
                String department = getValueOfNodeList(item, "jurMnofNm");
                if("교육부".equals(department))
                    benefitFlag |= 0b10000;
                else if("보건복지부".equals(department))
                    benefitFlag |= 0b01000;
                else if("문화체육관광부".equals(department))
                    benefitFlag |= 0b00100;
                else if("금융위원회".equals(department))
                    benefitFlag |= 0b00010;
                else
                    benefitFlag |= 0b00001;

                Welfare welfare = Welfare.builder()
                        .keywords(keyword)
                        .detailLink(getValueOfNodeList(item, "servDtlLink"))
                        .summary(getValueOfNodeList(item, "servDgst"))
                        .department(department)
                        .division(getValueOfNodeList(item, "jurOrgNm"))
                        .targetFlagForSearch(targetFlag)
                        .benefitFlagForSearch(benefitFlag)
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
