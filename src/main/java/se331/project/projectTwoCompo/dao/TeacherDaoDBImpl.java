package se331.project.projectTwoCompo.dao;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import se331.project.projectTwoCompo.entity.Teacher;
import se331.project.projectTwoCompo.repository.TeacherRepository;


@Repository
@RequiredArgsConstructor
@Profile("db")
public class TeacherDaoDBImpl implements TeacherDao{
    final TeacherRepository teacherRepository;
    @Override
    public Integer getTeacherSize() {
        return Math.toIntExact(teacherRepository.count());
    }

    @Override
    public Page<Teacher> getTeachers(Integer pageSize, Integer page) {
        return teacherRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Teacher getTeacher(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Teacher> findById(Long id){
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher save(Teacher Teacher) {
        return teacherRepository.save(Teacher);
    }

    @Override
    public Page<Teacher> getTeachers(String title, Pageable page) {
        return teacherRepository.findByAcademicPositionIgnoreCaseContainingOrFirstnameIgnoreCaseContainingOrSurnameIgnoreCaseContaining(title, title, title, page);
    }

}
