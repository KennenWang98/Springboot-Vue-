package cn.net.sybt.springboot.mapperTest;

import cn.net.sybt.springboot.bean.TeaDocument;
import cn.net.sybt.springboot.mapper.TeaDocumentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TeaDocuTest {
    @Autowired
    TeaDocumentMapper teaDocumentMapper;

    @Test
    public void testGetTeaDocumentByClassId(){
        List<TeaDocument> documents = teaDocumentMapper.getTeaDocumentByClassId(1);
        documents.forEach(System.out::println);
    }

    @Test
    public void testGetAllTeaDocuments() {
        List<TeaDocument> documents = teaDocumentMapper.getAllTeaDocuments();
        documents.forEach(System.out::println);
    }

    @Test
    public void testDeleteTeaDocumentById() {
        System.out.println(teaDocumentMapper.deleteTeaDocumentById(4));
    }

    @Test
    public void testAddTeaDocument() {
        TeaDocument document = new TeaDocument();
        document.setDocuName("123");
        document.setClassId(3);
        System.out.println(teaDocumentMapper.addTeaDocument(document));
    }

    @Test
    public void testUpdateTeaDocument(){
        TeaDocument document = new TeaDocument();
        document.setDocuName("456");
        document.setClassId(6);
        document.setTeaDocuId(2);
        System.out.println(teaDocumentMapper.updateTeaDocument(document));
    }
}
