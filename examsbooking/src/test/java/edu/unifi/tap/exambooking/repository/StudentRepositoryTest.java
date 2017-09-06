package edu.unifi.tap.exambooking.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)//Se presente usa DB reale invece di H2 in memoria
public class StudentRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired 
	private StudentRepository studentRepository;
	
	private Student expectedStudent;
	private Exam expectedExam;
	
	@Before
	public void setUp(){
		expectedStudent = new Student();
		expectedStudent.setStudentId(null);
		expectedStudent.setFirstName("firstName");
		expectedStudent.setFirstName("lastName");
		expectedStudent.setIdNumber("1000000");
		expectedStudent.setEmail("firstName.lastName@email.it");
		
		expectedExam = new Exam();
		expectedExam.setExamId(null);
		expectedExam.setExamCode("CSZ");
		expectedExam.setExamName("Codici e sicurezza");
		expectedExam.setExamDate(new Date());
		expectedExam.setExamClassRoom("Aual 101");
	}
	
	@Test
	public void testFindStudentByIdNumberAndExam(){
		entityManager.persist(expectedExam);
		
		Student actualStudent = new Student(null,"firstName","lastName","firstName.lastName@email.it","1000000");
		actualStudent.setExam(expectedExam);
		
		entityManager.persist(actualStudent);
	    entityManager.flush();
	 
	    Student found = studentRepository.findStudentByIdNumberAndExam(actualStudent.getIdNumber(), expectedExam.getExamId());
	 
	    assertThat(found.getFirstName()).isEqualTo(actualStudent.getFirstName());
	    assertThat(found.getLastName()).isEqualTo(actualStudent.getLastName());
	}
}


//@ContextConfiguration(classes = {PersistenceContext.class})
//@Test
//public void add() {
//    TodoDTO dto = TodoTestUtil.createDTO(null, TodoTestUtil.DESCRIPTION, TodoTestUtil.TITLE);
//
//    service.add(dto);
//
//    ArgumentCaptor<Todo> toDoArgument = ArgumentCaptor.forClass(Todo.class);
//    verify(repositoryMock, times(1)).save(toDoArgument.capture());
//    verifyNoMoreInteractions(repositoryMock);
//
//    Todo model = toDoArgument.getValue();
//
//    assertNull(model.getId());
//    assertEquals(dto.getDescription(), model.getDescription());
//    assertEquals(dto.getTitle(), model.getTitle());
//}
//@Test
//public void findById() throws TodoNotFoundException {
//    Todo model = TodoTestUtil.createModel(TodoTestUtil.ID, TodoTestUtil.DESCRIPTION, TodoTestUtil.TITLE);
//    when(repositoryMock.findOne(TodoTestUtil.ID)).thenReturn(model);
//
//    Todo actual = service.findById(TodoTestUtil.ID);
//
//    verify(repositoryMock, times(1)).findOne(TodoTestUtil.ID);
//    verifyNoMoreInteractions(repositoryMock);
//
//    assertEquals(model, actual);
//}
//
//@Test(expected = TodoNotFoundException.class)
//public void findByIdWhenToDoIsNotFound() throws TodoNotFoundException {
//    when(repositoryMock.findOne(TodoTestUtil.ID)).thenReturn(null);
//
//    service.findById(TodoTestUtil.ID);
//
//    verify(repositoryMock, times(1)).findOne(TodoTestUtil.ID);
//    verifyNoMoreInteractions(repositoryMock);
//}
//@Test
//public void findAll() {
//    List<Todo> models = new ArrayList<Todo>();
//    when(repositoryMock.findAll()).thenReturn(models);
//
//    List<Todo> actual = service.findAll();
//
//    verify(repositoryMock, times(1)).findAll();
//    verifyNoMoreInteractions(repositoryMock);
//
//    assertEquals(models, actual);
//}