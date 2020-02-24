package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.Document;
import cn.net.sybt.springboot.mapper.DocumentMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class DocumentTest {
    @Autowired
    DocumentMapper documentMapper;

    @Test
    public void testGetDocumentById(){
        Document document = documentMapper.getDocumentById(1);
        System.out.println(document);
    }

    @Test
    public void testGetAllDocuments(){
        List<Document> documents = documentMapper.getAllDocuments();
        documents.forEach(System.out::println);
    }

    @Test
    public void testAddDocument(){
        Document document = new Document();
        document.setDocuName("1");
        document.setDocuType("2");
        System.out.println(documentMapper.addDocument(document));
    }

    @Test
    public void testDeleteDocumentById(){
        System.out.println(documentMapper.deleteDocumentById(3));
    }

    @Test
    public void testUpdateDocument(){
        Document document = new Document();
        document.setDocuName("1");
        document.setDocuType("2");
        document.setDocuId(1);
        System.out.println(documentMapper.updateDocument(document));
    }
}
