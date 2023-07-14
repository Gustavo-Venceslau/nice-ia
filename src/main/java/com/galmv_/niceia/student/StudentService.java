package com.galmv_.niceia.student;

import com.galmv_.niceia.comment.CommentRepository;
import com.galmv_.niceia.post.PostRepository;
import com.galmv_.niceia.reaction.ReactionRepository;
import com.galmv_.niceia.student.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ReactionRepository reactionRepository;

    public Set<Student> findAll(){
        Set<Student> students = new HashSet<>(repository.findAll());

        if(students.isEmpty()){
            throw new UserNotFoundException("users not founded");
        }

        return students;
    }

    public Student findById(UUID id){
        Optional<Student> optionalStudent = this.repository.findById(id);

        if(optionalStudent.isEmpty()){
            throw new UserNotFoundException("User not founded!");
        }

        return optionalStudent.get();
    }

    public void update(UUID id, StudentDTO data){
        Student studentExists = findById(id);

        if(studentExists == null){
            throw new UserNotFoundException("User not exists!");
        }

        updateStudentData(studentExists, data);

        repository.save(studentExists);
    }

    private void updateStudentData(Student studentToUpdate, StudentDTO data) {
        studentToUpdate.setFirstName(data.firstName());
        studentToUpdate.setLastName(data.lastName());
        studentToUpdate.setEmail(data.email());
        studentToUpdate.setPassword(data.password());
    }

    @Transactional
    public void delete(UUID id){
        Student student = findById(id);

        if(student == null){
            throw new UserNotFoundException("User not founded");
        }

        this.reactionRepository.deleteAllByStudent(student);
        this.commentRepository.deleteAllByStudent(student);
        this.postRepository.deleteAllByStudent(student);
        this.repository.delete(student);
    }
}
