package se331.project.projectTwoCompo.config;
//TEST COMMIT

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import se331.project.projectTwoCompo.entity.CommentHistory;
import se331.project.projectTwoCompo.entity.CommentMessage;
import se331.project.projectTwoCompo.entity.Student;
import se331.project.projectTwoCompo.entity.Teacher;
import se331.project.projectTwoCompo.repository.CommentHistoryRepository;
import se331.project.projectTwoCompo.repository.CommentMessageRepository;
import se331.project.projectTwoCompo.repository.StudentRepository;
import se331.project.projectTwoCompo.repository.TeacherRepository;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final StudentRepository studentRepository;
    final TeacherRepository teacherRepository;
    final CommentMessageRepository commentMessageRepository;
    final CommentHistoryRepository commentHistoryRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("Init started.");

        Teacher t1, t2;
        t1 = teacherRepository.save(Teacher.builder()
            .firstname("Kanye")
            .surname("West")
            .academicPosition("Something")
            .department("Somewhere")
            .build()
        );
        t2 = teacherRepository.save(Teacher.builder()
            .firstname("Travis")
            .surname("Scott")
            .academicPosition("Something")
            .department("Somewhere")
            .build()
        );

        Student tempSt;
        tempSt = studentRepository.save(Student.builder()
            .studentId("644115001")
            .firstname("Ariana")
            .surname("Grande")
            .department("Software Engineering")
            .build()
        );
        tempSt.setAdvisor(t1);
        t1.getAdvisee().add(tempSt);
        CommentHistory his1 = commentHistoryRepository.save(CommentHistory.builder()
            .adviseeId(tempSt.getId())
            .advisorId(t1.getId())
            .build()
        );
        CommentHistory his2 = commentHistoryRepository.save(CommentHistory.builder()
            .adviseeId(tempSt.getId())
            .advisorId(t2.getId())
            .build()
        );
        CommentMessage msg1 = commentMessageRepository.save(CommentMessage.builder()
            .message("hello")
            .sentFromAdvisor(false)
            .timeSent("now")
            .build()
        );
        msg1.setFrom(his1);
        his1.getHistory().add(msg1);
        CommentMessage msg2 = commentMessageRepository.save(CommentMessage.builder()
            .message("no")
            .sentFromAdvisor(true)
            .timeSent("when")
            .build()
        );
        msg2.setFrom(his1);
        his1.getHistory().add(msg2);
        CommentMessage msg3 = commentMessageRepository.save(CommentMessage.builder()
            .message("why")
            .sentFromAdvisor(false)
            .timeSent("idk")
            .build()
        );
        msg3.setFrom(his1);
        his1.getHistory().add(msg3);
        
        tempSt = studentRepository.save(Student.builder()
            .studentId("644115002")
            .firstname("Olivia")
            .surname("Rodrigo")
            .department("Software Engineering")
            .build()
        );
        tempSt.setAdvisor(t1);
        t1.getAdvisee().add(tempSt);
        tempSt = studentRepository.save(Student.builder()
            .studentId("644115032")
            .firstname("Kimberly")
            .surname("Kardashian")
            .department("Software Engineering")
            .build()
        );
        tempSt.setAdvisor(t2);
        t2.getAdvisee().add(tempSt);
        tempSt = studentRepository.save(Student.builder()
            .studentId("642115004")
            .firstname("Taylor")
            .surname("Swift")
            .department("Software Engineering")
            .build()
        );
        tempSt.setAdvisor(t2);
        t2.getAdvisee().add(tempSt);

        System.out.println("Init Finished.");
    }
    
}
